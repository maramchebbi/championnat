package tn.esprit.ds.championnat.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.ds.championnat.entities.Pilote;

public interface PiloteRepository extends CrudRepository<Pilote, Long> {
}
