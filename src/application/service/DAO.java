package application.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import application.entite.Client;
import application.entite.Particulier;
import application.entite.Professionnel;

public class DAO {
	
	Connection connection;
	
	public DAO()
	{
		this.connection = getConnection();
	}
	
	private Connection getConnection()
	{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://127.0.0.1:3306/reservationresto";
			String username = "root";
			String password = "";
			Class.forName(driver);
			
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connecté");
			return connection;
		}
		catch(Exception e) {System.out.println(e);}
		return null;
	}
	
	public ArrayList<Client> getAllClient()
	{
		try {
			ArrayList<Client> lstClientRes = new ArrayList<Client>();
			Statement statement = this.connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from client");
			
			while(resultSet.next())
			{
				Client clientRes = null;
				if(resultSet.getString("type").equals("entreprise"))
				{
					clientRes = new Professionnel(resultSet.getInt("id_client"), resultSet.getString("tel"), resultSet.getString("mail"), resultSet.getString("nom"));					
				}
				else if(resultSet.getString("type").equals("particulier"))
				{
					String nom = resultSet.getString("nom").split(" ")[0];
					String prenom = resultSet.getString("nom").split(" ")[1];
					clientRes = new Particulier(resultSet.getInt("id_client"), resultSet.getString("tel"), resultSet.getString("mail"), nom, prenom);					
				}
				
				lstClientRes.add(clientRes);
			}
			
			return lstClientRes;			
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}		
		return null;			
	}
	
	public void addClient(Client clientAdd)
	{
		try {
			String nom = null;
			String type = null;
			if(clientAdd instanceof Professionnel)
			{
				nom = ((Professionnel) clientAdd).getNomsociete();
				type = "entreprise";
			}
			else if(clientAdd instanceof Particulier)
			{
				nom = ((Particulier) clientAdd).getNom() + " " + ((Particulier) clientAdd).getPrenom();
				type = "particulier";
			}
			PreparedStatement insertQuery = this.connection.prepareStatement("INSERT INTO post VALUES (?, ?, ?, ?)");
			insertQuery.setString(1, clientAdd.getTel());
			insertQuery.setString(2, clientAdd.getMail());
			insertQuery.setString(3, nom);
			insertQuery.setString(4, type);
			insertQuery.executeUpdate();
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}	
	}
	
	public void deleteClient(int idClient)
	{
		try {
			PreparedStatement deleteQuery = this.connection.prepareStatement("DELETE FROM client WHERE id = ? ");
			deleteQuery.setInt(1, idClient);
			deleteQuery.executeUpdate();
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}
	}

}
