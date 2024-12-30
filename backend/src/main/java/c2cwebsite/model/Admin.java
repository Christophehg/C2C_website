package c2cwebsite.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Admins")
public class Admin extends BaseUser {

    public Admin(String pseudo, String mdp) {
        super(pseudo, mdp);
        setRole(Role.ADMIN);
    }

    public Admin() {

    }

}
