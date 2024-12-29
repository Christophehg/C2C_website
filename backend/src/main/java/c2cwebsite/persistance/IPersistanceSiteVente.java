package c2cwebsite.persistance;

import c2cwebsite.pojo.Utilisateur;

import java.util.List;

public interface IPersistanceSiteVente {

    public void ajouterUtilisateur(Utilisateur utilisateur);
    public List<Utilisateur> getUtilisateurs();
    public Utilisateur getUtilisateur(String pseudo);

}
