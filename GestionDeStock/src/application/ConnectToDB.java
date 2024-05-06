package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectToDB {
	

//	etablir une connection a la db.
	public static Connection connectionDB() {

		String url = "jdbc:mysql://localhost:3306/gestioncommande";
		String user = "root";
		String password = "";
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
		
		System.out.println("Connextion reussite");
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
