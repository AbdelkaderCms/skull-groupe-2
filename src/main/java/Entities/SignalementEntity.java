package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SignalementEntity {
    @Id
    private Integer id;
    private String motif;

    @ManyToOne
    private MembreEntity membreEntity;
}
