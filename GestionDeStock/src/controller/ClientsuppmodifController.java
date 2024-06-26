package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.ConnectToDB;
import classes.Client;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientsuppmodifController implements Initializable{
	@FXML
	TableView<Client> TableClient;
	 @FXML
	    private TableColumn<Client, String> adresscolone;

	    @FXML
	    private Button cherche;
	    
	    @FXML
	    private Button annuler;

	    @FXML
	    private TextField choosetext;

	    @FXML
	    private ComboBox<String> combobox;

	    @FXML
	    private Label label;

	    @FXML
	    private Button modifier;

	    @FXML
	    private MenuButton export;

	    @FXML
	    private MenuItem exportToCsv;

	    @FXML
	    private MenuItem exportToPdf;

	    @FXML
	    private TableColumn<Client, String> nomcolon;

	    @FXML
	    private  TextField nomtext;

	    @FXML
	    private  TextField adresstext;
	    

	    @FXML
	    private TableColumn<Client, String> prenomcolone;

	    @FXML
	    private  TextField prenomtext;

	    @FXML
	    private TableColumn<Client, Integer> telecolone;

	    @FXML
	    private  TextField telephonetext;
	    @FXML
	    private TableColumn<Client,Button> modifcolone;
	    @FXML
	    private TableColumn<Client, Button> suppcolone;
		public static ClientsuppmodifController controller ;
       String selectedValue;
       String Valeur;
       int id_client;
       
       public ClientsuppmodifController (){
           controller = this;
          
       }
       public static  ClientsuppmodifController  returncreatcontroller(){
           return controller ;
          
       }
       
       
	    @FXML
	    public void  cherche(ActionEvent event) {
	    	 Valeur = choosetext.getText();
	    	 Connection connection = ConnectToDB.connectionDB();
	    	 ResultSet result = ConnectToDB.data(connection,"client",selectedValue,Valeur);
	    	 ObservableList<Client> ListClient = FXCollections.observableArrayList();
	    	 try {
	    	 while(result.next()) {
	    		
					ListClient.add(new Client(result.getInt("numeroclient"),result.getString("nom"),result.getString("prenom"),result.getString("adresse"),result.getInt("telephone")));
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
e.printStackTrace();
    });
				}
	    	
	    	 TableClient.setItems(ListClient);
	    }

	    @FXML
	    public void  modifier(ActionEvent event) {
         Client client = new Client(id_client,nomtext.getText(),prenomtext.getText(),adresstext.getText(),Integer.parseInt(telephonetext.getText()));
         Connection connection = ConnectToDB.connectionDB();
         ConnectToDB.modifieuser(connection, client);
	    }
	    @FXML
	    public void  getvalue(ActionEvent event) {
	    	selectedValue = combobox.getValue();
	    	label.setText("chercher par "+ selectedValue);
	    }
	    public static void setvaluesontextfield(Client client) {
	        controller = ClientsuppmodifController.returncreatcontroller();
	    	
	    	controller.nomtext.setText(client.getNom());
	    	controller.prenomtext.setText(client.getPrenom());
	    	controller.adresstext.setText(client.getAdresse());
	    	controller.telephonetext.setText(Integer.toString(client.getTelephone()));
	    	controller.id_client = client.getId_client();
	    }
	    @Override
		public void  initialize(URL arg0, ResourceBundle arg1) {
			ObservableList<String> options = 
			        FXCollections.observableArrayList(
			            "nom",
			            "prenom",
			            "adresse",
			            "telephone"
			        );
			combobox.setItems(options);
			 Connection connection = ConnectToDB.connectionDB();
	    	 ResultSet result = ConnectToDB.selecttous(connection,"client");
	    	 ObservableList<Client> ListClient = FXCollections.observableArrayList();
	    	 try {
	    	 while(result.next()) {
	    		
					ListClient.add(new Client(result.getInt("numeroclient"),result.getString("nom"),result.getString("prenom"),result.getString("adresse"),result.getInt("telephone")));
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
e.printStackTrace();
    });
				}
	    	 nomcolon.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
	    	 prenomcolone.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
	    	 adresscolone.setCellValueFactory(new PropertyValueFactory<Client,String>("adresse"));
	    	 telecolone.setCellValueFactory(new PropertyValueFactory<Client,Integer>("telephone"));
	    	 modifcolone.setCellValueFactory(new PropertyValueFactory<Client,Button>("modifbutton"));
	    	 suppcolone.setCellValueFactory(new PropertyValueFactory<Client,Button>("suppbutton"));
	    	 TableClient.setItems(ListClient);
	    	 
	    	 
	 		exportToCsv.setOnAction(event -> ConnectToDB.exportToCsvclient(TableClient));
			exportToPdf.setOnAction(event -> ConnectToDB.exportToPdfclient(TableClient));
			annuler.setOnAction(event -> {
				choosetext.clear();
				refreshClient();
			});
			
		}
	    public static void refreshClient() {
	    	 
	    	 Connection connection = ConnectToDB.connectionDB();
	    	 ResultSet result = ConnectToDB.selecttous(connection,"client");
	    	 ObservableList<Client> ListClient = FXCollections.observableArrayList();
	    	 try {
	    	 while(result.next()) {
	    		
					ListClient.add(new Client(result.getInt("numeroclient"),result.getString("nom"),result.getString("prenom"),result.getString("adresse"),result.getInt("telephone")));
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
e.printStackTrace();
    });
				}
	    	 controller.TableClient.setItems(ListClient);
	    }
}