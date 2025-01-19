package c2cwebsite.service.Interfaces;

import c2cwebsite.model.Admin;

import java.util.List;

public interface IAdminService {
    public Admin registerAdmin(Admin user);
    public String loginAdmin(String pseudo, String mdp);
    public List<Float> calculateCA();
}
