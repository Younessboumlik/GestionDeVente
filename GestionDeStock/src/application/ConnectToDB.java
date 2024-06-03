package application;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import classes.Client;
import classes.Commande;
import classes.Facture;
import classes.Livraison;
import classes.Produit;
import controller.SupModifFactureController;
import controller.SupModifLivraisonController;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
public class ConnectToDB {
	

//	etablir une connection a la db.
	public static Connection connectionDB() {
		
		
		String url = "jdbc:mysql://localhost:3306/gestioncommande";
		String user = "root";
		String password = "12345678";
		Connection connection = null;
		


		
//		String url = "jdbc:mysql://localhost:3306/gestioncommande";
//		String user = "root";
//		String password = "";
//		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
		}
		
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
		}
		
		System.out.println("Connextion reussite...");
		return connection;	
	
	}
	
//	prendre des donnes d'une collone d'un tableau preciser a partir d'une connection.
	
	public static ArrayList<String> getData(Connection connection,String table,String column) {
		
		try {
			Statement statement = connection.createStatement();
			
			String request = "SELECT * FROM " + table +" ;";
			
			
			ResultSet result = statement.executeQuery(request);
			
			ArrayList <String> results = new ArrayList<String>();
            
			while (result.next()) {
			    results.add(result.getString(column));
			}
			
			return results;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
		}
		return null;
	}
	public static void insertClientData(Connection connection,Client client) {
	  try {
		  String query = "insert into client(nom,prenom,adresse,telephone) values "
		  		+ "(?,?,?,?);";
		  PreparedStatement prepare = connection.prepareStatement(query);
		 
		  prepare.setString(1, client.getNom());
		  prepare.setString(2, client.getPrenom());
		  prepare.setString(3, client.getAdresse());
		  prepare.setInt(4, client.getTelephone());
		  prepare.execute();
		  
		  
	  }
	  catch (SQLException e){
		      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
          alert.showAndWait();
           e.printStackTrace();
                });
	              }
	              }
	
	public static void insertFactureData(Connection connection,Facture facture) {
		  try {
			  String query = "insert into Facture(datefacture,montant,numerocommande) values "
			  		+ "(?,?,?);";
			  PreparedStatement prepare = connection.prepareStatement(query);

			  prepare.setDate(1,Date.valueOf(facture.getDateFacture()));
			  prepare.setFloat(2,facture.getMontant());
			  prepare.setInt(3,(facture.getNumeroCommande()));
			  
			  prepare.execute();
			  
			  
		  }
		  catch (SQLException e){
			      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
		  }
}

	public static void insertLivraisonData(Connection connection,Livraison livraison) {
          try {
              String query = "insert into Livraison(datelivraison,numerocommande) values "
              		+ "(?,?);";
              PreparedStatement prepare = connection.prepareStatement(query);
              prepare.setDate(1,Date.valueOf(livraison.getDateLivraison()));
              prepare.setInt(2,livraison.getNumeroCommande());
              prepare.execute();
              
              
          }
          catch (SQLException e){
                  Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
          }
          }
public static void updatefacture(Connection connextion,Facture facture) {
	try {
		PreparedStatement prepare = connextion.prepareStatement(
				"Update facture set dateFacture = ? ,montant = ? ,numeroCommande = ? where numeroFacture = ? ");
		prepare.setInt(4, facture.getNumeroFacture());
		prepare.setDate(1, Date.valueOf(facture.getDateFacture()));
		prepare.setFloat(2, facture.getMontant());
		prepare.setInt(3, facture.getNumeroCommande());

		prepare.execute();
		SupModifFactureController.refreshfacture();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
	}
}

public static void updateLivraison(Connection connexion,Livraison livraison) {
	try {
		PreparedStatement prepare = connexion.prepareStatement(
				"Update livraison set datelivraison = ? ,numeroCommande = ? where numeroLivraison = ? ");

		prepare.setDate(1, Date.valueOf(livraison.getDateLivraison()));
		prepare.setInt(2, livraison.getNumeroCommande());
		prepare.setInt(3, livraison.getNumeroLivraison());

		prepare.execute();
		SupModifLivraisonController.refreshLivraison();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
	}
}
	
public static ResultSet data(Connection connection,String table,String condition,String value){
	
	try {
		Statement statement;
		statement = connection.createStatement();

	
	
		return statement.executeQuery("select * from "+ table +" where " + condition + " = '"+ value + "' ;");
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
		return null;
	}
}
public static void modifieuser(Connection connection,Client client) {
	

	try {

		 PreparedStatement prepare = connection.prepareStatement("Update client set nom = ? ,prenom = ? ,adresse = ? ,telephone = ? where numeroclient = ?");
		  prepare.setString(1, client.getNom());
		  prepare.setString(2, client.getPrenom());
		  prepare.setString(3, client.getAdresse());
		  prepare.setInt(4, client.getTelephone());
		  prepare.setInt(5, client.getId_client());
		  prepare.execute();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
       alert.showAndWait();
       e.printStackTrace();
         });
	     }
          }
public static ResultSet selecttous(Connection connection,String Table) {
	try {
		Statement statement;
		statement = connection.createStatement();
	
		return statement.executeQuery("select * from "+ Table );
	}
	catch (SQLException e) {
		    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
		return null;
	}
	
}


	

	
	public static void delete(Connection connection, String table,String columnid,Integer id) {
		try {
			Statement statement = connection.createStatement();
			
			String request = "DELETE FROM " + table +" WHERE " + columnid + " = "  +id ;
			
			statement.executeUpdate(request);
			
			System.out.println("Suppresion reussite.");
		}
		catch(SQLException e) {
			    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
      alert.showAndWait();
     e.printStackTrace();
       });
		}
     	}
//	public static void main(String[] args) {
//		Connection connexion = ConnectToDB.connectionDB();
//		getData(connexion,"facture","montant");
//	}

public static void insertcommande(Connection connection,Commande commande) {
	try {
		PreparedStatement prepare = connection.prepareStatement("insert into commande(datecommande,numeroclient) values (?,?);");
		prepare.setDate(1,Date.valueOf(commande.getDatecomande()));
		prepare.setInt(2,commande.getNum_client());
		prepare.execute();
//		prepare = connection.prepareStatement("select numerocommande from commande DESC limit 1");
//		ResultSet numerocommand
		Statement statement;
		statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select numerocommande from commande order by numerocommande DESC limit 1");
		result.next();
		String message = "";
		
		int numcom = result.getInt("numerocommande");
		message.concat("numero de commande effectue : " + Integer.toString(numcom));
		for(int i =0;i<commande.list_produit.size();i++) {
			prepare = connection.prepareStatement("insert into avoir(numerocommande,numeroproduit,Quantite_prod) values (?,?,?);");
			PreparedStatement prepare2 = connection.prepareStatement("Update produits set quantite = ? where numeroproduit = ?;");
			
			prepare2.setInt(2,commande.list_produit.get(i).numProduit);
			prepare2.setInt(1,commande.list_produit.get(i).QuantiteProduit-Integer.parseInt((commande.list_produit.get(i).getQuantitetext().getText())));
			prepare2.execute();
			prepare.setInt(1,numcom);
			prepare.setInt(2,commande.list_produit.get(i).numProduit);
			prepare.setInt(3,Integer.parseInt((commande.list_produit.get(i).getQuantitetext().getText())));
			prepare.execute();
			message= message + commande.list_produit.get(i).nomProduit + " : " + Integer.toString(commande.list_produit.get(i).QuantiteProduit-Integer.parseInt((commande.list_produit.get(i).getQuantitetext().getText())))+ "\n";
			
		}
	    System.out.println(message);
		SendSms.smssend(message);
		
	    
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
	}
}
public static ResultSet getproducts(Connection connection,int numcommmande){
	Statement statement;
	try {
		statement = connection.createStatement();
		return statement.executeQuery("select * from avoir,produits where avoir.numeroproduit = produits.numeroproduit and avoir.numerocommande = "+ Integer.toString(numcommmande)+ ";");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
		return null;
	}
}
public static void supprimercommande(Connection connection , Commande commande) {
	
	try {
		PreparedStatement prepare = connection.prepareStatement("DELETE FROM avoir WHERE numerocommande = ?");
		prepare.setInt(1, commande.numerocommande);
		prepare.execute();
		prepare = connection.prepareStatement("delete from commande where numerocommande = ?");
		prepare.setInt(1, commande.numerocommande);
		prepare.execute();
	}  catch (SQLException e) {
		// TODO Auto-generated catch block
		    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
	}
	
}
public static void  modifiercomande(Connection connection ,Commande commande) {
	try {
		PreparedStatement prepare = connection.prepareStatement("Update commande set numeroclient = ?,datecommande = ? where numerocommande = ?;");
		System.out.println(commande.num_client);
		prepare.setInt(1, commande.num_client);
		prepare.setDate(2, Date.valueOf(commande.getDatecomande()));
		prepare.setInt(3, commande.numerocommande);
		prepare.execute();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
	}
}
public static void modifierproduit(Connection connection ,Produit produit,int numCommande) {
	try {
		PreparedStatement prepare = connection.prepareStatement("Update avoir set Quantite_prod = ? where numerocommande = ? and numeroproduit = ? ;");
		
		prepare.setInt(1, produit.getQuantitechoisie());
		prepare.setInt(2, numCommande);
		prepare.setInt(3, produit.getNumProduit());
		prepare.execute();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
	}
}
public static void supprimerrproduit(Connection connection , int num_produit ,int num_commande) {
	try {
		PreparedStatement prepare = connection.prepareStatement("Delete from avoir  where numerocommande = ? and numeroproduit = ? ;");
		
		prepare.setInt(1,num_commande);
		prepare.setInt(2, num_produit);
		
		prepare.execute();
		System.out.println("llllllllll");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
	}
}
public static void modifierproduit(Connection connection,Produit produit) {
	try {

		 PreparedStatement prepare = connection.prepareStatement("Update produits set nomproduit = ? ,quantite = ? ,prix = ? where numeroproduit = ?");
		  prepare.setString(1, produit.getNomProduit());
		  prepare.setInt(2, produit.getQuantiteProduit());
		  prepare.setDouble(3, produit.getPrix());
		  prepare.setInt(4, produit.getNumProduit());
		  prepare.execute();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
	}
}
public static void AJouterProduit(Connection connection ,Produit produit) {
	try {
		  String query = "insert into produits(numeroproduit,nomproduit,quantite,prix) values (?,?,?,?);";
		  PreparedStatement prepare = connection.prepareStatement(query);
		 
		  prepare.setInt(1, produit.getNumProduit());
		  prepare.setString(2, produit.getNomProduit());
		  prepare.setInt(3, produit.getQuantiteProduit());
		  prepare.setDouble(4, produit.getPrix());
		  prepare.execute();
		  
		  
	  }
	  catch (SQLException e){
		  
		    Platform.runLater(() -> {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Erreur");
		        alert.setHeaderText("Une erreur s'est produite.");
		        alert.setContentText(e.getMessage());
		alert.showAndWait();
		e.printStackTrace();
		    });
}
}


public static void exportToCsvliv(TableView<Livraison> tableau){
	
	    FileChooser fileChooser = new FileChooser();

	    // Set extension filter for text files
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
	    fileChooser.getExtensionFilters().add(extFilter);

	    // Show save file dialog
	    File file = fileChooser.showSaveDialog(null);
	    
//	    pour prendre les donnes du tableau de livraison.\

	    ObservableList<Livraison> items = tableau.getItems();
	    


	    if (file != null) {
	        try {
	            FileWriter writer = new FileWriter(file);
	    	    writer.write("NumeroLivraison,DateLivraison,NumeroCommande\n");
	            // TODO: replace this with your actual data
	    		for (int i = 0; i < items.size(); i++) {
	    			writer.write(Integer.toString(items.get(i).getNumeroLivraison()));
	    			writer.write(",");
	    			writer.write(items.get(i).getDateLivraison().toString());
	    			writer.write(",");
	    			writer.write(Integer.toString(items.get(i).getNumeroCommande()));
	    			writer.write("\n");
	    		}
	            writer.close();
	        } catch (IOException ex) {
			    Platform.runLater(() -> {
			        Alert alert = new Alert(Alert.AlertType.ERROR);
			        alert.setTitle("Erreur");
			        alert.setHeaderText("Une erreur s'est produite.");
			        alert.setContentText(ex.getMessage());
			alert.showAndWait();
			ex.printStackTrace();
			    });
	        }
	    }
}
	
public static void exportToPdfliv(TableView<Livraison> tableau) {
    FileChooser fileChooser = new FileChooser();

    // Set extension filter for PDF files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show save file dialog
    File file = fileChooser.showSaveDialog(null);

    ObservableList<Livraison> items = tableau.getItems();

    if (file != null) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            PdfPTable pdfTable = new PdfPTable(3);
            pdfTable.addCell("NumeroLivraison");
            pdfTable.addCell("DateLivraison");
            pdfTable.addCell("NumeroCommande");

            for (Livraison item : items) {
                pdfTable.addCell(Integer.toString(item.getNumeroLivraison()));
                pdfTable.addCell(item.getDateLivraison().toString());
                pdfTable.addCell(Integer.toString(item.getNumeroCommande()));
            }

            document.add(pdfTable);
            document.close();

        } catch (DocumentException | IOException ex) {
		    Platform.runLater(() -> {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Erreur");
		        alert.setHeaderText("Une erreur s'est produite.");
		        alert.setContentText(ex.getMessage());
		alert.showAndWait();
		ex.printStackTrace();
		    });
        }
    }
}

public static void exportToCsvfact(TableView<Facture> tableau){

	FileChooser fileChooser = new FileChooser();

	// Set extension filter for text files
	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
	fileChooser.getExtensionFilters().add(extFilter);

	// Show save file dialog
	File file = fileChooser.showSaveDialog(null);
	
	//	pour prendre les donnes du tableau de facture.\
	ObservableList<Facture> items = tableau.getItems();
	
    if (file != null) {
        try {
            FileWriter writer = new FileWriter(file);
    	    writer.write("NumeroLivraison,DateLivraison,Montant,NumeroCommande\n");
            // TODO: replace this with your actual data
    		for (int i = 0; i < items.size(); i++) {
    			writer.write(Integer.toString(items.get(i).getNumeroFacture()));
    			writer.write(",");
    			writer.write(items.get(i).getDateFacture().toString());
    			writer.write(",");
    			writer.write(Float.toString(items.get(i).getMontant()));
    			writer.write(",");
    			writer.write(Integer.toString(items.get(i).getNumeroCommande()));
    			writer.write("\n");
    		}
            writer.close();
        } catch (IOException ex) {
		    Platform.runLater(() -> {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Erreur");
		        alert.setHeaderText("Une erreur s'est produite.");
		        alert.setContentText(ex.getMessage());
		alert.showAndWait();
		ex.printStackTrace();
		    });
        }
    }
}
public static void exportToPdffac(TableView<Facture> tableau) {
    FileChooser fileChooser = new FileChooser();

    // Set extension filter for PDF files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show save file dialog
    File file = fileChooser.showSaveDialog(null);

    ObservableList<Facture> items = tableau.getItems();

    if (file != null) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
//			Definition du tableau a 4 cases
            PdfPTable pdfTable = new PdfPTable(4);
//            les champs du tableau.
            pdfTable.addCell("NumeroFacture");
            pdfTable.addCell("DateFacture");
            pdfTable.addCell("Montant");
            pdfTable.addCell("NumeroCommande");

            for (Facture item : items) {
                pdfTable.addCell(Integer.toString(item.getNumeroCommande()));
                pdfTable.addCell(item.getDateFacture().toString());
                pdfTable.addCell(Float.toString(item.getMontant()));
                pdfTable.addCell(Integer.toString(item.getNumeroCommande()));
            }

            document.add(pdfTable);
            document.close();

        } catch (DocumentException | IOException ex) {
		    Platform.runLater(() -> {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Erreur");
		        alert.setHeaderText("Une erreur s'est produite.");
		        alert.setContentText(ex.getMessage());
		alert.showAndWait();
		ex.printStackTrace();
		    });
        }
    }
}

public static void exportToCsvprod(TableView<Produit> tableau){

    FileChooser fileChooser = new FileChooser();

    // Set extension filter for text files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show save file dialog
    File file = fileChooser.showSaveDialog(null);
    
    //	pour prendre les donnes du tableau de produit.\
//    ObservableList<Produit> items = tableau.getItems();
    
    if (file != null) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("NumeroProduit,NomProduit,Quantite,Prix\n");

}
        
        catch (IOException ex) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Une erreur s'est produite.");
                alert.setContentText(ex.getMessage());
        alert.showAndWait();
        ex.printStackTrace();
            });
        }
    }
}

public static void exportToPdfprod(TableView<Produit> tableau) {
    FileChooser fileChooser = new FileChooser();

    // Set extension filter for PDF files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show save file dialog
    File file = fileChooser.showSaveDialog(null);

    ObservableList<Produit> items = tableau.getItems();

    if (file != null) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            PdfPTable pdfTable = new PdfPTable(4);
            pdfTable.addCell("NumeroProduit");
            pdfTable.addCell("NomProduit");
            pdfTable.addCell("Quantite");
            pdfTable.addCell("Prix");

            for (Produit item : items) {
                pdfTable.addCell(Integer.toString(item.getNumProduit()));
                pdfTable.addCell(item.getNomProduit());
                pdfTable.addCell(Integer.toString(item.getQuantiteProduit()));
                pdfTable.addCell(Double.toString(item.getPrix()));
            }

            document.add(pdfTable);
            document.close();

        } catch (DocumentException | IOException ex) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Une erreur s'est produite.");
                alert.setContentText(ex.getMessage());
        alert.showAndWait();
        ex.printStackTrace();
            });
        }
    }
    
    
}

public static void exportToCsvclient(TableView<Client> tableau){

    FileChooser fileChooser = new FileChooser();

    // Set extension filter for text files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show save file dialog
    File file = fileChooser.showSaveDialog(null);
    
    //	pour prendre les donnes du tableau de client.\
    ObservableList<Client> items = tableau.getItems();
    
    if (file != null) {
        try {
            FileWriter writer = new FileWriter(file);
    	    writer.write("NumeroClient,Nom,Prenom,Adresse,Telephone\n");
    	    
        		for (int i = 0; i < items.size(); i++) {
        			writer.write(Integer.toString(items.get(i).getId_client()));
        			writer.write(",");
        			writer.write(items.get(i).getNom());
        			writer.write(",");
        			writer.write(items.get(i).getPrenom());
        			writer.write(",");
        			writer.write(items.get(i).getAdresse());
        			writer.write(",");
        			writer.write(Integer.toString(items.get(i).getTelephone()));
        			writer.write("\n");
        		}
            writer.close();
        		}
        catch (IOException ex) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Une erreur s'est produite.");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                ex.printStackTrace();
                });
            
        }
    }
}

public static void exportToPdfclient(TableView<Client> tableau) {
	FileChooser fileChooser = new FileChooser();

	// Set extension filter for PDF files
	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
	fileChooser.getExtensionFilters().add(extFilter);

	// Show save file dialog
	File file = fileChooser.showSaveDialog(null);

	ObservableList<Client> items = tableau.getItems();

	if (file != null) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			PdfPTable pdfTable = new PdfPTable(5);
			pdfTable.addCell("NumeroClient");
			pdfTable.addCell("Nom");
			pdfTable.addCell("Prenom");
			pdfTable.addCell("Adresse");
			pdfTable.addCell("Telephone");

			for (Client item : items) {
				pdfTable.addCell(Integer.toString(item.getId_client()));
				pdfTable.addCell(item.getNom());
				pdfTable.addCell(item.getPrenom());
				pdfTable.addCell(item.getAdresse());
				pdfTable.addCell(Integer.toString(item.getTelephone()));
			}

			document.add(pdfTable);
			document.close();

		} catch (DocumentException | IOException ex) {
			Platform.runLater(() -> {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText("Une erreur s'est produite.");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
				ex.printStackTrace();
			});
		}
	}
}

public static void exportToCsvcommande(TableView<Commande> tableau){

    FileChooser fileChooser = new FileChooser();

    // Set extension filter for text files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show save file dialog
    File file = fileChooser.showSaveDialog(null);
    
    //	pour prendre les donnes du tableau de commande.\
    ObservableList<Commande> items = tableau.getItems();
    
    if (file != null) {
        try {
            FileWriter writer = new FileWriter(file);
    	    writer.write("NumeroCommande,DateCommande,NumeroClient\n");
    	    
        		for (int i = 0; i < items.size(); i++) {
        			writer.write(Integer.toString(items.get(i).getNumerocommande()));
        			writer.write(",");
        			writer.write(items.get(i).getDatecomande().toString());
        			writer.write(",");
        			writer.write(Integer.toString(items.get(i).getNum_client()));
        			writer.write("\n");
        		}
            writer.close();
        		}
        catch (IOException ex) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Une erreur s'est produite.");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                ex.printStackTrace();
                });
            
        }
    }
}

public static void exportToPdfcommande(TableView<Commande> tableau) {
	FileChooser fileChooser = new FileChooser();

	// Set extension filter for PDF files
	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
	fileChooser.getExtensionFilters().add(extFilter);

	// Show save file dialog
	File file = fileChooser.showSaveDialog(null);

	ObservableList<Commande> items = tableau.getItems();

	if (file != null) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			PdfPTable pdfTable = new PdfPTable(3);
			pdfTable.addCell("NumeroCommande");
			pdfTable.addCell("DateCommande");
			pdfTable.addCell("NumeroClient");

			for (Commande item : items) {
				pdfTable.addCell(Integer.toString(item.getNumerocommande()));
				pdfTable.addCell(item.getDatecomande().toString());
				pdfTable.addCell(Integer.toString(item.getNum_client()));
			}

			document.add(pdfTable);
			document.close();

		} catch (DocumentException | IOException ex) {
			Platform.runLater(() -> {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText("Une erreur s'est produite.");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
				ex.printStackTrace();
			});
		}
	}
}
public static ResultSet selectnbrproduitgrpbynom(Connection connection) {
	try {
		Statement statement;
		statement = connection.createStatement();
	
		return statement.executeQuery("select count(Quantite_prod),nomproduit from avoir,produits where avoir.numeroproduit = produits.numeroproduit group by avoir.numeroproduit;");

		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}
public static ArrayList<Integer> getlivrasonended(Connection connection){
	Statement statement;
	try {
		statement = connection.createStatement();
		try {
			ArrayList<Integer> livrresult = new ArrayList<>();
			ResultSet alllivr = statement.executeQuery("select count(numerolivraison) from livraison;");
			alllivr.next();
			livrresult.add(alllivr.getInt("count(numerolivraison)"));
			
			ResultSet alllivrBeforNow = statement.executeQuery("select count(numerolivraison) from livraison where datelivraison <= NOW();");
			alllivrBeforNow.next();
			System.out.println(alllivrBeforNow.getInt("count(numerolivraison)"));
			livrresult.add(alllivrBeforNow.getInt("count(numerolivraison)"));
	
			
			
			return  livrresult;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	catch (Exception e) {
		e.printStackTrace();
		return null;
	}

	

	
}
public static ResultSet nbrCommandInMounth(Connection connection) {
	
	try {
		Statement statement = connection.createStatement();
		return statement.executeQuery("select MONTH(commande.datecommande) as month,sum(avoir.Quantite_prod*produits.prix) as TOTAL from commande,avoir,produits where commande.numerocommande = avoir.numerocommande and avoir.numeroproduit = produits.numeroproduit and YEAR(commande.datecommande) = YEAR(NOW()) group by MONTH(commande.datecommande);"
			);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}
public static ResultSet clientfidel(Connection connection) {
	
	try {
		Statement statement = connection.createStatement();
		return statement.executeQuery("select nom,prenom,sum(avoir.Quantite_prod*produits.prix) as  TotalBudget from client,commande,avoir,produits where client.numeroclient = commande.numeroclient and commande.numerocommande = avoir.numerocommande and avoir.numeroproduit = produits.numeroproduit group by client.numeroclient order by TotalBudget limit 2;");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}
public static ResultSet produitemptystock(Connection connection) {
	
	try {
		Statement statement = connection.createStatement();
		return statement.executeQuery("select quantite,nomproduit from produits where quantite <= 25 order by quantite desc limit 3");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
}
public static void labelsstats(Connection connection, Label cli,Label prds,Label comm,Label livr,Label facture){
	try {
		Statement statement = connection.createStatement();
		 ResultSet cliSet = statement.executeQuery("select count(numeroclient) as nbrclient from client");
		 cliSet.next();
		 cli.setText(Integer.toString(cliSet.getInt("nbrclient")));
		 ResultSet prdSet = statement.executeQuery("select count(numeroproduit) as nbrprd from produits");
		 prdSet.next();
		 prds.setText(Integer.toString(prdSet.getInt("nbrprd")));
		 ResultSet cmdSet = statement.executeQuery("select count(numerocommande) as nbrcmd from commande");
		 cmdSet.next();
		 comm.setText(Integer.toString(cmdSet.getInt("nbrcmd")));
		 ResultSet livSet = statement.executeQuery("select count(numerolivraison) as nbrliv from livraison");
		 livSet.next();
		 livr.setText(Integer.toString(livSet.getInt("nbrliv")));
		 ResultSet fctSet = statement.executeQuery("select count(numerofacture) as nbrfct from facture");
		 fctSet.next();
		 facture.setText(Integer.toString(fctSet.getInt("nbrfct")));
		 
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}



