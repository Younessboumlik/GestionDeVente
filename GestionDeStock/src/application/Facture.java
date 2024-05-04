package application;

import java.util.Date;

public class Facture {
	int numeroFacture;
	Date dateFacture;
	float montant;
	
	
	public Facture(int numeroFacture, Date dateFacture, float montant) {
		this.numeroFacture = numeroFacture;
		this.dateFacture = dateFacture;
		this.montant = montant;
	}


	public int getNumeroFacture() {
		return numeroFacture;
	}


	public void setNumeroFacture(int numeroFacture) {
		this.numeroFacture = numeroFacture;
	}


	public Date getDateFacture() {
		return dateFacture;
	}


	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}


	public float getMontant() {
		return montant;
	}


	public void setMontant(float montant) {
		this.montant = montant;
	}
	
	
	

}