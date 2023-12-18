package application.entite;

public abstract class Client {
	
	private int id_client;
	private String tel;
	private String mail;
	
	public Client(int id_client, String tel, String mail)
	{
		this.id_client = id_client;
		this.tel = tel;
		this.mail = mail;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
