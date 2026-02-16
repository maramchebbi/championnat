package tn.esprit.ds.championnat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.ds.championnat.entities.Equipe;
import tn.esprit.ds.championnat.repository.EquipeRepository;

@Service
@RequiredArgsConstructor

public class EquipeServiceImpl implements IEquipeService {

    private final EquipeRepository equipeRepository;

    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }
}
