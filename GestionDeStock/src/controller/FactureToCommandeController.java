package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ConnectToDB;
import classes.Commande;
import classes.Facture;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class FactureToCommandeController implements Initializable{


	   @FXML
	    private TableView<Commande> TableauCommande;

	    @FXML
	    private Button cherche;

	    @FXML
	    private ComboBox<String> combobox;

	    @FXML
	    private TableColumn<Commande, LocalDate> dateCommande;

	    @FXML
	    private TableColumn<Facture, LocalDate> datefacture;

	    @FXML
	    private TableColumn<Facture, Float> montant;

	    @FXML
	    private TableColumn<Commande, Integer> numeroClient;

	    @FXML
	    private TableColumn<Commande, Integer> numeroCommande;

	    @FXML
	    private TableColumn<Facture, Integer> numerocommande;

	    @FXML
	    private TableColumn<Facture, Integer> numerofacture;

	    @FXML
	    private TextField searchtext;

	    @FXML
	    private TableView<Facture> tableventes;

	    
   	 TableColumn<Commande, CheckBox> checkbox = new TableColumn<Commande, CheckBox>("Selectionner") ;
   	 
   	 
   	 
	    @FXML
	    public void  cherche(ActionEvent event) {
			controller.tableventes.getItems().clear();
	        ResultSet Commandes = ConnectToDB.data(connection, "Commande",combobox.getValue() , searchtext.getText());
	        ObservableList<Commande> ListCommandes = FXCollections.observableArrayList();
	        try {
	 	    	 while(Commandes.next()){
	 	    		 
	 	    		 Commande commande = new Commande(Commandes.getInt("numerocommande"),Commandes.getDate("datecommande").toLocalDate(),Commandes.getInt("numeroclient"));
	 	    		 


		    		 controller.arrayofcheckboxes.add(commande.getCheckcommandetofacture());
	 	    		 ListCommandes.add(commande);

	 				}
//	 	    	
	 	    	numeroClient.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("num_client"));
	 	    	numeroCommande.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("numerocommande"));
	 	    	dateCommande.setCellValueFactory(new PropertyValueFactory<Commande,LocalDate>("datecomande"));
	 	    	checkbox.setCellValueFactory(new PropertyValueFactory<Commande,CheckBox>("checkcommandetofacture"));
	 	    	TableauCommande.setItems(ListCommandes);
//	 	    	TableauCommande.getColumns().add(checkbox);
	 	    	

	        }
	        catch (SQLException e) {
	 			// TODO Auto-generated catch block
	 			    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    });
	 	}    
	    }
	    
//	    le numero de la commande choisie par le checkbox
		int numeroCommandeChoisie = 0;
//	    liste des checkboxs
	    ArrayList<CheckBox> arrayofcheckboxes = new ArrayList<>();
	    
	    
//	    controller public static pour acceder au attribue non public static dans les methode static.
	    public static FactureToCommandeController controller;
	    
	    
	    public FactureToCommandeController() {
	    	controller = this ;
	    }
	    
	    Connection connection = ConnectToDB.connectionDB();
	    
	    
	    public void  initialize(URL arg0, ResourceBundle arg1) {
	    	ObservableList<String> options = 
			        FXCollections.observableArrayList(
			            "numerocommande",
			            "datecommande",
			            "numeroclient"
			        );
			combobox.setItems(options);
	  
	    	ResultSet Commandes = ConnectToDB.selecttous(connection, "commande");
	    	ObservableList<Commande> ListCommandes = FXCollections.observableArrayList();
	    	try {
		    	 while(Commandes.next()){
		    		 
		    		 Commande commande = new Commande(Commandes.getInt("numerocommande"),Commandes.getDate("datecommande").toLocalDate(),Commandes.getInt("numeroclient"));
		    		

		    		 controller.arrayofcheckboxes.add(commande.getCheckcommandetofacture());
		    		 ListCommandes.add(commande);
					}
//		    	
		    	numeroCommande.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("numerocommande"));
		    	dateCommande.setCellValueFactory(new PropertyValueFactory<Commande,LocalDate>("datecomande"));
		    	numeroClient.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("num_client"));
		    	checkbox.setCellValueFactory(new PropertyValueFactory<Commande,CheckBox>("checkcommandetofacture"));
		    	TableauCommande.setItems(ListCommandes);
	            TableauCommande.getColumns().add(checkbox);
		    	 
		 		numerofacture.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("numeroFacture"));
				datefacture.setCellValueFactory(new PropertyValueFactory<Facture,LocalDate>("dateFacture"));
				montant.setCellValueFactory(new PropertyValueFactory<Facture,Float>("montant"));
				numerocommande.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("numeroCommande"));
				

		    	 }
		    	 catch (SQLException e) {
						// TODO Auto-generated catch block
						    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    });
				}    
	    }
	    
		public static void  disableCheckboxes(Commande commande) {
			System.out.println("clicked");
			if(commande.checkcommandetofacture.isSelected()) {controller.arrayofcheckboxes.forEach(checkbox -> {

				checkbox.setDisable(true);
//				System.out.println("disabled");
				

			});
			
//			
			commande.checkcommandetofacture.setDisable(false);
			controller.numeroCommandeChoisie = commande.numerocommande;
			ResultSet res = ConnectToDB.data(controller.connection,"facture", "numeroCommande", Integer.toString(controller.numeroCommandeChoisie));
			ObservableList<Facture> listFacture = FXCollections.observableArrayList();
			try {
				while (res.next()) {
					Facture facture = new Facture(res.getInt("numeroFacture"), res.getDate("dateFacture").toLocalDate(),
							res.getFloat("montant"), res.getInt("numeroCommande"));
					listFacture.add(facture);
				}
				controller.tableventes.setItems(listFacture);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    });
			}
		} else {
			controller.arrayofcheckboxes.forEach(checkbox -> {
				checkbox.setDisable(false);
				controller.tableventes.getItems().clear();
			});
		}
			
		}  
	    
    

}