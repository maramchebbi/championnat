package tn.esprit.ds.championnat.services;

import tn.esprit.ds.championnat.entities.Contract;

public interface IContractService {
    Contract ajouterContratEtAffecterASponsorEtEquipe(Contract contrat, Long sponsorId, Long equipeId);
}