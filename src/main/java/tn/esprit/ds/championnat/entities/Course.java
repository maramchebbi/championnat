package tn.esprit.ds.championnat.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCourse;

    private String emplacement;
    private LocalDate dateCourse;

    @ManyToMany(mappedBy = "courses")
    private List<Championnat> championnats;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Position> positions;
}