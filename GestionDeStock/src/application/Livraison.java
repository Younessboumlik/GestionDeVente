package application;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class Livraison {

	int numeroLivraison;
	LocalDate dateLivraison;
	int numeroCommande;

//	Ce button est associe a chaque objet de type Facture, il va nous permet de le supprimer du tableauview.
	Button deletebutton;
	Button modifbutton;
	
	public Livraison(int numeroLivraison, LocalDate dateLivraison,int numeroCommande) {
		this.numeroLivraison = numeroLivraison;
		this.dateLivraison = dateLivraison;
		this.numeroCommande = numeroCommande;
		
//		Le button de suppresion
		this.deletebutton.setText("Delete");
		this.deletebutton.setOnAction(event -> deletefacture());
		this.deletebutton.setStyle("-fx-background-color:#cc0202;-fx-text-fill:white;");
//		Le button de modification
		this.modifbutton = new Button("modifier");
		this.modifbutton.setStyle("-fx-background-color:blue;-fx-text-fill:white;");
		this.modifbutton.setOnAction(event -> modifieclicked());
	}
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
			ConnectToDB.delete(connexion, "facture", "numerofacture", this.numeroLivraison);

			SampleController.refreshfacture();
		}
	}
	
	public void modifieclicked() {
		SampleController.modifierFacture(this);
	}
	public int getNumeroLivraison() {
		return numeroLivraison;
	}
	public void setNumeroLivraison(int numeroLivraison) {
		this.numeroLivraison = numeroLivraison;
	}
	public LocalDate getDateLivraison() {
		return dateLivraison;
	}
	public void setDateLivraison(LocalDate dateLivraison) {
		this.dateLivraison = dateLivraison;
	}
	public int getNumeroCommande() {
		return numeroCommande;
	}
	public void setNumeroCommande(int numeroCommande) {
		this.numeroCommande = numeroCommande;
	}
	public Button getDeletebutton() {
		return deletebutton;
	}
	public void setDeletebutton(Button deletebutton) {
		this.deletebutton = deletebutton;
	}
	public Button getModifbutton() {
		return modifbutton;
	}
	public void setModifbutton(Button modifbutton) {
		this.modifbutton = modifbutton;
	}

}
