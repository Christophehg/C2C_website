package c2cwebsite.model;

import c2cwebsite.pojo.Item;
import c2cwebsite.pojo.Utilisateur;

import java.util.List;

public interface ISiteVente {

    public Utilisateur creationUtilisateur(String pseudo, String mdp, String villeResidence);

    public Utilisateur verifierConnextion(String pseudo, String mdp);

    public Item ajouterItem(String nom, String description, float prix, Utilisateur utilisateur);

    public boolean vendreItem(Item item, Utilisateur propritaireVente, Utilisateur acquereur);

    public float calculerCA();

    public List<Item> getItemsNonVendus();

    public List<Item> getItemsNonVendus(Utilisateur utilisateur);

    public List<Item> getItemsVendus(Utilisateur utilisateur);

}
