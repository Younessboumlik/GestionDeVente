package application;
import javafx.scene.control.CheckBox;

import javafx.scene.control.TextField;
public class Produit{
   int numProduit;
   String nomProduit;
   int QuantiteProduit;
   double prix;
   CheckBox checkProduit;
   public CheckBox getCheckProduit() {
	return checkProduit;
}
public void setCheckProduit(CheckBox checkProduit) {
	this.checkProduit = checkProduit;
	this.checkProduit.setOnAction(event->unablequatitetext());
}
public TextField getQuantitetext() {
	return quantitetext;
}
public void setQuantitetext(TextField quantitetext) {
	this.quantitetext = quantitetext;
	this.quantitetext.setDisable(true);
}
TextField quantitetext;
   public Produit(int numProduit,String nomProduit,int QuantiteProduit,double prix) {
	   this.numProduit = numProduit;
	   this.nomProduit = nomProduit;
	   this.QuantiteProduit = QuantiteProduit;
	   this.prix = prix;
	   
   }
public int getNumProduit() {
	return numProduit;
}
public void setNumProduit(int numProduit) {
	this.numProduit = numProduit;
}
public String getNomProduit() {
	return nomProduit;
}
public void setNomProduit(String nomProduit) {
	this.nomProduit = nomProduit;
}
public int getQuantiteProduit() {
	return QuantiteProduit;
}
public void setQuantiteProduit(int quantiteProduit) {
	QuantiteProduit = quantiteProduit;
}
public double getPrix() {
	return prix;
}
public void setPrix(double prix) {
	this.prix = prix;
}
void unablequatitetext(){
	if (this.checkProduit.isSelected()){
	 AjouterCommandeController.Ajouterproduit(this);
	this.quantitetext.setDisable(false);
	}
	else {
		AjouterCommandeController.Ajouterproduit(this);
	 this.quantitetext.setDisable(true);
	}
}
   
}
