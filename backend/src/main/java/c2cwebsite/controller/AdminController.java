package c2cwebsite.controller;

import c2cwebsite.model.Admin;
import c2cwebsite.model.User;
import c2cwebsite.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin adminJson) {
        Admin user = adminService.registerAdmin(adminJson);
        if (user != null) {
            return ResponseEntity.ok("Utilisateur créé avec succès !");
        } else {
            return ResponseEntity.status(400).body("Erreur lors de la création de l'utilisateur");
        }
    }


    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin userJson) {
        String reponse = adminService.loginAdmin(userJson.getPseudo(), userJson.getMdp());

        if (reponse != null) {
            return ResponseEntity.ok(reponse);
        } else {
            return ResponseEntity.status(400).body("Erreur lors de la connexion");
        }
    }

    @GetMapping("/commission")
    public float calculateCommission() {
        return adminService.calculateCommission();
    }
}
