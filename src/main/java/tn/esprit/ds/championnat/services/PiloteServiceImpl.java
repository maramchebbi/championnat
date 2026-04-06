package tn.esprit.ds.championnat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.ds.championnat.entities.Pilote;
import tn.esprit.ds.championnat.repository.PiloteRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PiloteServiceImpl implements IPiloteService {

    private final PiloteRepository piloteRepository;

    @Override
    public String addPilote(Pilote p) {
        piloteRepository.save(p);
        return "Pilote ajouté";
    }
    @Scheduled(fixedRate = 30_000)
    //@Scheduled(cron = "0 15 11 31 12 *")
    @Transactional
    public void miseAJourPointsEtClassementGeneral() {
        System.out.println("[SCHEDULER 5.2] Exécution: " + LocalDateTime.now());

        List<Pilote> pilotes = org.springframework.data.util.Streamable.of(piloteRepository.findAll()).toList();
        if (pilotes.isEmpty()) {
            System.out.println("[SCHEDULER 5.2] Aucun pilote en base -> rien à mettre à jour.");
            return;
        }

        for (Pilote p : pilotes) {
            if (p.getNbPointsTotal() == null) {
                p.setNbPointsTotal(0);
            }
        }
        piloteRepository.saveAll(pilotes);

        Map<Object, List<Pilote>> parCategorie = pilotes.stream()
                .collect(Collectors.groupingBy(Pilote::getCategorie));

        for (List<Pilote> list : parCategorie.values()) {
            list.sort(Comparator.comparing(
                    Pilote::getNbPointsTotal,
                    Comparator.nullsLast(Integer::compareTo)
            ).reversed());

            int rank = 1;
            for (Pilote p : list) {
                p.setClassementGeneral(rank++);
            }
            piloteRepository.saveAll(list);
        }

        System.out.println("[SCHEDULER 5.2] Classements mis à jour.");
    }
}