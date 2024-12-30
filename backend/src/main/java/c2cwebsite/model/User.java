package c2cwebsite.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="Utilisateurs")
public class User {

    @Id
    @GeneratedValue
    private int numero;

    private String pseudo;
    private String mdp;
    private String villeResidence;

    @OneToMany(mappedBy = "proprietaire", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itemsAVendre = new ArrayList<>();

    @OneToMany(mappedBy = "acquereur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itemsAchetes = new ArrayList<>();

    public User() {

    }

    public User(String pseudo, String mdp, String villeResidence) {
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.villeResidence = villeResidence;
        this.itemsAVendre = new ArrayList<Item>();
        this.itemsAchetes = new ArrayList<Item>();
    }

    public void addItemAVendre(Item item) {
        itemsAVendre.add(item);
    }

    public void addItemAchete(Item item) {
        itemsAchetes.add(item);
    }

    public void vendreObjet(Item item, User acquereur) {
        for (Item obj : itemsAVendre) {
            if (obj.isSame(item)) {
                acquereur.addItemAVendre(obj);
                obj.vendreObjet(acquereur);
                break;
            }
        }
    }


    public List<Item> getItemsAchetes() {
        return itemsAchetes;
    }

    public List<Item> getItemsNonVendus(){
        List<Item> itemsNonVendus = new ArrayList();
        for (Item item : itemsAchetes) {
            if (!item.isVendu()){
                itemsNonVendus.add(item);
            }
        }
        return itemsNonVendus;
    }

    public List<Item> getItemsVendus(){
        List<Item> itemsVendus = new ArrayList();
        for (Item item : itemsAchetes) {
            if (item.isVendu()){
                itemsVendus.add(item);
            }
        }
        return itemsVendus;
    }

    public boolean isSame(User user) {
        if (this.pseudo.equals(user.getPseudo())) {
            return true;
        }
        return false;
    }


    public String getPseudo() {
        return pseudo;
    }

    public String getVilleResidence() {
        return villeResidence;
    }

    public String getMdp() {
        return mdp;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setVilleResidence(String villeResidence) {
        this.villeResidence = villeResidence;
    }
}
