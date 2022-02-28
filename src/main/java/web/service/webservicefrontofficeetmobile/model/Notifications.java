package web.service.webservicefrontofficeetmobile.model;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.*;

@Entity
@Table(name = "notification")
public class Notifications 
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private long idNotification;
    @Column(name = "id_utilisateur")
    private long id_utilisateur;
    @Column(name = "message")
    private String message;
    @Column(name = "date_notification")
    private Date dateNotification;
    @Column(name = "heure_notification")
    private Time heureNotification;

    public long getIdNotification() 
    {
        return this.idNotification;
    }

    public void setIdNotification(int idNotification) 
    {
        this.idNotification = idNotification;
    }

    public long getId_utilisateur() 
    {
        return this.id_utilisateur;
    }

    public void setId_utilisateur(long id_utilisateur) 
    {
        this.id_utilisateur = id_utilisateur;
    }

    public String getMessage() 
    {
        return this.message;
    }

    public void setMessage(String message) 
    {
        this.message = message;
    }

    public Date getDateNotification() 
    {
        return this.dateNotification;
    }

    public void setDateNotification(Date dateNotification) 
    {
        this.dateNotification = dateNotification;
    }

    public Time getHeureNotification() 
    {
        return this.heureNotification;
    }

    public void setHeureNotification(Time heureNotification) 
    {
        this.heureNotification = heureNotification;
    }

}
