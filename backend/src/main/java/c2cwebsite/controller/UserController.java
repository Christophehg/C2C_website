package c2cwebsite.controller;

import c2cwebsite.model.Item;
import c2cwebsite.model.User;
import c2cwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
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
    }

    @RequestMapping("/mesItems")
    public ResponseEntity<?> items(@RequestHeader("Authorization") String token){
        String realToken = token.substring(7);
        List<Item> items = userService.getMesItems(realToken);
        if (items == null) {
            return ResponseEntity.status(400).body("Erreur lors de la récupération des items");
        }
        else {
            return ResponseEntity.ok(items);
        }
    }

    @RequestMapping("/mesItemsAchetes")
    public ResponseEntity<?> itemsAchetes(@RequestHeader("Authorization") String token){
        String realToken = token.substring(7);
        List<Item> items = userService.getMesItemsAchetes(realToken);
        if (items == null) {
            return ResponseEntity.status(400).body("Erreur lors de la récupération des items");
        }
        else {
            return ResponseEntity.ok(items);
        }
    }

}
