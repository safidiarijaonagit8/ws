package web.service.webservicefrontofficeetmobile.model;

import java.util.Date;

public class ListeSignalementRegion {
	int id_signalement;
	String type_signalement;
	int id_region;
	double longitude;
	double latitude;
	Date date_signal;
	
	public int getId_signalement() {
		return id_signalement;
	}
	public void setId_signalement(int id_signalement) {
		this.id_signalement = id_signalement;
	}
	public String getType_signalement() {
		return type_signalement;
	}
	public void setType_signalement(String type_signalement) {
		this.type_signalement = type_signalement;
	}
	public int getId_region() {
		return id_region;
	}
	public void setId_region(int id_region) {
		this.id_region = id_region;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Date getDate_signal() {
		return date_signal;
	}
	public void setDate_signal(Date date_signal) {
		this.date_signal = date_signal;
	}
	public ListeSignalementRegion(int id_signalement, String type_signalement, int id_region, double longitude,
			double latitude, Date date_signal) {
		super();
		this.id_signalement = id_signalement;
		this.type_signalement = type_signalement;
		this.id_region = id_region;
		this.longitude = longitude;
		this.latitude = latitude;
		this.date_signal = date_signal;
	}
	

}
