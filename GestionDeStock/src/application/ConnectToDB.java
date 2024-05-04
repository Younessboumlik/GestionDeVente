package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public static void getData(Connection connection,String table,String column) {
		
		try {
			Statement statement = connection.createStatement();
			
			String request = "SELECT * FROM " + table +" ;";
			
			
			ResultSet result = statement.executeQuery(request);
	
			while (result.next()) {
			    System.out.println(result.getString(column));
			    // Process columnValue
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection connexion = ConnectToDB.connectionDB();
		getData(connexion,"facture","montant");
	}
	

}
