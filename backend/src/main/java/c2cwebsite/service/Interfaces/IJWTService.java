package c2cwebsite.service.Interfaces;

import c2cwebsite.model.Role;
import io.jsonwebtoken.Claims;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

public interface IJWTService {
    public String generateToken(String username, Role role);

    public String extractRole(String token);

    public String extractUserName(String token);

    public boolean validateToken(String token, String pseudo, String role);

}
