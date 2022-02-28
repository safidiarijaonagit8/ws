package web.service.webservicefrontofficeetmobile.model;

import java.sql.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Signalement")
public class Signalement
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSignalement;
    @Column(name = "id_type_signalement")
    private int idTypeSignalement;
    @Column(name = "id_region")
    private int idRegion;
    @Column(name = "id_utilisateur")
    private long idUtilisateur;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "description_signal")
    private String descriptionSignal;
    @Column(name = "date_signal")
    private Date date;
    @Column(name = "heure_signal")
    private Time heure;
    private PhotoSignalement[] photo;

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeure() {
        return this.heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public long getIdSignalement() {
        return idSignalement;
    }

    public void setIdSignalement(long idSignalement) {
        this.idSignalement = idSignalement;
    }

    public int getIdTypeSignalement() {
        return idTypeSignalement;
    }

    public void setIdTypeSignalement(int idTypeSignalement) {
        this.idTypeSignalement = idTypeSignalement;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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

    public String getDescriptionSignal() {
        return descriptionSignal;
    }

    public void setDescriptionSignal(String descriptionSignal) {
        this.descriptionSignal = descriptionSignal;
    }

    public void setPhoto(PhotoSignalement[] photo)
    {
        this.photo = photo;
    }

    public PhotoSignalement[] getPhoto()
    {
        return photo;
    }

    public Signalement(long idSignalement, int idTypeSignalement, long idUtilisateur, double longitude, double latitude, String descriptionSignal, Date date, Time heure) 
    {
        this.idSignalement = idSignalement;
        this.idTypeSignalement = idTypeSignalement;
        this.idUtilisateur = idUtilisateur;
        this.longitude = longitude;
        this.latitude = latitude;
        this.descriptionSignal = descriptionSignal;
        this.date = date;
        this.heure = heure;
    }

    public Signalement(int idTypeSignalement, long idUtilisateur, double longitude, double latitude, String descriptionSignal, Date date, Time heure) 
    {
        this.idTypeSignalement = idTypeSignalement;
        this.idUtilisateur = idUtilisateur;
        this.longitude = longitude;
        this.latitude = latitude;
        this.descriptionSignal = descriptionSignal;
        this.date = date;
        this.heure = heure;
    }

    public Signalement() 
    {

    }
}
