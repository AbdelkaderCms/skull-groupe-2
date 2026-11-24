package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@Entity
@MappedSuperclass
public class JoueurEntity {
    @Id
    private Long id;
    private String pseudo;

    @ManyToOne
    private SetEntity setEntity;

    public Long getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
