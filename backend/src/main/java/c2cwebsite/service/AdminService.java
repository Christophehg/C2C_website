package c2cwebsite.service;


import c2cwebsite.model.Admin;
import c2cwebsite.model.Role;
import c2cwebsite.repository.AdminRepository;
import c2cwebsite.repository.ItemRepository;
import c2cwebsite.service.Interfaces.IAdminService;
import c2cwebsite.service.Interfaces.IJWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AdminRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IJWTService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Admin registerAdmin(Admin user) {
        if(userRepository.findByPseudo(user.getPseudo()) != null) {
            throw new RuntimeException("User already exists");
        }
        Admin newUser = new Admin(user.getPseudo(), passwordEncoder.encode(user.getMdp()));
        System.out.println("User created");
        return userRepository.save(newUser);
    }

    public String loginAdmin(String pseudo, String mdp) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(pseudo, mdp)
        );

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Role role = Role.valueOf(userDetails.getAuthorities().stream().findFirst().get().getAuthority().substring(5));
            return jwtService.generateToken(pseudo, role);
        }
        return null;
    }


    public List<Float> calculateCA() {
        List<Float> infos = new ArrayList<>();
        Float CA =itemRepository.findAll().stream()
                .filter(item -> item.isVendu())
                .map(item -> item.getPrix() * 0.1f)
                .reduce(0f, Float::sum);
        infos.add(CA);
        infos.add(10f);
        Float nombreItems = itemRepository.findAll().stream()
                .filter(item -> item.isVendu())
                .count() * 1f;
        infos.add(nombreItems);
        return infos;
    }

}
