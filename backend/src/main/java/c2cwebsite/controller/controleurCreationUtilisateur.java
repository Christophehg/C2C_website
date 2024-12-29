package c2cwebsite.controller;

import c2cwebsite.model.ISiteVente;
import c2cwebsite.pojo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class controleurCreationUtilisateur {

    @Autowired
    ISiteVente siteVente;


    // On attend des données en POST au format JSON (avec @RequestBody)
    @PostMapping("/CreationUtilisateur")
    public ResponseEntity<?> creationUtilisateur(@RequestParam("pPseudo") String pseudo,
                                                 @RequestParam("pMdp") String mdp,
                                                 @RequestParam("pVilleResidence") String villeResidence) {

        // Crée l'utilisateur en utilisant le service
        Utilisateur user = siteVente.creationUtilisateur(pseudo, mdp, villeResidence);

        // Si tout se passe bien, on renvoie un message de succès
        if (user != null) {
            return ResponseEntity.ok("Utilisateur créé avec succès !");
        } else {
            // Si une erreur survient, on renvoie un message d'erreur
            return ResponseEntity.status(400).body("Erreur lors de la création de l'utilisateur");
        }
    }



}
