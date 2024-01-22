package application.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.entite.Client;
import application.entite.Particulier;
import application.entite.Professionnel;
import application.entite.Reservation;
import application.entite.Service;
import application.entite.Tables;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainService {
	

	private DAO dao;
	
	public MainService() {
		this.dao = new DAO();
	}
	
	public void navigateTo(MouseEvent e, String vue) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(
			    getClass().getResource(
			      vue
			    )
			  );
			
			Parent listeReservationFXML = loader.load();
			Scene sceneListeReservationFXML = new Scene(listeReservationFXML);
			
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			
			stage.setScene(sceneListeReservationFXML);

			stage.show();
	}
	
	public String getDateNow() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }
	
	public String formatDate(LocalDate date)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
	}
	
	public ArrayList<Reservation> getReservationByDate(LocalDate dateReservation)
	{
		return this.dao.getAllReservationByDate(java.sql.Date.valueOf(dateReservation));
	}
	
	public String getDisplayReservationListView(Reservation reservation)
	{
		Service service = reservation.getService();
		Client client = reservation.getClient();
		
		String libelleClient = "";
		if(client instanceof Particulier)
		{
			libelleClient = ((Particulier) client).getNom().toUpperCase() + " " + ((Particulier) client).getPrenom();
		}
		else if(client instanceof Professionnel)
		{
			libelleClient = ((Professionnel) client).getNomsociete().toUpperCase();
		}
		
		String dateFormatted = this.formatDate(new java.sql.Date(service.getDate_service().getTime()).toLocalDate());
		
		return dateFormatted + "   " + service.getHoraire_service() + "     " + libelleClient + "     " + Integer.toString(reservation.getNbpersonne()) + " personnes    " + client.getTel();
	}
	
	public void addReservationByWeb(String contentList) {
    try {
      String[] elements = contentList.split(" - "); 
    
    String type = elements[0]; 
    String name = elements[1];
    
    String firstname = elements[1].split(" ")[1];
    String lastname = elements[1].split(" ")[0];

    String horaire = elements[2];
    String date = elements[3];
    int nb_person = Integer.parseInt(elements[4].split(" ")[0]);
    String email = elements[5];
    String tel = elements[6];
    
    if(type.equals("particulier")) {
      Particulier particulier = new Particulier(1, tel, email, lastname, firstname); 
      this.dao.addClient(particulier);
    } else {
      Professionnel professionnel = new Professionnel(1, tel, email, name); 
      this.dao.addClient(professionnel);
    }

    String dateString = date;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date dateFormatted = null;
    try {
        dateFormatted = dateFormat.parse(dateString);
    } catch (ParseException e) {
        e.printStackTrace();
        return; 
    }

    Service service = new Service(1, dateFormatted, 1, "description", horaire);
    this.dao.addService(service);
    
    Client clientByEmail = this.dao.getClient("mail", email);
    Service serviceByHoraire = this.dao.getServiceByHoraire( dateFormatted, horaire); 
   
    Reservation reservation = new Reservation(1, nb_person, type, true, clientByEmail, serviceByHoraire, new ArrayList<Tables>());
    this.dao.addReservation(reservation);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
	}
}
