package c2cwebsite.service;

import c2cwebsite.model.Item;
import c2cwebsite.model.User;
import c2cwebsite.repository.ItemRepository;
import c2cwebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    public Item addItem(Item item, String pseudo) {
        User user = userRepository.findByPseudo(pseudo);
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        Item newItem = new Item(item.getNom(), item.getDescription(), item.getPrix(), user.getPseudo());
        newItem.setVendu(false);
        newItem.setAcquereur("");
        newItem.setProprietaire(user.getPseudo());
        user.addItemAVendre(item);

        return itemRepository.save(item);
    }

    public boolean sellItem(Long itemId, String pseudoAcheteur) {
        Item item = itemRepository.findById(Math.toIntExact(itemId))
                .orElseThrow(() -> new IllegalArgumentException("Objet non trouvé."));
        if (item.getAcquereur() != null) {
            throw new IllegalStateException("Objet déjà vendu.");
        }

        User acquereur = userRepository.findByPseudo(pseudoAcheteur);

        item.setAcquereur(acquereur.getPseudo());
        item.setVendu(true);
        acquereur.addItemAchete(item);
        itemRepository.save(item);
        return true;
    }
}
