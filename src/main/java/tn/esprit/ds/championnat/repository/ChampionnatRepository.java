package tn.esprit.ds.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.ds.championnat.entities.Championnat;

public interface ChampionnatRepository extends JpaRepository<Championnat, Long> {
}