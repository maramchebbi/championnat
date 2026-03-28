package tn.esprit.ds.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.ds.championnat.entities.Contract;

public interface ContratRepository extends JpaRepository<Contract, Long> {
}