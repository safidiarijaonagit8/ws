package web.service.webservicefrontofficeetmobile.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_utilisateur;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@Column(name = "naissance")
	private Date naissance;
	@Column(name = "genre")
	private String genre;
	@Column(name = "username")
	private String username;
	@Column(name = "mot_de_passe")
	private String mot_de_passe;

	public void setIdUtilisateur(long id)
	{
		this.id_utilisateur = id;
	}

	public long getIdUtilisateur()
	{
		return this.id_utilisateur;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public String getNom()
	{
		return this.nom;
	}

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	public String getPrenom()
	{
		return this.prenom;
	}

	public void setNaissance(Date naissance)
	{
		this.naissance = naissance;
	}

	public Date getNaissance()
	{
		return this.naissance;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public String getGenre()
	{
		return this.genre;
	}

	public void setUsername(String login)
	{
		this.username = login;
	}

	public String getUsername()
	{
		return this.username;
	}

	public void setMotDePasse(String motDePasse)
	{
		this.mot_de_passe = motDePasse;
	}

	public String getMotDePasse()
	{
		return this.mot_de_passe;
	}

	public Utilisateur()
	{

	}

	public Utilisateur(String nom, String prenom, Date naissance, String genre, String login, String motDePasse)
	{
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setNaissance(naissance);
		this.setGenre(genre);
		this.setUsername(login);
		this.setMotDePasse(motDePasse);
	}

	public Utilisateur(long id, String nom, String prenom, Date naissance, String genre, String login, String motDePasse)
	{
		this.setIdUtilisateur(id);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setNaissance(naissance);
		this.setGenre(genre);
		this.setUsername(login);
		this.setMotDePasse(motDePasse);
	}
}
