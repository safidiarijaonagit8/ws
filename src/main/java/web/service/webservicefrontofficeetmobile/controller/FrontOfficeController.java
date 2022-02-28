package web.service.webservicefrontofficeetmobile.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.webservicefrontofficeetmobile.model.TypeSignalement;
import web.service.webservicefrontofficeetmobile.fonctionsBdd.FonctionsBdd;
import web.service.webservicefrontofficeetmobile.model.ChefDeRegion;
import web.service.webservicefrontofficeetmobile.model.Fonctions;
import web.service.webservicefrontofficeetmobile.model.Recherche;
import web.service.webservicefrontofficeetmobile.model.Signalement;
import web.service.webservicefrontofficeetmobile.model.Statut;
import web.service.webservicefrontofficeetmobile.model.ListeSignalementRegion;
import web.service.webservicefrontofficeetmobile.model.ChefRegionAvecidRegion;
import web.service.webservicefrontofficeetmobile.repository.FrontOfficeRepository;
import web.service.webservicefrontofficeetmobile.repository.FrontOfficeRepositoryTypeSignalement;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/frontOffice/")
public class FrontOfficeController extends FonctionsBdd
{
    @Autowired
    FrontOfficeRepository fontOfficeRepository;

    @Autowired
    FrontOfficeRepositoryTypeSignalement fontOfficeRepositoryTypeSignalement;
    
    @PostMapping("utilisateurs/loginChefDeRegion")
    public HashMap<String, Object> verifierLoginChefDeRegion(@RequestBody ChefDeRegion chefDeRegion)
    {
        HashMap<String, Object> reponse = new HashMap<>();
        Fonctions f = new Fonctions();
        Boolean verifiantAccent = f.verifiantAccent(chefDeRegion.getMotDePasse());
        Boolean verifierMail = f.verifierMail(chefDeRegion.getMotDePasse());
        if(chefDeRegion.getMotDePasse().length() >= 8 && verifiantAccent == false && verifierMail == true)
        {
            Boolean condition = checkLoginChefDeRegion(chefDeRegion.getUsername(), chefDeRegion.getMotDePasse());
            if(condition == true)
            {
                ArrayList<Signalement> signalement = getAllSignalementParRegion(chefDeRegion.getUsername(), chefDeRegion.getMotDePasse());
                ChefDeRegion chefDeRegion2 = getSimpleChefDeRegion(chefDeRegion);
                Boolean verifierToken = verifierTokenChefDeRegion(chefDeRegion2.getIdChefDeRegion());
                if(verifierToken == true)
                {
                    String token = getTokenChefDeRegion(chefDeRegion2.getIdChefDeRegion());
                    reponse.put("Accès autorisée", true);
                    reponse.put("Votre token", token);
                }
                else
                {
                    createTokenChefDeRegion(chefDeRegion2);
                    String token = getTokenChefDeRegion(chefDeRegion2.getIdChefDeRegion());
                    reponse.put("Accès autorisée", true);
                    reponse.put("Votre token", token);
                }
                reponse.put("Signalement", signalement);
            }
            else
            {
                reponse.put("Accès refusée", false);
            }
        }
        else
        {
            reponse.put("Le mot de passe doit être superieur ou égale à 8 caractère et ne support pas les caractères spéciaux.", false);
        }
        return reponse;
    }

    @GetMapping("utilisateurs/typeSignalements")
    public List<TypeSignalement> getAllTypeSignalement()
    {
        return this.fontOfficeRepositoryTypeSignalement.findAll();
    }

    @GetMapping("utilisateurs/listechefregion")
    public ArrayList<ChefRegionAvecidRegion> getListeChefRegionId()
    {
        ArrayList<ChefRegionAvecidRegion> a = getListeChefRegionId();
        return a;
    }
    
    @GetMapping("utilisateurs/listechefregion/{id}")
    public List<ListeSignalementRegion> getListeSignalement(@PathVariable(value = "id") int id)

   
    {
         List<ListeSignalementRegion> a = getListeSignalementRegion(id);
         
        return a;
    }
    
    @GetMapping("utilisateurs/listestatut")
    public ArrayList<Statut> getListeStatut()
    {
         ArrayList<Statut> a = getListeStatut();
         
        return a;
    }
    
    @PostMapping("utilisateurs/updatestatut/{idsign}/{idstatus}")
    public void updateStatut(@PathVariable(value = "idsign") int idsign,@PathVariable(value = "idstatus") int idstatus)
    {
         updateStatusSignalement(idsign,idstatus);
         
        
    }
    

    @PostMapping("utilisateurs/recherche/signalements")
    public Signalement[] rechercherAvance(@RequestBody Recherche recherche)
    {
        Signalement[] signal = null;
        FonctionsBdd f  = new FonctionsBdd();
        if(recherche.getDate_signal() != null && recherche.getDate_affectation() != null)
        {
            signal = f.rechercherSignalement(recherche.getDate_signal(), recherche.getDate_affectation());
        }
        else if(recherche.getDate_signal() != null)
        {
            signal = recherche.getAllSignalement();
        }
        else if(recherche.getDate_affectation() != null)
        {
            Date temp = null;
            recherche.setDate_signal(recherche.getDate_affectation());
            recherche.setDate_affectation(temp);
            signal = recherche.getAllSignalement();
        }
        return signal;
    }
}



