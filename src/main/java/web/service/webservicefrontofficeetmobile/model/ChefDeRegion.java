package web.service.webservicefrontofficeetmobile.model;

public class ChefDeRegion 
{

    private int idChefDeRegion;
    private String username;
    private String MotDePasse;

    public int getIdChefDeRegion() 
    {
        return idChefDeRegion;
    }

    public void setIdChefDeRegion(int ChefDeRegion) 
    {
        this.idChefDeRegion = ChefDeRegion;
    }

    public String getUsername() 
    {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getMotDePasse() 
    {
        return MotDePasse;
    }

    public void setMotDePasse(String MotDePasse) 
    {
        this.MotDePasse = MotDePasse;
    }

    public ChefDeRegion(int ChefDeRegion, String username, String MotDePasse) 
    {
        this.idChefDeRegion = ChefDeRegion;
        this.username = username;
        this.MotDePasse = MotDePasse;
    }

    public ChefDeRegion() 
    {
    }
}
