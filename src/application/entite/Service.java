package application.entite;

import java.util.Date;

public class Service {
	
	private int id_service;
	private int ordre_service;
	private String description;
	private String horaire_service;
	
	public Service(int id_service, int ordre_service, String description, String horaire_service)
	{
		this.id_service = id_service;
		this.ordre_service = ordre_service;
		this.description = description;
		this.horaire_service = horaire_service;
	}
	
	public int getId_service() {
		return id_service;
	}
	
	public void setId_service(int id_service) {
		this.id_service = id_service;
	}
	
	public int getOrdre_service() {
		return ordre_service;
	}
	
	public void setOrdre_service(int ordre_service) {
		this.ordre_service = ordre_service;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getHoraire_service() {
		return horaire_service;
	}
	
	public void setHoraire_service(String horaire_service) {
		this.horaire_service = horaire_service;
	}

}
