package application.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import application.entite.Reservation;
import application.service.MainService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ListeReservationController implements Initializable {
	@FXML
	private Label labAccueil;
	
	@FXML
	private Label labAddReservation;
	
	@FXML
	private Label labSalle;
	
	@FXML
	private Label labDate;
	
	@FXML
	private DatePicker datePicker;
	
	private LocalDate dateReservation;
	
	private ArrayList<Reservation> lstReservation;
	
	private MainService mainService;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.mainService = new MainService();
		this.setDateToday(this.mainService.getDateNow());
		this.dateReservation = LocalDate.now();
		this.datePicker.setValue(dateReservation);		
	}
	
	public void goToAccueil(MouseEvent e) throws IOException
	{
		this.mainService.navigateTo(e, "../vue/Index.fxml");
	}
	
	public void goToAddReservation(MouseEvent e) throws IOException
	{
		this.mainService.navigateTo(e, "../vue/AddReservation.fxml");
	}
	
	public void goToSalle(MouseEvent e) throws IOException
	{
		this.mainService.navigateTo(e, "../vue/Salle.fxml");
	}
	
	public void setDateToday(String date)
	{
		labDate.setText("  Réservation du " + date);
	}
	
	public void changeDateReservation()
	{
		this.dateReservation = this.datePicker.getValue();
		this.setDateToday(this.mainService.formatDate(this.dateReservation));
	}
	
	public void getReservation()
	{
		
	}

	
}
