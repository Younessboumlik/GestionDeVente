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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AjouterFactureController implements Initializable {
	Connection connection = ConnectToDB.connectionDB();

	

	
    @FXML
    private TableView<Commande> TableauCommande;

    @FXML
    private Button ajouterFacture;

    @FXML
    private Button cherche;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private TableColumn<Commande, LocalDate> dateCommande;

    @FXML
    private DatePicker dateFacture;

    @FXML
    private TextField montantFacture;

    @FXML
    private TableColumn<Commande, Integer> numeroClient;

    @FXML
    private TableColumn<Commande, Integer> numeroCommande;

    @FXML
    private TextField searchtext;

	    

	

	int numeroCommandeChoisie = 0;
	
	
//	list pour manipuler les checkboxes.
	ArrayList<CheckBox> Checkboxs = new ArrayList<>();
//	listes des commandes.
	ArrayList<Commande> Commandes = new ArrayList<>();

	 
	public static AjouterFactureController  controller;
	

	 public AjouterFactureController() {
	controller = this;
	 }
	    
	    @FXML
	    public void  cherche(ActionEvent event) {
	    	 String Valeur = searchtext.getText();
	    	 String selectedValue = combobox.getValue();
	    	 Connection connection = ConnectToDB.connectionDB();
	    	 ResultSet result = ConnectToDB.data(connection,"Commande",selectedValue,Valeur);
	    	 ObservableList<Commande> ListCommande = FXCollections.observableArrayList();
//	    	 effacer les checkboxs du tableau initial.
	    	 controller.Checkboxs.clear();
	    	 try {
	    	 while(result.next()) {
//	    		 creation d'un objet commande a partir de ResultSet.
	    		 Commande commandes = new Commande(result.getInt("numerocommande"),result.getDate("datecommande").toLocalDate(),result.getInt("numeroclient"));
//	    		  definition de checkbox pour chaque commande.
	    		 controller.Checkboxs.add(commandes.getCheck());
	    		 ListCommande.add(commandes);
				}
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
	    	
	    	 TableauCommande.setItems(ListCommande);
	    }
	    
	    @FXML
	    public void  Ajoutercommand(ActionEvent event){
	    	System.out.println("numeroCommandeChoisie" + numeroCommandeChoisie);
	          Facture newFacture = new Facture(0,dateFacture.getValue(),Float.parseFloat(montantFacture.getText()),numeroCommandeChoisie);
	          Connection connection = ConnectToDB.connectionDB();
	          ConnectToDB.insertFactureData(connection, newFacture);
}

	    public void  initialize(URL arg0, ResourceBundle arg1) {
	    
	    	
	    	ResultSet commande = ConnectToDB.selecttous(connection, "Commande");
	    	ObservableList<Commande> listCommande = FXCollections.observableArrayList();

	    	try {

		    	 while(commande.next()) {
//		    		  creation d'un objet commande a partir de ResultSet.
		    		 Commande commandes = new Commande(commande.getInt("numerocommande"),commande.getDate("datecommande").toLocalDate(),commande.getInt("numeroClient"));
		    		 
//		    		 definition de checkbox pour chaque commande.
		    		 
		    		 controller.Checkboxs.add(commandes.getCheck());
		    		 
		    		 listCommande.add(commandes);	 
		    }
  	    
		    	 TableColumn<Commande, CheckBox> checkbox = new TableColumn<Commande, CheckBox>("Selectionner") ;
		    	 
		    	 numeroCommande.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("numerocommande"));
		    	 dateCommande.setCellValueFactory(new PropertyValueFactory<Commande,LocalDate>("datecomande"));
		    	 numeroClient.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("num_client"));
		    	 checkbox.setCellValueFactory(new PropertyValueFactory<Commande,CheckBox>("check"));

		    	 TableauCommande.setItems(listCommande);
		    	 TableauCommande.getColumns().add(checkbox);
				 ObservableList<String> options = 
					        FXCollections.observableArrayList(
					            "Numerocommande",
					            "datecommande",
					            "numeroclient"
					        );
					combobox.setItems(options);
		    	 }
		    	 catch (SQLException e) {
    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur SQL");
        alert.setHeaderText("Une erreur SQL s'est produite.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    });
}

	    }
	    
//	    action pour desactiver les autres checkboxes si une checkbox est selectionner.
	    
		public static void  disableCheckboxes(Commande commande) {
			if(commande.check.isSelected()) {controller.Checkboxs.forEach(checkbox -> {
				checkbox.setDisable(true);
			});
			
			commande.check.setDisable(false);
			controller.numeroCommandeChoisie = commande.numerocommande;
		} else {
			controller.Checkboxs.forEach(checkbox -> {
				checkbox.setDisable(false);
			});
		}
			
		}  
}
