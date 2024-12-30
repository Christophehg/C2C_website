package c2cwebsite.service;

import c2cwebsite.model.User;
import c2cwebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User register(User user) {

        if(userRepository.findByPseudo(user.getPseudo()) != null) {
            throw new RuntimeException("User already exists");
        }
        User newUser = new User(user.getPseudo(), passwordEncoder.encode(user.getMdp()), user.getVilleResidence());

        return userRepository.save(newUser);
    }

    public String login(String pseudo, String mdp) {
//        User user = userRepository.findByPseudo(pseudo);
//        if(user != null) {
//            if (passwordEncoder.matches(mdp, user.getMdp())){
//                return user;
//            }
//        }
//        return null;
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(pseudo, mdp));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(pseudo);
        }
        return null;

    }
}
