package web.service.webservicefrontofficeetmobile.model;

public class Fonctions 
{
    public Boolean verifiantAccent(String mdp)
    {
        Boolean retour = false;
        String accent = "éèêôîïà";
        char[] tabAccent = accent.toCharArray();
        char[] tabMdp = mdp.toCharArray();
        for(int i = 0; i < tabMdp.length; i++)
        {
            for(int j = 0; j < tabAccent.length; j++)
            {
                if(tabMdp[i] == tabAccent[j])
                {
                    retour = true;
                    break;
                }
            }
            if(retour == true)
            {
                break;
            }
        }
        return retour;
    }

    public Boolean verifierMail(String mail)
    {
        Boolean retour = null;
        int compteur = 0;
        char[] tabMail = mail.toCharArray();
        for(int i = 0; i < tabMail.length; i++)
        {
            if(tabMail[i] == '@' || tabMail[i] == '.')
            {
                compteur++;
            }
            else if(compteur == 2)
            {
                retour = true;
                break;
            }
        }
        return retour;
    }
}
