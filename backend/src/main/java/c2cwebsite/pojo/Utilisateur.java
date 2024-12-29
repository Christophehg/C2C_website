package c2cwebsite.pojo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="Utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue
    private int numero;

    private String pseudo;
    private String mdpHache;
    private String villeResidence;

    @OneToMany(mappedBy = "proprietaire", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itemsAVendre = new ArrayList<>();

    @OneToMany(mappedBy = "acquereur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itemsAchetes = new ArrayList<>();

    public Utilisateur(String pseudo, String mdpHache, String villeResidence) {
        this.pseudo = pseudo;
        this.mdpHache = mdpHache;
        this.villeResidence = villeResidence;
        this.itemsAVendre = new ArrayList<Item>();
        this.itemsAchetes = new ArrayList<Item>();
    }

    public Utilisateur() {

    }

    public void addItemAVendre(Item item) {
        itemsAVendre.add(item);
    }

    public void addItemAchete(Item item) {
        itemsAchetes.add(item);
    }

    public void vendreObjet(Item item, Utilisateur acquereur) {
        for (Item obj : itemsAVendre) {
            if (obj.isSame(item)) {
                acquereur.addItemAVendre(obj);
                obj.vendreObjet(acquereur);
                break;
            }
        }
    }

    public String getPseudo() {
        return pseudo;
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

    public boolean isSame(Utilisateur utilisateur) {
        if (this.pseudo.equals(utilisateur.getPseudo())) {
            return true;
        }
        return false;
    }


}
