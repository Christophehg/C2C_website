package c2cwebsite.repository;

import c2cwebsite.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByPseudo(String pseudo);
}
