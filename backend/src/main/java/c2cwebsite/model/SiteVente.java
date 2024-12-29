package c2cwebsite.model;

import c2cwebsite.persistance.IPersistanceSiteVente;
import c2cwebsite.pojo.Administrateur;
import c2cwebsite.pojo.Item;
import c2cwebsite.pojo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SiteVente implements ISiteVente {
    private final static int COMMISION = 10;

    @Autowired
    private IPersistanceSiteVente persistanceSiteVente;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private List<Utilisateur> utilisateurList = new ArrayList<Utilisateur>();
    private List<Administrateur> administrateurList = new ArrayList<Administrateur>();


    @Override
    public Utilisateur creationUtilisateur(String pseudo, String mdp, String villeResidence) {
        String mdpHache = passwordEncoder.encode(mdp);
        Utilisateur nouveauUtilisateur = new Utilisateur(pseudo, mdpHache, villeResidence);
        utilisateurList.add(nouveauUtilisateur);
        persistanceSiteVente.ajouterUtilisateur(nouveauUtilisateur);
        return nouveauUtilisateur;
    }

    @Override
    public Utilisateur verifierConnextion(String pseudo, String mdp) {
        Utilisateur utilisateur = persistanceSiteVente.getUtilisateur(pseudo);

        if(utilisateur != null) {
            if (passwordEncoder.matches(utilisateur.getMdpHache(), passwordEncoder.encode(mdp))){
                return utilisateur;
            }
        }
        return null;
    }


    @Override
    public Item ajouterItem(String nom, String description, float prix, Utilisateur utilisateur) {
        Item nouveauItem = new Item(nom, description, prix, utilisateur.getPseudo());
        ajouterItem(nouveauItem, utilisateur);
        return nouveauItem;
    }

    private void ajouterItem(Item item, Utilisateur utilisateur) {
        for (Utilisateur u : utilisateurList) {
            if (u.isSame(utilisateur)) {
                u.addItemAVendre(item);
                return;
            }
        }
    }

    @Override
    public boolean vendreItem(Item item, Utilisateur propritaireVente, Utilisateur acquereur) {
        for (Utilisateur u : utilisateurList) {
            if (u.isSame(propritaireVente)) {
                u.vendreObjet(item, acquereur);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Item> getItemsNonVendus(){
        List<Item> nonVendus = new ArrayList<>();
        for (Utilisateur u : utilisateurList) {
            List<Item> itemsUtilisateur = u.getItemsNonVendus();
            for (Item i : itemsUtilisateur) {
                nonVendus.add(i);
            }
        }
        return nonVendus;
    }

    @Override
    public List<Item> getItemsNonVendus(Utilisateur utilisateur){
        List<Item> itemsNonVendus = new ArrayList<>();
        for (Utilisateur u : utilisateurList) {
            if (u.isSame(utilisateur)) {
                itemsNonVendus = u.getItemsNonVendus();
            }

        }
        return itemsNonVendus;
    }

    @Override
    public List<Item> getItemsVendus(Utilisateur utilisateur){
        List<Item> itemsVendus = new ArrayList<>();
        for (Utilisateur u : utilisateurList) {
            if (u.isSame(utilisateur)) {
                itemsVendus = u.getItemsVendus();
            }

        }
        return itemsVendus;
    }



    @Override
    public float calculerCA() {
        float ca = 0;
        for (Utilisateur user : utilisateurList) {
            for (Item itemAchete : user.getItemsAchetes()){
                  float prixItem = itemAchete.getPrix();
                  float pourcentage = COMMISION/100;
                  ca += prixItem*pourcentage;

            }
        }
        return ca;
    }
}
