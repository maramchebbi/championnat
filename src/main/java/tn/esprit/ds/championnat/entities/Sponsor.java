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
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSponsor;

    private String nom;
    private String pays;
    private Float budgetAnnuel;
    private Boolean bloquerContrat;

    @OneToMany(mappedBy = "sponsor", cascade = CascadeType.ALL)
    private List<Contract> contrats;
}