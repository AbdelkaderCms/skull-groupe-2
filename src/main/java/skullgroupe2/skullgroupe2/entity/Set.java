package skullgroupe2.skullgroupe2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity 
@Table(name = "sets") // "set" est un mot réservé en SQL, donc on nomme la table autrement
public class Set {

    @Id @GeneratedValue 
    private Long id;
    
}
