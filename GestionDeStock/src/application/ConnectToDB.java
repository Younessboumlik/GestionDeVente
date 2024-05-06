package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.control.Button;

import java.sql.*;
public class ConnectToDB {
	

//	etablir une connection a la db.
	public static Connection connectionDB() {

		String url = "jdbc:mysql://localhost:3306/gestioncommande";
		String user = "root";
		String password = "12345678";
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Connextion reussite...");
		return connection;	
	
	}
	
//	prendre des donnes d'une collone d'un tableau preciser a partir d'une connection.
	
	public static ArrayList<String> getData(Connection connection,String table,String column) {
		
		try {
			Statement statement = connection.createStatement();
			
			String request = "SELECT * FROM " + table +" ;";
			
			
			ResultSet result = statement.executeQuery(request);
			
			ArrayList <String> results = new ArrayList<String>();
            
			while (result.next()) {
			    results.add(result.getString(column));
			}
			
			return results;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void insertClientData(Connection connection,Client client) {
	  try {
		  String query = "insert into client(nom,prenom,adresse,telephone) values "
		  		+ "(?,?,?,?);";
		  PreparedStatement prepare = connection.prepareStatement(query);
		 
		  prepare.setString(1, client.getNom());
		  prepare.setString(2, client.getPrenom());
		  prepare.setString(3, client.getAdresse());
		  prepare.setInt(4, client.getTelephone());
		  prepare.execute();
		  
		  
	  }
	  catch (SQLException e){
		  e.printStackTrace();
	  }
	  }
	
public static ResultSet data(Connection connection,String table,String condition,String value){
	
	try {
		Statement statement;
		statement = connection.createStatement();
	 System.out.println(table);
	 System.out.println(condition);
	 System.out.println(value);
	
	
		return statement.executeQuery("select * from "+ table +" where " + condition + " = '"+ value + "' ;");
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}
public static void modifieuser(Connection connection,Client client) {
	

	try {

		 PreparedStatement prepare = connection.prepareStatement("Update client set nom = ? ,prenom = ? ,adresse = ? ,telephone = ? where numeroclient = ?");
		  prepare.setString(1, client.getNom());
		  prepare.setString(2, client.getPrenom());
		  prepare.setString(3, client.getAdresse());
		  prepare.setInt(4, client.getTelephone());
		  prepare.setInt(5, client.getId_client());
		  prepare.execute();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static ResultSet selecttous(Connection connection,String Table) {
	try {
		Statement statement;
		statement = connection.createStatement();
	
		return statement.executeQuery("select * from "+ Table );
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
}


	

	
	public static void delete(Connection connection, String table,String columnid,Integer ind) {
		try {
			Statement statement = connection.createStatement();
			
			String request = "DELETE FROM " + table +" WHERE " + columnid + " = "  +ind ;
			
			statement.executeUpdate(request);
			
			System.out.println("Suppresion reussite.");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
//	public static void main(String[] args) {
//		Connection connexion = ConnectToDB.connectionDB();
//		getData(connexion,"facture","montant");
//	}
	

}
