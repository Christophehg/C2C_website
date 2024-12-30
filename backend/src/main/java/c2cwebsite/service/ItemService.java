package c2cwebsite.service;

import c2cwebsite.DTO.ItemDTO;
import c2cwebsite.model.Item;
import c2cwebsite.model.User;
import c2cwebsite.repository.ItemRepository;
import c2cwebsite.repository.UserRepository;
import c2cwebsite.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public List<ItemDTO> getItemsNonVendus() {
        List<Item> items = itemRepository.findByVendu(false);

        List<ItemDTO> itemDTOs = new ArrayList<>();

        // Mapper chaque objet Item en un ItemDTO
        for (Item item : items) {
            ItemDTO itemDTO = new ItemDTO(
                    item.getNumeroE(),
                    item.getNom(),
                    item.getDescription(),
                    item.getPrix(),
                    item.isVendu(),
                    item.getProprietaire().getPseudo()  // Inclure le nom du vendeur
            );
            itemDTOs.add(itemDTO);
        }

//        return itemRepository.findByVendu(false);
        return itemDTOs;
    }

    public Item buyItem(String token, int itemId) {
        String pseudo = jwtService.extractUserName(token);
        User user = userRepository.findByPseudo(pseudo);
        if(user == null) {
            throw new RuntimeException("User not found from dataBase");
        }
        System.out.println("item id: " + itemId);
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Objet non trouvé."));

        if (item.getAcquereur() != null) {
            throw new IllegalStateException("Objet déjà vendu.");
        }

        boolean isUserOwnItme = user.ownItem(item);
        if (isUserOwnItme) {
            throw new IllegalStateException("Vous ne pouvez pas acheter votre propre objet.");
        }

        item.setAcquereur(user);
        item.setVendu(true);
        user.addItemAchete(item);
        return itemRepository.save(item);
    }

}
