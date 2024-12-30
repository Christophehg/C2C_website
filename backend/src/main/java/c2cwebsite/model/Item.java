package c2cwebsite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


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

    @ManyToOne
    @JoinColumn(name = "proprietaire_id")
    @JsonIgnore
    private User proprietaire;

    @ManyToOne
    @JoinColumn(name = "acquereur_id")
    @JsonIgnore
    private User acquereur;


    public Item(String nom, String description, float prix, User proprietaire) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.vendu = false;
        this.proprietaire = proprietaire;
        acquereur = null;
    }

    public Item() {

    }

    public void vendreObjet(User acquereur) {
        this.vendu = true;
        this.acquereur = acquereur;
        acquereur.addItemAchete(this);
    }

    public boolean isSame(Item item) {
        return this.getNumeroE() == item.getNumeroE();
    }


    public boolean isVendu() {
        return vendu;
    }

    public float getPrix() {
        return prix;
    }

    public User getAcquereur() {
        return acquereur;
    }

    public User getProprietaire() {
        return proprietaire;
    }

    public String getDescription() {
        return description;
    }

    public String getNom() {
        return nom;
    }

    public int getNumeroE() {
        return numeroE;
    }

    public void setAcquereur(User acquereur) {
        this.acquereur = acquereur;
    }

    public void setProprietaire(User proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void setVendu(boolean vendu) {
        this.vendu = vendu;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumeroE(int numeroE) {
        this.numeroE = numeroE;
    }

}
