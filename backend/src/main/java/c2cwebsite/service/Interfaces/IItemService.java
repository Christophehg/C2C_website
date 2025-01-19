package c2cwebsite.service.Interfaces;

import c2cwebsite.DTO.ItemDTO;
import c2cwebsite.model.Item;
import java.util.List;

public interface IItemService {

    public Item addItem(String token, Item item);
    public List<ItemDTO> getItemsNonVendus();
    public Item buyItem(String token, int itemId);
    public Item changeEtatItem(String token, int itemId);
}
