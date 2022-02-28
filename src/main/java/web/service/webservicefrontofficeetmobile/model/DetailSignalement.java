package web.service.webservicefrontofficeetmobile.model;

import java.sql.Date;
import java.sql.Time;

public class DetailSignalement 
{
    private int idDetailSignalement;
    private int idStatusSignalement;
    private int idSignalement;
    private Date daty;
    private Time heure;

    public int getIdDetailSignalement() {
        return idDetailSignalement;
    }

    public void setIdDetailSignalement(int idDetailSignalement) {
        this.idDetailSignalement = idDetailSignalement;
    }

    public int getIdStatusSignalement() {
        return idStatusSignalement;
    }

    public void setIdStatusSignalement(int idStatusSignalement) {
        this.idStatusSignalement = idStatusSignalement;
    }

    public int getIdSignalement() {
        return idSignalement;
    }

    public void setIdSignalement(int idSignalement) {
        this.idSignalement = idSignalement;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public DetailSignalement(int idDetailSignalement, int idStatusSignalement, int idSignalement, Date daty, Time heure) {
        this.idDetailSignalement = idDetailSignalement;
        this.idStatusSignalement = idStatusSignalement;
        this.idSignalement = idSignalement;
        this.daty = daty;
        this.heure = heure;
    }

    public DetailSignalement() {
    }
    
}
