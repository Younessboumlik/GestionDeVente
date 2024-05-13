package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProduitsupmodifController {

    @FXML
    private TableView<Produit> TableProduit;

    @FXML
    private TableColumn<Produit, Integer> numeroproduit;

    @FXML
    private TableColumn<Produit, String> nomproduit;
    
    @FXML
    private TableColumn<Produit, Integer> quantite;

    @FXML
    private TableColumn<Produit, Double> prix;

    @FXML
    private TableColumn<Produit, Button> supprimer;

    @FXML
    private TableColumn<Produit, Button> modifier;

    // Add your methods here
}
