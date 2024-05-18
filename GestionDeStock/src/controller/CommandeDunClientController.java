package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ConnectToDB;
import classes.Client;
import classes.Commande;
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

public class CommandeDunClientController implements Initializable{
   
	@FXML
    private TableColumn<Client, CheckBox> checkforcommande;

    @FXML
    private TableColumn<Client, String> adressclient;

    @FXML
    private Button cherche;

    @FXML
    private TextField cherchetext;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private TableColumn<Commande, LocalDate> datecommande;

    @FXML
    private TableColumn<Client, String> nomclient;

    @FXML
    private TableColumn<Client, Integer> numclient;

    @FXML
    private TableColumn<Commande, Integer> numcommande;

    @FXML
    private TableColumn<Client, String> prenomclient;

    @FXML
    private TableView<Client> tableauclient;

    @FXML
    private TableView<Commande> tableaucommande;

    @FXML
    private TableColumn<Client, Integer> teleclient;
    public static CommandeDunClientController controller;
    Connection connection = ConnectToDB.connectionDB();
    int num_client;
    ArrayList<CheckBox> arrayOfcheckboxs = new ArrayList<>();
    ObservableList<Commande> observerlistofcommandes = FXCollections.observableArrayList();
    public CommandeDunClientController() {
    	controller = this;
    }

    @FXML
    public void  chercher(ActionEvent event) {
    	detruiretableau();
    	ResultSet Clients = ConnectToDB.data(connection, "client",combobox.getValue(),cherchetext.getText());
    	ObservableList<Client> ListClient = FXCollections.observableArrayList();
    	System.out.println("llllllll");
    	try {
	    	 while(Clients.next()) {
	    		 Client client = new Client(Clients.getInt("numeroclient"),Clients.getString("nom"),Clients.getString("prenom"),Clients.getString("adresse"),Clients.getInt("telephone"));
	    		 
	    		 client.setCheckforcommands(new CheckBox());
	    		 controller.arrayOfcheckboxs.add(client.getCheckforcommands());
					ListClient.add(client);
				}
	    	 System.out.println("hhhhhhh");
	    	 tableauclient.setItems(ListClient);
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
    public static void checkboxIsSelected(boolean selected,int numClient) {
	      if(selected) {
	    	  controller.num_client = numClient;
	    	for (int i =0;i<controller.arrayOfcheckboxs.size();i++) {
	    		if(!controller.arrayOfcheckboxs.get(i).isSelected()) {
	    			controller.arrayOfcheckboxs.get(i).setDisable(true);
	    		}
	    		
	    	}
	    	setcommandetotable();
	      }
	      else {
	    	  controller.num_client = 0;
	    	  for (int i =0;i<controller.arrayOfcheckboxs.size();i++) {
		    		
		    			controller.arrayOfcheckboxs.get(i).setDisable(false);
		    		
		    		
		    	}
	    	  detruiretableau();
	    	  
	      }
	    	
	    }
    public static void setcommandetotable() {
        
        try {
      	  controller.observerlistofcommandes = FXCollections.observableArrayList();
      	  ResultSet result = ConnectToDB.data(controller.connection, "commande","numeroclient",Integer.toString(controller.num_client));
			while(result.next()) {
				Commande commande = new Commande(result.getInt("numerocommande"),result.getDate("datecommande").toLocalDate(),result.getInt("numeroclient"));
			  
			  
			  controller.observerlistofcommandes.add(commande);  
			  
			  }
			controller.tableaucommande.setItems(controller.observerlistofcommandes);
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
   }
    public static void detruiretableau() {
    	controller.observerlistofcommandes = FXCollections.observableArrayList();
    	controller.tableaucommande.setItems(controller.observerlistofcommandes);
    }
    public void  initialize(URL arg0, ResourceBundle arg1) {
    	ObservableList<String> options = 
		        FXCollections.observableArrayList(
		            "nom",
		            "prenom",
		            "adresse",
		            "telephone"
		        );
		combobox.setItems(options);
    	
    	ResultSet Clients = ConnectToDB.selecttous(connection, "client");
    	ObservableList<Client> ListClient = FXCollections.observableArrayList();
    	System.out.println("llllllll");
    	try {
	    	 while(Clients.next()) {
	    		 Client client = new Client(Clients.getInt("numeroclient"),Clients.getString("nom"),Clients.getString("prenom"),Clients.getString("adresse"),Clients.getInt("telephone"));
	    		 System.out.println("llllllll");
	    		 client.setCheckforcommands(new CheckBox());
	    		 controller.arrayOfcheckboxs.add(client.getCheckforcommands());
					ListClient.add(client);
				}
	    	 System.out.println("hhhhhhh");
	    	 nomclient.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
	    	 prenomclient.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
	    	 adressclient.setCellValueFactory(new PropertyValueFactory<Client,String>("adresse"));
	    	 teleclient.setCellValueFactory(new PropertyValueFactory<Client,Integer>("telephone"));
	    	 checkforcommande.setCellValueFactory(new PropertyValueFactory<Client,CheckBox>("checkforcommands"));
	    	 System.out.println("hhhhhhh");
	    	 tableauclient.setItems(ListClient);
	    	 numcommande.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("numerocommande"));
	    	 datecommande.setCellValueFactory(new PropertyValueFactory<Commande,LocalDate>("datecomande"));
	    	 
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
}
