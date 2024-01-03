package application.entite;

import java.util.ArrayList;

public class Reservation {	

	private int id_reservation;
	private int nbpersonne;
	private String type;
	private boolean is_validated;
	private Client client;
	private Service service;
	private ArrayList<Tables> lstTables;
	
	public Reservation(int id_reservation, int nbpersonne, String type, boolean is_validated, Client client, Service service, ArrayList<Tables> lstTables)
	{
		this.id_reservation = id_reservation;
		this.nbpersonne = nbpersonne;
		this.type = type;
		this.is_validated = is_validated;
		this.client = client;
		this.service = service;
		this.lstTables = lstTables;
	}
	
	public int getIdReservation() {
		return id_reservation;
	}
	
	public void setIdReservation(int id_reservation) {
		this.id_reservation = id_reservation;
	}
	
	public int getNbpersonne() {
		return nbpersonne;
	}
	
	public void setNbpersonne(int nbpersonne) {
		this.nbpersonne = nbpersonne;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean getIs_validated() {
		return is_validated;
	}

	public void setIs_validated(boolean is_validated) {
		this.is_validated = is_validated;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public ArrayList<Tables> getLstTables() {
		return lstTables;
	}

	public void setLstTables(ArrayList<Tables> lstTables) {
		this.lstTables = lstTables;
	}

}
