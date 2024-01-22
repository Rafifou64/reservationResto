package application.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.entite.Reservation;
import application.service.MainService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SalleController implements Initializable {
	
	@FXML
	private Label labListeReservation;
	
	@FXML
	private Label labAddReservation;
	
	@FXML
	private Label labSalle;
	
	@FXML
	private ImageView table_1_1;
	
	@FXML
	private ImageView table_1_2;
	
	@FXML
	private ImageView table_1_3;
	
	@FXML
	private ImageView table_1_4;
	
	@FXML
	private ImageView table_1_5;
	
	@FXML
	private ImageView table_2_1;
	
	@FXML
	private ImageView table_2_2;
	
	@FXML
	private ImageView table_2_3;
	
	@FXML
	private ImageView table_2_4;
	
	@FXML
	private ImageView table_2_5;
	
	@FXML
	private ImageView table_3_1;
	
	@FXML
	private ImageView table_3_2;
	
	@FXML
	private ImageView table_3_3;
	
	@FXML
	private ImageView table_3_4;
	
	@FXML
	private ImageView table_3_5;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private Label labDate;
	
	private LocalDate dateReservation;
	
	private ArrayList<Reservation> lstReservation;
	
	private ArrayList<ImageView> lstTableDisplay;
	
	private MainService mainService;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.mainService = new MainService();
		this.setDateToday(this.mainService.getDateNow());
		this.dateReservation = LocalDate.now();
		this.datePicker.setValue(dateReservation);
		this.getReservation();
		this.initListeTable();
		this.displayTable();
	}
	
	public void goToListeReservation(MouseEvent e) throws IOException
	{
		this.mainService.navigateTo(e, "../vue/ListeReservation.fxml");
	}
	
	public void goToAddReservation(MouseEvent e) throws IOException
	{
		this.mainService.navigateTo(e, "../vue/AddReservation.fxml");
	}
	
	public void goToAccueil(MouseEvent e) throws IOException
	{
		this.mainService.navigateTo(e, "../vue/Index.fxml");
	}
	
	public void initListeTable()
	{
		this.lstTableDisplay = new ArrayList<ImageView>();
		this.lstTableDisplay.add(table_1_1);
		this.lstTableDisplay.add(table_1_2);
		this.lstTableDisplay.add(table_1_3);
		this.lstTableDisplay.add(table_1_4);
		this.lstTableDisplay.add(table_1_5);
		this.lstTableDisplay.add(table_2_1);
		this.lstTableDisplay.add(table_2_2);
		this.lstTableDisplay.add(table_2_3);
		this.lstTableDisplay.add(table_2_4);
		this.lstTableDisplay.add(table_2_5);
		this.lstTableDisplay.add(table_3_1);
		this.lstTableDisplay.add(table_3_2);
		this.lstTableDisplay.add(table_3_3);
		this.lstTableDisplay.add(table_3_4);
		this.lstTableDisplay.add(table_3_5);
	}
	
	public void setDateToday(String date)
	{
		labDate.setText("  Réservation du " + date);
	}
	
	public void getReservation()
	{
		this.lstReservation = this.mainService.getReservationByDate(dateReservation);
	}
	
	public void changeDateReservation()
	{
		this.dateReservation = this.datePicker.getValue();
		this.setDateToday(this.mainService.formatDate(this.dateReservation));
		this.getReservation();
		this.displayTable();
	}
	
	public void displayTable()
	{
		for (int i = 0; i < this.lstTableDisplay.size(); i++) {
			this.lstTableDisplay.get(i).visibleProperty().set(false);
		}
		
		int nbTable = 0;
		for (int i = 0; i < lstReservation.size(); i++) {
			Reservation reservation = lstReservation.get(i);
			for (int j = 0; j < reservation.getLstTables().size(); j++) {
				this.lstTableDisplay.get(nbTable).visibleProperty().set(true);
				nbTable++;
			}
			nbTable++;
		}
	}
}
