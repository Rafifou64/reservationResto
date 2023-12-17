package application.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

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
    private ListView<String> pendingReservations;

    public void goToListeReservation(MouseEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/ListeReservation.fxml"));
        Parent listeReservationFXML = loader.load();
        Scene sceneListeReservationFXML = new Scene(listeReservationFXML);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneListeReservationFXML);
        stage.show();
    }

    public void goToAddReservation(MouseEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/AddReservation.fxml"));
        Parent addReservationFXML = loader.load();
        Scene sceneAddReservationFXML = new Scene(addReservationFXML);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneAddReservationFXML);
        stage.show();
    }

    public void goToSalle(MouseEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/Salle.fxml"));
        Parent salleFXML = loader.load();
        Scene sceneSalleFXML = new Scene(salleFXML);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneSalleFXML);
        stage.show();
    }

    public void getDateNow() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);
        dateNow.setText("  Réservation du " + formattedDate);
    }

    public void getReservationDay() {
        nb_reservation_day.setText("6 tables réservées");
    }

    private void insertElementInListView(String cheminFichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] elements = ligne.split(",");
                if (elements.length >= 4) {
                    String displayInformation = elements[0] + " - " + elements[1] + " - " + elements[2] + " - " + elements[4] + " - "
                            + elements[6];
                    pendingReservations.getItems().add(displayInformation);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayInformation(String contenu) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation");
      alert.setHeaderText(null);
      alert.setContentText("Voulez-vous valider ou refuser ?");

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
      getDateNow();
      getReservationDay();
      pendingReservations.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
          if (newValue != null && !newValue.isEmpty()) {
              displayInformation(newValue);
          }
      });
      insertElementInListView("src/reservationWaiting.txt");
    }
}
