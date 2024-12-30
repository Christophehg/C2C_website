package c2cwebsite.model.old;

import c2cwebsite.model.Admin;
import c2cwebsite.model.Item;
import c2cwebsite.model.User;
import c2cwebsite.repository.old.IRepoSiteVente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SiteVente implements ISiteVente {
    private final static int COMMISION = 10;

    @Autowired
    private IRepoSiteVente repoSite;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private List<User> userList = new ArrayList<User>();
    private List<Admin> adminList = new ArrayList<Admin>();


    @Override
    public User inscription(String pseudo, String mdp, String villeResidence) {
        String mdpHache = passwordEncoder.encode(mdp);
        User nouveauUser = new User(pseudo, mdpHache, villeResidence);
        userList.add(nouveauUser);
        repoSite.ajouterUtilisateur(nouveauUser);
        return nouveauUser;
    }

    @Override
    public User verifierConnextion(String pseudo, String mdp) {
        User user = repoSite.getUtilisateur(pseudo);
        if(user != null) {
            if (passwordEncoder.matches(user.getMdp(), passwordEncoder.encode(mdp))){
                return user;
            }
        }
        return null;
    }


    @Override
    public Item ajouterItem(String nom, String description, float prix, User user) {
        Item nouveauItem = new Item(nom, description, prix, user.getPseudo());
        ajouterItem(nouveauItem, user);
        return nouveauItem;
    }

    private void ajouterItem(Item item, User user) {
        for (User u : userList) {
            if (u.isSame(user)) {
                u.addItemAVendre(item);
                return;
            }
        }
    }

    @Override
    public boolean vendreItem(Item item, User propritaireVente, User acquereur) {
        for (User u : userList) {
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
        for (User u : userList) {
            List<Item> itemsUtilisateur = u.getItemsNonVendus();
            for (Item i : itemsUtilisateur) {
                nonVendus.add(i);
            }
        }
        return nonVendus;
    }

    @Override
    public List<Item> getItemsNonVendus(User user){
        List<Item> itemsNonVendus = new ArrayList<>();
        for (User u : userList) {
            if (u.isSame(user)) {
                itemsNonVendus = u.getItemsNonVendus();
            }

        }
        return itemsNonVendus;
    }

    @Override
    public List<Item> getItemsVendus(User user){
        List<Item> itemsVendus = new ArrayList<>();
        for (User u : userList) {
            if (u.isSame(user)) {
                itemsVendus = u.getItemsVendus();
            }

        }
        return itemsVendus;
    }



    @Override
    public float calculerCA() {
        float ca = 0;
        for (User user : userList) {
            for (Item itemAchete : user.getItemsAchetes()){
                  float prixItem = itemAchete.getPrix();
                  float pourcentage = COMMISION/100;
                  ca += prixItem*pourcentage;

            }
        }
        return ca;
    }
}
