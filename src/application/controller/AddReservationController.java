package application.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import application.entite.Particulier;
import application.entite.Service;
import application.service.MainService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddReservationController implements Initializable {

	@FXML
	private Label labListeReservation;
	
	@FXML
	private Label labAccueil;
	
	@FXML
	private Label labSalle;
	
	@FXML
  private TextField nameCustomer;

	@FXML
	private TextField firstnameCustomer;
	
	@FXML
  private TextField phoneCustomer;
	
	@FXML
  private TextField emailCustomer; 

	@FXML
  private DatePicker dateReservation;
	
	@FXML
  private TextField nbPerson;
	
	@FXML
	private ListView<String> listHour; 
	
	@FXML
	private Label labMessage;
	
	@FXML
	private Button addCustomer;
	
	private MainService mainService;
	

	
	public void goToListeReservation(MouseEvent e) throws IOException
	{
		this.mainService.navigateTo(e, "../vue/ListeReservation.fxml");
	}
	
	public void goToAccueil(MouseEvent e) throws IOException
	{
		this.mainService.navigateTo(e, "../vue/Index.fxml");
	}
	
	public void goToSalle(MouseEvent e) throws IOException
	{
		this.mainService.navigateTo(e, "../vue/Salle.fxml");
	}	
	
	public void submitFormCustomer()
	{
		this.labMessage.setText("");
		String name = nameCustomer.getText();
		String firstname = firstnameCustomer.getText();		
		String phone = phoneCustomer.getText();
		String email = emailCustomer.getText();
		String nbPersons = nbPerson.getText();
		ObservableList selectedHourObs = listHour.getSelectionModel().getSelectedItems(); 	  
		String selectedHour = selectedHourObs.get(0).toString();
		LocalDate localDate = dateReservation.getValue();

		Date selectedDate = java.sql.Date.valueOf(localDate);


		if(this.mainService.addReservationByForm(name, firstname, phone, email, nbPersons, selectedHour, selectedDate))
		{
			this.cleanForm();
			this.labMessage.setText("L'ajout de la réservation a bien été pris en compte");
		}
	}
	
	public void cleanForm()
	{
		this.nameCustomer.setText("");
		this.firstnameCustomer.setText("");
		this.phoneCustomer.setText("");
		this.emailCustomer.setText("");
		this.nbPerson.setText("");
		this.dateReservation.setValue(null);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.mainService = new MainService(); 
    
		ArrayList<Service> services = this.mainService.getAllService(); 
    
		for (Service service : services) {
			listHour.getItems().add(service.getHoraire_service());
		} 
    
	}
}
