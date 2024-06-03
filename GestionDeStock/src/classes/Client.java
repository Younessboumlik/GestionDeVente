package classes;

import java.sql.Connection;

import application.ConnectToDB;
import controller.AjouterCommandeController;
import controller.ClientsuppmodifController;
import controller.CommandeDunClientController;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Client{
	public int id_client;
	public String nom;
	public String prenom ;
	public String adresse;
	public int telephone;
	public Button suppbutton;
	public Button modifbutton;
	public CheckBox checkbox;
	
	CheckBox checkforcommands;
	public CheckBox getCheckforcommands() {
		return checkforcommands;
	}
	public void  setCheckforcommands(CheckBox checkforcommands) {
		this.checkforcommands = checkforcommands;
		this.checkforcommands.setOnAction(event->affichecommande());
	}
	public Client(int id,String nom,String prenom,String adress,int tele) {
		this.id_client = id;
		this.nom = nom;
		this.suppbutton = new Button("supprimer");
		this.suppbutton.setOnAction(event -> delet());
		this.suppbutton.setStyle("-fx-background-color:#cc0202;-fx-text-fill:white;");
		Image image = new Image("images/delete.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(16);
		imageView.setFitHeight(16);
		this.suppbutton.setGraphic(imageView);
		this.modifbutton = new Button("modifier");
		this.modifbutton.setStyle("-fx-background-color:blue;-fx-text-fill:white;");
		Image imagemod = new Image("imges/pen.png");
		ImageView imageViewmod = new ImageView(imagemod);
		imageViewmod.setFitWidth(16);
		imageViewmod.setFitHeight(16);
		this.modifbutton.setGraphic(imageViewmod);
		this.modifbutton.setOnAction(event -> modifie());
		
		this.prenom = prenom;
		this.adresse = adress;
		this.telephone = tele;
	}
	public CheckBox getCheckbox() {
		return checkbox;
	}
	public void  setCheckbox(CheckBox checkbox) {
		this.checkbox = checkbox;
		this.checkbox.setOnAction(event->checkdecheck());
	}
	public int getId_client() {
		return id_client;
	}
	public void  setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getNom() {
		return nom;
	}
	public void  setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void  setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public Button getSuppbutton() {
		return suppbutton;
	}
	public void  setSuppbutton(Button suppbutton) {
		this.suppbutton = suppbutton;
	}
	public Button getModifbutton() {
		return modifbutton;
	}
	public void  setModifbutton(Button modifbutton) {
		this.modifbutton = modifbutton;
	}
	public void  setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getTelephone() {
		return telephone;
	}
	public void  setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public void  modifie() {
		ClientsuppmodifController.setvaluesontextfield(this);
	}
	public void  delet() {
		 Connection connection = ConnectToDB.connectionDB();
		ConnectToDB.delete(connection, "client","numeroclient", this.id_client);
		ClientsuppmodifController.refreshClient();
	}
	public void  checkdecheck() {
		if (this.checkbox.isSelected()) {
			AjouterCommandeController.checkboxIsSelected(true,this.id_client);
		}
		else {
			AjouterCommandeController.checkboxIsSelected(false,this.id_client);

		}
		}
	public void  affichecommande() {
		if (checkforcommands.isSelected()) {
			CommandeDunClientController.checkboxIsSelected(true, id_client);
		}
		else {
			CommandeDunClientController.checkboxIsSelected(false, id_client);
		}
	}

	}

	
	
