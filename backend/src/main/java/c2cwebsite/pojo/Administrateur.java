package c2cwebsite.pojo;

public class Administrateur {
    private String pseudo;
    private String mdpHache;

    public Administrateur(String pseudo, String mdpHache) {
        this.pseudo = pseudo;
        this.mdpHache = mdpHache;
    }
}
