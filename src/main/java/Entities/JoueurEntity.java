package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@Entity
@MappedSuperclass
public class JoueurEntity {
    @Id
    private Integer id;
    private String pseudo;

    @ManyToOne
    private SetEntity setEntity;

    public Integer getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
