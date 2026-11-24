package Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("PoseTuile")
public class PoseTuileEntity extends CoupEntity{
    @ManyToOne
    private TuileEntity tuileEntity;
}
