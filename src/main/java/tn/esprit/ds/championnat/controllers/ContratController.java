package tn.esprit.ds.championnat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ds.championnat.entities.Contract;
import tn.esprit.ds.championnat.services.IContractService;

@RestController
@AllArgsConstructor
@RequestMapping("/contrat")
@Tag(name = "Contrats", description = "API pour la gestion des contrats")
public class ContratController {

    private final IContractService contractService;

    @PostMapping("/add-and-affect/{sponsorId}/{equipeId}")
    @Operation(summary = "Ajouter un contrat et l'affecter à un sponsor et une équipe")
    public Contract ajouterContratEtAffecterASponsorEtEquipe(
            @RequestBody Contract contrat,
            @PathVariable Long sponsorId,
            @PathVariable Long equipeId) {
        return contractService.ajouterContratEtAffecterASponsorEtEquipe(contrat, sponsorId, equipeId);
    }
}