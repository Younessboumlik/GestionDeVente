package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class produitDunecommandeController implements Initializable{
	 @FXML
	    private TableColumn<Produit, Integer> QDS;

	    @FXML
	    private TableColumn<Commande, CheckBox> checkocommande;

	    @FXML
	    private Button cherche;

	    @FXML
	    private TextField cherchetext;

	    @FXML
	    private ComboBox<String> combobox;

	    @FXML
	    private TableColumn<Commande, LocalDate> date;

	    @FXML
	    private TableColumn<Produit, String> nomproduit;

	    @FXML
	    private TableColumn<Commande, Integer> numclient;

	    @FXML
	    private TableColumn<Commande, Integer> numcommande;

	    @FXML
	    private TableColumn<Produit, Double> prix;

	    @FXML
	    private TableColumn<Produit, Integer> quantiteprod;

	    @FXML
	    private TableView<Commande> tableaucommand;

	    @FXML
	    private TableView<Produit> tableauproduit;
	    Connection connection  = ConnectToDB.connectionDB();
	    ObservableList<Produit> observerlistofproduit = FXCollections.observableArrayList();
	    ArrayList<CheckBox> arrayofcheckboxes = new ArrayList<>();
	    int num_commande;
	    static produitDunecommandeController controller;
	    public produitDunecommandeController() {
	    	controller = this;
	    }
 
	    @FXML
	    void chercher(ActionEvent event) {
	    	 ResultSet Commandes = ConnectToDB.data(connection, "Commande",combobox.getValue() , cherchetext.getText());
	         ObservableList<Commande> ListCommandes = FXCollections.observableArrayList();
	         try {
	  	    	 while(Commandes.next()){
	  	    		 
	  	    		 Commande commande = new Commande(Commandes.getInt("numerocommande"),Commandes.getDate("datecommande").toLocalDate(),Commandes.getInt("numeroclient"));
	  	    		commande.setCheckboxProduitpourcommande(new CheckBox());
		    		 
		    		 arrayofcheckboxes.add(commande.getCheckboxProduitpourcommande());

	  	    		 ListCommandes.add(commande);
	  	    		 System.out.println("ddddddddd");
	  				}
	  	    	detruiretableau();
	  	    	 tableaucommand.setItems(ListCommandes);
	         }
	         catch (SQLException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  	}    
	         
	      }  
	    
	    static void checkboxIsSelected(boolean selected,int num_commande) {
		      if(selected) {
		    	  controller.num_commande = num_commande;
		    	for (int i =0;i<controller.arrayofcheckboxes.size();i++) {
		    		if(!controller.arrayofcheckboxes.get(i).isSelected()) {
		    			controller.arrayofcheckboxes.get(i).setDisable(true);
		    		}
		    		setpruducttotable();
		    		
		    	}
		      }
		      else {
		    	  controller.num_commande = 0;
		    	  for (int i =0;i<controller.arrayofcheckboxes.size();i++) {
			    		
			    			controller.arrayofcheckboxes.get(i).setDisable(false);
			    		
			    		
			    	}
		    	  detruiretableau();
		    	  
		      }
		    	
		    }
	    static void setpruducttotable() {
	          
	          try {
	        	  controller.observerlistofproduit = FXCollections.observableArrayList();
	        	  ResultSet result = ConnectToDB.getproducts(controller.connection, controller.num_commande);
				while(result.next()) {
				  Produit produit = new Produit(result.getInt("numeroproduit"),result.getString("nomproduit"),result.getInt("quantite"),result.getInt("prix"));
				  produit.setQuantitechoisie(result.getInt("Quantite_prod"));
				  
				  controller.observerlistofproduit.add(produit);  
				  
				  }
				controller.tableauproduit.setItems(controller.observerlistofproduit);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    static void detruiretableau() {
	    	controller.observerlistofproduit = FXCollections.observableArrayList();
	    	controller.tableauproduit.setItems(controller.observerlistofproduit);
	    }
	    
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	ObservableList<String> options = 
			        FXCollections.observableArrayList(
			            "numerocommande",
			            "datecommande",
			            "numeroclient"
			        );
	    	System.out.println(",,,,,,,,,,,,,,,");
			combobox.setItems(options);
	  
	    	ResultSet Commandes = ConnectToDB.selecttous(connection, "commande");
	    	System.out.println(",,,,,,,,,,,,,,,");
	    	ObservableList<Commande> ListCommandes = FXCollections.observableArrayList();
	    	try {
		    	 while(Commandes.next()){
		    		 
		    		 Commande commande = new Commande(Commandes.getInt("numerocommande"),Commandes.getDate("datecommande").toLocalDate(),Commandes.getInt("numeroclient"));
		    		 
		    		 commande.setCheckboxProduitpourcommande(new CheckBox());
		    		 
		    		 controller.arrayofcheckboxes.add(commande.getCheckboxProduitpourcommande());
		    		 ListCommandes.add(commande);
		    		 System.out.println("ddddddddd");
					}
//		    	
		    	 numclient.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("num_client"));
		    	 numcommande.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("numerocommande"));
		    	 date.setCellValueFactory(new PropertyValueFactory<Commande,LocalDate>("datecomande")); 
		    	 checkocommande.setCellValueFactory(new PropertyValueFactory<Commande,CheckBox>("checkboxProduitpourcommande"));
		    	 tableaucommand.setItems(ListCommandes);
		    	 quantiteprod.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("Quantitechoisie"));
		    	 nomproduit.setCellValueFactory(new PropertyValueFactory<Produit,String>("nomProduit"));
		    	 QDS.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("QuantiteProduit"));
		    	 prix.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prix"));
		    	 }
		    	 catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}    
	    }
}
