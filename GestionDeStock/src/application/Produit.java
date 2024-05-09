package application;
import java.awt.Checkbox;
public class Produit{
   int numProduit;
   String nomProduit;
   int QuantiteProduit;
   double prix;
   Checkbox checkProduit;
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
   
}
