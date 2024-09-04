package com.saturne;

import com.saturne.dto.FormationDTO;
import com.saturne.dto.ThemeDTO;
import com.saturne.entities.Catalogue;
import com.saturne.entities.Formation;
import com.saturne.entities.Session;
import com.saturne.entities.Theme;
import com.saturne.services.CatalogueService;
import com.saturne.services.FormationService;
import com.saturne.services.SessionService;
import com.saturne.services.ThemeService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@QuarkusMain
public class BackendApplication {

    private static final Logger log = Logger.getLogger(BackendApplication.class);

    public static void main(String[] args) {
        Quarkus.run(App.class, args);
        log.info("our server is listening in port 8080");
    }

    public static class App implements QuarkusApplication {

        @Inject
        FormationService formationService;

        @Inject
        CatalogueService catalogueService;

        @Inject
        SessionService sessionService;

        @Inject
        ThemeService themeService;


        @Override
        public int run(String... args) throws Exception {
            demo();
            demo1();
            demo2();
            demo3();
            Quarkus.waitForExit();
            return 0;
        }

        private void demo() {
            formationService.addFormation(
                    new FormationDTO(
                            101,
                            "OB101",
                            "Java - Initiation",
                            "Lyon",
                            true,
                            6,
                            "Programmation orientée objet",
                            "Apprendre à coder en Java",
                            "-29ans",
                            "Chapitre1, chapitre2..etc",
                            null,
                            null,
                            null
                    )
            );
            formationService.addFormation(
                    new FormationDTO(
                            301,
                            "OB301",
                            "Java - accès aux données",
                            "Toulouse",
                            true,
                            8,
                            "aucun",
                            "Comprendre la notion de classe/objet",
                            "tout public",
                            "Chapter3,chapter5",
                            null,
                            null,
                            null
                    )
            );
            formationService.addFormation(
                    new FormationDTO(
                            134,
                            "AB134",
                            "Programmation web",
                            "Lyon",
                            true,
                            3,
                            "Aucun",
                            "apprendre à faire des sites web en HTML/CSS/JS",
                            "tout public",
                            "Chapter6, Chapter4",
                            null,
                            null,
                            null
                    )
            );
            // fetch all trainings
            log.info("Trainings found with findAllFormations():");
            log.info("-------------------------------");
            for (FormationDTO f : formationService.findAllFormations()) {
                log.info(f.toString());
            }
            log.info("");
        }

        private void demo1() {
            catalogueService.addCatalogue(new Catalogue("title", "auteur1", "creationDate"));
            catalogueService.addCatalogue(new Catalogue("title2", "auteur2", "creationDate2"));
            catalogueService.addCatalogue(new Catalogue("title3", "auteur3", "creationDate3"));
            // fetch all catalogues
            log.info("Catalogues found with findAllCatalogues():");
            log.info("-------------------------------");
            for (Catalogue catalogue : catalogueService.findAllCatalogues()) {
                log.info(catalogue.toString());
            }
            log.info("");
        }

        private void demo2() {
            //Session(LocalDate dateDebut, LocalDate dateFin, String lieu, float prix)
            Session s = new Session(LocalDate.of(2022, 5, 20), LocalDate.of(2022, 5, 26), "Lyon", 3000.0f);
            Session s1 = new Session(LocalDate.of(2022, 7, 12), LocalDate.of(2022, 8, 12), "Toulouse", 3500.0f);
            Session s2 = new Session(LocalDate.of(2022, 3, 20), LocalDate.of(2022, 3, 23), "Lyon", 6000.0f);
            sessionService.createSession(s);
            sessionService.createSession(s1);
            sessionService.createSession(s2);
            // fetch all sessions
            log.info("Catalogues found with findAllSessions():");
            log.info("-------------------------------");
            log.info(sessionService.getSession(1).toString());
            log.info(sessionService.getSession(2).toString());
            log.info(sessionService.getSession(3).toString());

            FormationDTO f1 = formationService.findFormationById(1);
            FormationDTO f2 = formationService.findFormationById(2);

            s.setFormation(formationService.toEntity(f1));
            s1.setFormation(formationService.toEntity(f2));
            s2.setFormation(formationService.toEntity(f2));
            sessionService.saveAll(Arrays.asList(s, s1, s2));
            log.info("");
        }

        ;

        private void demo3() {
            themeService.addTheme(new Theme("Développement"));
            themeService.addTheme(new Theme("Big Data, Data Science et IA"));
            themeService.addTheme(new Theme("Informatique décicionnelle"));
            themeService.addTheme(new Theme("Bases de données"));
            themeService.addTheme(new Theme("Réseaux et Télécoms"));
            themeService.addTheme(new Theme("Cybersécurité"));
            themeService.addTheme(new Theme("Cloud computing"));
            themeService.addTheme(new Theme("Virtualisation"));
            themeService.addTheme(new Theme("Windows et System Center"));
            themeService.addTheme(new Theme("Linux, Unix, Mac"));
            themeService.addTheme(new Theme("Solutions collaboratives Microsoft"));
            themeService.addTheme(new Theme("IBM"));
            themeService.addTheme(new Theme("SAP"));
            themeService.addTheme(new Theme("Tests"));
            themeService.addTheme(new Theme("Développement web et mobilité"));
            themeService.addTheme(new Theme("IoT, Systèmes embarquées, Robotic Process Automation"));
            themeService.addTheme(new Theme("DevOps, industrialisation et gestion de production"));
            themeService.addTheme(new Theme("PAO, CAO, DAO, BIM"));
            // fetch all Themes
            log.info("Themes found with findAllThemes():");
            log.info("-------------------------------");
            log.info(themeService.findAllThemes().toString());

            FormationDTO f1 = formationService.findFormationById(1);
            FormationDTO f2 = formationService.findFormationById(2);

            Set<ThemeDTO> themes1 = Stream.of(themeService.findThemeById(1), themeService.findThemeById(3)).collect(Collectors.toSet());
            Set<ThemeDTO> themes2 = Stream
                    .of(themeService.findThemeById(2), themeService.findThemeById(5), themeService.findThemeById(7))
                    .collect(Collectors.toSet());
            f1.setThemes(themes1);
            f2.setThemes(themes2);

            //themeService.saveAll();
            log.info("Demo3-Themes OK");
        }
    }
}