package c2cwebsite.persistance;

import c2cwebsite.pojo.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersistanceUtilisateur  extends JpaRepository<Utilisateur,Long> {
}
