package web.service.webservicefrontofficeetmobile.model;

import java.sql.Time;
import java.sql.Date;

public class AffectationSignalement 
{
    private int idAffectation;
    private int idSignalement;
    private int idStatusSignalement;
    private int idRegion;
    private Date date;
    private Time heure;

    public void setIdAffection(int idAffectation)
    {
        this.idAffectation = idAffectation;
    }

    public int getIdAffectation()
    {
        return this.idAffectation;
    }

    public void setIdSignalement(int idSignalement) 
    {
        this.idSignalement = idSignalement;
    }

    public int getIdSignalement() 
    {
        return idSignalement;
    }

    public void setIdStatusSignalement(int idStatusSignalement) 
    {
        this.idStatusSignalement = idStatusSignalement;
    }

    public int getIdStatusSignalement() 
    {
        return idStatusSignalement;
    }

    public void setIdRegion(int idRegion) 
    {
        this.idRegion = idRegion;
    }

    public int getIdRegion() 
    {
        return idRegion;
    }

    public void setDateAffectation(Date date)
    {
        this.date = date;
    }

    public Date getDateAffectation()
    {
        return this.date;
    }

    public void setTimeAffectation(Time heure)
    {
        this.heure = heure;
    }

    public Time getTimeAffectation()
    {
        return this.heure;
    }

    public AffectationSignalement()
    {

    }

    public AffectationSignalement(int idAffectation, int idSignalement, int idStatusSignalement, int idRegion, Date date, Time heure)
    {
        this.setIdAffection(idAffectation);
        this.setIdSignalement(idSignalement);
        this.setIdStatusSignalement(idStatusSignalement);
        this.setIdRegion(idRegion);
        this.setDateAffectation(date);
        this.setTimeAffectation(heure);
    }
}
