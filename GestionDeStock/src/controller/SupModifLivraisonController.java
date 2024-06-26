package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ConnectToDB;
import classes.Livraison;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SupModifLivraisonController implements Initializable{

    @FXML
    private Button cherche;
    
    @FXML
    private Button annuler;

    @FXML
    private TextField choosetext;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private TableColumn<Livraison, LocalDate> dateLivraison;

    @FXML
    private MenuButton export;

    @FXML
    private MenuItem exportToCsv;

    @FXML
    private MenuItem exportToPdf;

    @FXML
    private Label label;

    @FXML
    private Button modifier;

    @FXML
    private DatePicker newDateLivraison;

    @FXML
    private TextField newNumeroCommande;

    @FXML
    private TextField newNumeroLivraison;

    @FXML
    private TableColumn<Livraison, Integer> numeroLivraison;

    @FXML
    private TableColumn<Livraison, Integer> numerocommande;

    @FXML
    private TableView<Livraison> tableLivraison;

    @FXML
    public void  cherche(ActionEvent event) {
      	 String Valeur = choosetext.getText();
       	 Connection connection = ConnectToDB.connectionDB();
       	 ResultSet result = ConnectToDB.data(connection,"Livraison",combobox.getValue(),Valeur);
       	 ObservableList<Livraison> listLivraison = FXCollections.observableArrayList();
       	 try {
       	 while(result.next()) {

//       		 Conversion de date en type localDate.
       		 Date date = result.getDate("dateLivraison");
       		LocalDate localDate = new Date(date.getTime()).toLocalDate();
       		listLivraison.add(new Livraison(result.getInt("numeroLivraison"),localDate,result.getInt("numeroCommande")));
    	
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
       	
       	
       	tableLivraison.setItems(listLivraison);
    }

    @FXML
    public void  modifier(ActionEvent event) {
        Connection connection = ConnectToDB.connectionDB();
        ConnectToDB.updateLivraison(connection, new Livraison(Integer.parseInt(newNumeroLivraison.getText()),newDateLivraison.getValue(),Integer.parseInt(newNumeroCommande.getText())));
    }
    
    
    public static void  modifierLivraison(Livraison livraison) {
    	
    	controller.newNumeroLivraison.setText(Integer.toString(livraison.numeroLivraison));
    	controller.newNumeroLivraison.setDisable(true);
//    	controller.newNumeroFacture.setDisable(true);
    	controller.newDateLivraison.setValue(livraison.dateLivraison);
    	controller.newNumeroCommande.setText(Integer.toString(livraison.numeroCommande));	
    	
    	
    }
	public static SupModifLivraisonController  controller;
	
	public SupModifLivraisonController() {
		controller = this;
	}
	
    
    public static ObservableList <Livraison> getlivraisondata() {
    	
    	ObservableList<Livraison> listLivraison = FXCollections.observableArrayList();
    	
    	Connection connexion = ConnectToDB.connectionDB();

//    	ArrayList <Livraison> livraisons = new ArrayList<Livraison>();

    	ArrayList<String> list = ConnectToDB.getData(connexion, "livraison", "numeroLivraison");
    	ArrayList<String> list2 = ConnectToDB.getData(connexion, "livraison", "dateLivraison");
    	ArrayList<String> list3 = ConnectToDB.getData(connexion, "livraison", "numeroCommande");
    	
    	
    	
    	
    	for (int i = 0; i < list.size(); i++) {
    		Livraison livraison = new Livraison(Integer.parseInt(list.get(i)),LocalDate.parse(list2.get(i)),Integer.parseInt(list3.get(i)));
    		listLivraison.add(livraison);
  
    	}

    	return listLivraison;
    	


    }
    
    
    
    public static void refreshLivraison() {

    	controller.tableLivraison.setItems(getlivraisondata());
    }
    
    
//  permet d'initialiser le tableau des factures
	@Override
	public void  initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<String> options = FXCollections.observableArrayList("numeroLivraison", "dateLivraison", "numeroCommande");
		combobox.setItems(options);

		
		
//		TableColumn<Facture,Button> deletebutton = new TableColumn<Facture,Button>("Supprimer");
		TableColumn<Livraison, Button> deletebutton = new TableColumn<>("Supprimer");
		TableColumn<Livraison, Button> modifiebutton = new TableColumn<>("Modifier");
		
		numeroLivraison.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("numeroLivraison"));
		dateLivraison.setCellValueFactory(new PropertyValueFactory<Livraison,LocalDate>("dateLivraison"));
		numerocommande.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("numeroCommande"));
		modifiebutton.setCellValueFactory(new PropertyValueFactory<Livraison,Button>("modifbutton"));

//		deletebutton.setCellValueFactory(new PropertyValueFactory<Facture,String>("deletebutton"));
		deletebutton.setCellValueFactory(new PropertyValueFactory<Livraison,Button>("deletebutton"));

		tableLivraison.setItems(getlivraisondata());
		
		
		tableLivraison.getColumns().add(modifiebutton);
		tableLivraison.getColumns().add(deletebutton);
		

		exportToCsv.setOnAction(event -> ConnectToDB.exportToCsvliv(tableLivraison));
		exportToPdf.setOnAction(event -> ConnectToDB.exportToPdfliv(tableLivraison));
		annuler.setOnAction(event -> {
			choosetext.clear();
			refreshLivraison();
		});
		
	}

}
