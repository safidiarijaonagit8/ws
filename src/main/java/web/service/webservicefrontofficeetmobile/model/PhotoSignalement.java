package web.service.webservicefrontofficeetmobile.model;

public class PhotoSignalement 
{
    private int idPhotoSignalement;
    private long idSignalement;
    private String nomPhoto;

    public int getIdPhotoSignalement() 
    {
        return idPhotoSignalement;
    }

    public void setIdPhotoSignalement(int idPhotoSignalement) 
    {
        this.idPhotoSignalement = idPhotoSignalement;
    }

    public long getIdSignalement() 
    {
        return idSignalement;
    }

    public void setIdSignalement(long idSignalement) 
    {
        this.idSignalement = idSignalement;
    }

    public String getNomPhoto() 
    {
        return nomPhoto;
    }

    public void setNomPhoto(String nomPhoto) 
    {
        this.nomPhoto = nomPhoto;
    }

    public PhotoSignalement(int idPhotoSignalement, int idSignalement, String nomPhoto) 
    {
        this.idPhotoSignalement = idPhotoSignalement;
        this.idSignalement = idSignalement;
        this.nomPhoto = nomPhoto;
    }

    public PhotoSignalement(long idSignalement, String nomPhoto) 
    {
        this.idSignalement = idSignalement;
        this.nomPhoto = nomPhoto;
    }

    public PhotoSignalement() 
    {

    }
}