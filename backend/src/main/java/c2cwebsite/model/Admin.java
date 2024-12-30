package c2cwebsite.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Administrateurs")
public class Admin {

    @Id
    @GeneratedValue
    private Long id;

    private String pseudo;
    private String mdpHache;


    public Admin(String pseudo, String mdpHache) {
        this.pseudo = pseudo;
        this.mdpHache = mdpHache;
    }

    public Admin() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
