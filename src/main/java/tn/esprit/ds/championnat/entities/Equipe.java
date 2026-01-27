package tn.esprit.ds.championnat.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.ds.championnat.entities.Contract;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;

    private String libelle;
    private Integer nbPointsTotal;
    private Integer classementGeneral;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<tn.esprit.entities.Pilote> pilotes;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Contract> contrats;
}