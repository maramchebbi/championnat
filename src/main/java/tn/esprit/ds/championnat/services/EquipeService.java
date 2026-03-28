package tn.esprit.ds.championnat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.ds.championnat.entities.Equipe;
import tn.esprit.ds.championnat.entities.Pilote;
import tn.esprit.ds.championnat.repository.EquipeRepository;
import tn.esprit.ds.championnat.repository.PiloteRepository;

@Service
@RequiredArgsConstructor
public class EquipeService implements IEquipeService {
    private final EquipeRepository equipeRepository;
    private final PiloteRepository piloteRepository;

    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        // Initialisation des valeurs par défaut
        if (equipe.getNbPointsTotal() == null) {
            equipe.setNbPointsTotal(0);
        }
        if (equipe.getClassementGeneral() == null) {
            equipe.setClassementGeneral(0);
        }
        return equipeRepository.save(equipe);
    }


    //Affecter un Pilote existant à une Equipe existante (ManyToOne)

    @Override
    @Transactional
    public Equipe affecterPiloteAEquipe(Long piloteId, Long equipeId) {
        // Récupérer l'équipe (parent)
        Equipe equipe = equipeRepository.findById(equipeId)
                .orElseThrow(() -> new RuntimeException("Equipe non trouvée avec l'id: " + equipeId));

        // Récupérer le pilote (child)
        Pilote pilote = piloteRepository.findById(piloteId)
                .orElseThrow(() -> new RuntimeException("Pilote non trouvé avec l'id: " + piloteId));

        // Affecter le child (pilote) au parent (équipe)
        pilote.setEquipe(equipe);

        // Sauvegarder le pilote
        piloteRepository.save(pilote);

        return equipe;
    }
}