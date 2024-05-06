package application;


import java.net.URL;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SampleController implements Initializable{
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
    @FXML
    private TableColumn<Facture, Integer> numerofacture;

    @FXML
    private TableColumn<Facture, LocalDate> datefacture;

    @FXML
    private TableColumn<Facture, Float> montant;

    @FXML
    private TableView<Facture> tableventes;
    
  

    
    
    
 
    
    
//    listFacture.add(new Facture(1, date1, 4.4f));
    
//    new Facture(1, date1, 4.4f),
//    new Facture(2, date2, 4.2f)
    
    
    public static void main(String[] args) {
//        LocalDate  date1 = LocalDate.parse("2024-06-04");
//        LocalDate  date2 = LocalDate.parse("2024-04-02");
    

    	

      
    }
    
    public static ObservableList <Facture> getfacturedata() {
    	
    	ObservableList<Facture> listFacture = FXCollections.observableArrayList(

    	    	);
    	
    	Connection connexion = ConnectToDB.connectionDB();

    	ArrayList <Facture> factures = new ArrayList<Facture>();

    	ArrayList<String> list = ConnectToDB.getData(connexion, "facture", "numeroFacture");
    	ArrayList<String> list2 = ConnectToDB.getData(connexion, "facture", "dateFacture");
    	ArrayList<String> list3 = ConnectToDB.getData(connexion, "facture", "montant");
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	
    	
    	for (int i = 0;i < list.size();i++) {
    		Facture facture = new Facture(Integer.parseInt(list.get(i)),LocalDate.parse(list2.get(i)),Float.parseFloat(list3.get(i)));
    		listFacture.add(facture);
    	}
    	
    	return listFacture;
    	
//        listFacture.add(new Facture(1, date1, 4.4f));

    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		numerofacture.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("numeroFacture"));
		datefacture.setCellValueFactory(new PropertyValueFactory<Facture,LocalDate>("dateFacture"));
		montant.setCellValueFactory(new PropertyValueFactory<Facture,Float>("montant"));

		tableventes.setItems(getfacturedata());
	}

}
