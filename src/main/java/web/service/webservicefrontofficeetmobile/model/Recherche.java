package web.service.webservicefrontofficeetmobile.model;

import java.sql.Date;
import java.util.ArrayList;
import java.sql.*;
import web.service.webservicefrontofficeetmobile.fonctionsBdd.Connexion;
import web.service.webservicefrontofficeetmobile.fonctionsBdd.Crud;
import web.service.webservicefrontofficeetmobile.fonctionsBdd.FonctionsBdd;

public class Recherche extends Crud
{
    private String type_signalement;
    private String status_signalement;
    private Date date_signal;
    private Date date_affectation;

    public String getType_signalement() 
    {
        return this.type_signalement;
    }

    public void setType_signalement(String type_signalement) 
    {
        this.type_signalement = type_signalement;
    }

    public Date getDate_signal() 
    {
        return this.date_signal;
    }

    public void setDate_signal(Date date_signalement) 
    {
        this.date_signal = date_signalement;
    }

    public Date getDate_affectation() 
    {
        return this.date_affectation;
    }

    public void setDate_affectation(Date date_signalement) 
    {
        this.date_affectation = date_signalement;
    }

    public String getStatus_signalement() 
    {
        return this.status_signalement;
    }

    public void setStatus_signalement(String status_signalement) 
    {
        this.status_signalement = status_signalement;
    }

    public Recherche()
    {

    }

    public Recherche(String type_signalement, String status_signalement, Date date_signalement_debut, Date date_signalement_fin) 
    {
        this.type_signalement = type_signalement;
        this.status_signalement = status_signalement;
        this.date_signal = date_signalement_debut;
        this.date_affectation = date_signalement_fin;
    }

    public Boolean verifierDate(String mot)
    {
        Boolean retour =  false;
        int condition = Integer.parseInt(mot.split("-")[0]);
        if(condition >= 1 && condition <= 31)
        {
            retour = true;
        }
        return retour;
    }

    public Signalement[] getAllSignalement()
    {
        Signalement[] retour = null;
        Connexion connex = new Connexion();
        Connection con = null;
        try
        {
            FonctionsBdd f = new FonctionsBdd();
            ArrayList<Signalement> list = new ArrayList<>();
            con = connex.connexion();
            String requete = this.getRequeteFind();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            while(rs.next())
            {
                PhotoSignalement[] tabPhoto = f.getAllPhotoSignalement(rs.getLong("id_signalement"));
                Signalement signal = new Signalement();
                signal.setIdSignalement(rs.getLong("id_signalement"));
                signal.setIdTypeSignalement(rs.getInt("id_type_signalement"));
                signal.setIdRegion(rs.getInt("id_region"));
                signal.setIdUtilisateur(rs.getLong("id_utilisateur"));
                signal.setLongitude(rs.getDouble("longitude"));
                signal.setLatitude(rs.getDouble("latitude"));
                signal.setDescriptionSignal("description_signal");
                signal.setDate(rs.getDate("date_signal"));
                signal.setHeure(rs.getTime("heure_signal"));
                signal.setPhoto(tabPhoto);
                list.add(signal);
            }
            retour = new Signalement[list.size()];
            for(int i = 0; i < list.size(); i++)
            {
                retour[i] = list.get(i);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            connex.deconnexion(con);
        }
        return retour;
    }
}
