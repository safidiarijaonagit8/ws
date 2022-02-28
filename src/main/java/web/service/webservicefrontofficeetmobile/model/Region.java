package web.service.webservicefrontofficeetmobile.model;

public class Region 
{
    private int idRegion;
    private int ChefDeRegion;
    private String nomRegion;

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public int getChefDeRegion() {
        return ChefDeRegion;
    }

    public void setChefDeRegion(int ChefDeRegion) {
        this.ChefDeRegion = ChefDeRegion;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    public Region(int idRegion, int ChefDeRegion, String nomRegion) {
        this.idRegion = idRegion;
        this.ChefDeRegion = ChefDeRegion;
        this.nomRegion = nomRegion;
    }

    public Region() {
    }
}
