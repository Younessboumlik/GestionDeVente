package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SampleController {
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    private TableColumn<Facture, Date> datefacture;

    @FXML
    private TableColumn<Facture, Float> montant;

    @FXML
    private TableColumn<Facture, Integer> numerofacture;

    @FXML
    private TableView<Facture> tableventes;
    
    try {
    Date date1 = formatter.parse("2024-06-04");
    Date date2 = formatter.parse("2024-04-02");
    
    
    
    ObservableList<Facture> listFacture = FXCollections.observableArrayList(
    	    new Facture(1, date1, 4.4f),
    	    new Facture(2, date2, 4.2f)
    	);
//
//    
//    ObservableList<Facture> listFacture = FXCollections.observableArrayList(
//    		new Facture(1,date1,4.4f),
//    		new Facture(2,date2,4.2f)
//    		);
    		
    }
    catch(Exception e) {
    	e.printStackTrace();
    }
    


}
