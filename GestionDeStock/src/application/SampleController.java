package application;

import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SampleController {
	
    @FXML
    private TableColumn<Facture, Date> datefacture;

    @FXML
    private TableColumn<Facture, Float> montant;

    @FXML
    private TableColumn<Facture, Integer> numerofacture;

    @FXML
    private TableView<Facture> tableventes;
    
}
