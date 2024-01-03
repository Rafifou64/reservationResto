package application.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.entite.Reservation;
import application.service.MainService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class IndexController implements Initializable {

    @FXML
    private Label labListeReservation;

    @FXML
    private Label labAddReservation;

    @FXML
    private Label labSalle;

    @FXML
    private Label dateNow;

    @FXML
    private Label nb_reservation_day;
    

    @FXML
    private Label nb_request_reservation;

    @FXML
    private ListView<String> pendingReservations;
    
    private MainService mainService;
    

    public void goToListeReservation(MouseEvent e) throws IOException {
    	this.mainService.navigateTo(e, "../vue/ListeReservation.fxml");
    }

    public void goToAddReservation(MouseEvent e) throws IOException {
    	this.mainService.navigateTo(e, "../vue/AddReservation.fxml");
    }

    public void goToSalle(MouseEvent e) throws IOException {
    	this.mainService.navigateTo(e, "../vue/Salle.fxml");
    }

    public void getDateNow() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);
        dateNow.setText("  Réservation du " + formattedDate);
    }

    public void getReservationDay() {
        nb_reservation_day.setText("X tables réservées");
    }

    private void insertElementInListView(String cheminFichier) {
      int nbLines = 0; 

      try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
          String ligne;
          while ((ligne = reader.readLine()) != null) {
            nbLines++; 

              String[] elements = ligne.split(",");
              if (elements.length >= 4) {
                  String displayInformation = "Nom: " + elements[1].toUpperCase() + " - " + elements[5] + " - " + elements[3] + " - " + elements[6] + " personnes";
                  
                  ArrayList<Reservation> reservations = new ArrayList<Reservation>();
                  reservations.add(elements[1] + " - " + elements[3] + " - " + elements[4] + " - " + elements[5] + " - "
                      + elements[6]);
                  
                  pendingReservations.getItems().add(displayInformation);
              }
          }
          
          if (nbLines > 0 ) {
            nb_request_reservation.setText(nbLines + " réservations à confirmer");
          } else {
            nb_request_reservation.setText("0 réservations à confirmer");

          }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayAllInformation(String contenu) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Validation de la réservation");
      alert.setHeaderText(null);
      alert.setContentText(contenu);

      ButtonType validerButton = new ButtonType("Valider");
      ButtonType refuserButton = new ButtonType("Refuser");

      alert.getButtonTypes().setAll(validerButton, refuserButton, ButtonType.CANCEL);

      Optional<ButtonType> result = alert.showAndWait();

      if (result.isPresent()) {
          if (result.get() == validerButton) {
              System.out.println("Action Valider");
          } else if (result.get() == refuserButton) {
              System.out.println("Action Refuser");
          } else {
              System.out.println("Action Annuler");
          }

          Platform.runLater(() -> {
              pendingReservations.getSelectionModel().clearSelection();
          });
      }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
      this.mainService = new MainService();
      getDateNow();
      getReservationDay();
      pendingReservations.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
          if (newValue != null && !newValue.isEmpty()) {
              displayAllInformation(newValue);
          }
      });
      insertElementInListView("src/reservationWaiting.txt");
    }
}
