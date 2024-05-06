package application;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class Facture {

	int numeroFacture;
	LocalDate dateFacture;
	float montant;
//	Ce button est associe a chaque objet de type Facture, il va nous permet de le supprimer du tableauview.
	Button deletebutton;


	public Facture(int numeroFacture, LocalDate date1, float montant) {
		this.numeroFacture = numeroFacture;
		this.dateFacture = date1;
		this.montant = montant;
		
//		Le button de suppresion
		this.deletebutton = new Button();
		this.deletebutton.setText("Delete");
		this.deletebutton.setOnAction(event -> deletefacture());
//		this.deletebutton.sty
	}

//	l'evenement pour supprimer une facture.

	public void deletefacture() {

//		System.out.println(this.montant);
		
//		Un alert pour confirmer la suppresion

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Suppresion de facture");
		alert.setContentText("Tu veux supprimer la facture de maniere definitive?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isEmpty() || result.get() == ButtonType.CANCEL) {
		}
		else if(result.get() == ButtonType.OK) {
			Connection connexion = ConnectToDB.connectionDB();
			ConnectToDB.delete(connexion, "facture", "numerofacture", this.numeroFacture);
		}
	}

	
//	getters et setters.
	public int getNumeroFacture() {
		return numeroFacture;
	}


	public void setNumeroFacture(int numeroFacture) {
		this.numeroFacture = numeroFacture;
	}


	public LocalDate getDateFacture() {
		return dateFacture;
	}


	public void setDateFacture(LocalDate dateFacture) {
		this.dateFacture = dateFacture;
	}


	public float getMontant() {
		return montant;
	}


	public void setMontant(float montant) {
		this.montant = montant;
	}


	public Button getDeletebutton() {
		return deletebutton;
	}


	public void setDeletebutton(Button deletebutton) {
		this.deletebutton = deletebutton;
	}
	
	
	

}