package tn.esprit.ds.championnat.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.ds.championnat.enums.Categorie;

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
    private DetailChampionnat detailChampionnat;

    @ManyToMany
    @JoinTable(
            name = "championnat_course",
            joinColumns = @JoinColumn(name = "championnat_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}