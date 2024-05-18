package controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.ConnectToDB;
import classes.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class supmodifproduitcontroller implements Initializable{
	
	@FXML
    private TableView<Produit> TableProduit;


    @FXML
    private Button cherche;

    @FXML
    private TextField choosetext;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private Label label;

    @FXML
    private Button modifierbtn;

    @FXML
    private TableColumn<Produit, String> nomproduit;


    @FXML
    private TextField nomprdtext;

    @FXML
    private TableColumn<Produit, Integer> numeroproduit;

    @FXML
    private TextField prixtext;

    @FXML
    private TableColumn<Produit, Double> prix;

    @FXML
    private TableColumn<Produit, Integer> quantite;

    @FXML
    private TableColumn<Produit, Button> supprimer;

    @FXML
    private TextField quatitetext;
    @FXML
    private TableColumn<Produit, Button> modifier;
    int numproduit;
    public static supmodifproduitcontroller controller;

    @FXML
    public void  cherche(ActionEvent event) {
    	 Connection connection = ConnectToDB.connectionDB();
    	 ResultSet result = ConnectToDB.data(connection,"produits",combobox.getValue(),choosetext.getText());
    	 ObservableList<Produit> ListProduit = FXCollections.observableArrayList();
    	 try {
    	 while(result.next()) {
    		Produit produit = new Produit(result.getInt("numeroproduit"),result.getString("nomproduit"),result.getInt("quantite"),result.getDouble("prix"));
    		produit.setModifierprd(new Button("modifier"));
   		    produit.setSupprimerprd(new Button("supprimer"));
   		 
				ListProduit.add(produit);
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
    	 controller.TableProduit.setItems(ListProduit);
    	 
    }

    @FXML
    public void  getvalue(ActionEvent event) {

    }
    public supmodifproduitcontroller (){
        controller = this;
       
    }

    @FXML
    public void  modifier(ActionEvent event) {
    	Produit produit = new Produit(numproduit,nomprdtext.getText(),Integer.parseInt(quatitetext.getText()),Double.parseDouble(prixtext.getText()));
    	Connection connection = ConnectToDB.connectionDB();
    	ConnectToDB.modifierproduit(connection,produit);
         refreche();
    }
     public static void refreche() {
    	 Connection connection = ConnectToDB.connectionDB();
    	 ResultSet result = ConnectToDB.selecttous(connection,"produits");
    	 ObservableList<Produit> ListProduit = FXCollections.observableArrayList();
    	 try {
    	 while(result.next()) {
    		Produit produit = new Produit(result.getInt("numeroproduit"),result.getString("nomproduit"),result.getInt("quantite"),result.getDouble("prix"));
    		produit.setModifierprd(new Button("modifier"));
   		    produit.setSupprimerprd(new Button("supprimer"));
   		 
				ListProduit.add(produit);
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
    	 controller.TableProduit.setItems(ListProduit);
    	 
    }
    public static void setvaluesontextfield(Produit produit) {

    	
    	controller.nomprdtext.setText(produit.getNomProduit());
    	controller.quatitetext.setText(Integer.toString(produit.getQuantiteProduit()));
    	controller.prixtext.setText(Double.toString(produit.getPrix()));
    	controller.numproduit = produit.getNumProduit();
    	
    }
    public void  initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> options = 
		        FXCollections.observableArrayList(
		            "numeroproduit",
		            "nomproduit",
		            "quantite",
		            "prix"
		        );
		 combobox.setItems(options);
		 Connection connection = ConnectToDB.connectionDB();
    	 ResultSet result = ConnectToDB.selecttous(connection,"produits");
    	 ObservableList<Produit> ListProduit = FXCollections.observableArrayList();
    	 try {
    	 while(result.next()) {
    		 Produit produit = new Produit(result.getInt("numeroproduit"),result.getString("nomproduit"),result.getInt("quantite"),result.getDouble("prix"));
    		 produit.setModifierprd(new Button("modifier"));
    		 produit.setSupprimerprd(new Button("supprimer"));
    		 ListProduit.add(produit);
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
    	 nomproduit.setCellValueFactory(new PropertyValueFactory<Produit,String>("nomProduit"));
    	 numeroproduit.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("numProduit"));
    	 quantite.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("QuantiteProduit"));
    	 prix.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prix"));
    	 supprimer.setCellValueFactory(new PropertyValueFactory<Produit,Button>("supprimerprd"));
    	 modifier.setCellValueFactory(new PropertyValueFactory<Produit,Button>("modifierprd"));
    	 TableProduit.setItems(ListProduit);
		
	}
    
}

