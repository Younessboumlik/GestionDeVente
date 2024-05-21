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
import classes.Livraison;
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

public class LivraisonToCommandeController implements Initializable {

    @FXML
    private TableView<Commande> TableauCommande;

    @FXML
    private Button cherche;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private TableColumn<Commande, LocalDate> dateCommande;

    @FXML
    private TableColumn<Livraison, LocalDate> dateLivraison;

    @FXML
    private TableColumn<Commande, Integer> numeroClient;

    @FXML
    private TableColumn<Commande, Integer> numeroCommande;

    @FXML
    private TableColumn<Livraison, Integer> numeroLivraison;

    @FXML
    private TableColumn<Livraison, Integer> numerocommande;

    @FXML
    private TextField searchtext;

    @FXML
    private TableView<Livraison> tableLivraison;

//    @FXML
//    private TableColumn<Commande, CheckBox> checkboxColumn;

    ArrayList<CheckBox> arrayofcheckboxes = new ArrayList<>();

    public static LivraisonToCommandeController controller;
    
//    le numero de la commande choisie par le checkbox
	int numeroCommandeChoisie = 0;
	

  	 TableColumn<Commande, CheckBox> checkbox = new TableColumn<Commande, CheckBox>("Selectionner") ;
    
    public LivraisonToCommandeController() {
        controller = this;
    }

    Connection connection = ConnectToDB.connectionDB();

    public void  initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<String> options = FXCollections.observableArrayList(
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
                controller.arrayofcheckboxes.add(commande.getCheckcommandetolivraison());
                ListCommandes.add(commande);
            }

            numeroCommande.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("numerocommande"));
            dateCommande.setCellValueFactory(new PropertyValueFactory<Commande,LocalDate>("datecomande"));
            numeroClient.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("num_client"));
            checkbox.setCellValueFactory(new PropertyValueFactory<Commande,CheckBox>("checkcommandetolivraison"));
            TableauCommande.setItems(ListCommandes);
            TableauCommande.getColumns().add(checkbox);

            numeroLivraison.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("numeroLivraison"));
            dateLivraison.setCellValueFactory(new PropertyValueFactory<Livraison,LocalDate>("dateLivraison"));
            numerocommande.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("numeroCommande"));
        }
        catch (SQLException e) {
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
    public void  cherche(ActionEvent event) {
//    	D'abord effacer le contenur du tableau de livraison.
		controller.tableLivraison.getItems().clear();

		
        ResultSet Commandes = ConnectToDB.data(connection, "Commande",combobox.getValue() , searchtext.getText());
        ObservableList<Commande> ListCommandes = FXCollections.observableArrayList();
        try {
 	    	 while(Commandes.next()){
 	    		 
 	    		 Commande commande = new Commande(Commandes.getInt("numerocommande"),Commandes.getDate("datecommande").toLocalDate(),Commandes.getInt("numeroclient"));
 	    		 


	    		 controller.arrayofcheckboxes.add(commande.getCheckcommandetolivraison());
 	    		 ListCommandes.add(commande);

 				}
// 	    	
 	    	numeroClient.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("num_client"));
 	    	numeroCommande.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("numerocommande"));
 	    	dateCommande.setCellValueFactory(new PropertyValueFactory<Commande,LocalDate>("datecomande"));
 	    	checkbox.setCellValueFactory(new PropertyValueFactory<Commande,CheckBox>("checkcommandetolivraison"));
 	    	TableauCommande.setItems(ListCommandes);
// 	    	TableauCommande.getColumns().add(checkbox);
 	    	

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
}  
    

	public static void  disableCheckboxes(Commande commande) {
		
//		System.out.println("disabled");
		if(commande.checkcommandetolivraison.isSelected()) {controller.arrayofcheckboxes.forEach(checkbox -> {
			checkbox.setDisable(true);
//			System.out.println("disabled");
			

		});
		
//		
		commande.checkcommandetolivraison.setDisable(false);
		controller.numeroCommandeChoisie = commande.numerocommande;
		ResultSet res = ConnectToDB.data(controller.connection,"livraison", "numeroCommande", Integer.toString(controller.numeroCommandeChoisie));

		ObservableList<Livraison> listLivraison = FXCollections.observableArrayList();
		try {
			while (res.next()) {
				Livraison livrasion = new Livraison(res.getInt("numeroLivraison"), res.getDate("dateLivraison").toLocalDate(),res.getInt("numeroCommande"));
				listLivraison.add(livrasion);
				System.out.println(livrasion.numeroLivraison);
			}

			controller.tableLivraison.setItems(listLivraison);
		} catch (SQLException e) {
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
	} else {
		controller.arrayofcheckboxes.forEach(checkbox -> {
			checkbox.setDisable(false);
			controller.tableLivraison.getItems().clear();
		});
	}
		
	}  
}
