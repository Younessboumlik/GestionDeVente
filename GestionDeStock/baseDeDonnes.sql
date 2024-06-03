CREATE DATABASE GestionCommande;
use GestionCommande;


CREATE TABLE Client (
    numeroclient INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    adresse VARCHAR(255),
    telephone VARCHAR(15)
);

CREATE TABLE Produits (
    numeroproduit INT AUTO_INCREMENT PRIMARY KEY,
    nomproduit VARCHAR(255),
    quantite INT,
    prix DECIMAL(10,2)
);

CREATE TABLE Commande (
    numerocommande INT AUTO_INCREMENT PRIMARY KEY,
    datecommande DATE,
    numeroclient INT,
    FOREIGN KEY (numeroclient) REFERENCES Client(numeroclient) ON DELETE CASCADE
);

CREATE TABLE Facture (
   numerofacture INT AUTO_INCREMENT PRIMARY KEY,
   datefacture DATE,
   montant DECIMAL(10,2),
   numerocommande INT,
   FOREIGN KEY (numerocommande) REFERENCES Commande(numerocommande) ON DELETE CASCADE
);

CREATE TABLE Livraison (
   numerolivraison INT AUTO_INCREMENT PRIMARY KEY,
   datelivraison DATE,
   numerocommande INT,
   FOREIGN KEY (numerocommande) REFERENCES Commande(numerocommande) ON DELETE CASCADE
);

CREATE TABLE Avoir (
   numeroproduit INT,
   numerocommande INT,
   PRIMARY KEY (numeroproduit, numerocommande),
   FOREIGN KEY (numeroproduit) REFERENCES Produits(numeroproduit) ON DELETE CASCADE,
   FOREIGN KEY (numerocommande) REFERENCES Commande(numerocommande) ON DELETE CASCADE
);
