package web.service.webservicefrontofficeetmobile.model;

public class ChefRegionAvecidRegion {
	
	int idRegion;
    String username;
    String MotDePasse;
	public int getIdDeRegion() {
		return idRegion;
	}
	public void setIdRegion(int idChefDeRegion) {
		this.idRegion = idChefDeRegion;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMotDePasse() {
		return MotDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		MotDePasse = motDePasse;
	}
	public ChefRegionAvecidRegion(int idChefDeRegion, String username, String motDePasse) {
		super();
		this.idRegion = idChefDeRegion;
		this.username = username;
		MotDePasse = motDePasse;
	}
   
	public ChefRegionAvecidRegion()
	{
		
	}
}
