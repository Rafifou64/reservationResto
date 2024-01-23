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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
	
	public void submitFormCustomer() {
	  
	  String name = nameCustomer.getText();
    String firstname = firstnameCustomer.getText();
    String phone = phoneCustomer.getText();
    String email = emailCustomer.getText();
    String nbPersons = nbPerson.getText();
    String selectedHour = listHour.getSelectionModel().getSelectedItem(); 
    LocalDate localDate = dateReservation.getValue();

    Date selectedDate = java.sql.Date.valueOf(localDate);

    System.out.println("Nom: " + name);
    System.out.println("Prénom: " + firstname);
    System.out.println("Téléphone: " + phone);
    System.out.println("Email: " + email);
    System.out.println("Nombre de personnes: " + nbPersons);
    System.out.println("Heure sélectionnée: " + selectedHour);
    System.out.println("Date sélectionnée (java.util.Date): " + selectedDate);

    this.mainService.addReservationByForm(name, firstname, phone, email, nbPersons, selectedHour, selectedDate);
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
