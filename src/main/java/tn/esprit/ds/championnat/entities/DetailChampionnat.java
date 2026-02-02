package tn.esprit.ds.championnat.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.ds.championnat.entities.Championnat;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetailChampionnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String description;

    @OneToOne(mappedBy = "detailChampionnat")
    private Championnat championnat;
}