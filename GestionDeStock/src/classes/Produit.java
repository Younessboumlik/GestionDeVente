package classes;
import java.sql.Connection;

import application.ConnectToDB;
import controller.AjouterCommandeController;
import controller.supmodifcommandecontroller;
import controller.supmodifproduitcontroller;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Produit{
   public int numProduit;
   public String nomProduit;
   public int QuantiteProduit;
   public double prix;
   public CheckBox checkProduit;
   public int Quantitechoisie;
   public Button suppproduittinavoir;
   public Button modifproduitinavoir;
   public Button supprimerprd;
   public Button modifierprd;
   public Button getSupprimerprd() {
	return supprimerprd;
}
public void  setSupprimerprd(Button supprimerprd) {
	this.supprimerprd = supprimerprd;
	supprimerprd.setOnAction(event->supprimerproduit());
	this.supprimerprd.setStyle("-fx-background-color:#cc0202;-fx-text-fill:white;");
	Image image = new Image("images/delete.png");
	ImageView imageView = new ImageView(image);
	imageView.setFitWidth(16);
	imageView.setFitHeight(16);
	this.supprimerprd.setGraphic(imageView);
}
public Button getModifierprd() {
	return modifierprd;
}
public void  setModifierprd(Button modifierprd) {
	this.modifierprd = modifierprd;
	this.modifierprd.setOnAction(event->modifierproduit());
	this.modifierprd.setStyle("-fx-background-color:blue;-fx-text-fill:white;");
	Image imagemod = new Image("imges/pen.png");
	ImageView imageViewmod = new ImageView(imagemod);
	imageViewmod.setFitWidth(16);
	imageViewmod.setFitHeight(16);
	this.modifierprd.setGraphic(imageViewmod);
}
public Button getSuppproduittinavoir() {
	return suppproduittinavoir;
}
public void  setSuppproduittinavoir(Button suppproduittinavoir) {
	this.suppproduittinavoir = suppproduittinavoir;
	this.suppproduittinavoir.setOnAction(event->suppproduitinavoir());
	this.suppproduittinavoir.setStyle("-fx-background-color:#cc0202;-fx-text-fill:white;");
	Image image = new Image("images/delete.png");
	ImageView imageView = new ImageView(image);
	imageView.setFitWidth(16);
	imageView.setFitHeight(16);
	this.suppproduittinavoir.setGraphic(imageView);
}
public Button getModifproduitinavoir() {
	return modifproduitinavoir;
}
public void  setModifproduitinavoir(Button modifproduitinavoir) {
	this.modifproduitinavoir = modifproduitinavoir;
	this.modifproduitinavoir.setOnAction(event->modifierproduitinavoir());
	this.modifproduitinavoir.setStyle("-fx-background-color:blue;-fx-text-fill:white;");
	Image imagemod = new Image("imges/pen.png");
	ImageView imageViewmod = new ImageView(imagemod);
	imageViewmod.setFitWidth(16);
	imageViewmod.setFitHeight(16);
	this.modifproduitinavoir.setGraphic(imageViewmod);
	

}

   public CheckBox getCheckProduit() {
	return checkProduit;
}
public void  setCheckProduit(CheckBox checkProduit) {
	this.checkProduit = checkProduit;
	this.checkProduit.setOnAction(event->unablequatitetext());
}
public TextField getQuantitetext() {
	return quantitetext;
}
public int getQuantitechoisie() {
	return Quantitechoisie;
}
public void  setQuantitechoisie(int quantitechoisie) {
	Quantitechoisie = quantitechoisie;
}
public void  setQuantitetext(TextField quantitetext) {
	this.quantitetext = quantitetext;
	this.quantitetext.setDisable(true);
}
TextField quantitetext;
   public Produit(int numProduit,String nomProduit,int QuantiteProduit,double prix ) {
	   this.numProduit = numProduit;
	   this.nomProduit = nomProduit;
	   this.QuantiteProduit = QuantiteProduit;
	   this.prix = prix;
	   
   }
   public Produit(int numProduit,String nomProduit,int Quantitechoisie) {
	   this.numProduit = numProduit;
	   this.nomProduit = nomProduit;
	   this.Quantitechoisie = Quantitechoisie;
	   
	   
   }
public int getNumProduit() {
	return numProduit;
}
public void  setNumProduit(int numProduit) {
	this.numProduit = numProduit;
}
public String getNomProduit() {
	return nomProduit;
}
public void  setNomProduit(String nomProduit) {
	this.nomProduit = nomProduit;
}
public int getQuantiteProduit() {
	return QuantiteProduit;
}
public void  setQuantiteProduit(int quantiteProduit) {
	QuantiteProduit = quantiteProduit;
}
public double getPrix() {
	return prix;
}
public void  setPrix(double prix) {
	this.prix = prix;
}
public void  unablequatitetext(){
	if (this.checkProduit.isSelected()){
	 AjouterCommandeController.Ajouterproduit(this);
	this.quantitetext.setDisable(false);
	}
	else {
		AjouterCommandeController.removeproduit(null);
	 this.quantitetext.setDisable(true);
	}
}
public void  modifierproduitinavoir() {
	supmodifcommandecontroller.setinfotomodifproduit(this);
}
public void  suppproduitinavoir() {
	supmodifcommandecontroller.suppproduitfromavoir(this.numProduit);
}
public void  supprimerproduit() {
	 Connection connection = ConnectToDB.connectionDB();
		ConnectToDB.delete(connection, "produits","numeroproduit", this.numProduit);
		supmodifproduitcontroller.refreche();
}
public void  modifierproduit() {
	supmodifproduitcontroller.setvaluesontextfield(this);
	
}
   
}
