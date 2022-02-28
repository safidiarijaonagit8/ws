package web.service.webservicefrontofficeetmobile.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;
import web.service.webservicefrontofficeetmobile.exception.ResourceNotFoundException;
import web.service.webservicefrontofficeetmobile.fonctionsBdd.Connexion;
import web.service.webservicefrontofficeetmobile.fonctionsBdd.FonctionsBdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import web.service.webservicefrontofficeetmobile.model.Fonctions;
import web.service.webservicefrontofficeetmobile.model.Notifications;
import web.service.webservicefrontofficeetmobile.model.Utilisateur;
import web.service.webservicefrontofficeetmobile.repository.FrontOfficeRepositoryNotif;
import web.service.webservicefrontofficeetmobile.repository.MobileRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/mobile/")
public class MobileController extends FonctionsBdd
{
    @Autowired
    MobileRepository utilisateurRepository;

    @Autowired
    FrontOfficeRepositoryNotif notifRepository;

    @GetMapping("utilisateurs")
    public List<Utilisateur> getAllUtilisateur()
    {
        return this.utilisateurRepository.findAll();
    }

    @GetMapping("utilisateurs/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable(value = "id") Long idUtilisateur) 
    throws ResourceNotFoundException
    {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found for this id :"+idUtilisateur));
        return ResponseEntity.ok().body(utilisateur);
    }

    @PostMapping("utilisateurs/add")
    public HashMap<String, Boolean> insertionUtilisateur(@RequestBody Utilisateur utilisateur) throws SQLException
    {
        Fonctions f = new Fonctions();
        HashMap<String, Boolean> reponse = new HashMap<>();
        Boolean verifiantAccent = f.verifiantAccent(utilisateur.getMotDePasse());
        Boolean verifiantMail = f.verifierMail(utilisateur.getUsername());
        if(utilisateur.getMotDePasse().length() >= 8 && verifiantAccent == false && verifiantMail == true)
        {
            String formatDate = utilisateur.getNaissance().toString();
            String[] splitter = formatDate.split("-");
            int condition = Integer.parseInt(splitter[0]);
            Date date = null;
            if(condition>=1 && condition<=31)
            {
                String date2 = splitter[2]+"-"+splitter[1]+"-"+splitter[0];
                date = Date.valueOf(date2);
            }
            else
            {
                date = Date.valueOf(formatDate);
            }
            Connexion connex = new Connexion();
            String mdp = formatSha1(utilisateur.getMotDePasse(), connex.connexion());
            Utilisateur utilisateurs = new Utilisateur(utilisateur.getNom(), utilisateur.getPrenom(), date, utilisateur.getGenre(), utilisateur.getUsername(), mdp);
            this.utilisateurRepository.save(utilisateurs);
            reponse.put("create user succeffully", true);
        }
        else
        {
            reponse.put("Le mot de passe doit être superieur ou égale à 8 caractère et ne support pas les caractères spéciaux.", false);
        }
        return reponse;
    }

    @PutMapping("utilisateurs/modif/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable(value = "id") Long idUtilisateur, @Valid @RequestBody Utilisateur detailsUtilisateur) throws ResourceNotFoundException
    {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
        .orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found for this id :"+idUtilisateur));
        utilisateur.setNom(detailsUtilisateur.getNom());
        utilisateur.setPrenom(detailsUtilisateur.getPrenom());
        utilisateur.setNaissance(detailsUtilisateur.getNaissance());
        utilisateur.setGenre(detailsUtilisateur.getGenre());
        utilisateur.setUsername(detailsUtilisateur.getUsername());
        utilisateur.setMotDePasse(detailsUtilisateur.getMotDePasse());
        return ResponseEntity.ok(this.utilisateurRepository.save(utilisateur));
    }

    @DeleteMapping("utilisateurs/supp/{id}")
    public HashMap<String, Boolean>  deleteUtilisateur(@PathVariable(value = "id") Long idUtilisateur) throws ResourceNotFoundException
    {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
        .orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found for this id :"+idUtilisateur));
        this.utilisateurRepository.delete(utilisateur);
        HashMap<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", true);
        return reponse;
    }

    @PostMapping("utilisateurs/loginMobile")
    public HashMap<String, Object> verifierLoginUtilisateur(@RequestBody Utilisateur utilisateur)
    {
        HashMap<String, Object> reponse = new HashMap<>();
        Fonctions f = new Fonctions();
        Boolean verifiantAccent = f.verifiantAccent(utilisateur.getMotDePasse());
        Boolean verifiantMail = f.verifierMail(utilisateur.getUsername());
        if(utilisateur.getMotDePasse().length() >= 8 && verifiantAccent == false && verifiantMail == true)
        {
            Boolean condition = checkLoginUser(utilisateur.getUsername(), utilisateur.getMotDePasse());
            if(condition == true)
            {
                Utilisateur user = getSimpleUtilisateur(utilisateur.getUsername(), utilisateur.getMotDePasse());
                Boolean verifierToken = verifierToken(user.getIdUtilisateur());
                if(verifierToken == true)
                {
                    String token = getTokenUtilisateur(user.getIdUtilisateur());
                    reponse.put("Accès autorisée", true);
                    reponse.put("Votre token", token);
                }
                else
                {
                    createToken(user);
                    String token = getTokenUtilisateur(user.getIdUtilisateur());
                    reponse.put("Accès autorisée", true);
                    reponse.put("Votre token", token);
                }
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
    
    @PostMapping("utilisateurs/envoiSignalement")
    public HashMap<String, Boolean> envoiSignalement(@RequestParam(value = "token") String token, @RequestParam(value = "typeSignalement") String typeSignalement, @RequestParam(value = "longitude") double longitude, @RequestParam(value = "latitude") double latitude, @RequestParam(value = "description") String description, @RequestParam(value = "photo") MultipartFile[] photo) throws SQLException
    {
        HashMap<String, Boolean> reponse = new HashMap<>();
        Utilisateur user = getSimpleUtilisateur2(token);
        envoyerSignalement(user, typeSignalement, longitude, latitude, description, photo);
        reponse.put("Signalement effectue", true);
        return reponse;
    }

    @GetMapping("utilisateurs/notifications")
    public List<Notifications> getAllNotification()
    {
        return this.notifRepository.findAll();
    }

    @GetMapping("utilisateurs/notifications/{id}")
    public ResponseEntity<Notifications> getNotificationById(@PathVariable(value = "id") Long idNotification) throws ResourceNotFoundException
    {
        Notifications notif = notifRepository.findById(idNotification)
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found for this id :"+idNotification));
        return ResponseEntity.ok().body(notif);
    }
}