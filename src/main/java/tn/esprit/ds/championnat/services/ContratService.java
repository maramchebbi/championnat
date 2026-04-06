package tn.esprit.ds.championnat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.ds.championnat.entities.Contract;
import tn.esprit.ds.championnat.entities.Equipe;
import tn.esprit.ds.championnat.entities.Sponsor;
import tn.esprit.ds.championnat.repository.ContratRepository;
import tn.esprit.ds.championnat.repository.EquipeRepository;
import tn.esprit.ds.championnat.repository.SponsorRepository;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratService implements IContractService {

    private final ContratRepository contratRepository;
    private final SponsorRepository sponsorRepository;
    private final EquipeRepository equipeRepository;


    //Ajouter un contrat et l'affecter à un sponsor et une équipe

    @Override
    @Transactional
    public Contract ajouterContratEtAffecterASponsorEtEquipe(Contract contrat, Long sponsorId, Long equipeId) {
        // Récupérer le sponsor existant
        Sponsor sponsor = sponsorRepository.findById(sponsorId)
                .orElseThrow(() -> new RuntimeException("Sponsor non trouvé avec l'id: " + sponsorId));

        // Récupérer l'équipe existante
        Equipe equipe = equipeRepository.findById(equipeId)
                .orElseThrow(() -> new RuntimeException("Equipe non trouvée avec l'id: " + equipeId));

        // Initialiser les valeurs par défaut du contrat
        if (contrat.getMontant() == null) {
            contrat.setMontant(0.0f);
        }
        if (contrat.getArchived() == null) {
            contrat.setArchived(false);
        }

        // Affecter le sponsor et l'équipe au contrat
        contrat.setSponsor(sponsor);
        contrat.setEquipe(equipe);

        // Sauvegarder le contrat
        return contratRepository.save(contrat);
    }


    @Scheduled(fixedRate = 30_000)
    @Transactional
    public void archiverContratsExpireesEtAffichageContratsActifsParEquipe() {

        int anneeCourante = Year.now().getValue();

        System.out.println("Start " + java.time.LocalDateTime.now());


        // 1) Archiver les contrats expirés (annee < année courante)
        List<Contract> expires = contratRepository.findContratsExpires(anneeCourante);
        for (Contract c : expires) {
            c.setArchived(true);
        }
        contratRepository.saveAll(expires);

        // 2) Afficher les contrats actifs par équipe
        List<Contract> actifs = contratRepository.findByArchivedFalse();

        for (Contract c : actifs) {
            String equipeLibelle = (c.getEquipe() != null) ? c.getEquipe().getLibelle() : "Equipe inconnue";
            String sponsorNom = (c.getSponsor() != null) ? c.getSponsor().getNom() : "Sponsor inconnu";
            Float montant = (c.getMontant() != null) ? c.getMontant() : 0.0f;

            System.out.println("L'équipe " + equipeLibelle
                    + " a un contrat d'un montant de " + montant
                    + " avec le sponsor " + sponsorNom);
        }
    }
}