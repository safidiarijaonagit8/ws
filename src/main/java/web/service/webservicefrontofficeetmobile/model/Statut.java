package web.service.webservicefrontofficeetmobile.model;

public class Statut {
	int id_status_signalement;
	String status_signalement;
	public int getId_status_signalement() {
		return id_status_signalement;
	}
	public void setId_status_signalement(int id_status_signalement) {
		this.id_status_signalement = id_status_signalement;
	}
	public String getStatus_signalement() {
		return status_signalement;
	}
	public void setStatus_signalement(String status_signalement) {
		this.status_signalement = status_signalement;
	}
	public Statut(int id_status_signalement, String status_signalement) {
		super();
		this.id_status_signalement = id_status_signalement;
		this.status_signalement = status_signalement;
	}
	

}
