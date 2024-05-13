package application;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.CheckBox;

public class Commande {
 int numerocommande;
 LocalDate datecomande;
 int num_client;
 CheckBox check;
 ArrayList<Produit> list_produit;
public Commande(int numerocommande, LocalDate datecomande, int num_client){
	this.numerocommande = numerocommande;
	this.datecomande = datecomande;
	this.num_client = num_client;
	this.check = new CheckBox();
	this.check.setOnAction(event -> AjouterFactureController.disableCheckboxes(this));
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
public LocalDate getDatecomande() {
	return datecomande;
}
public void setDatecomande(LocalDate datecomande) {
	this.datecomande = datecomande;
}
public int getNum_client() {
	return num_client;
}
public void setNum_client(int num_client) {
	this.num_client = num_client;
}

 
}
