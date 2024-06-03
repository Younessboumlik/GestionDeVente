package classes;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Optional;

import application.ConnectToDB;
import controller.SupModifFactureController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Facture {

	public int numeroFacture;
	public LocalDate dateFacture;
	public float montant;
//	Ce button est associe a chaque objet de type Facture, il va nous permet de le supprimer du tableauview.
	public Button deletebutton;
	public Button modifbutton;
	public CheckBox check;
	
	public int numeroCommande;


	public Facture(int numeroFacture, LocalDate date1, float montant,int numeroCommande) {
		this.numeroFacture = numeroFacture;
		this.dateFacture = date1;
		this.montant = montant;
		
		
		this.numeroCommande = numeroCommande;
		
//		Le button de suppresion
		this.deletebutton = new Button();
		this.deletebutton.setText("Delete");
		this.deletebutton.setOnAction(event -> deletefacture());
		this.deletebutton.setStyle("-fx-background-color:#cc0202;-fx-text-fill:white;");
		Image image = new Image("images/delete.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(16);
		imageView.setFitHeight(16);
		this.deletebutton.setGraphic(imageView);
		this.modifbutton = new Button("modifier");
		this.modifbutton.setStyle("-fx-background-color:blue;-fx-text-fill:white;");
		Image imagemod = new Image("imges/pen.png");
		ImageView imageViewmod = new ImageView(imagemod);
		imageViewmod.setFitWidth(16);
		imageViewmod.setFitHeight(16);
		this.modifbutton.setGraphic(imageViewmod);
		this.modifbutton.setOnAction(event -> modifieclicked());
		
		this.check = new CheckBox();
	}

//	l'evenement pour supprimer une facture.

	public void  deletefacture() {

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

			SupModifFactureController.refreshfacture();
		}
	}
	
	public void  modifieclicked() {
		SupModifFactureController.modifierFacture(this);
	}

	
//	getters et setters.
	public int getNumeroFacture() {
		return numeroFacture;
	}


	public void  setNumeroFacture(int numeroFacture) {
		this.numeroFacture = numeroFacture;
	}


	public LocalDate getDateFacture() {
		return dateFacture;
	}


	public void  setDateFacture(LocalDate dateFacture) {
		this.dateFacture = dateFacture;
	}


	public float getMontant() {
		return montant;
	}


	public void  setMontant(float montant) {
		this.montant = montant;
	}


	public Button getDeletebutton() {
		return deletebutton;
	}


	public void  setDeletebutton(Button deletebutton) {
		this.deletebutton = deletebutton;
	}

	public int getNumeroCommande() {
		return numeroCommande;
	}

	public void  setNumeroCommande(int numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	public Button getModifbutton() {
		return modifbutton;
	}

	public void  setModifbutton(Button modifbutton) {
		this.modifbutton = modifbutton;
	}
	
	
	

}