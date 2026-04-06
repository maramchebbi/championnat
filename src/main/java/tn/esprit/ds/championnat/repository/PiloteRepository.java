package tn.esprit.ds.championnat.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.ds.championnat.entities.Pilote;

import java.util.List;

public interface PiloteRepository extends CrudRepository<Pilote, Long> {

    List<Pilote> findByCategorieOrderByNbPointsTotalDesc(Object categorie);
}
