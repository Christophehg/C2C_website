package c2cwebsite.controller;

import c2cwebsite.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class testController {

    @PostMapping("/test")
    public ResponseEntity<String> test(@RequestBody User user) {
        System.out.println(user);
        System.out.println("Pseudo : " + user.getPseudo());
        System.out.println("Ville de résidence : " + user.getVilleResidence());
        System.out.println("Mot de passe : " + user.getMdp());

        return ResponseEntity.ok("Test réussi");
    }
}
