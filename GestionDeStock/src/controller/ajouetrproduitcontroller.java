package controller;

import java.sql.Connection;

import application.ConnectToDB;
import classes.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ajouetrproduitcontroller {

    @FXML
    private Button ajouter;

    @FXML
    private TextField textnomproduit;

    @FXML
    private TextField textprix;

    @FXML
    private TextField textquantite;

    @FXML
    public void  AjouterProduit(ActionEvent event){
    	Connection connecton = ConnectToDB.connectionDB();
    	Produit produit = new Produit(0,textnomproduit.getText(),Integer.parseInt(textquantite.getText()),Double.parseDouble(textprix.getText()));
          ConnectToDB.AJouterProduit(connecton,produit);
          textnomproduit.setText(null);
          textprix.setText(null);
          textquantite.setText(null);
    }

}