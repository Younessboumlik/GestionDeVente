package application;


import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class Commande {
 int numerocommande;
 LocalDate datecomande;
 int num_client;
 CheckBox check;
 CheckBox checkForLivraison;

 CheckBox checkforproducts;
 
 CheckBox checkcommandetofacture;
 CheckBox checkcommandetolivraison;

 ArrayList<Produit> list_produit;
 Button suppbutton;
 Button modifbutton;
public Button getSuppbutton() {
	return suppbutton;
}
public void setSuppbutton(Button suppbutton) {
	this.suppbutton = suppbutton;
	this.suppbutton.setOnAction(event ->supprimercommmande());
}
public Button getModifbutton() {
	return modifbutton;
}
public void setModifbutton(Button modifbutton) {
	this.modifbutton = modifbutton;
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
public void setCheck(CheckBox check) {
	this.check = check;
}
public ArrayList<Produit> getList_produit() {
	return list_produit;
}
public void setList_produit(ArrayList<Produit> list_produit) {
	this.list_produit = list_produit;
}
public int getNumerocommande() {
	return numerocommande;
}
public void setNumerocommande(int numerocommande) {
	this.numerocommande = numerocommande;
}
public CheckBox getCheckforproducts() {
	return checkforproducts;
}
public void setCheckforproducts(CheckBox checkforproducts) {
	this.checkforproducts = checkforproducts;
	this.checkforproducts.setOnAction(event ->disablecheckboxes());
}
public LocalDate getDatecomande() {
	return datecomande;
}
public void setDatecomande(LocalDate datecomande) {
	this.datecomande = datecomande;
}
public int getNum_client() {
	return num_client;
}
public void setNum_client(int num_client){
	this.num_client = num_client;
}
void disablecheckboxes() {
	if (checkforproducts.isSelected()) {
	supmodifcommandecontroller.checkboxIsSelected(true, numerocommande);
	supmodifcommandecontroller.setpruducttotable();
	}
	else {
		supmodifcommandecontroller.checkboxIsSelected(false, numerocommande);
		supmodifcommandecontroller.detruiretableau();
	}
}
void supprimercommmande() {
	Connection connection = ConnectToDB.connectionDB();
	ConnectToDB.supprimercommande(connection, this);
	supmodifcommandecontroller.refresh();
}
void setinfotomodif() {
	supmodifcommandecontroller .setinfotomodifcommande(this);
}
public CheckBox getCheckForLivraison() {
	return checkForLivraison;
}
public void setCheckForLivraison(CheckBox checkForLivraison) {
	this.checkForLivraison = checkForLivraison;
}
public CheckBox getCheckcommandetofacture() {
	return checkcommandetofacture;
}
public void setCheckcommandetofacture(CheckBox checkcommandetofacture) {
	this.checkcommandetofacture = checkcommandetofacture;
}
public CheckBox getCheckcommandetolivraison() {
	return checkcommandetolivraison;
}
public void setCheckcommandetolivraison(CheckBox checkcommandetolivraison) {
	this.checkcommandetolivraison = checkcommandetolivraison;
}


 

}
