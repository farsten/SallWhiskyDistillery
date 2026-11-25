package gui;

import controller.Controller;
import javafx.application.Application;
import model.*;

import java.time.LocalDate;

public class App {
    void main() {
        initStorage();
        Application.launch(SystemGui.class);
    }

    private void initStorage() {



        Mark mark = Controller.opretMark(1, "Stadsgård");
        MaltBatch maltBatch = Controller.opretMaltBatch(1, "Evergreen", LocalDate.of(2020, 8, 15), mark);
        Medarbejder medarbejder = Controller.opretMedarbejder(1, "Snævar");

        Lager lager = Controller.opretLager(1, "Sall", 100);
        Controller.opretReol(lager, 3, 2, 2);
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Spansk");
        Controller.placerFad(fad, lager, 3, 2, 1);
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);
        Controller.registrerPåfyldning(1, LocalDate.of(2021, 2, 28), 100, 63, fad, d);
        Blanding b = Controller.opretBlanding(1, LocalDate.of(2026, 11, 1), "Første udgivelse", 500, 52.2);
        Controller.tilføjFadTilBlanding(b, fad);
        Controller.opretWhisky(1, "Færste udgivelse", 52.2, 2.1, "Baghave", "Lækker whisky til aftensmad", b);
        IO.println(Controller.visWhiskyHistorik(1));
    }



}
