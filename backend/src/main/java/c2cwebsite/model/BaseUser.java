package c2cwebsite.model;


import jakarta.persistence.*;

import java.util.List;

import static c2cwebsite.model.Role.USER;

@MappedSuperclass
public class BaseUser {

    @Id
    @GeneratedValue
    private Long id;

    private String pseudo;
    private String mdp;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public BaseUser() {
    }

    public BaseUser(String pseudo, String mdp) {
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.role = USER;
    }



    public Role getRole() {
        return role;
    }


    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isSame(User user) {
        if (this.pseudo.equals(user.getPseudo())) {
            return true;
        }
        return false;
    }
}
