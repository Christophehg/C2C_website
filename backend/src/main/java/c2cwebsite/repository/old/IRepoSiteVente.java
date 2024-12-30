package c2cwebsite.repository.old;

import c2cwebsite.model.User;

import java.util.List;

public interface IRepoSiteVente {

    public void ajouterUtilisateur(User user);
    public List<User> getUtilisateurs();
    public User getUtilisateur(String pseudo);

}
