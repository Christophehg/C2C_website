package c2cwebsite.pojo;

public class Item {

    private String nom;
    private String description;
    private float prix;
    private int numeroE;
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
