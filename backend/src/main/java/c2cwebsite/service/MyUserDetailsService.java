package c2cwebsite.service;

import c2cwebsite.model.UserPrincipal;
import c2cwebsite.model.User;
import c2cwebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByPseudo(username);
        if(user == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé");
        }

        return new UserPrincipal(user);
    }
}
