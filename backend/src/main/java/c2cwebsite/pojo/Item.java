package c2cwebsite.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Item")
public class Item {
    @Id
    @GeneratedValue
    private int numeroE;

    private String nom;
    private String description;
    private float prix;
    private boolean vendu;
    private String proprietaire;
    private String acquereur;


    public Item(String nom, String description, float prix, String proprietaire) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.vendu = false;
        this.proprietaire = proprietaire;
        acquereur = "";
    }

    public Item() {

    }

    public void vendreObjet(Utilisateur acquereur) {
        this.vendu = true;
        this.acquereur = acquereur.getPseudo();
        acquereur.addItemAchete(this);
    }

    public boolean isSame(Item item) {
        return this.nom.equals(item.nom);
    }


    public boolean isVendu() {
        return vendu;
    }

    public float getPrix() {
        return prix;
    }

}
