package application.entite;

import java.util.Date;

public class Reservation {
	
	private int numero;
	private int nbpersonne;
	private Date date;
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getNbpersonne() {
		return nbpersonne;
	}
	
	public void setNbpersonne(int nbpersonne) {
		this.nbpersonne = nbpersonne;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

}
