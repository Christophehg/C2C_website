package c2cwebsite.model.old;

import c2cwebsite.model.Item;
import c2cwebsite.model.User;

import java.util.List;

public interface ISiteVente {

    public User inscription(String pseudo, String mdp, String villeResidence);

    public User verifierConnextion(String pseudo, String mdp);

    public Item ajouterItem(String nom, String description, float prix, User user);

    public boolean vendreItem(Item item, User propritaireVente, User acquereur);

    public float calculerCA();

    public List<Item> getItemsNonVendus();

    public List<Item> getItemsNonVendus(User user);

    public List<Item> getItemsVendus(User user);

}
