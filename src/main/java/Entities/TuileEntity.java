package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TuileEntity {
    @Id
    private Integer id;
    private Boolean estCache;
}
