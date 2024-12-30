package c2cwebsite.controller.old;

import c2cwebsite.model.old.ISiteVente;
import c2cwebsite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class controleurCreationUtilisateur {

    @Autowired
    ISiteVente siteVente;

    @PostMapping("/inscription")
    public ResponseEntity<?> creationUtilisateur(@RequestBody User userJson) {


        User user = siteVente.inscription(userJson.getPseudo(), userJson.getMdp(), userJson.getVilleResidence());

        if (user != null) {
            return ResponseEntity.ok("Utilisateur créé avec succès !");
        } else {
            return ResponseEntity.status(400).body("Erreur lors de la création de l'utilisateur");
        }
    }



}
