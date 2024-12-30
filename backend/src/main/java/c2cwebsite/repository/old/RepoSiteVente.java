package c2cwebsite.repository.old;

import c2cwebsite.repository.UserRepository;
import c2cwebsite.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepoSiteVente implements IRepoSiteVente {

    @Autowired
    private UserRepository repoUser;

    @Override
    public void ajouterUtilisateur(User user) {
        repoUser.save(user);
    }

    @Override
    public List<User> getUtilisateurs() {
        return repoUser.findAll();
    }

    @Override
    public User getUtilisateur(String pseudo) {
        return repoUser.findByPseudo(pseudo);
    }
}
