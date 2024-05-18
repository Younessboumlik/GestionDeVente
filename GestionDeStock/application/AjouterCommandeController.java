package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AjouterCommandeController implements Initializable{
	Connection connection = ConnectToDB.connectionDB();
	 @FXML
	    private Button Ajoutercommand;

	    @FXML
	    private TableView<Client> TabeaulClient;

	    @FXML
	    private TableView<Produit> TableauProduit;

	    @FXML
	    private TableColumn<Client, String> adresseclient;

	    @FXML
	    private DatePicker calendrier;

	    @FXML
	    private ComboBox<String> combobox;

	    @FXML
	    private TableColumn<Client, CheckBox> checkclient;

	    @FXML
	    private TableColumn<Produit,CheckBox> checkproduit;

	    @FXML
	    private Button cherche;

	    @FXML
	    private TableColumn<Client, String> nomclient;

	    @FXML
	    private TableColumn<Produit, String> nomproduit;

	    @FXML
	    private TableColumn<Client, String> prenomclient;
	    @FXML
	    private TableColumn<Produit,Double> prixProduit;
	    @FXML
	    private TableColumn<Produit,TextField> quantiteChoisi;

	    @FXML
	    private TableColumn<Produit, Integer> quatiteproduit;

	    @FXML
	    private TextField searchtext;

	    @FXML
	    private TableColumn<Client, Integer> teleclient;
	    ArrayList<CheckBox> arrayOfcheckboxs = new ArrayList<>();
	    ArrayList<Produit> arrayOfProduit = new ArrayList<>();
	    
	    public static AjouterCommandeController controller;
	    int num_client;
	    public AjouterCommandeController() {
	    	controller = this;
	    }
	    public static void Ajouterproduit(Produit produit) {
	    	controller.arrayOfProduit.add(produit);
	    }
	    public static void removeproduit(Produit produit) {
	    	controller.arrayOfProduit.remove(produit);
	    }
	    public static void checkboxIsSelected(boolean selected,int numClient) {
	      if(selected) {
	    	  controller.num_client = numClient;
	    	for (int i =0;i<controller.arrayOfcheckboxs.size();i++) {
	    		if(!controller.arrayOfcheckboxs.get(i).isSelected()) {
	    			controller.arrayOfcheckboxs.get(i).setDisable(true);
	    		}
	    		
	    	}
	      }
	      else {
	    	  controller.num_client = 0;
	    	  for (int i =0;i<controller.arrayOfcheckboxs.size();i++) {
		    		
		    			controller.arrayOfcheckboxs.get(i).setDisable(false);
		    		
		    		
		    	}
	    	  
	      }
	    	
	    }
	    @FXML
	    public void  Ajoutercommand(ActionEvent event){
	    	Commande commande = new Commande(0,calendrier.getValue(),num_client);
	    	commande.setList_produit(arrayOfProduit);
	    	ConnectToDB.insertcommande(connection, commande);
	    	arrayOfProduit = new ArrayList<>();
	    	refreche();
	    }
	    @FXML
	    public void  cherche() {
	    	ResultSet Clients = ConnectToDB.data(connection, "client", combobox.getValue(), searchtext.getText());
	    	ObservableList<Client> ListClient = FXCollections.observableArrayList();
	    	try {
	    	 while(Clients.next()) {
	    		 Client client;
				
					client = new Client(Clients.getInt("numeroclient"),Clients.getString("nom"),Clients.getString("prenom"),Clients.getString("adresse"),Clients.getInt("telephone"));
					client.setCheckbox(new CheckBox());
		    		 controller.arrayOfcheckboxs.add(client.getCheckbox());
						ListClient.add(client);
					
		    	TabeaulClient.setItems(ListClient);
	    	 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		 
	    		 
	    }
	    public void  refreche() {
	    	ResultSet Clients = ConnectToDB.selecttous(connection, "client");
	    	ObservableList<Client> ListClient = FXCollections.observableArrayList();
	    	ResultSet produits = ConnectToDB.selecttous(connection, "produits");
	    	ObservableList<Produit> ListProduit = FXCollections.observableArrayList();
	    	try {
		    	 while(Clients.next()) {
		    		 Client client = new Client(Clients.getInt("numeroclient"),Clients.getString("nom"),Clients.getString("prenom"),Clients.getString("adresse"),Clients.getInt("telephone"));
		    		 
		    		 client.setCheckbox(new CheckBox());
		    		 controller.arrayOfcheckboxs.add(client.getCheckbox());
						ListClient.add(client);
					}
		    	 while(produits.next()) {
			    		Produit produit = new Produit(produits.getInt("numeroproduit"),produits.getString("nomproduit"),produits.getInt("quantite"),produits.getDouble("prix"));
			    		produit.setQuantitetext(new TextField());
			    		produit.setCheckProduit(new CheckBox());
						ListProduit.add(produit);
					}
		    	 
		    	 TabeaulClient.setItems(ListClient);
		    	 TableauProduit.setItems(ListProduit);
		    	 
		    	 }
		    	 catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	
            
	    	
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
	    	ResultSet produits = ConnectToDB.selecttous(connection, "produits");
	    	ObservableList<Produit> ListProduit = FXCollections.observableArrayList();
	    	try {
		    	 while(Clients.next()) {
		    		 Client client = new Client(Clients.getInt("numeroclient"),Clients.getString("nom"),Clients.getString("prenom"),Clients.getString("adresse"),Clients.getInt("telephone"));
		    		 
		    		 client.setCheckbox(new CheckBox());
		    		 controller.arrayOfcheckboxs.add(client.getCheckbox());
						ListClient.add(client);
					}
		    	 while(produits.next()) {
			    		Produit produit = new Produit(produits.getInt("numeroproduit"),produits.getString("nomproduit"),produits.getInt("quantite"),produits.getDouble("prix"));
			    		produit.setQuantitetext(new TextField());
			    		produit.setCheckProduit(new CheckBox());
						ListProduit.add(produit);
					}
		    	 nomclient.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
		    	 prenomclient.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
		    	 adresseclient.setCellValueFactory(new PropertyValueFactory<Client,String>("adresse"));
		    	 teleclient.setCellValueFactory(new PropertyValueFactory<Client,Integer>("telephone"));
		    	 checkclient.setCellValueFactory(new PropertyValueFactory<Client,CheckBox>("checkbox"));
		    	 TabeaulClient.setItems(ListClient);
		    	 nomproduit.setCellValueFactory(new PropertyValueFactory<Produit,String>("nomProduit"));
		    	 quatiteproduit.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("QuantiteProduit"));
		    	 prixProduit.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prix"));
		    	 quantiteChoisi.setCellValueFactory(new PropertyValueFactory<Produit,TextField>("quantitetext"));
		    	 checkproduit.setCellValueFactory(new PropertyValueFactory<Produit,CheckBox>("checkProduit"));
		    	 TableauProduit.setItems(ListProduit);
		    	 
		    	 }
		    	 catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	
            
	    }
	    
}

