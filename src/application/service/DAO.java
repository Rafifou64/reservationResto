package application.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import application.entite.Client;
import application.entite.Particulier;
import application.entite.Professionnel;
import application.entite.Reservation;
import application.entite.Service;
import application.entite.Tables;

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
	
	//CRUD client
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
	
    public Client getClient(String critere, Object valeur) {
      try {
          Client clientRes = null;
          String query = "SELECT * FROM client WHERE " + critere + " = ?";
          try (PreparedStatement selectQuery = this.connection.prepareStatement(query)) {
              selectQuery.setObject(1, valeur);
              ResultSet resultSet = selectQuery.executeQuery();
  
              while (resultSet.next()) {
                  String type = resultSet.getString("type");
                  if ("entreprise".equals(type)) {
                      clientRes = new Professionnel(resultSet.getInt("id_client"), resultSet.getString("tel"),
                              resultSet.getString("mail"), resultSet.getString("nom"));
                  } else if ("particulier".equals(type)) {
                      String nom = resultSet.getString("nom").split(" ")[0];
                      String prenom = resultSet.getString("nom").split(" ")[1];
                      clientRes = new Particulier(resultSet.getInt("id_client"), resultSet.getString("tel"),
                              resultSet.getString("mail"), nom, prenom);
                  }
              }
          }
  
          return clientRes;
      } catch (Exception e) {
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
					
      PreparedStatement insertQuery = this.connection.prepareStatement("INSERT INTO client (tel, mail, nom, type) VALUES (?, ?, ?, ?)");
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
	
	public void updateClient(Client clientUpdate)
	{
		try {
			String nom = null;
			String type = null;
			if(clientUpdate instanceof Professionnel)
			{
				nom = ((Professionnel) clientUpdate).getNomsociete();
				type = "entreprise";
			}
			else if(clientUpdate instanceof Particulier)
			{
				nom = ((Particulier) clientUpdate).getNom() + " " + ((Particulier) clientUpdate).getPrenom();
				type = "particulier";
			}
			PreparedStatement updateQuery = this.connection.prepareStatement("UPDATE client SET tel = ?, mail = ?, nom = ?, type = ? WHERE id_client = ?");
			updateQuery.setString(1, clientUpdate.getTel());
			updateQuery.setString(2, clientUpdate.getMail());
			updateQuery.setString(3, nom);
			updateQuery.setString(4, type);
			updateQuery.setInt(5, clientUpdate.getId_client());
			updateQuery.executeUpdate();
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}	
	}
	
	public void deleteClient(int idClient)
	{
		try {
			PreparedStatement deleteQuery = this.connection.prepareStatement("DELETE FROM client WHERE id_client = ? ");
			deleteQuery.setInt(1, idClient);
			deleteQuery.executeUpdate();
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}
	}
	
	//CRUD reservation
	public ArrayList<Reservation> getAllReservation()
	{
		try {
			ArrayList<Reservation> lstReservationRes = new ArrayList<Reservation>();
			Statement statement = this.connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from reservation");
			
			while(resultSet.next())
			{
				Client client = this.getClient("id_client", resultSet.getInt("id_client"));
				Service service = this.getServiceById(resultSet.getInt("id_service"));
				ArrayList<Tables> tables = this.getAllTableByIdReservation(resultSet.getInt("id_reservation"));
				
				Reservation reservationRes = new Reservation(resultSet.getInt("id_reservation"), resultSet.getInt("nb_personne"), resultSet.getString("type"), resultSet.getBoolean("is_validated"), resultSet.getDate("date_reservation"), client, service, tables);
				
				lstReservationRes.add(reservationRes);
			}
			
			return lstReservationRes;
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}
		return null;			
	}
	
	public void addReservation(Reservation reservationAdd)
	{
		try {
			PreparedStatement insertQuery = this.connection.prepareStatement("INSERT INTO reservation (nb_personne, type, is_validated, id_client, id_service, date_reservation)VALUES (?, ?, ?, ?, ? , ?)");
			insertQuery.setInt(1, reservationAdd.getNbpersonne());
			insertQuery.setString(2, reservationAdd.getType());
			insertQuery.setBoolean(3, reservationAdd.getIs_validated());
			insertQuery.setInt(4, reservationAdd.getClient().getId_client());
			insertQuery.setInt(5, reservationAdd.getService().getId_service());
			insertQuery.setDate(6, java.sql.Date.valueOf(reservationAdd.getDate_reservation().toString()));

			insertQuery.executeUpdate();
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}	
	}
	
	public void addReservationTables(int idReservation, int idTable)
	{
		try {
			PreparedStatement insertQuery = this.connection.prepareStatement("INSERT INTO reservation_tables (id_tables, id_reservation)VALUES (?, ?)");
			insertQuery.setInt(1, idTable);
			insertQuery.setInt(2, idReservation);

			insertQuery.executeUpdate();
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}	
	}
	
	public void updateReservation(Reservation reservationUpdate)
	{
		try {
			PreparedStatement updateQuery = this.connection.prepareStatement("UPDATE reservation SET nb_personne = ?, type = ?, is_validated = ?, id_client = ?, id_service = ? WHERE id_reservation = ?");
			updateQuery.setInt(1, reservationUpdate.getNbpersonne());
			updateQuery.setString(2, reservationUpdate.getType());
			updateQuery.setBoolean(3, reservationUpdate.getIs_validated());
			updateQuery.setInt(4, reservationUpdate.getClient().getId_client());
			updateQuery.setInt(5, reservationUpdate.getService().getId_service());
			updateQuery.setInt(6, reservationUpdate.getIdReservation());
			updateQuery.executeUpdate();
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}
	}
	
	public void deleteReservation(int idReservation)
	{
		try {
			PreparedStatement deleteQuery = this.connection.prepareStatement("DELETE FROM reservation WHERE id_reservation = ? ");
			deleteQuery.setInt(1, idReservation);
			deleteQuery.executeUpdate();
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}
	}
	
	public void deleteReservationTablesByIdReservation(int idReservation)
	{
		try {
			PreparedStatement deleteQuery = this.connection.prepareStatement("DELETE FROM reservation_tables WHERE id_reservation = ? ");
			deleteQuery.setInt(1, idReservation);
			deleteQuery.executeUpdate();
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}
	}
	
	public ArrayList<Reservation> getAllReservationByDate(Date dateReservation)
	{
		try {
			ArrayList<Reservation> lstReservationRes = new ArrayList<Reservation>();
			
			PreparedStatement selectQuery = this.connection.prepareStatement("SELECT r.* FROM reservation r INNER JOIN service s ON s.id_service = r.id_service WHERE r.date_reservation = ? ");
			selectQuery.setDate(1, (java.sql.Date) dateReservation);
			ResultSet resultSet = selectQuery.executeQuery();
			
			while(resultSet.next())
			{
				Client client = this.getClient("id_client", resultSet.getInt("id_client"));
				Service service = this.getServiceById(resultSet.getInt("id_service"));
				ArrayList<Tables> tables = this.getAllTableByIdReservation(resultSet.getInt("id_reservation"));
				
				Reservation reservationRes = new Reservation(resultSet.getInt("id_reservation"), resultSet.getInt("nb_personne"), resultSet.getString("type"), resultSet.getBoolean("is_validated"), resultSet.getDate("date_reservation"), client, service, tables);
				
				lstReservationRes.add(reservationRes);
			}
			
			return lstReservationRes;
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}
		return null;	
	}
	
	public ArrayList<Reservation> getAllReservationByDateAndHoraire(Date dateReservation, int ordre_service)
	{
		try {
			ArrayList<Reservation> lstReservationRes = new ArrayList<Reservation>();
			
			PreparedStatement selectQuery = this.connection.prepareStatement("SELECT r.* FROM reservation r INNER JOIN service s ON s.id_service = r.id_service WHERE r.date_reservation = ? AND s.ordre_service = ? ");
			selectQuery.setDate(1, (java.sql.Date) dateReservation);
			selectQuery.setInt(2, ordre_service);
			ResultSet resultSet = selectQuery.executeQuery();
			
			while(resultSet.next())
			{
				Client client = this.getClient("id_client", resultSet.getInt("id_client"));
				Service service = this.getServiceById(resultSet.getInt("id_service"));
				ArrayList<Tables> tables = this.getAllTableByIdReservation(resultSet.getInt("id_reservation"));
				
				Reservation reservationRes = new Reservation(resultSet.getInt("id_reservation"), resultSet.getInt("nb_personne"), resultSet.getString("type"), resultSet.getBoolean("is_validated"), resultSet.getDate("date_reservation"), client, service, tables);
				
				lstReservationRes.add(reservationRes);
			}
			
			return lstReservationRes;
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}
		return null;	
	}
	
	public Reservation getReservationByDateAndHoraireAndIdClient(Date dateReservation, String horaire_service, int idClient)
	{
		try {
			Reservation reservationRes = null;
			
			PreparedStatement selectQuery = this.connection.prepareStatement("SELECT r.* FROM reservation r INNER JOIN service s ON s.id_service = r.id_service WHERE r.date_reservation = ? AND s.ordre_service = ? AND r.id_client = ? ");
			selectQuery.setDate(1, java.sql.Date.valueOf(dateReservation.toString()));
			selectQuery.setString(2, horaire_service);
			selectQuery.setInt(3, idClient);
			ResultSet resultSet = selectQuery.executeQuery();
			
			while(resultSet.next())
			{
				Client client = this.getClient("id_client", resultSet.getInt("id_client"));
				Service service = this.getServiceById(resultSet.getInt("id_service"));
				ArrayList<Tables> tables = this.getAllTableByIdReservation(resultSet.getInt("id_reservation"));
				
				Reservation reservation = new Reservation(resultSet.getInt("id_reservation"), resultSet.getInt("nb_personne"), resultSet.getString("type"), resultSet.getBoolean("is_validated"), resultSet.getDate("date_reservation"), client, service, tables);
				System.out.println(reservation);
				reservationRes = reservation;
			}
			System.out.println(reservationRes);
			
			return reservationRes;
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}
		return null;	
	}
	
	public Reservation getReservationByIdClient(int idClient)
	{
		try {
			Reservation reservationRes = null;
			
			PreparedStatement selectQuery = this.connection.prepareStatement("SELECT r.* FROM reservation r WHERE r.id_client = ? ");
			selectQuery.setInt(1, idClient);
			ResultSet resultSet = selectQuery.executeQuery();
			
			while(resultSet.next())
			{
				Client client = this.getClient("id_client", idClient);
				Service service = this.getServiceById(resultSet.getInt("id_service"));
				ArrayList<Tables> tables = this.getAllTableByIdReservation(resultSet.getInt("id_reservation"));
				
				Reservation reservation = new Reservation(resultSet.getInt("id_reservation"), resultSet.getInt("nb_personne"), resultSet.getString("type"), resultSet.getBoolean("is_validated"), resultSet.getDate("date_reservation"), client, service, tables);
				System.out.println(reservation);
				reservationRes = reservation;
			}
			System.out.println(reservationRes);
			
			return reservationRes;
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}
		return null;	
	}
	
	//CRUD service
	public ArrayList<Service> getAllService()
	{
		try {
			ArrayList<Service> lstServiceRes = new ArrayList<Service>();
			Statement statement = this.connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from service ORDER BY ordre_service");
			
			while(resultSet.next())
			{				
				Service serviceRes = new Service(resultSet.getInt("id_service"), resultSet.getInt("ordre_service"), resultSet.getString("description"), resultSet.getString("horaire_service"));

				lstServiceRes.add(serviceRes);
			}
			
			return lstServiceRes;
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}		
		return null;			
	}
	
	public Service getServiceById(int idService)
	{
		try {
			Service serviceRes = null;

			PreparedStatement selectQuery = this.connection.prepareStatement("SELECT * FROM service WHERE id_service = ? ");
			selectQuery.setInt(1, idService);
			ResultSet resultSet = selectQuery.executeQuery();
      
			while(resultSet.next())
			{
				serviceRes = new Service(resultSet.getInt("id_service"), resultSet.getInt("ordre_service"), resultSet.getString("description"), resultSet.getString("horaire_service"));
			}
      
			return serviceRes;
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}
		return null;      
  }
  
   public Service getServiceByHoraire(String horaire )
   {
      try {  
        Service serviceRes = null;

        PreparedStatement selectQuery = this.connection.prepareStatement("SELECT * FROM service WHERE horaire_service= ? ");
         selectQuery.setString(1, horaire);

        ResultSet resultSet = selectQuery.executeQuery();
        
        while(resultSet.next())
        {
          serviceRes = new Service(resultSet.getInt("id_service"), resultSet.getInt("ordre_service"), resultSet.getString("description"), resultSet.getString("horaire_service"));
        }
        
        return serviceRes;
      }
      catch (Exception e) {
        System.err.println("Erreur de connexion !!");
        System.err.println(e.getMessage());
      }   
      return null;      
    }
	
	public void deleteService(int idService)
	{
		try {
			PreparedStatement deleteQuery = this.connection.prepareStatement("DELETE FROM service WHERE id = ? ");
			deleteQuery.setInt(1, idService);
			deleteQuery.executeUpdate();
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}
	}
	
	//CRUD tables
	public ArrayList<Tables> getAllTableByIdReservation(int idReservation)
	{		
		try {
			ArrayList<Tables> lstTablesRes = new ArrayList<Tables>();
			
			PreparedStatement selectQuery = this.connection.prepareStatement("SELECT t.* FROM tables t INNER JOIN reservation_tables rt on t.id_tables = rt.id_tables WHERE rt.id_reservation = ? ");
			selectQuery.setInt(1, idReservation);
			ResultSet resultSet = selectQuery.executeQuery();
			
			while(resultSet.next())
			{
				Tables tablesRes = null;

				tablesRes = new Tables(resultSet.getInt("id_tables"), resultSet.getInt("nb_chaise"));					
				
				lstTablesRes.add(tablesRes);
			}
			
			return lstTablesRes;
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}		
		return null;
	}
	
	public ArrayList<Tables> getAllTablesAvailableByDateReservationAndHoraireService(java.sql.Date dateReservation, String horaireService)
	{
		try {
			ArrayList<Tables> lstTablesRes = new ArrayList<Tables>();
			
			PreparedStatement selectQuery = this.connection.prepareStatement("SELECT t.* FROM tables t WHERE NOT EXISTS ( SELECT 1 FROM reservation_tables rt INNER JOIN reservation r ON rt.id_reservation = r.id_reservation AND r.date_reservation = ? INNER JOIN service s ON r.id_service = s.id_service AND s.horaire_service = ? WHERE rt.id_tables = t.id_tables )");
			selectQuery.setDate(1, dateReservation);
			selectQuery.setString(2, horaireService);
			ResultSet resultSet = selectQuery.executeQuery();
			
			while(resultSet.next())
			{
				Tables tablesRes = null;

				tablesRes = new Tables(resultSet.getInt("id_tables"), resultSet.getInt("nb_chaise"));					
				
				lstTablesRes.add(tablesRes);
			}
			
			return lstTablesRes;
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}		
		return null;
	}
	
}
