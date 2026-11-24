package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AdminEntity {
    @Id
    private String email;
    private String mdp;

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
