package application.entite;


public class ReservationWeb {

  private int id_reservation;
  private String type;
  private String name; 
  private String email;
  private String hour;
  private String phone;
  private String date;
  private int nb_person;
  
  public ReservationWeb(int id_reservation, String type,  String name, String email, String hour, String phone, String date,
      int nb_person) {
    super();
    this.id_reservation = id_reservation;
    this.type = type;
    this.name = name;
    this.email = email;
    this.hour = hour;
    this.phone = phone;
    this.date = date;
    this.nb_person = nb_person;
  }

  public int getId_reservation() {
    return id_reservation;
  }

  public void setId_reservation(int id_reservation) {
    this.id_reservation = id_reservation;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getHour() {
    return hour;
  }

  public void setHour(String hour) {
    this.hour = hour;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getNb_person() {
    return nb_person;
  }

  public void setNb_person(int nb_person) {
    this.nb_person = nb_person;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
  
}
