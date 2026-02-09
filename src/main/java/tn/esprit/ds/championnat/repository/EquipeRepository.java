package tn.esprit.ds.championnat.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.ds.championnat.entities.Equipe;

public interface EquipeRepository extends CrudRepository<Equipe, Long> {
}
