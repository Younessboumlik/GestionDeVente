package controller;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ConnectToDB;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ringprogress.RingProgressIndicator;
import ringprogress.SousClassRingProgressIndicator;

public class MainController implements Initializable{
	@FXML
	private Label livrpourclabel;

	@FXML
	private Label nbrclientlabel;

	@FXML
	private Label nbrcommandeabel;

	@FXML
	private Label nbrfactlabel;

	@FXML
	private Label nbrlivrlabel;

	@FXML
	private Label nbrprdlabel;

	@FXML
	private PieChart pie;

	@FXML
	private VBox leftVbox;

	@FXML
	private Label affeclivrnbr;

	@FXML
	private Label nonaffeclivrnbr;

	@FXML
	private BarChart<String, Double> barchart;

	@FXML
	private Label budgetclone;

	@FXML
	private Label budgetcltwo;

	@FXML
	private Label nameclientone;

	@FXML
	private Label nameclienttwo;

	@FXML
	private HBox Hboxfooter;

	@FXML
	private AnchorPane choices;

	@FXML
	private AnchorPane choices1;

	@FXML
	private AnchorPane choices2;

	@FXML
	private AnchorPane choices3;

	@FXML
	private AnchorPane choices4;

	@FXML
	private Label choix1_1;

	@FXML
	private Label choix1_2;

	@FXML
	private Label choix2_1;

	@FXML
	private Label choix2_2;

	@FXML
	private Label choix3_1;

	@FXML
	private Label choix3_2;

	@FXML
	private Label choix4_1;

	@FXML
	private Label choix4_2;

	@FXML
	private Label choix5_1;

	@FXML
	private Label choix5_2;

	@FXML
	private Button close;

	@FXML
	private Button menuicon;

	@FXML
	private Label label1;

	@FXML
	private Label label2;

	@FXML
	private Label label3;

	@FXML
	private Label label4;

	@FXML
	private Label label5;

	@FXML
	private AnchorPane sidebar;
	
	
	@FXML
	private Button livcom;
	 @FXML
	    private AnchorPane movmentwindow;
	 private double xOffset = 0;
	    private double yOffset = 0;
	    
	

    
    @FXML
    void comdecli(MouseEvent event) {
    	Stage primaryStage = new Stage();
		Parent root;
    	try {
			root = FXMLLoader.load(getClass().getResource("/fxml/CommandeDunClient.fxml"));
			Scene Scene = new Scene(root);
			Scene.getStylesheets().add(getClass().getResource("/css/tablestyle.css").toExternalForm());
////			Scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());
			primaryStage.setScene(Scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void facdecom(MouseEvent event) {
    	Stage primaryStage = new Stage();
		Parent root;
    	try {
			root = FXMLLoader.load(getClass().getResource("/fxml/FactureForCommande.fxml"));
			Scene Scene = new Scene(root);
			Scene.getStylesheets().add(getClass().getResource("/css/tablestyle.css").toExternalForm());
////			Scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());
			primaryStage.setScene(Scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void livdecom(MouseEvent event) {
    	Stage primaryStage = new Stage();
		Parent root;
    	try {
			root = FXMLLoader.load(getClass().getResource("/fxml/LivraisonToCommande.fxml"));
			Scene Scene = new Scene(root);
			Scene.getStylesheets().add(getClass().getResource("/css/tablestyle.css").toExternalForm());
////			Scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());
			primaryStage.setScene(Scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void proddecom(MouseEvent event) {
    	Stage primaryStage = new Stage();
		Parent root;
    	try {
			root = FXMLLoader.load(getClass().getResource("/fxml/produitDuneCommande.fxml"));
			Scene Scene = new Scene(root);
			Scene.getStylesheets().add(getClass().getResource("/css/tablestyle.css").toExternalForm());
////			Scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());
			primaryStage.setScene(Scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void supmodifClient(MouseEvent event) {
       	Stage primaryStage = new Stage();
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/fxml/Clientmodifsup.fxml"));
			Scene Scene = new Scene(root);
    		Scene.getStylesheets().add(getClass().getResource("/css/tablestyle.css").toExternalForm());
//			Scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());
			primaryStage.setScene(Scene);
			primaryStage.show();
		} catch (IOException e) {
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

    @FXML
    void supmodifCommande(MouseEvent event) {
    	Stage primaryStage = new Stage();
		try {
			Parent root;

			root = FXMLLoader.load(getClass().getResource("../fxml/modifsuppcommande.fxml"));
			
			Scene Scene = new Scene(root);
			Scene.getStylesheets().add(getClass().getResource("/css/tablestyle.css").toExternalForm());
//			Scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());
			primaryStage.setScene(Scene);
			primaryStage.show();
		} catch (IOException e) {
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

    @FXML
    void supmodifFacture(MouseEvent event) {
    	Stage primaryStage = new Stage();
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/fxml/SupModifFacture.fxml"));
			Scene Scene = new Scene(root);
////			Scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());
			primaryStage.setScene(Scene);
			primaryStage.show();
		} catch (IOException e) {
		}
		
		// primaryStage.setHeight(400);
		// primaryStage.setWidth(400);
		// primaryStage.setResizable(false);
		// primaryStage.setFullScreen(true);
		// primaryStage.show();
	}


    @FXML
    void supmodifLivraison(MouseEvent event) {
    	Stage primaryStage = new Stage();
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/fxml/SupModifLivraison.fxml"));
			Scene Scene = new Scene(root);
			Scene.getStylesheets().add(getClass().getResource("/css/tablestyle.css").toExternalForm());
//			Scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());
			primaryStage.setScene(Scene);
			primaryStage.show();
		} catch (IOException e) {
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

    @FXML
    void supmodifProduit(MouseEvent event) {
    	Stage primaryStage = new Stage();
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/fxml/produitsupmodif.fxml"));
			Scene Scene = new Scene(root);
    		Scene.getStylesheets().add(getClass().getResource("/css/tablestyle.css").toExternalForm());
			primaryStage.setScene(Scene);
			primaryStage.show();
		} catch (IOException e) {
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
    
    
    @FXML
    void closingWindow(MouseEvent event) {
    	Platform.exit();
    }


    
    @FXML
    void showMenu(MouseEvent event) {
    	sidebar.translateXProperty().set(300);
    }
    
//    cette methode permet de cacher ou afficher les choix de l'utilisateur dans le cas de client
    @FXML
    void show(MouseEvent event) {
    	
//    	ceci veut dire que si le choix 1_1 est visible, alors on le cache
    	if(choix1_1.isVisible()) {
			choix1_1.setVisible(false);
			choix1_2.setVisible(false);
			choices.setMaxHeight(0);
			choices.setMinHeight(0);
			choices.setPrefHeight(0);
		}
//    	dans le cas contraire, on affiche le choix 1_1....
    	else {
        choices.setMaxHeight(70);
//        choices.setMinWidth(700);
        choices.setMinHeight(70);
        choices.setPrefHeight(70);
        choix1_1.setVisible(true);
        choix1_2.setVisible(true);
//        choix1_1.setMouseTransparent(true);
    	}
    }
    
    
	//    cette methode permet de cacher ou afficher les choix de l'utilisateur dans le cas de produit
@FXML
void showProduit(MouseEvent event) {
	
	//    	ceci veut dire que si le choix 1_1 est visible, alors on le cache
	        if (choix2_1.isVisible()) {
				choix2_1.setVisible(false);
				choix2_2.setVisible(false);
				choices1.setMaxHeight(0);
				choices1.setMinHeight(0);
				choices1.setPrefHeight(0);
	        }
	        //    	dans le cas contraire, on affiche le choix 1_1....
	        else {
	System.out.println("showing choices");
    choices.setMaxHeight(70);
//    choices.setMinWidth(700);
    choices1.setMinHeight(70);
    choices1.setPrefHeight(70);
    choix2_1.setVisible(true);
    choix2_2.setVisible(true);

	        }
}
    
    //    cette methode permet de cacher ou afficher les choix de l'utilisateur dans le cas de commande
    @FXML
    void showCommande(MouseEvent event) {
    	
//    	ceci veut dire que si le choix 1_1 est visible, alors on le cache
    	if(choix3_1.isVisible()) {
			choix3_1.setVisible(false);
			choix3_2.setVisible(false);
			choices2.setMaxHeight(0);
			choices2.setMinHeight(0);
			choices2.setPrefHeight(0);
		}
//    	dans le cas contraire, on affiche le choix 1_1....
    	else {

    	
        choices2.setMaxHeight(70);
//        choices.setMinWidth(700);
        choices2.setMinHeight(70);
        choices2.setPrefHeight(70);
        choix3_1.setVisible(true);
        choix3_2.setVisible(true);
        
    	}
    }

    //    cette methode permet de cacher ou afficher les choix de l'utilisateur dans le cas de facture
    @FXML
    void showFacture(MouseEvent event) {
    	
    	//    	ceci veut dire que si le choix 1_1 est visible, alors on le cache
    	if(choix4_1.isVisible()) {
    		            choix4_1.setVisible(false);
    		            choix4_2.setVisible(false);
    		            choices3.setMaxHeight(0);
    		            choices3.setMinHeight(0);
    		            choices3.setPrefHeight(0);
    		            
    	}
    	//    	dans le cas contraire, on affiche le choix 1_1....
    	else {

    	System.out.println("showing choices");
        choices3.setMaxHeight(70);
//        choices.setMinWidth(700);
        choices3.setMinHeight(70);
        choices3.setPrefHeight(70);
        choix4_1.setVisible(true);
        choix4_2.setVisible(true);
    	}
    }
    
    
    
    //    cette methode permet de cacher ou afficher les choix de l'utilisateur dans le cas de livraison
    @FXML
    void showLivraison(MouseEvent event) {
    	
    	//    	ceci veut dire que si le choix 1_1 est visible, alors on le cache
		if (choix5_1.isVisible()) {
			choix5_1.setVisible(false);
			choix5_2.setVisible(false);
			choices4.setMaxHeight(0);
			choices4.setMinHeight(0);
			choices4.setPrefHeight(0);

		}
		//    	dans le cas contraire, on affiche le choix 1_1....
		else {
    	System.out.println("showing choices");
        choices4.setMaxHeight(70);
//        choices.setMinWidth(700);
        choices4.setMinHeight(70);
        choices4.setPrefHeight(70);
        choix5_1.setVisible(true);
        choix5_2.setVisible(true);
		}
    }


    @FXML
    void ajouterClient(MouseEvent event) {
    	try {
    		
    	Stage primaryStage = new Stage();
    	Parent root;
		root = FXMLLoader.load(getClass().getResource("/fxml/clientAjoue.fxml"));
		Scene Scene = new Scene(root);
		Scene.getStylesheets().add(getClass().getResource("/css/tablestyle.css").toExternalForm());
//		Scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());
		primaryStage.setScene(Scene);
		primaryStage.show();
	} catch (IOException e) {
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
    

    @FXML
    void ajouterCommande(MouseEvent event) {
		try {
	    	Stage primaryStage = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/fxml/AjoueCommand.fxml"));
			Scene Scene = new Scene(root);
    		Scene.getStylesheets().add(getClass().getResource("/css/tablestyle.css").toExternalForm());
////			Scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());
			primaryStage.setScene(Scene);
			primaryStage.show();
		} catch (IOException e) {
			// primaryStage.setHeight(400);
			// primaryStage.setWidth(400);
			// primaryStage.setResizable(false);
			// primaryStage.setFullScreen(true);
			// primaryStage.show();
		}
		

	}

    @FXML
    void ajouterFacture(MouseEvent event) {
		try {
	    	Stage primaryStage = new Stage();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/fxml/AjouterFacture.fxml"));
			Scene scene = new Scene(root,663,560);
//			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
    		scene.getStylesheets().add(getClass().getResource("/css/tablestyle.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) { 
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

    @FXML
    void ajouterLivraison(MouseEvent event) {
		try {
	    	Stage primaryStage = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/fxml/AjouterLivraison.fxml"));
			Scene Scene = new Scene(root);
    		Scene.getStylesheets().add(getClass().getResource("/css/tablestyle.css").toExternalForm());
//			Scene.getStylesheets(j).add(getClass().getResource("/fxml/application.css").toExternalForm());
			primaryStage.setScene(Scene);
			primaryStage.show();
		} catch (IOException e) {
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

    @FXML
    void ajouterProduit(MouseEvent event) {
		try {
	    	Stage primaryStage = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/fxml/ajouterproduit.fxml"));
			Scene Scene = new Scene(root);
    		Scene.getStylesheets().add(getClass().getResource("/css/tablestyle.css").toExternalForm());
////			Scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());
			primaryStage.setScene(Scene);
			primaryStage.show();
		} catch (IOException e) {
		}
    }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("sssssssss");
		Connection connection = ConnectToDB.connectionDB();
		// TODO Auto-generated method stub
		ResultSet result = ConnectToDB.selectnbrproduitgrpbynom(connection);
		ObservableList<PieChart.Data> listPie = FXCollections.observableArrayList();
		try {
			while(result.next()) {
				try {
					listPie.add(new PieChart.Data(result.getString("nomproduit"),result.getInt("count(Quantite_prod)")));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			pie.setData(listPie);
			pie.setLabelsVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RingProgressIndicator prog = new RingProgressIndicator();
	    prog.setMaxWidth(10);
	    prog.makeIndeterminate();
	    ArrayList<Integer> nbrlivrrlist = ConnectToDB.getlivrasonended(connection);
	    
	    System.out.println(((((double)nbrlivrrlist.get(1))/nbrlivrrlist.get(0))*100));
//	    prog.setProgress();
	    System.out.println("sssssssss");
	    nonaffeclivrnbr.setText(Integer.toString(nbrlivrrlist.get(0)- nbrlivrrlist.get(1)));
	    affeclivrnbr.setText(Integer.toString(nbrlivrrlist.get(1)));
	    leftVbox.getChildren().add(prog);
	    threadForRingProgress threadprogress = new threadForRingProgress(prog,(int)(((double)nbrlivrrlist.get(1)/nbrlivrrlist.get(0))*100));
	    ResultSet res = ConnectToDB.nbrCommandInMounth(connection);
	    XYChart.Series serie = new XYChart.Series();
	    serie.setName("2024");
	    try {
			while(res.next()) {
				serie.getData().add(new XYChart.Data(res.getString("month"), res.getDouble("TOTAL")));
				
			}
			barchart.getData().addAll(serie);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    threadprogress.start();
	    
        ResultSet clientfidel = ConnectToDB.clientfidel(connection);
        try {
        	System.out.println("kkkkkk");
			clientfidel.next();
			nameclientone.setText(clientfidel.getString("nom") + " " + clientfidel.getString("prenom"));
			budgetclone.setText(clientfidel.getString("TotalBudget")+ "$");
			clientfidel.next();
			nameclienttwo.setText(clientfidel.getString("nom") + " " + clientfidel.getString("prenom"));
			budgetcltwo.setText(clientfidel.getString("TotalBudget")+ "$");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ResultSet produitepuiselist = ConnectToDB.produitemptystock(connection);
        try {
			while(produitepuiselist.next()) {
			
			SousClassRingProgressIndicator produitepuise = new SousClassRingProgressIndicator();
			produitepuise.makeIndeterminate();
			try {
				produitepuise.setProgress(produitepuiselist.getInt("quantite"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			VBox Hboxprd = new VBox();
			Hboxprd.getChildren().add(produitepuise);
			Label label = new Label(produitepuiselist.getString("nomproduit"));
			label.setStyle("-fx-alignment:center");
			Hboxprd.getChildren().add(label);
			Hboxfooter.getChildren().add(Hboxprd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ConnectToDB.labelsstats(connection,nbrclientlabel,nbrprdlabel,nbrcommandeabel,nbrlivrlabel,nbrfactlabel);
        movmentwindow.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        
        movmentwindow.setOnMouseDragged(event -> {
        	Node source = (Node) event.getSource();
            Stage primaryStage = (Stage) source.getScene().getWindow();
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

		
	}


}
