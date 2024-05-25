package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ConnectToDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ringprogress.RingProgressIndicator;
import ringprogress.SousClassRingProgressIndicator;

public class MainController implements Initializable{
	@FXML
    private Label livrpourclabel;

    @FXML
    private Label nbrclientlabel;

    @FXML
    private Label nbrcommandeabel;

    @FXML
    private Label nbrfactlabel;

    @FXML
    private Label nbrlivrlabel;

    @FXML
    private Label nbrprdlabel;

    @FXML
    private PieChart pie;
    @FXML
    private VBox leftVbox;
    @FXML
    private Label affeclivrnbr;
    @FXML
    private Label nonaffeclivrnbr;
    @FXML
    private BarChart<String, Double> barchart;
    @FXML
    private Label budgetclone;

    @FXML
    private Label budgetcltwo;
    @FXML
    private Label nameclientone;

    @FXML
    private Label nameclienttwo;
    @FXML
    private HBox Hboxfooter;
	@SuppressWarnings("rawtypes")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("sssssssss");
		Connection connection = ConnectToDB.connectionDB();
		// TODO Auto-generated method stub
		ResultSet result = ConnectToDB.selectnbrproduitgrpbynom(connection);
		ObservableList<PieChart.Data> listPie = FXCollections.observableArrayList();
		try {
			while(result.next()) {
				try {
					listPie.add(new PieChart.Data(result.getString("nomproduit"),result.getInt("count(Quantite_prod)")));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			pie.setData(listPie);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RingProgressIndicator prog = new RingProgressIndicator();
	    prog.setMaxWidth(10);
	    prog.makeIndeterminate();
	    ArrayList<Integer> nbrlivrrlist = ConnectToDB.getlivrasonended(connection);
	    
	    System.out.println(((((double)nbrlivrrlist.get(1))/nbrlivrrlist.get(0))*100));
//	    prog.setProgress();
	    System.out.println("sssssssss");
	    nonaffeclivrnbr.setText(Integer.toString(nbrlivrrlist.get(0)- nbrlivrrlist.get(1)));
	    affeclivrnbr.setText(Integer.toString(nbrlivrrlist.get(1)));
	    leftVbox.getChildren().add(prog);
	    threadForRingProgress threadprogress = new threadForRingProgress(prog,(int)(((double)nbrlivrrlist.get(1)/nbrlivrrlist.get(0))*100));
	    ResultSet res = ConnectToDB.nbrCommandInMounth(connection);
	    XYChart.Series serie = new XYChart.Series();
	    serie.setName("2024");
	    try {
			while(res.next()) {
				serie.getData().add(new XYChart.Data(res.getString("month"), res.getDouble("TOTAL")));
				
			}
			barchart.getData().addAll(serie);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    threadprogress.start();
	    
        ResultSet clientfidel = ConnectToDB.clientfidel(connection);
        try {
        	System.out.println("kkkkkk");
			clientfidel.next();
			nameclientone.setText(clientfidel.getString("nom") + " " + clientfidel.getString("prenom"));
			budgetclone.setText(clientfidel.getString("TotalBudget")+ "$");
			clientfidel.next();
			nameclienttwo.setText(clientfidel.getString("nom") + " " + clientfidel.getString("prenom"));
			budgetcltwo.setText(clientfidel.getString("TotalBudget")+ "$");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ResultSet produitepuiselist = ConnectToDB.produitemptystock(connection);
        try {
			while(produitepuiselist.next()) {
			
			SousClassRingProgressIndicator produitepuise = new SousClassRingProgressIndicator();
			produitepuise.makeIndeterminate();
			try {
				produitepuise.setProgress(produitepuiselist.getInt("quantite"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			VBox Hboxprd = new VBox();
			Hboxprd.getChildren().add(produitepuise);
			Label label = new Label(produitepuiselist.getString("nomproduit"));
			label.setStyle("-fx-alignment:center");
			Hboxprd.getChildren().add(label);
			Hboxfooter.getChildren().add(Hboxprd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ConnectToDB.labelsstats(connection,nbrclientlabel,nbrprdlabel,nbrcommandeabel,nbrlivrlabel,nbrfactlabel);
        
		
	}
	
	

}
