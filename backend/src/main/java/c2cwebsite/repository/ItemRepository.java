package c2cwebsite.repository;

import c2cwebsite.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByNom(String nom);
    List<Item> findByVendu(boolean vendu);
}
