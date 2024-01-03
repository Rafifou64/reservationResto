package application.service;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import application.entite.Reservation;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainService implements Initializable {
	
	private DAO dao;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		Date date = this.convertLocalDateToDate(dateReservation);
		return this.dao.getAllReservationByDate(date);
	}
	
	public Date convertLocalDateToDate(LocalDate localDate)
	{
		//default time zone
		ZoneId defaultZoneId = ZoneId.systemDefault();
        
        //local date + atStartOfDay() + default time zone + toInstant() = Date
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        
        return date;
	}

}
