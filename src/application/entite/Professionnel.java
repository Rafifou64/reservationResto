package application.entite;

public class Professionnel extends Client {
	
	private String nom_societe;
	
	public Professionnel(int id_client, String tel, String mail, String nom_societe) {
		super(id_client, tel, mail);
		this.nom_societe = nom_societe;
	}

	public String getNomsociete() {
		return nom_societe;
	}

	public void setNomsociete(String nom_societe) {
		this.nom_societe = nom_societe;
	}

}
