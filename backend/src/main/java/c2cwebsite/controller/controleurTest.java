package c2cwebsite.controller;

import c2cwebsite.pojo.Utilisateur;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class controleurTest {

    @PostMapping("/test")
    public ResponseEntity<String> test(@RequestParam("pPseudo") String pseudo,
                                       @RequestParam("pMdp") String mdp,
                                       @RequestParam("pVilleResidence") String villeResidence) {
        System.out.println("Pseudo : " + pseudo);
        System.out.println("Ville de résidence : " + villeResidence);
        System.out.println("Mot de passe : " + mdp);

        return ResponseEntity.ok("Test réussi");
    }
}
