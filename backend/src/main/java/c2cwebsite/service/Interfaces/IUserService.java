package c2cwebsite.service.Interfaces;

import c2cwebsite.model.Item;
import c2cwebsite.model.User;

import java.util.List;

public interface IUserService {
    public User register(User user);
    public List<String> login(String pseudo, String mdp);
    public List<Item> getMesItems(String token);
    public List<Item> getMesItemsAchetes(String token);
}
