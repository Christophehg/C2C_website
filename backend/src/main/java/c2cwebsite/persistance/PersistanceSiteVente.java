package c2cwebsite.persistance;

import c2cwebsite.persistance.repository.IPersistanceUtilisateur;
import c2cwebsite.pojo.Utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistanceSiteVente implements IPersistanceSiteVente {

    @Autowired
    private IPersistanceUtilisateur persistanceUtilisateur;

    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        persistanceUtilisateur.save(utilisateur);
    }

    @Override
    public List<Utilisateur> getUtilisateurs() {
        return persistanceUtilisateur.findAll();
    }
}
