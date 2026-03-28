package tn.esprit.ds.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.ds.championnat.entities.DetailChampionnat;

public interface DetailChampionnatRepository extends JpaRepository<DetailChampionnat, Long> {
}