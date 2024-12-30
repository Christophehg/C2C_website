package c2cwebsite.controller;

import c2cwebsite.model.User;
import c2cwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public ResponseEntity<?> register(@RequestBody User userJson) {
        User user = userService.register(userJson);
        if (user != null) {
            return ResponseEntity.ok("Utilisateur créé avec succès !");
        } else {
            return ResponseEntity.status(400).body("Erreur lors de la création de l'utilisateur");
        }
    }

    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userJson) {
          String reponse = userService.login(userJson.getPseudo(), userJson.getMdp());

            if (reponse != null) {
                return ResponseEntity.ok(reponse);
            } else {
                return ResponseEntity.status(400).body("Erreur lors de la connexion");
            }
//        User user = userService.login(userJson.getPseudo(), userJson.getMdp());
//
//
//        if (user != null) {
//            return ResponseEntity.ok("Connexion réussie !" + user);
//        } else {
//            return ResponseEntity.status(400).body("Erreur lors de la connexion");
//        }
    }
}
