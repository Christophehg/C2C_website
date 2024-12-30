package c2cwebsite.DTO;

public class ItemDTO {
    private int numeroE;
    private String nom;
    private String description;
    private float prix;
    private boolean vendu;
    private String proprietaireNom;  // Nom du vendeur

    // Constructeur
    public ItemDTO(int numeroE, String nom, String description, float prix, boolean vendu, String proprietaireNom) {
        this.numeroE = numeroE;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.vendu = vendu;
        this.proprietaireNom = proprietaireNom;
    }

    // Getters et Setters
    public int getNumeroE() {
        return numeroE;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public float getPrix() {
        return prix;
    }

    public boolean isVendu() {
        return vendu;
    }

    public String getProprietaireNom() {
        return proprietaireNom;
    }
}
