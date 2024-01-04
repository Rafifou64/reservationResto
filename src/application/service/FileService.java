package application.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FileService {
	
	public ObservableList<String> getListReservationWeb()
	{
		ObservableList<String> items = FXCollections.observableArrayList();
	    
        try (BufferedReader reader = new BufferedReader(new FileReader("src/reservationWaiting.txt"))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] elements = ligne.split(",");
                String displayInformation = elements[1].toUpperCase() + " - " + elements[3] + " - " + elements[5] + " - " + elements[6] + " personnes";
                items.add(displayInformation);
            }
            
            return items;
        } catch (IOException e) {
            // Handle IOException
        	return null;
        }
	}

}
