# README.md

## Gestion de Commandes - JavaFX Application

### Réalisé par
- Youness Boumlik
- Abdellah Boulidam

### Encadré par
- M. GHERABI

## Sommaire

- [Introduction](#introduction)
- [Conception](#conception)
- [Hiérarchie de l'application](#hiérarchie-de-lapplication)
- [Interface Graphique](#interface-graphique)
- [Fonctionnalités](#fonctionnalités)
- [APIs Utilisées](#apis-utilisées)
- [Documentation d'installation](#documentation-dinstallation)
- [Collaboration](#collaboration)
- [Les Difficultés](#les-difficultés)
- [Conclusion](#conclusion)

## Introduction

Ce projet consiste en une application de bureau développée avec JavaFX, visant à optimiser la gestion des ventes. Elle permet de gérer divers aspects essentiels tels que les factures, les livraisons, les commandes, les clients et les produits. L'application offre une interface intuitive et facilite l'accès et la manipulation des données pour les utilisateurs.

### Objectifs

- Améliorer le processus de vente.
- Assurer un suivi rigoureux des transactions et des livraisons.
- Maintenir une base de données client précise et à jour.
- Fournir un outil puissant et flexible pour les entreprises.

### Fonctionnalités Clés

- Gestion des clients, produits, commandes et livraisons.
- Exportation des tableaux au format PDF ou CSV.
- Intégration d'API pour la restauration de mots de passe et les notifications par SMS.

## Conception

### Modèle Logique de Données (MLD)

- **Client**(numeroclient, nom, prenom, adresse, telephone)
- **Produits**(numeroproduit, nomproduit, quantite, prix)
- **Commande**(numerocommande, datecommande, #numeroclient)
- **Facture**(numerofacture, datefacture, montant, #numerocommande)
- **Livraison**(numerolivraison, datelivraison, #numerocommande)

## Hiérarchie de l'application

- **Interfaces Graphiques**: Contient les fichiers FXML, images, icônes et stylesheets.
- **Classes Java**: Contient les classes définies dans le diagramme de classes.
- **Contrôleurs**: Contient les contrôleurs pour les interfaces graphiques.
- **Dépendances**: Contient les fichiers .jar utilisés dans le projet.

## Interface Graphique

### Principales Interfaces

- **Gestion des Clients**: Saisie des informations clients et ajout des nouveaux clients.
- **Gestion des Commandes**: Ajout, modification et suppression des commandes.
- **Tableau de Bord**: Visualisation des statistiques avec des graphiques.
- **Sécurité**: Interfaces de login, réinitialisation de mot de passe et vérification des utilisateurs.

  ![image](https://github.com/Younessboumlik/GestionDeVente/assets/104656844/5a27c9f6-0fc9-474b-9f3e-d4854e816305)
  ![image](https://github.com/Younessboumlik/GestionDeVente/assets/104656844/8f7fb0cd-1a96-4ff7-92f2-d871c4c7776b)
  


## Fonctionnalités

- **Exportation**: Exportation des données des tableaux en fichiers CSV ou PDF.
- **Notifications SMS**: Envoi de SMS pour notifier les responsables de l'état des stocks.
- **Récupération de Mot de Passe**: Utilisation d'API pour envoyer des codes de vérification par email.

## APIs Utilisées

- **Twilio API**: Pour l'envoi de SMS automatiques.
- **Mailtrap API**: Pour la gestion de la récupération des mots de passe par email.
- **Jasypt**: Pour le chiffrement des données sensibles.

### Exemple de Code - Exportation CSV

```java
public void exportToCSV(TableView tableView, File file) {
    try (PrintWriter writer = new PrintWriter(file)) {
        for (Object row : tableView.getItems()) {
            writer.println(row.toString());
        }
    } catch (FileNotFoundException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Error writing to file.");
        alert.showAndWait();
    }
}
```

## Documentation d'installation

1. Ajouter les fichiers `.jar` du dossier `dependencies` dans le classpath.
2. Ajouter le jar de Font Awesome pour Scene Builder.
3. Importer le fichier `baseDeDonnes.sql` pour configurer la base de données.

## Collaboration

Nous avons utilisé GitHub pour notre travail d'équipe, ce qui nous a permis de partager efficacement notre code, de suivre les modifications et de résoudre les problèmes ensemble. Le lien vers notre repository GitHub est [ici](https://github.com/Younessboumlik/GestionDeVente).

## Les Difficultés

- **Gestion des Dépendances**: Problèmes rencontrés lors de l'ajout de bibliothèques externes dans Eclipse.
- **API de Courrier Électronique**: Difficultés avec le port 25, nécessitant de changer de service de courrier électronique.

## Conclusion

Ce projet a été une expérience enrichissante, nous permettant d'appliquer nos connaissances en Java, de travailler avec JavaFX et Scene Builder, et de manipuler des fichiers CSV et PDF. Nous avons aussi appris à utiliser GitHub pour une collaboration efficace et à surmonter des défis techniques.

Nous remercions notre professeur M. GHERABI pour cette opportunité et sommes impatients de mettre en pratique ces compétences dans nos futurs projets. Merci pour cette expérience précieuse.

---

Pour plus de détails, veuillez consulter le [Rapport de Projet](RapportProjetJava.pdf).
