package tn.esprit.ds.championnat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.ds.championnat.entities.Championnat;
import tn.esprit.ds.championnat.entities.Course;
import tn.esprit.ds.championnat.entities.DetailChampionnat;
import tn.esprit.ds.championnat.repository.ChampionnatRepository;
import tn.esprit.ds.championnat.repository.CourseRepository;
import tn.esprit.ds.championnat.repository.DetailChampionnatRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ChampionnatService implements IChampionnatService {

    private final ChampionnatRepository championnatRepository;
    private final CourseRepository courseRepository;
    private final DetailChampionnatRepository detailChampionnatRepository;

    // Ajouter un championnat avec ses courses associées
    @Override
    @Transactional
    public Championnat addChampionnatAndAssociatedCourses(Championnat championnat) {
        // Sauvegarder le championnat (parent)
        Championnat championnatSaved = championnatRepository.save(championnat);

        // Parcourir la liste des courses et les associer au championnat
        if (championnatSaved.getCourses() != null) {
            championnatSaved.getCourses().forEach(course -> {

                // Pour ManyToMany, il faut gérer les deux côtés
                if (course.getChampionnats() == null) {
                    course.setChampionnats(new ArrayList<>());
                }

                // Eviter les doublons (optionnel mais utile avec List)
                if (!course.getChampionnats().contains(championnatSaved)) {
                    course.getChampionnats().add(championnatSaved);
                }

                courseRepository.save(course);
            });
        }

        return championnatSaved;
    }

    // Ajouter un DetailChampionnat et l'affecter à un Championnat existant
    @Override
    @Transactional
    public Championnat ajouterEtAffecterDetailChampionnatAChampionnat(DetailChampionnat detailChampionnat, Long idChampionnat) {
        Championnat championnat = championnatRepository.findById(idChampionnat)
                .orElseThrow(() -> new RuntimeException("Championnat non trouvé avec l'id: " + idChampionnat));

        DetailChampionnat detailSaved = detailChampionnatRepository.save(detailChampionnat);

        championnat.setDetailChampionnat(detailSaved);

        return championnatRepository.save(championnat);
    }

    // Affecter une Course existante à un Championnat existant
    @Override
    @Transactional
    public String affecterCourseAChampionnat(Long courseId, Long championnatId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course non trouvée avec l'id: " + courseId));

        Championnat championnat = championnatRepository.findById(championnatId)
                .orElseThrow(() -> new RuntimeException("Championnat non trouvé avec l'id: " + championnatId));

        // Initialiser les listes si null
        if (course.getChampionnats() == null) {
            course.setChampionnats(new ArrayList<>());
        }
        if (championnat.getCourses() == null) {
            championnat.setCourses(new ArrayList<>());
        }

        // Ajouter dans les deux sens (ManyToMany)
        if (!championnat.getCourses().contains(course)) {
            championnat.getCourses().add(course);
        }
        if (!course.getChampionnats().contains(championnat)) {
            course.getChampionnats().add(championnat);
        }

        courseRepository.save(course);
        championnatRepository.save(championnat);

        // Si ton entité n'a pas getLibelle(), remplace par le bon getter (ex: getNom())
        return "Course affectée avec succès au championnat id=" + championnat.getIdChampionnat();    }
}