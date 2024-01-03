package application.entite;

public class Tables {

	private int id_tables;
	private int nbchaise;
	
	public Tables(int id_tables, int nbchaise) {
		this.id_tables = id_tables;
		this.nbchaise = nbchaise;
	}
	
	public int getIdTables() {
		return id_tables;
	}
	
	public void setIdTables(int id_tables) {
		this.id_tables = id_tables;
	}
	
	public int getNbchaise() {
		return nbchaise;
	}
	
	public void setNbchaise(int nbchaise) {
		this.nbchaise = nbchaise;
	}

}
