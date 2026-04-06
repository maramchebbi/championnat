package tn.esprit.ds.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.ds.championnat.entities.Contract;

import java.util.List;

public interface ContratRepository extends JpaRepository<Contract, Long> {

    List<Contract> findByArchivedFalse();

    // Archive = false ET annee < anneeCourante (annee est String -> cast en int)
    @Query("select c from Contract c where c.archived = false and cast(c.annee as int) < :anneeCourante")
    List<Contract> findContratsExpires(int anneeCourante);
}