package application;

import java.time.LocalDate;

public class Facture {

	int numeroFacture;
	LocalDate dateFacture;
	float montant;
	
	
	public Facture(int numeroFacture, LocalDate date1, float montant) {
		this.numeroFacture = numeroFacture;
		this.dateFacture = date1;
		this.montant = montant;
	}


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
	
	
	

}