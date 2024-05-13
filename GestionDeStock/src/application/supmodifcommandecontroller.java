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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class supmodifcommandecontroller implements Initializable{
	 @FXML
	    private Button cherche;
    @FXML
    private TableColumn<Commande, CheckBox> checkocommande;

    @FXML
    private TextField cherchetext;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private TableColumn<Commande, Integer> numcommande;

    @FXML
    private TableColumn<Commande, LocalDate> date;

    @FXML
    private DatePicker datetext;

    @FXML
    private Button modifiercomm;

    @FXML
    private TableColumn<Commande, Button> modifiercommande;

    @FXML
    private TableColumn<Produit, Button> modifierprod;

    @FXML
    private Button modifierproduit;

    @FXML
    private TableColumn<Produit, String> nomproduit;

    @FXML
    private TableColumn<Commande, Integer> numclient;

    @FXML
    private TableColumn<Produit, Integer> quantiteprod;

    @FXML
    private TextField quantitetext;

    @FXML
    private TableColumn<Commande, Button> suppcommande;

    @FXML
    private TableColumn<Produit, Button> supprimerprod;

    @FXML
    private TableView<Commande> tableaucommand;

    @FXML
    private TableView<Produit> tableauproduit;

    @FXML
    private TextField textnumclient;
    Connection connection = ConnectToDB.connectionDB();
    static supmodifcommandecontroller controller;
    int num_commande;
    int num_prod;
    String nom_prod;
    int num_commandeAmodifier;
    ArrayList<CheckBox> arrayofcheckboxes = new ArrayList<>();
    ObservableList<Produit> observerlistofproduit = FXCollections.observableArrayList();
    @FXML
    void chercher(ActionEvent event) {
       ResultSet Commandes = ConnectToDB.data(connection, "Commande",combobox.getValue() , cherchetext.getText());
       ObservableList<Commande> ListCommandes = FXCollections.observableArrayList();
       try {
	    	 while(Commandes.next()){
	    		 
	    		 Commande commande = new Commande(Commandes.getInt("numerocommande"),Commandes.getDate("datecommande").toLocalDate(),Commandes.getInt("numeroclient"));
	    		 
	    		 commande.setCheckforproducts(new CheckBox());
	    		 commande.setModifbutton(new Button("modifier"));
	    		 commande.setSuppbutton(new Button("supprimer"));
	    		 controller.arrayofcheckboxes.add(commande.getCheckforproducts());
	    		 ListCommandes.add(commande);
	    		 System.out.println("ddddddddd");
				}
//	    	
	    	 numclient.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("num_client"));
	    	 numcommande.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("numerocommande"));
	    	 date.setCellValueFactory(new PropertyValueFactory<Commande,LocalDate>("datecomande"));
	    	 suppcommande.setCellValueFactory(new PropertyValueFactory<Commande,Button>("suppbutton"));
	    	 modifiercommande.setCellValueFactory(new PropertyValueFactory<Commande,Button>("modifbutton"));
	    	 checkocommande.setCellValueFactory(new PropertyValueFactory<Commande,CheckBox>("checkforproducts"));
	    	 tableaucommand.setItems(ListCommandes);
       }
       catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}    
       
    }
    static void detruiretableau() {
    	controller.observerlistofproduit = FXCollections.observableArrayList();
    	controller.tableauproduit.setItems(controller.observerlistofproduit);
    }
    static void setpruducttotable() {
          
          try {
        	  controller.observerlistofproduit = FXCollections.observableArrayList();
        	  ResultSet result = ConnectToDB.getproducts(controller.connection, controller.num_commande);
			while(result.next()) {
			  Produit produit = new Produit(result.getInt("numeroproduit"),result.getString("nomproduit"),result.getInt("Quantite_prod"));
			  produit.setModifproduitinavoir(new Button("modifier"));
			  produit.setSuppproduittinavoir(new Button("supprimer"));
			  
			  controller.observerlistofproduit.add(produit);  
			  
			  }
			controller.tableauproduit.setItems(controller.observerlistofproduit);
		} catch (SQLException e) {
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
	    		
	    	}
	      }
	      else {
	    	  controller.num_commande = 0;
	    	  for (int i =0;i<controller.arrayofcheckboxes.size();i++) {
		    		
		    			controller.arrayofcheckboxes.get(i).setDisable(false);
		    		
		    		
		    	}
	    	  
	      }
	    	
	    }
    public supmodifcommandecontroller() {
    	controller = this ;
    }

   @FXML
    void modifiercomm(ActionEvent event) {
      ConnectToDB.modifiercomande(connection, new Commande(num_commandeAmodifier,datetext.getValue(),Integer.parseInt(textnumclient.getText())));
      refresh();
    }

    @FXML
    void modifierproduit(ActionEvent event) {
    	ConnectToDB.modifierproduit(connection,new Produit(num_prod,nom_prod,Integer.parseInt(quantitetext.getText())), num_commande);
    	setpruducttotable();
    }
    static void suppproduitfromavoir(int num_prod) {
    	ConnectToDB.supprimerrproduit(controller.connection,num_prod, controller.num_commande);
    	setpruducttotable();
    	
    }
static void setinfotomodifproduit (Produit produit) {
    	controller.quantitetext.setText(Integer.toString(produit.getQuantitechoisie()));
         
    	controller.num_prod = produit.getNumProduit();
    	controller.nom_prod = produit.getNomProduit();
    }
    
static void setinfotomodifcommande(Commande commande) {
	controller.textnumclient.setText(Integer.toString(commande.getNum_client()));
	controller.datetext.setValue(commande.getDatecomande());
	controller.num_commandeAmodifier = commande.getNumerocommande();
}
    static void refresh() {
    	ResultSet Commandes = ConnectToDB.selecttous(controller.connection, "commande");
    	ObservableList<Commande> ListCommandes = FXCollections.observableArrayList();
    	try {
			while(Commandes.next()){
			 
			 Commande commande = new Commande(Commandes.getInt("numerocommande"),Commandes.getDate("datecommande").toLocalDate(),Commandes.getInt("numeroclient"));
			 
			 commande.setCheckforproducts(new CheckBox());
			 commande.setModifbutton(new Button("modifier"));
			 commande.setSuppbutton(new Button("supprimer"));
			 controller.arrayofcheckboxes.add(commande.getCheckforproducts());
			 ListCommandes.add(commande);
			 System.out.println("ddddddddd");
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	controller.tableaucommand.setItems(ListCommandes);
    	controller.textnumclient.setText(null);
    	controller.datetext.setValue(null);
    	detruiretableau();
    }
    public void initialize(URL arg0, ResourceBundle arg1) {
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
	    		 
	    		 commande.setCheckforproducts(new CheckBox());
	    		 commande.setModifbutton(new Button("modifier"));
	    		 commande.setSuppbutton(new Button("supprimer"));
	    		 controller.arrayofcheckboxes.add(commande.getCheckforproducts());
	    		 ListCommandes.add(commande);
	    		 System.out.println("ddddddddd");
				}
//	    	
	    	 numclient.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("num_client"));
	    	 numcommande.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("numerocommande"));
	    	 date.setCellValueFactory(new PropertyValueFactory<Commande,LocalDate>("datecomande"));
	    	 suppcommande.setCellValueFactory(new PropertyValueFactory<Commande,Button>("suppbutton"));
	    	 modifiercommande.setCellValueFactory(new PropertyValueFactory<Commande,Button>("modifbutton"));
	    	 checkocommande.setCellValueFactory(new PropertyValueFactory<Commande,CheckBox>("checkforproducts"));
	    	 tableaucommand.setItems(ListCommandes);
	    	 quantiteprod.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("Quantitechoisie"));
	    	 nomproduit.setCellValueFactory(new PropertyValueFactory<Produit,String>("nomProduit"));
	    	 supprimerprod.setCellValueFactory(new PropertyValueFactory<Produit,Button>("suppproduittinavoir"));
	    	 modifierprod.setCellValueFactory(new PropertyValueFactory<Produit,Button>("modifproduitinavoir"));
	    	 }
	    	 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}    
    }

}