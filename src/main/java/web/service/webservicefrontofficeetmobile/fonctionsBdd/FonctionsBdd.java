package web.service.webservicefrontofficeetmobile.fonctionsBdd;

import web.service.webservicefrontofficeetmobile.model.ChefDeRegion;
import web.service.webservicefrontofficeetmobile.model.PhotoSignalement;
import web.service.webservicefrontofficeetmobile.model.Signalement;
import web.service.webservicefrontofficeetmobile.model.Utilisateur;
import web.service.webservicefrontofficeetmobile.model.Statut;
import web.service.webservicefrontofficeetmobile.model.ListeSignalementRegion;
import web.service.webservicefrontofficeetmobile.model.ChefRegionAvecidRegion;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;


public class FonctionsBdd 
{
    Connexion con = new Connexion();

    public String formatSha1(String mdp, Connection con)
    {
        String motDePasse = null;
        try
        {
            String requete = "select encode(digest('"+mdp+"', 'sha1'), 'hex') as motDePasse";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            while(rs.next())
            {
                motDePasse = rs.getString("motDePasse");
                break;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();;
        }
        return motDePasse;
    }

    public ArrayList<ChefRegionAvecidRegion> getListeChefRegionId()
    {
        ArrayList<ChefRegionAvecidRegion> a = new ArrayList<ChefRegionAvecidRegion>();
        
         Connection connex = null;
         try
         {
             connex = con.connexion();
             PreparedStatement pstmt = connex.prepareStatement("select * from region r join chefderegion c on r.id_chef_de_region=c.id_chef_de_region");
            
             ResultSet rs = pstmt.executeQuery();
             while(rs.next())
             {
                 ChefRegionAvecidRegion s = new ChefRegionAvecidRegion(rs.getInt("id_region"),rs.getString("username"),rs.getString("mot_de_passe"));
                 a.add(s);
                 
                 //s.setIdRegion0); = rs.getInt("idregion");
             }
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
         finally
         {
             con.deconnexion(connex);
         }
        
        return a;
    }
    
    public ArrayList<ListeSignalementRegion> getListeSignalementRegion(int idregion)
    {
        ArrayList<ListeSignalementRegion> a = new ArrayList<ListeSignalementRegion>();
         Connection connex = null;
         try
         {
             connex = con.connexion();
             PreparedStatement pstmt = connex.prepareStatement("select id_signalement,type_signalement,id_region,longitude,latitude,date_signal from signalement s join type_signalement ts on s.id_type_signalement=ts.id_type_signalement where id_region= ?");
             pstmt.setInt(1,idregion);
             ResultSet rs = pstmt.executeQuery();
             while(rs.next())
             {
                
                 ListeSignalementRegion lr = new ListeSignalementRegion(rs.getInt("id_signalement"),rs.getString("type_signalement"),rs.getInt("id_region"),rs.getDouble("longitude"),rs.getDouble("latitude"),rs.getDate("date_signal"));
                 //s.setIdRegion0); = rs.getInt("idregion");
                 a.add(lr);
             }
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
         finally
         {
             con.deconnexion(connex);
         }
        
        return a;
    }
    
    public ArrayList<Statut> getListeStatut()
    {
        ArrayList<Statut> a =  new ArrayList<Statut>();
     Connection connex = null;
        try
        {
            connex = con.connexion();
            PreparedStatement pstmt = connex.prepareStatement("select * from status_signalement");
           
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
            Statut s = new Statut(rs.getInt("id_status_signalement"),rs.getString("status_signalement"));
             a.add(s);
             
                //s.setIdRegion0); = rs.getInt("idregion");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
            con.deconnexion(connex);
        }
    
    return a;
    }
    
    public void updateStatusSignalement(int idsignalement,int idstatus)
    {
        Connection connex = null;
          try
          {
              connex = con.connexion();
              
            // create our java preparedstatement using a sql update query
            PreparedStatement ps = connex.prepareStatement(
              "UPDATE statut_par_signal SET id_status_signalement = ? WHERE id_status_signalement = ?");

            // set the preparedstatement parameters
            ps.setInt(1,idstatus);
            ps.setInt(2,idsignalement);
          

            // call executeUpdate to execute our sql update statement
            ps.executeUpdate();
            ps.close();
          }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        finally
        {
            con.deconnexion(connex);
        }
        
        
    }
    
    
    
    public Boolean checkLoginUser(String login, String mdp)
    {
        Connection cn = null;
        Boolean check = false;
        try {
            cn = con.connexion();
            String motDePasse = formatSha1(mdp, cn);
            PreparedStatement st = cn.prepareStatement("select count(*) as compte from utilisateur where username = ? and mot_de_passe = ?");
            st.setString(1, login);
            st.setString(2, motDePasse);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int compte = rs.getInt("compte");
                if(compte == 1)
                {
                    check = true;
                }
                break;
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally
        {
            con.deconnexion(cn);
        }
        return check;
    }

    public Boolean checkLoginChefDeRegion(String login, String mdp)
    {
        Connection cn = null;
        Boolean check = false;
        try {
            cn = con.connexion();
            String motDePasse = formatSha1(mdp, cn);
            PreparedStatement st = cn.prepareStatement("select count(*) as compte from chefDeRegion where username = ? and mot_de_passe = ?");
            st.setString(1, login);
            st.setString(2, motDePasse);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int compte = rs.getInt("compte");
                if(compte == 1)
                {
                    check = true;
                }
                break;
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally
        {
            con.deconnexion(cn);
        }
        return check;
    }

    public int getIdRegion(String nomRegion)
    {
        int idRegion = 0;
        Connection connex = null;
        try
        {
            connex = con.connexion();
            PreparedStatement pstmt = connex.prepareStatement("select * from region where nom_region = ?");
            pstmt.setString(1, nomRegion);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                idRegion = rs.getInt("idregion");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            con.deconnexion(connex);
        }
        return idRegion;
    }

    public Utilisateur getSimpleUtilisateur(String login, String motDePasse)
    {
        Utilisateur user = null;
        Connection connex = null;
        try
        {
            connex = con.connexion();
            String mdp = formatSha1(motDePasse, connex);
            PreparedStatement pstmt = connex.prepareStatement("select * from utilisateur where username = ? and mot_de_passe = ?");
            pstmt.setString(1, login);
            pstmt.setString(2, mdp);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                user = new Utilisateur(rs.getLong("id_utilisateur"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("naissance"), rs.getString("genre"), rs.getString("username"), rs.getString("mot_de_passe"));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            con.deconnexion(connex);
        }
        return user;
    }

    public void insert(Utilisateur user) throws SQLException
    {
        Connection connex = null;
        try
        {
            connex = con.connexion();
            PreparedStatement pst = connex.prepareStatement("insert into utilisateur values(?, ?, ?, ?, ?, ?, ?)");
            pst.setLong(1, user.getIdUtilisateur());
            pst.setString(2, user.getNom());
            pst.setString(3, user.getPrenom());
            pst.setDate(4, (Date) user.getNaissance());
            pst.setString(5, user.getGenre());
            pst.setString(6, user.getUsername());
            pst.setString(7, user.getMotDePasse());
            pst.executeUpdate();
            connex.commit();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            con.deconnexion(connex);
        }
    }

    public void createToken(Utilisateur user)
    {
        Connection connex = null;
        try
        {
            connex = con.connexion();
            String date = null;
            String requete = "select now() as date";
            Statement stmt = connex.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            if(rs.next())
            {
                date = rs.getString("date");
            }
            String token = "encode(digest('"+user.getNom()+user.getPrenom()+date+"', 'sha1'), 'hex')";
            String sql = "insert into token (id_utilisateur, token) values("+user.getIdUtilisateur()+", "+token+")";
            Statement stmt2 = connex.createStatement();
            stmt2.executeUpdate(sql);
            connex.commit();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            con.deconnexion(connex);
        }
    }

    public String getTokenUtilisateur(long idUtilisateur)
    {
        String token = null;
        Connection connex = null;
        try
        {
            connex = con.connexion();
            PreparedStatement pstmt = connex.prepareStatement("select * from token where id_utilisateur = ?");
            pstmt.setLong(1, idUtilisateur);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                token = rs.getString("token");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            con.deconnexion(connex);
        }
        return token;
    }

    public Boolean verifierToken(long idUtilisateur)
    {
        Boolean verifierToken = false;
        Connection connex = null;
        try
        {
            connex = con.connexion();
            PreparedStatement pstmt = connex.prepareStatement("select count(*) as token from token where id_tilisateur = ?");
            pstmt.setLong(1, idUtilisateur);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                int compter = rs.getInt("token");
                if(compter == 1)
                {
                    verifierToken = true;
                }
                break;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            con.deconnexion(connex);
        }
        return verifierToken;
    }

    public Utilisateur getSimpleUtilisateur2(String token)
    {
        Utilisateur user = null;
        Connection connex = null;
        try
        {
            connex = con.connexion();
            PreparedStatement pstmt = connex.prepareStatement("select * from v_utilisateur where token = ?");
            pstmt.setString(1, token);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                user = new Utilisateur(rs.getLong("id_utilisateur"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("naissance"), rs.getString("genre"), rs.getString("username"), rs.getString("mot_de_passe"));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            con.deconnexion(connex);
        }
        return user;
    }

    public int getIdTypeSignalement(Connection con, String typeSignalement)
    {
        int idTypeSignalement = 0;
        try
        {
            PreparedStatement pstmt = con.prepareStatement("select * from type_signalement where type_signalement = ?");
            pstmt.setString(1, typeSignalement);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                idTypeSignalement = rs.getInt("id_type_signalement");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return idTypeSignalement;
    }

    public Object getDateTime(String dateTime, Connection con)
    {
        Object retour = null;
        try
        {
            String sql = "select "+dateTime+" as dateTime";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                retour = rs.getObject("dateTime");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return retour;
    }

    public int getIdSingnalement(Connection con, Utilisateur user, Date date, Time time)
    {
        int idSignalement = 0;
        try
        {
            PreparedStatement pstmt = con.prepareStatement("select * from signalement where id_utilisateur = ? and date_signal = ? and heure_signal = ?");
            pstmt.setLong(1, user.getIdUtilisateur());
            pstmt.setDate(2, date);
            pstmt.setTime(3, time);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                idSignalement = rs.getInt("id_signalement");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return idSignalement;
    }

    public void insertionPhoto(PhotoSignalement photo) throws SQLException
    {
        Connection connex = null;
        try
        {   
            connex = con.connexion();
            PreparedStatement pstmt = connex.prepareStatement("insert into photoSignalement (id_signalement, nom_photo) values(?, ?)");
            pstmt.setLong(1, photo.getIdSignalement());
            pstmt.setString(2, photo.getNomPhoto());
            pstmt.executeUpdate();
            connex.commit();
        }
        catch(Exception ex)
        {
            connex.rollback();
            ex.printStackTrace();
        }
        finally
        {
            con.deconnexion(connex);
        }
    }

    public String[] formatFile(MultipartFile[] file)
    {
        String[] retour = new String[file.length];
        for(int i = 0; i < file.length; i++)
        {
            retour[i] = file[i].getOriginalFilename();
        }
        return retour;
    }

    public void envoyerSignalement(Utilisateur user, String typeSignalement, double longitude, double latitude, String description, MultipartFile[] photo) throws SQLException
    {
        Connection connex = null;
        try
        {
            connex = con.connexion();
            int idTypSignalement = getIdTypeSignalement(connex, typeSignalement);
            Date date = (Date)getDateTime("current_date", connex);
            Time time = (Time)getDateTime("current_time", connex);
            Signalement signal = new Signalement(idTypSignalement, user.getIdUtilisateur(), longitude, latitude, description, date, time);
            PreparedStatement pstmt = connex.prepareStatement("insert into signalement (id_type_signalement, id_region, id_utilisateur, longitude, latitude, description_signal, date_signal, heure_signal)values(?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, signal.getIdTypeSignalement());
            pstmt.setInt(2, 0);
            pstmt.setLong(3, signal.getIdUtilisateur());
            pstmt.setDouble(4, signal.getLongitude());
            pstmt.setDouble(5, signal.getLatitude());
            pstmt.setString(6, signal.getDescriptionSignal());
            pstmt.setDate(7, signal.getDate());
            pstmt.setTime(8, signal.getHeure());
            pstmt.executeUpdate();
            int idSignalement = getIdSingnalement(connex, user, date, time);
            String[] tabPhoto = formatFile(photo);
            for(int i = 0; i < tabPhoto.length; i++)
            {
                PhotoSignalement photos = new PhotoSignalement(idSignalement, tabPhoto[i]);
                PreparedStatement pstmt2 = connex.prepareStatement("insert into photoSignalement (id_signalement, nom_photo) values(?, ?)");
                pstmt2.setLong(1, photos.getIdSignalement());
                pstmt2.setString(2, photos.getNomPhoto());
                pstmt2.executeUpdate();
            }
            connex.commit();
        }
        catch(Exception ex)
        {
            connex.rollback();
            ex.printStackTrace();
        }
        finally
        {
            con.deconnexion(connex);
        }
    }

    public ArrayList<Signalement> getAllSignalementParRegion(String login, String mdp)
    {
        Connection connex = null;
        ArrayList<Signalement> signalement = new ArrayList<>();
        try
        {
            connex = con.connexion();
            String motDePasse = formatSha1(mdp, connex);
            PreparedStatement pstmt = connex.prepareStatement("select * from v_signalementEffectue where username = ? and mot_de_passe = ?");
            pstmt.setString(1, login);
            pstmt.setString(2, motDePasse);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                Signalement temp = new Signalement(rs.getLong("id_signalement"), rs.getInt("id_type_signalement"), rs.getLong("id_utilisateur"), rs.getDouble("longitude"), rs.getDouble("latitude"), rs.getString("description_signal"), rs.getDate("date_signal"), rs.getTime("heure_signal")); 
                PhotoSignalement[] photoSignalements = getAllPhotoSignalement(rs.getLong("id_signalement"));
                temp.setPhoto(photoSignalements);
                signalement.add(temp);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            con.deconnexion(connex);
        }
        return signalement;
    }

    public ChefDeRegion getSimpleChefDeRegion(ChefDeRegion chefDeRegion)
    {
        ChefDeRegion retour = null;
        Connection connex = null;
        try
        {
            connex = con.connexion();
            String mdp = formatSha1(chefDeRegion.getMotDePasse(), connex);
            PreparedStatement pstmt = connex.prepareStatement("select * from chefderegion where username = ? and mot_de_passe= ? ");
            pstmt.setString(1, chefDeRegion.getUsername());
            pstmt.setString(2, mdp);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                retour = new ChefDeRegion(rs.getInt("id_chef_de_region"), rs.getString("username"), rs.getString("mot_de_passe"));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            con.deconnexion(connex);
        }
        return retour;
    }

    public Boolean verifierTokenChefDeRegion(int idChefDeRegion)
    {
        Boolean verifierToken = false;
        Connection connex = null;
        try
        {
            connex = con.connexion();
            PreparedStatement pstmt = connex.prepareStatement("select count(*) as compte from tokenchefderegion where id_chef_de_region = ?");
            pstmt.setInt(1, idChefDeRegion);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                int compte = rs.getInt("compte");
                if(compte == 1)
                {
                    verifierToken = true;
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            con.deconnexion(connex);
        }
        return verifierToken;
    }

    public void createTokenChefDeRegion(ChefDeRegion chefDeRegion)
    {
        Connection connex = null;
        try
        {
            connex = con.connexion();
            String date = null;
            String requete = "select now() as date";
            Statement stmt = connex.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            if(rs.next())
            {
                date = rs.getString("date");
            }
            String token = "encode(digest('"+chefDeRegion.getUsername()+chefDeRegion.getMotDePasse()+date+"', 'sha1'), 'hex')";
            String sql = "insert into tokenchefderegion (id_chef_de_region, token) values("+chefDeRegion.getIdChefDeRegion()+", "+token+")";
            Statement stmt2 = connex.createStatement();
            stmt2.executeUpdate(sql);
            connex.commit();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            con.deconnexion(connex);
        }
    }

    public String getTokenChefDeRegion(int idChefDeRegion)
    {
        String token = null;
        Connection connex = null;
        try
        {
            connex = con.connexion();
            PreparedStatement pstmt = connex.prepareStatement("select * from tokenchefderegion where id_chef_de_region = ?");
            pstmt.setInt(1, idChefDeRegion);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                token = rs.getString("token");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            con.deconnexion(connex);
        }
        return token;
    }

    public PhotoSignalement[] getAllPhotoSignalement(long idSignalement)
    {
        PhotoSignalement[] photoSignalements = null;
        Connection connex = null;
        ArrayList<Object> list = new ArrayList<>();
        try
        {
            connex = con.connexion();
            PreparedStatement pstmt = connex.prepareStatement("select * from photoSignalement where id_signalement = ?");
            pstmt.setLong(1, idSignalement);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                PhotoSignalement temp = new PhotoSignalement(rs.getInt("id_photo_signalement"), rs.getInt("id_signalement"), rs.getString("nom_photo"));
                list.add(temp);
            }
            photoSignalements = new PhotoSignalement[list.size()];
            for(int i = 0; i < list.size(); i++)
            {
                photoSignalements[i] = (PhotoSignalement) list.get(i);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            con.deconnexion(connex);
        }
        return photoSignalements;
    }

    public Signalement[] rechercherSignalement(Date debut, Date fin)
    {
        Signalement[] retour = null;
        Connection connex = null;
        ArrayList<Signalement> list = new ArrayList<>();
        try
        {
            connex = con.connexion();
            PreparedStatement pstmt = connex.prepareStatement("select * from v_views where date_signal >= ? and date_signal <= ?");
            pstmt.setDate(1, debut);
            pstmt.setDate(2, fin);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                PhotoSignalement[] tabPhoto = getAllPhotoSignalement(rs.getLong("id_signalement"));
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
            con.deconnexion(connex);
        }
        return retour;
    }
}
