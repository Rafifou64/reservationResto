package application.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.entite.ReservationWeb;
import application.service.FileService;
import application.service.MainService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

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
    
    private FileService fileService;
    private MainService mainService;
    ArrayList<ReservationWeb> reservations = new ArrayList<ReservationWeb>();
    ArrayList<ReservationWeb> deleteReservations = new ArrayList<ReservationWeb>();

    public void goToListeReservation(MouseEvent e) throws IOException {
    	this.mainService.navigateTo(e, "../vue/ListeReservation.fxml");
    }

    public void goToAddReservation(MouseEvent e) throws IOException {
    	this.mainService.navigateTo(e, "../vue/AddReservation.fxml");
    }

    public void goToSalle(MouseEvent e) throws IOException {
    	this.mainService.navigateTo(e, "../vue/Salle.fxml");
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
                String displayInformation = elements[1] + " - " + elements[3] + " - " + elements[5] + " - " + elements[6] + " personnes";
                reservations.add(new ReservationWeb(nbLines, elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], Integer.parseInt(elements[6])));
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
    
    private void removeReservationWeb(String email) throws IOException {
      String ligne;
      String file = "src/reservationWaiting.txt";

      int nbLines = 0;
      ArrayList <ReservationWeb> deleteReservations = new ArrayList<>();

      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
          while ((ligne = reader.readLine()) != null) {
              nbLines++;
              String[] elements = ligne.split(",");
              email = email.replace(" ", "");
              if (!email.equals(elements[2])) {
                  deleteReservations.add(new ReservationWeb(nbLines, elements[0], elements[1].toUpperCase(), elements[2], elements[3], elements[4], elements[5], Integer.parseInt(elements[6])));
              }
          }
      } catch (IOException e) {
      }

      try (PrintWriter writer = new PrintWriter(file)) {
          writer.print("");
      } catch (FileNotFoundException e) {
      }

        try (FileWriter writer = new FileWriter(file, true)) {
            for (ReservationWeb deleteReservation : deleteReservations) {
              writer.write(deleteReservation.getType() + "," +
                  deleteReservation.getName() + "," +
                  deleteReservation.getEmail() + "," +
                  deleteReservation.getHour() + "," +
                  deleteReservation.getPhone() + "," +
                  deleteReservation.getDate() + "," +
                  deleteReservation.getNb_person() + "\n");
              }
            refreshListView();

        } catch (IOException e) { 
            // Handle IOException
        }
    }

    private void refreshListView() {
        Platform.runLater(() -> {
            ObservableList<String> items = this.fileService.getListReservationWeb();
    
            pendingReservations.setItems(items);
        });
    }


    private void displayAllInformation(String contenu) throws FileNotFoundException, IOException {
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
            removeReservationWeb(contenu.split("-")[0]);
          }

          Platform.runLater(() -> {
              pendingReservations.getSelectionModel().clearSelection();
          });
      }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
      this.mainService = new MainService();
      this.fileService = new FileService();
      dateNow.setText("  Réservation du " + this.mainService.getDateNow());
      getReservationDay();
      
      insertElementInListView("src/reservationWaiting.txt");
     
        pendingReservations.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue != null && !newValue.isEmpty()) {
             for (ReservationWeb reservation : reservations) {
               if(reservation.getName().equals(newValue.split(" - ")[0])) {
              String email = reservation.getEmail();
              String phone = reservation.getPhone();
              try {
                displayAllInformation(email + " - " + phone);
              } catch (IOException e) {
                e.printStackTrace();
              }
               }
             }
          }
      });
    }
}
