package tn.esprit.ds.championnat.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.ds.championnat.entities.Sponsor;


public interface SponsorRepository extends CrudRepository<Sponsor, Long> {


}


//CrudRepository : methodes de CRUD
//PagingAndSorting : pagination+tri
//JPARepository :batch treating