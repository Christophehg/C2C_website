package c2cwebsite.controller;

import c2cwebsite.model.Item;
import c2cwebsite.model.User;
import c2cwebsite.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
          List<String> reponse = userService.login(userJson.getPseudo(), userJson.getMdp());

            if (reponse != null) {
                return ResponseEntity.ok(reponse);
            } else {
                return ResponseEntity.status(400).body("Erreur lors de la connexion");
            }
    }
    // Méthode pour la déconnexion
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // Si tu utilises un token JWT, tu peux ajouter une logique pour invalider le token (par exemple, l'ajouter à une liste noire)
        // Par exemple, si tu utilises un cookie, tu peux le supprimer ici :
        // response.setHeader("Set-Cookie", "token=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/; HttpOnly");

        // Si tu utilises un mécanisme de session (Spring Security, etc.), tu peux invalider la session de l'utilisateur :
        request.getSession().invalidate();  // Invalide la session côté serveur

        // Réponse de déconnexion
        return ResponseEntity.ok("Déconnexion réussie.");
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
