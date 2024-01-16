package application.service;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import application.entite.Client;
import application.entite.Particulier;
import application.entite.Professionnel;
import application.entite.Reservation;
import application.entite.Service;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
}
