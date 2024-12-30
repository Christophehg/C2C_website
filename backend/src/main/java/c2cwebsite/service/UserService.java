package c2cwebsite.service;

import c2cwebsite.model.Item;
import c2cwebsite.model.Role;
import c2cwebsite.model.User;
import c2cwebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User register(User user) {
        if(userRepository.findByPseudo(user.getPseudo()) != null) {
            throw new RuntimeException("User already exists");
        }
        User newUser = new User(user.getPseudo(), passwordEncoder.encode(user.getMdp()), user.getVilleResidence());

        return userRepository.save(newUser);
    }

//    public String login(String pseudo, String mdp) {
////        User user = userRepository.findByPseudo(pseudo);
////        if(user != null) {
////            if (passwordEncoder.matches(mdp, user.getMdp())){
////                return user;
////            }
////        }
////        return null;
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(pseudo, mdp));
//        if (authentication.isAuthenticated()){
//            return jwtService.generateToken(pseudo);
//        }
//        return null;
//
//    }

    public String login(String pseudo, String mdp) {
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

    public List<Item> getMesItems(String token) {
        System.out.println("Get Mes items");
        String pseudo = jwtService.extractUserName(token);
        if (pseudo == null) {
            throw new RuntimeException("User not found from token");
        }
        User user = userRepository.findByPseudo(pseudo);
        if(user == null) {
            throw new RuntimeException("User not found from dataBase");
        }
        return user.getMesItems();
    }

    public List<Item> getMesItemsAchetes(String token) {
        System.out.println("Get Mes items achetés");
        String pseudo = jwtService.extractUserName(token);
        if (pseudo == null) {
            throw new RuntimeException("User not found from token");
        }
        User user = userRepository.findByPseudo(pseudo);
        if(user == null) {
            throw new RuntimeException("User not found from dataBase");
        }
        return user.getItemsAchetes();
    }

}
