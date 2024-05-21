package controller;



import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ConnectToDB;
import classes.Facture;
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

public class SupModifFactureController implements Initializable{
	
	public static SupModifFactureController  controller;
	
	public SupModifFactureController() {
		controller = this;
	}
	

	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
    @FXML
    public TableView<Facture> tableventes;
	
    @FXML
    private TableColumn<Facture, Integer> numerofacture;

    @FXML
    private TableColumn<Facture, LocalDate> datefacture;

    @FXML
    private TableColumn<Facture, Float> montant;
    
    @FXML
    private TableColumn<Facture, Integer> numerocommande;
  
    @FXML
    private DatePicker newDateFacture;
    

    @FXML
    private TextField newMontant;

    @FXML
    private TextField newNumeroCommande;

    @FXML
    private TextField newNumeroFacture;
    
    @FXML
    private TextField choosetext;

    @FXML
    private Button cherche;
    
    @FXML
    private Button annuler;
    

    @FXML
    private Button modifier;
    
    @FXML
    private MenuButton export;

    @FXML
    private MenuItem exportToCsv;

    @FXML
    private MenuItem exportToPdf;
    

    @FXML
    private ComboBox<String> combobox;





    @FXML
    private Label label;



    @FXML
    public void  cherche(ActionEvent event) {
   	 String Valeur = choosetext.getText();
   	 Connection connection = ConnectToDB.connectionDB();
   	 ResultSet result = ConnectToDB.data(connection,"facture",combobox.getValue(),Valeur);
   	 ObservableList<Facture> listFacture = FXCollections.observableArrayList();
   	 try {
   	 while(result.next()) {

//   		 Conversion de date en type localDate.
   		 Date date = result.getDate("dateFacture");
   		LocalDate localDate = new Date(date.getTime()).toLocalDate();
   		listFacture.add(new Facture(result.getInt("numeroFacture"),localDate,result.getFloat("montant"),result.getInt("numeroCommande")));
	
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
   	
   	tableventes.setItems(listFacture);
    }
    
    



//  l'evenement apres clicker sur le button pour modifier une facture.
    @FXML
    public void  modifier(ActionEvent event) {
        Connection connection = ConnectToDB.connectionDB();
        new Facture(Integer.parseInt(newNumeroFacture.getText()),newDateFacture.getValue(),Float.parseFloat(newMontant.getText()),Integer.parseInt(newNumeroCommande.getText()));
        ConnectToDB.updatefacture(connection, new Facture(Integer.parseInt(newNumeroFacture.getText()),newDateFacture.getValue(),Float.parseFloat(newMontant.getText()),Integer.parseInt(newNumeroCommande.getText())));
        
        
    }
    
//    apres clicker le button modifier dans le tableau de facture
    public static void  modifierFacture(Facture facture) {
    	
    	controller.newNumeroFacture.setText(Integer.toString(facture.numeroFacture));
    	controller.newNumeroFacture.setDisable(true);
    	controller.newDateFacture.setValue(facture.dateFacture);
    	controller.newMontant.setText(Float.toString(facture.montant));
    	controller.newNumeroCommande.setText(Integer.toString(facture.numeroCommande));	
    }
     
    
//    listFacture.add(new Facture(1, date1, 4.4f));
    
//    new Facture(1, date1, 4.4f),
//    new Facture(2, date2, 4.2f)
    
    
    public static void main(String[] args) {
//        LocalDate  date1 = LocalDate.parse("2024-06-04");
//        LocalDate  date2 = LocalDate.parse("2024-04-02");
      
    }
    
//    retourn une list ObservableList qui contient tout les factures de la base de donnes.
    public static ObservableList <Facture> getfacturedata() {
    	
    	ObservableList<Facture> listFacture = FXCollections.observableArrayList(

    	    	);
    	
    	Connection connexion = ConnectToDB.connectionDB();

//    	ArrayList <Facture> factures = new ArrayList<Facture>();

    	ArrayList<String> list = ConnectToDB.getData(connexion, "facture", "numeroFacture");
    	ArrayList<String> list2 = ConnectToDB.getData(connexion, "facture", "dateFacture");
    	ArrayList<String> list3 = ConnectToDB.getData(connexion, "facture", "montant");
    	ArrayList<String> list4 = ConnectToDB.getData(connexion, "facture", "numeroCommande");
    	
    	
//    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	
    	
    	for (int i = 0; i < list.size(); i++) {
    		Facture facture = new Facture(Integer.parseInt(list.get(i)),LocalDate.parse(list2.get(i)),Float.parseFloat(list3.get(i)),Integer.parseInt(list4.get(i)));
    		listFacture.add(facture);
  
    	}

    	return listFacture;
    	
//        listFacture.add(new Facture(1, date1, 4.4f));

    }
    
//    @FXML
//    private TableColumn<Facture, public void > deleteButtonColumn;

    
//    permer de refrechir le tableau des factures
    
    
    
    public static void refreshfacture() {

    	controller.tableventes.setItems(getfacturedata());
    }
    
//    permet d'initialiser le tableau des factures
	@Override
	public void  initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<String> options = FXCollections.observableArrayList("numeroFacture", "dateFacture", "montant", "numeroCommande");
		combobox.setItems(options);

		
		
//		TableColumn<Facture,Button> deletebutton = new TableColumn<Facture,Button>("Supprimer");
		TableColumn<Facture, Button> deletebutton = new TableColumn<>("Supprimer");
		TableColumn<Facture, Button> modifiebutton = new TableColumn<>("Modifier");
		
		
		numerofacture.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("numeroFacture"));
		datefacture.setCellValueFactory(new PropertyValueFactory<Facture,LocalDate>("dateFacture"));
		montant.setCellValueFactory(new PropertyValueFactory<Facture,Float>("montant"));
		numerocommande.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("numeroCommande"));
		modifiebutton.setCellValueFactory(new PropertyValueFactory<Facture,Button>("modifbutton"));

//		deletebutton.setCellValueFactory(new PropertyValueFactory<Facture,String>("deletebutton"));
		deletebutton.setCellValueFactory(new PropertyValueFactory<Facture,Button>("deletebutton"));
		
		tableventes.getColumns().add(modifiebutton);
		tableventes.getColumns().add(deletebutton);
		
		
		tableventes.setItems(getfacturedata());
		
		exportToCsv.setOnAction(event -> ConnectToDB.exportToCsvfact(tableventes));
		exportToPdf.setOnAction(event -> ConnectToDB.exportToPdffac(tableventes));
		annuler.setOnAction(event -> {
			choosetext.clear();
			refreshfacture();
		});
		
		
	}
	
	



}
