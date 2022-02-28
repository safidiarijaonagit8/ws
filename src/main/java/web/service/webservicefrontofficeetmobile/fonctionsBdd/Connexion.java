package web.service.webservicefrontofficeetmobile.fonctionsBdd;

import java.sql.*;

public class Connexion 
{
    public Connection connexion()
    {
	    Connection con = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://ec2-54-146-116-84.compute-1.amazonaws.com:5432/d7b17g8hm4oupa", "fpenyqdveiwkue", "b4173eef32c2db2fc544146ce8a58cf94920a792a7ca1cbf4b9eea1ed8067cc3");
            con.setAutoCommit(false);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return con;
    }

    public void deconnexion(Connection con)
    {
        try
        {
            con.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
