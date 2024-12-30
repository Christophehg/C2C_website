package c2cwebsite.service;

import c2cwebsite.model.Item;
import c2cwebsite.model.User;
import c2cwebsite.repository.ItemRepository;
import c2cwebsite.repository.UserRepository;
import c2cwebsite.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    public Item addItem(String token, Item item) {
        //Bearer token
        System.out.println("Adding item");
        String pseudo = jwtService.extractUserName(token);


        if (pseudo == null) {
            throw new RuntimeException("User not found from token");
        }

        User user = userRepository.findByPseudo(pseudo);
        if(user == null) {
            throw new RuntimeException("User not found from dataBase");
        }
        System.out.println("User found: " + user.getPseudo());

        Item newItem = new Item(item.getNom(), item.getDescription(), item.getPrix(), user);
        newItem.setVendu(false);
        newItem.setAcquereur(null);
        user.addItemAVendre(newItem);

        return itemRepository.save(newItem);
    }


    public List<Item> getItemsNonVendus() {
        return itemRepository.findByVendu(false);
    }

    public Item buyItem(String token, Long itemId) {
        String pseudo = jwtService.extractUserName(token);
        User user = userRepository.findByPseudo(pseudo);
        if(user == null) {
            throw new RuntimeException("User not found from dataBase");
        }

        Item item = itemRepository.findById(Math.toIntExact(itemId))
                .orElseThrow(() -> new IllegalArgumentException("Objet non trouvé."));

        if (item.getAcquereur() != null) {
            throw new IllegalStateException("Objet déjà vendu.");
        }

        item.setAcquereur(user);
        item.setVendu(true);
        user.addItemAchete(item);
        return itemRepository.save(item);
    }

}
