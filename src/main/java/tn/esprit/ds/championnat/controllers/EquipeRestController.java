package tn.esprit.ds.championnat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ds.championnat.entities.Equipe;
import tn.esprit.ds.championnat.services.IEquipeService;

@RestController
@RequestMapping("/equipe")
@AllArgsConstructor
@Tag(name = "Equipe Controller", description = "Gestion des équipes du championnat")

public class EquipeRestController {

    private final IEquipeService equipeService;

    @PostMapping("/add")
    @Operation(
            summary = "Ajout d'une equipe", description = "Permet d'ajouter une nouvelle equipe"
    )

    @ApiResponses(value = {
            @ApiResponse(   responseCode = "200" , description = "Equipe bien ajoutée")
    })
    public Equipe ajouterEquipe(@RequestBody Equipe equipe) {
        return equipeService.ajouterEquipe(equipe);
    }
}