package tn.esprit.ds.championnat.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.ds.championnat.entities.Equipe;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pilote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPilote;

    private String libelle;
    private Integer nbPointsTotal;
    private Integer classementGeneral;

    @ManyToOne
    private Equipe equipe;

    @OneToMany(mappedBy = "pilote", cascade = CascadeType.ALL)
    private List<Position> positions;
}