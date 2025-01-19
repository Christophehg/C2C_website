package c2cwebsite.controller;

import c2cwebsite.DTO.ItemDTO;
import c2cwebsite.model.Item;
import c2cwebsite.service.Interfaces.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private IItemService itemService;


    @PostMapping("/add")
    public ResponseEntity<?> addItem(@RequestHeader("Authorization") String token, @RequestBody Item itemJson) {
        String realToken = token.substring(7); // Récupère le token sans le "Bearer "
        // 1. Appeler le service pour ajouter l'item
        Item item = itemService.addItem(realToken, itemJson);

        // 2. Vérifier si l'item a été créé avec succès
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.CREATED); // Renvoie l'item créé et un statut 201
        } else {
            return new ResponseEntity<>("Erreur lors de la création de l'élément", HttpStatus.BAD_REQUEST); // Si une erreur se produit
        }
    }

    @PostMapping("/buy/{itemId}")
    public ResponseEntity<?> buyItem(@RequestHeader("Authorization") String token, @PathVariable int itemId) {
        String realToken = token.substring(7);
        Item success = itemService.buyItem(realToken, itemId);
        if (success != null) {
            return ResponseEntity.ok("Item acheté avec succès");
        } else {
            return ResponseEntity.status(400).body("Erreur lors de l'achat de l'item");
        }
    }

    @GetMapping("/itemsNonVendus")
    public ResponseEntity<?> getItemsNonVendus() {
        List<ItemDTO> items = itemService.getItemsNonVendus();
        if (items == null) {
            return ResponseEntity.status(400).body("Erreur lors de la récupération des items");
        }
        else {
            return ResponseEntity.ok(items);
        }
    }

    @PostMapping("/changerEtat/{itemId}")
    public ResponseEntity<?> changerEtat(@RequestHeader("Authorization") String token, @PathVariable int itemId) {
        String realToken = token.substring(7);
        Item success = itemService.changeEtatItem(realToken, itemId);
        if (success != null) {
            return ResponseEntity.ok("Item acheté avec succès");
        } else {
            return ResponseEntity.status(400).body("Erreur lors de l'achat de l'item");
        }
    }

}
