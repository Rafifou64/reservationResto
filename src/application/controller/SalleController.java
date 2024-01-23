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
	private ImageView plate_1_1_1;
	
	@FXML
	private ImageView plate_1_1_2;
	
	@FXML
	private ImageView plate_1_2_1;
	
	@FXML
	private ImageView plate_1_2_2;
	
	@FXML
	private ImageView plate_1_3_1;
	
	@FXML
	private ImageView plate_1_3_2;
	
	@FXML
	private ImageView plate_1_4_1;
	
	@FXML
	private ImageView plate_1_4_2;
	
	@FXML
	private ImageView plate_1_5_1;
	
	@FXML
	private ImageView plate_1_5_2;
	
	@FXML
	private ImageView plate_2_1_1;
	
	@FXML
	private ImageView plate_2_1_2;
	
	@FXML
	private ImageView plate_2_2_1;
	
	@FXML
	private ImageView plate_2_2_2;
	
	@FXML
	private ImageView plate_2_3_1;
	
	@FXML
	private ImageView plate_2_3_2;
	
	@FXML
	private ImageView plate_2_4_1;
	
	@FXML
	private ImageView plate_2_4_2;
	
	@FXML
	private ImageView plate_2_5_1;
	
	@FXML
	private ImageView plate_2_5_2;
	
	@FXML
	private ImageView plate_3_1_1;
	
	@FXML
	private ImageView plate_3_1_2;
	
	@FXML
	private ImageView plate_3_2_1;
	
	@FXML
	private ImageView plate_3_2_2;
	
	@FXML
	private ImageView plate_3_3_1;
	
	@FXML
	private ImageView plate_3_3_2;
	
	@FXML
	private ImageView plate_3_4_1;
	
	@FXML
	private ImageView plate_3_4_2;
	
	@FXML
	private ImageView plate_3_5_1;
	
	@FXML
	private ImageView plate_3_5_2;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private Label labDate;
	
	@FXML
	private Label labMessage;
	
	private LocalDate dateReservation;
	
	private ArrayList<Reservation> lstReservation;
	
	private ArrayList<ImageView> lstTableDisplay;
	
	private ArrayList<ImageView> lstPlateDisplay;
	
	private MainService mainService;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.mainService = new MainService();
		this.setDateToday(this.mainService.getDateNow());
		this.dateReservation = LocalDate.now();
		this.datePicker.setValue(dateReservation);
		this.getReservation();
		this.initListeTable();
		this.initListePlate();
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
	
	public void initListePlate()
	{
		this.lstPlateDisplay = new ArrayList<ImageView>();
		this.lstPlateDisplay.add(plate_1_1_1);
		this.lstPlateDisplay.add(plate_1_1_2);
		this.lstPlateDisplay.add(plate_1_2_1);
		this.lstPlateDisplay.add(plate_1_2_2);
		this.lstPlateDisplay.add(plate_1_3_1);
		this.lstPlateDisplay.add(plate_1_3_2);
		this.lstPlateDisplay.add(plate_1_4_1);
		this.lstPlateDisplay.add(plate_1_4_2);
		this.lstPlateDisplay.add(plate_1_5_1);
		this.lstPlateDisplay.add(plate_1_5_2);
		this.lstPlateDisplay.add(plate_2_1_1);
		this.lstPlateDisplay.add(plate_2_1_2);
		this.lstPlateDisplay.add(plate_2_2_1);
		this.lstPlateDisplay.add(plate_2_2_2);
		this.lstPlateDisplay.add(plate_2_3_1);
		this.lstPlateDisplay.add(plate_2_3_2);
		this.lstPlateDisplay.add(plate_2_4_1);
		this.lstPlateDisplay.add(plate_2_4_2);
		this.lstPlateDisplay.add(plate_2_5_1);
		this.lstPlateDisplay.add(plate_2_5_2);
		this.lstPlateDisplay.add(plate_3_1_1);
		this.lstPlateDisplay.add(plate_3_1_2);
		this.lstPlateDisplay.add(plate_3_2_1);
		this.lstPlateDisplay.add(plate_3_2_2);
		this.lstPlateDisplay.add(plate_3_3_1);
		this.lstPlateDisplay.add(plate_3_3_2);
		this.lstPlateDisplay.add(plate_3_4_1);
		this.lstPlateDisplay.add(plate_3_4_2);
		this.lstPlateDisplay.add(plate_3_5_1);
		this.lstPlateDisplay.add(plate_3_5_2);
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
	
	public void setDisplayDefault()
	{
		this.labMessage.setText("");
		for (int i = 0; i < this.lstTableDisplay.size(); i++)
			this.lstTableDisplay.get(i).visibleProperty().set(false);
		
		for (int i = 0; i < this.lstPlateDisplay.size(); i++)
			this.lstPlateDisplay.get(i).visibleProperty().set(false);
	}
	
	public void displayTable()
	{
		this.setDisplayDefault();
		
		int cptTable = 0;
		int nbTableReservation = 0;
		for (int i = 0; i < lstReservation.size(); i++) {
			Reservation reservation = lstReservation.get(i);
			int nbPersonne = reservation.getNbpersonne();
			nbTableReservation = nbTableReservation + reservation.getLstTables().size();
			for (int j = 0; j < reservation.getLstTables().size(); j++) {
				this.displayPlate(nbPersonne, cptTable*2);
				this.lstTableDisplay.get(cptTable).visibleProperty().set(true);
				nbPersonne = nbPersonne - 2;
				cptTable++;
			}
			cptTable++;
		}
		
		if(nbTableReservation <= 0)
			this.labMessage.setText("Il n'y a pas de table réservée pour cette date et horaire");
	}
	
	public void displayPlate(int nbPersonne, int cptPlate)
	{
		for (int i = 0; (i < nbPersonne && i < 2); i++) {
			this.lstPlateDisplay.get(cptPlate).visibleProperty().set(true);
			cptPlate++;
		}
	}
}
