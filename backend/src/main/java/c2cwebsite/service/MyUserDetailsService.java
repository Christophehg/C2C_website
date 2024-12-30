package c2cwebsite.service;

import c2cwebsite.model.BaseUser;
import c2cwebsite.model.UserPrincipal;
import c2cwebsite.repository.AdminRepository;
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

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        BaseUser baseUser = userRepository.findByPseudo(username);
        if (baseUser == null) {
            baseUser = adminRepository.findByPseudo(username);
        }

        if (baseUser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new UserPrincipal(baseUser);
    }
}
