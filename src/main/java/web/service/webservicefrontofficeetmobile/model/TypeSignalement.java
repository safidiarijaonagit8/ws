package web.service.webservicefrontofficeetmobile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "type_signalement")
public class TypeSignalement 
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_signalement")
    private long id_type_signalement;
    @Column(name = "type_signalement")
    private String type_signalement;
    @Column(name = "couleur_signalement")
    private String couleur_signalement;

    public long getId_type_signalement() 
    {
        return this.id_type_signalement;
    }

    public void setId_type_signalement(long id_type_signalement) 
    {
        this.id_type_signalement = id_type_signalement;
    }

    public String getType_signalement() 
    {
        return this.type_signalement;
    }

    public void setType_signalement(String type_signalement) 
    {
        this.type_signalement = type_signalement;
    }
    
    public String getCouleur_signalement() 
    {
        return this.couleur_signalement;
    }

    public void setCouleur_signalement(String couleur_signalement) 
    {
        this.couleur_signalement = couleur_signalement;
    }

    public TypeSignalement()
    {

    }
}
