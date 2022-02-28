package web.service.webservicefrontofficeetmobile.model;

import java.sql.Date;
import java.sql.Time;

public class StatusSignalement extends AffectationSignalement
{
    private int idStatusSignalement;
    private String statusSignal;

    public int getIdStatusSignalement() 
    {
        return idStatusSignalement;
    }

    public void setIdStatusSignalement(int idStatusSignalement) 
    {
        this.idStatusSignalement = idStatusSignalement;
    }

    public String getStatusSignal() 
    {
        return statusSignal;
    }

    public void setStatusSignal(String statusSignal) 
    {
        this.statusSignal = statusSignal;
    }

    public StatusSignalement(int idAffectation, int idSignalement, int idStatusSignalement, int idRegion, Date date, Time heure, String statusSignal) 
    {
        super(idAffectation, idSignalement, idStatusSignalement, idRegion, date, heure);
        this.idStatusSignalement = idStatusSignalement;
        this.statusSignal = statusSignal;
    }

    public StatusSignalement() 
    {

    }
}
