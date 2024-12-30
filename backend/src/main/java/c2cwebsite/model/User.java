package c2cwebsite.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="Users")
public class User extends BaseUser {


    private String villeResidence;

    @OneToMany(mappedBy = "proprietaire", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> mesItems = new ArrayList<>();


    @OneToMany(mappedBy = "acquereur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itemsAchetes = new ArrayList<>();


    public User() {

    }

    public User(String pseudo, String mdp, String villeResidence) {
        super(pseudo, mdp);
        this.villeResidence = villeResidence;
        this.mesItems = new ArrayList<Item>();
        this.itemsAchetes = new ArrayList<Item>();
    }

    public void addItemAVendre(Item item) {
        mesItems.add(item);
    }

    public void addItemAchete(Item item) {
        itemsAchetes.add(item);
    }

    public void vendreObjet(Item item, User acquereur) {
        for (Item obj : mesItems) {
            if (obj.isSame(item)) {
                acquereur.addItemAVendre(obj);
                obj.vendreObjet(acquereur);
                break;
            }
        }
    }

    public List<Item> getMesItems() {
        return mesItems;
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


    public String getVilleResidence() {
        return villeResidence;
    }

    public void setVilleResidence(String villeResidence) {
        this.villeResidence = villeResidence;
    }

}
