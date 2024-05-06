package application;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SampleController implements Initializable{
	
	static SampleController  controller;
	
	public SampleController() {
		controller = this;
	}
	
	public static SampleController  returnController() {
		return controller;
	}
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
    @FXML
    private TableColumn<Facture, Integer> numerofacture;

    @FXML
    private TableColumn<Facture, LocalDate> datefacture;

    @FXML
    private TableColumn<Facture, Float> montant;

    @FXML
    public TableView<Facture> tableventes;
    
  

    
    
    
 
    
    
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

    	ArrayList <Facture> factures = new ArrayList<Facture>();

    	ArrayList<String> list = ConnectToDB.getData(connexion, "facture", "numeroFacture");
    	ArrayList<String> list2 = ConnectToDB.getData(connexion, "facture", "dateFacture");
    	ArrayList<String> list3 = ConnectToDB.getData(connexion, "facture", "montant");
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	
    	
    	for (int i = 0; i < list.size(); i++) {
    		Facture facture = new Facture(Integer.parseInt(list.get(i)),LocalDate.parse(list2.get(i)),Float.parseFloat(list3.get(i)));
    		listFacture.add(facture);
    		
    	}

    	return listFacture;
    	
//        listFacture.add(new Facture(1, date1, 4.4f));

    }
    
//    @FXML
//    private TableColumn<Facture, Void> deleteButtonColumn;

    
//    permer de refrechir le tableau des factures
    public static void refreshfacture() {

    	controller = SampleController.returnController();
    	controller.tableventes.setItems(getfacturedata());
    }
//    permet d'initialiser le tableau des factures
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
//		TableColumn<Facture,Button> deletebutton = new TableColumn<Facture,Button>("Supprimer");
		TableColumn<Facture, Button> deletebutton = new TableColumn<>("Supprimer");
		
		
		numerofacture.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("numeroFacture"));
		datefacture.setCellValueFactory(new PropertyValueFactory<Facture,LocalDate>("dateFacture"));
		montant.setCellValueFactory(new PropertyValueFactory<Facture,Float>("montant"));

//		deletebutton.setCellValueFactory(new PropertyValueFactory<Facture,String>("deletebutton"));
		deletebutton.setCellValueFactory(new PropertyValueFactory<Facture,Button>("deletebutton"));
		
		tableventes.getColumns().add(deletebutton);
		
		tableventes.setItems(getfacturedata());
		
		
	}
	
    @FXML
    void toCsv(ActionEvent event) {
		
    	FileWriter w;
		try {
			w = new FileWriter("C:\\Users\\YOUNESS\\Desktop\\studies\\ENSA\\IID\\S2\\Java\\factures.csv");
	    	BufferedWriter w2 = new BufferedWriter(w);
	    	
	    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
	    	
//	    	w2.write();
	    	for (Facture facture : getfacturedata()) {
	    		String value = Integer.toString(facture.numeroFacture) + ',' +facture.dateFacture.format(formatter) + ',' + facture.montant ;
	    		w2.write(value);
	    		System.out.println(facture.numeroFacture);
	    		w2.newLine();
	    	}
	    	
	        w2.flush();
	        w2.close();
	        
	        System.out.println("Le fichiers est cree avec succes");
	   

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
	}

}
