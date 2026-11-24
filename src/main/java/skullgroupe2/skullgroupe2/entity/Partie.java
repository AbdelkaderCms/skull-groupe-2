package skullgroupe2.skullgroupe2.entity;

import java.time.LocalDate;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity 
public class Partie {

    @Id @GeneratedValue 
    private Long id;

    private Integer nbJoueurs ;
    private Integer nbIA;
    private LocalDate date;

    // UNE partie contient PLUSIEURS manches
    @OneToMany
    @JoinColumn(name = "partie_id")
    private List<Manche> manches;

    // PLUSIEURS joueurs participent à PLUSIEURS parties 
    @ManyToMany 
    private List<Joueur> joueurs;

    // UN seul gagnant par partie, mais un joueur peut gagner plusieurs parties
    @ManyToOne 
    private Joueur gagnant;


    // getters 

    public  Long getId(){
        return id;
    }


    public Integer getNbJoueurs(){
        return nbJoueurs;
    }

    public Integer getNbIA(){
        return nbIA;
    }

    public LocalDate getDate(){
        return date;
    }

    public List<Manche> getManches(){
        return manches;
    }

    public List<Joueur> getJoueurs(){
        return joueurs;
    }

    public Joueur getGagnant(){
        return gagnant;
    }


    //Seters

    public void setNbJoueurs (Integer nbJoueurs){
        this.nbJoueurs = nbJoueurs;
    }

    public void setNbIA (Integer nbIA){
        this.nbIA = nbIA;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setManches(List<Manche> manches){
        this.manches = manches;
    }

    public void setJoueurs(List<Joueur> joueurs){
        this.joueurs = joueurs;
    }

    public void setGagnant(Joueur gagnant){
        this.gagnant = gagnant;
    }

    
}
