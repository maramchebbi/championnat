package tn.esprit.ds.championnat.services;

import tn.esprit.ds.championnat.entities.Championnat;
import tn.esprit.ds.championnat.entities.DetailChampionnat;

public interface IChampionnatService {
    Championnat addChampionnatAndAssociatedCourses(Championnat championnat);
    Championnat ajouterEtAffecterDetailChampionnatAChampionnat(DetailChampionnat detailChampionnat, Long idChampionnat);
    String affecterCourseAChampionnat(Long courseId, Long championnatId);
}