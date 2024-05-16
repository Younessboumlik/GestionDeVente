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

public class AjouterLivraisonController implements Initializable{
	
	
	Connection connection = ConnectToDB.connectionDB();

    @FXML
    private TableView<Commande> TableauCommande;

    @FXML
    private Button ajouterLivraison;

    @FXML
    private Button cherche;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private TableColumn<Commande, LocalDate> dateCommande;

    @FXML
    private DatePicker dateLivraison;

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

	
//	Ceci va nous pemer d'acceder au champ de la classe AjouterLivraisonController dans une methode static.
	 
	static AjouterLivraisonController  controller;
	

	 public AjouterLivraisonController() {
	controller = this;
	 }
	    
	    @FXML
	    void cherche(ActionEvent event) {
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
	    		 controller.Checkboxs.add(commandes.getCheckForLivraison());
	    		 ListCommande.add(commandes);
				}
	    	 }
	    	 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	
	    	 TableauCommande.setItems(ListCommande);
	    }
	    
	    @FXML
	    void Ajoutercommand(ActionEvent event){
	    	System.out.println("numeroCommandeChoisie" + numeroCommandeChoisie);
	          Livraison newLivraison = new Livraison(0,dateLivraison.getValue(),numeroCommandeChoisie);
	          Connection connection = ConnectToDB.connectionDB();
	          ConnectToDB.insertLivraisonData(connection, newLivraison);
}

	    
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    
	    	
	    	ResultSet commande = ConnectToDB.selecttous(connection, "Commande");
	    	ObservableList<Commande> listCommande = FXCollections.observableArrayList();

	    	try {

		    	 while(commande.next()) {
//		    		  creation d'un objet commande a partir de ResultSet.
		    		 Commande commandes = new Commande(commande.getInt("numerocommande"),commande.getDate("datecommande").toLocalDate(),commande.getInt("numeroClient"));
		    		 
//		    		 definition de checkbox pour chaque commande.
		    		 
		    		 controller.Checkboxs.add(commandes.getCheckForLivraison());
		    		 
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    }

	    
//	    action pour desactiver les autres checkboxes si une checkbox est selectionner.
	    
		static void  disableCheckboxes(Commande commande) {
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
