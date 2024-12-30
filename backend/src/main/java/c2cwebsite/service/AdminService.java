package c2cwebsite.service;


import c2cwebsite.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private ItemRepository itemRepository;

    public float calculateCommission() {
        return itemRepository.findAll().stream()
                .filter(item -> item.isVendu())
                .map(item -> item.getPrix() * 0.1f)
                .reduce(0f, Float::sum);
    }

}
