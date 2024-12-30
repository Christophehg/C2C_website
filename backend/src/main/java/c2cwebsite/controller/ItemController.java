package c2cwebsite.controller;

import c2cwebsite.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;


//    @PostMapping("/add")
//    public ResponseEntity<Item> addItem(@RequestBody ItemDto itemDto) {
//        Item item = itemService.ajouterItem(itemDto.getDescription(), itemDto.getPrix(), itemDto.getPseudoProprietaire());
//        return new ResponseEntity<>(item, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/sell/{itemId}")
//    public ResponseEntity<String> sellItem(@PathVariable Long itemId, @RequestBody SellItemDto sellItemDto) {
//        boolean success = itemService.vendreItem(itemId, sellItemDto.getPseudoAcheteur());
//        if (success) {
//            return new ResponseEntity<>("Item vendu avec succès", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Échec de la vente", HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("/available")
//    public ResponseEntity<List<Item>> getAvailableItems(@RequestParam String keyword) {
//        List<Item> items = itemService.rechercherItemsDisponibles(keyword);
//        return new ResponseEntity<>(items, HttpStatus.OK);
//    }
}
