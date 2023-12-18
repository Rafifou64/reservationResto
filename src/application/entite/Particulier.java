package application.entite;

public class Particulier extends Client {	

	private String nom;
	private String prenom;
	
	public Particulier(int id_client, String tel, String mail, String nom, String prenom) {
		super(id_client, tel, mail);
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
