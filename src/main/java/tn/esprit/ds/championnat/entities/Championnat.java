package tn.esprit.ds.championnat.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Championnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChampionnat;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    private String libelleC;
    private Integer annee;

    @OneToOne
    private tn.esprit.entities.DetailChampionnat detailChampionnat;

    @OneToMany(mappedBy = "championnat", cascade = CascadeType.ALL)
    private List<Course> courses;
}