package application.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ListeReservationController {
	@FXML
	private Label labAccueil;
	
	@FXML
	private Label labAddReservation;
	
	@FXML
	private Label labSalle;
	
	public void goToAccueil(MouseEvent e) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(
		    getClass().getResource(
		      "../vue/Index.fxml"
		    )
		  );
		
		Parent indexFXML = loader.load();
		Scene sceneIndexFXML = new Scene(indexFXML);
		
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		
		stage.setScene(sceneIndexFXML);

		stage.show();
	}
	
	public void goToAddReservation(MouseEvent e) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(
		    getClass().getResource(
		      "../vue/AddReservation.fxml"
		    )
		  );
		
		Parent addReservationFXML = loader.load();
		Scene sceneAddReservationFXML = new Scene(addReservationFXML);
		
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		
		stage.setScene(sceneAddReservationFXML);

		stage.show();
	}
	
	public void goToSalle(MouseEvent e) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(
		    getClass().getResource(
		      "../vue/Salle.fxml"
		    )
		  );
		
		Parent salleFXML = loader.load();
		Scene sceneSalleFXML = new Scene(salleFXML);
		
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		
		stage.setScene(sceneSalleFXML);

		stage.show();
	}
}
