package tn.esprit.ds.championnat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.ds.championnat.entities.Pilote;
import tn.esprit.ds.championnat.services.IPiloteService;

@Configuration
@RequiredArgsConstructor
public class AspectTestRunner {

    private final IPiloteService piloteService;

    @Bean
    CommandLineRunner testAspects() {
        return args -> {
            piloteService.addPilote(new Pilote());
        };
    }
}