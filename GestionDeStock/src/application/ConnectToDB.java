package application;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
public class ConnectToDB {
	

//	etablir une connection a la db.
	public static Connection connectionDB() {
		
		
		String url = "jdbc:mysql://localhost:3306/gestioncommande";
		String user = "root";
		String password = "";
		Connection connection = null;
		


		
//		String url = "jdbc:mysql://localhost:3306/gestioncommande";
//		String user = "root";
//		String password = "";
//		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			e.printStackTrace();
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
		  e.printStackTrace();
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
			  e.printStackTrace();
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
              e.printStackTrace();
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
		SampleController.refreshfacture();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
public static ResultSet data(Connection connection,String table,String condition,String value){
	
	try {
		Statement statement;
		statement = connection.createStatement();
	 System.out.println(table);
	 System.out.println(condition);
	 System.out.println(value);
	
	
		return statement.executeQuery("select * from "+ table +" where " + condition + " = '"+ value + "' ;");
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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
		e.printStackTrace();
	}
}
public static ResultSet selecttous(Connection connection,String Table) {
	try {
		Statement statement;
		statement = connection.createStatement();
	
		return statement.executeQuery("select * from "+ Table );
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
}


	

	
	public static void delete(Connection connection, String table,String columnid,Integer ind) {
		try {
			Statement statement = connection.createStatement();
			
			String request = "DELETE FROM " + table +" WHERE " + columnid + " = "  +ind ;
			
			statement.executeUpdate(request);
			
			System.out.println("Suppresion reussite.");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
//	public static void main(String[] args) {
//		Connection connexion = ConnectToDB.connectionDB();
//		getData(connexion,"facture","montant");
//	}

static void insertcommande(Connection connection,Commande commande) {
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
		int numcom = result.getInt("numerocommande");
		for(int i =0;i<commande.list_produit.size();i++) {
			prepare = connection.prepareStatement("insert into avoir(numerocommande,numeroproduit,Quantite_prod) values (?,?,?);");
			PreparedStatement prepare2 = connection.prepareStatement("Update produits set quantite = ? where numeroproduit;");
			prepare2.setInt(1,commande.list_produit.get(i).numProduit);
			prepare2.setInt(1,commande.list_produit.get(i).QuantiteProduit-Integer.parseInt((commande.list_produit.get(i).getQuantitetext().getText())));
			prepare2.execute();
			prepare.setInt(1,numcom);
			prepare.setInt(2,commande.list_produit.get(i).numProduit);
			prepare.setInt(3,Integer.parseInt((commande.list_produit.get(i).getQuantitetext().getText())));
			prepare.execute();
			
		}
		
	    
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


}
