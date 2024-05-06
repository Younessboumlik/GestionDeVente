package application;

import java.sql.Connection;

import javafx.scene.control.Button;


public class Client{
	private int id_client;
	private String nom;
	private String prenom ;
	private String adresse;
	private int telephone;
	private Button suppbutton;
	private Button modifbutton;
	public Client(int id,String nom,String prenom,String adress,int tele) {
		this.id_client = id;
		this.nom = nom;
		this.suppbutton = new Button("supprimer");
		this.suppbutton.setOnAction(event -> delet());
		this.suppbutton.setStyle("-fx-background-color:red");
		this.modifbutton = new Button("modifier");
		this.modifbutton.setStyle("-fx-background-color:blue");
		this.modifbutton.setOnAction(event -> modifie());
		
		this.prenom = prenom;
		this.adresse = adress;
		this.telephone = tele;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public Button getSuppbutton() {
		return suppbutton;
	}
	public void setSuppbutton(Button suppbutton) {
		this.suppbutton = suppbutton;
	}
	public Button getModifbutton() {
		return modifbutton;
	}
	public void setModifbutton(Button modifbutton) {
		this.modifbutton = modifbutton;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public void modifie() {
		ClientsuppmodifController.setvaluesontextfield(this);
	}
	public void delet() {
		 Connection connection = ConnectToDB.connectionDB();
		ConnectToDB.delete(connection, "client","numeroclient", this.id_client);
		ClientsuppmodifController.refreshClient();
	}
	
	
}