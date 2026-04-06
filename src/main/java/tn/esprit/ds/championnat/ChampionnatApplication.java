package tn.esprit.ds.championnat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ChampionnatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChampionnatApplication.class, args);
    }
}