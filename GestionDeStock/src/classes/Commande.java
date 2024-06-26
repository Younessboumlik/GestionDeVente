package classes;


import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import application.ConnectToDB;
import controller.AjouterFactureController;
import controller.AjouterLivraisonController;
import controller.FactureToCommandeController;
import controller.LivraisonToCommandeController;
import controller.produitDunecommandeController;
import controller.supmodifcommandecontroller;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Commande {
 public int numerocommande;
 public LocalDate datecomande;
 public int num_client;
 public CheckBox check;
 public CheckBox checkForLivraison;

 public CheckBox checkforproducts;
 
 public CheckBox checkcommandetofacture;
 public CheckBox checkcommandetolivraison;

 public ArrayList<Produit> list_produit;
 public Button suppbutton;
 public Button modifbutton;
 public CheckBox checkboxProduitpourcommande;
public CheckBox getCheckboxProduitpourcommande() {
	return checkboxProduitpourcommande;
}
public void  setCheckboxProduitpourcommande(CheckBox checkboxProduitpourcommande) {
	this.checkboxProduitpourcommande = checkboxProduitpourcommande;
	this.checkboxProduitpourcommande.setOnAction(event ->afichierproduitdecommande());
}
public Button getSuppbutton() {
	return suppbutton;
}
public void  setSuppbutton(Button suppbutton) {
	this.suppbutton = suppbutton;
	this.suppbutton.setOnAction(event ->supprimercommmande());
	this.suppbutton.setStyle("-fx-background-color:#cc0202;-fx-text-fill:white;");
	Image image = new Image("images/delete.png");
	ImageView imageView = new ImageView(image);
	imageView.setFitWidth(16);
	imageView.setFitHeight(16);
	this.suppbutton.setGraphic(imageView);
}
public Button getModifbutton() {
	return modifbutton;
}
public void  setModifbutton(Button modifbutton) {
	this.modifbutton = modifbutton;
	this.modifbutton.setStyle("-fx-background-color:blue;-fx-text-fill:white;");
	Image imagemod = new Image("imges/pen.png");
	ImageView imageViewmod = new ImageView(imagemod);
	imageViewmod.setFitWidth(16);
	imageViewmod.setFitHeight(16);
	this.modifbutton.setGraphic(imageViewmod);
	this.modifbutton.setOnAction(event ->setinfotomodif());
}
public Commande(int numerocommande, LocalDate datecomande, int num_client){
	this.numerocommande = numerocommande;
	this.datecomande = datecomande;
	this.num_client = num_client;

	this.check = new CheckBox();

	this.check.setOnAction(event -> AjouterFactureController.disableCheckboxes(this));

	this.check.setOnAction(event -> AjouterFactureController.disableCheckboxes(this));
	this.checkForLivraison = new CheckBox();
	this.checkForLivraison.setOnAction(event -> AjouterLivraisonController.disableCheckboxes(this));
	
	this.checkcommandetofacture = new CheckBox();
	this.checkcommandetofacture.setOnAction(event -> FactureToCommandeController.disableCheckboxes(this));

	this.checkcommandetolivraison = new CheckBox();
	this.checkcommandetolivraison.setOnAction(event -> LivraisonToCommandeController.disableCheckboxes(this));
}
public CheckBox getCheck() {
	return check;
}
public void  setCheck(CheckBox check) {
	this.check = check;
}
public ArrayList<Produit> getList_produit() {
	return list_produit;
}
public void  setList_produit(ArrayList<Produit> list_produit) {
	this.list_produit = list_produit;
}
public int getNumerocommande() {
	return numerocommande;
}
public void  setNumerocommande(int numerocommande) {
	this.numerocommande = numerocommande;
}
public CheckBox getCheckforproducts() {
	return checkforproducts;
}
public void  setCheckforproducts(CheckBox checkforproducts) {
	this.checkforproducts = checkforproducts;
	this.checkforproducts.setOnAction(event ->disablecheckboxes());
}
public LocalDate getDatecomande() {
	return datecomande;
}
public void  setDatecomande(LocalDate datecomande) {
	this.datecomande = datecomande;
}
public int getNum_client() {
	return num_client;
}
public void  setNum_client(int num_client){
	this.num_client = num_client;
}
public void  disablecheckboxes() {
	if (checkforproducts.isSelected()) {
	supmodifcommandecontroller.checkboxIsSelected(true, numerocommande);
	supmodifcommandecontroller.setpruducttotable();
	}
	else {
		supmodifcommandecontroller.checkboxIsSelected(false, numerocommande);
		supmodifcommandecontroller.detruiretableau();
	}
}
public void  supprimercommmande() {
	Connection connection = ConnectToDB.connectionDB();
	ConnectToDB.supprimercommande(connection, this);
	supmodifcommandecontroller.refresh();
}
public void  setinfotomodif() {
	supmodifcommandecontroller .setinfotomodifcommande(this);
}
public CheckBox getCheckForLivraison() {
	return checkForLivraison;
}
public void  setCheckForLivraison(CheckBox checkForLivraison) {
	this.checkForLivraison = checkForLivraison;
}
public void  afichierproduitdecommande() {
	if(checkboxProduitpourcommande.isSelected()) {
	produitDunecommandeController.checkboxIsSelected(true, numerocommande);
	}
	else {
		produitDunecommandeController.checkboxIsSelected(false, numerocommande);
	}
}

public CheckBox getCheckcommandetofacture() {
	return checkcommandetofacture;
}
public void  setCheckcommandetofacture(CheckBox checkcommandetofacture) {
	this.checkcommandetofacture = checkcommandetofacture;
}
public CheckBox getCheckcommandetolivraison() {
	return checkcommandetolivraison;
}
public void  setCheckcommandetolivraison(CheckBox checkcommandetolivraison) {
	this.checkcommandetolivraison = checkcommandetolivraison;
}



 

}
