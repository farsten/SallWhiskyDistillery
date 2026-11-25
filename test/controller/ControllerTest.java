package controller;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Mark mark;
    private MaltBatch maltBatch;
    private Medarbejder medarbejder;

    @BeforeEach
    void setUp() {
        Storage.ryd();
        mark = Controller.opretMark(1, "Stadsgård");
        maltBatch = Controller.opretMaltBatch(1, "Evergreen", LocalDate.of(2020, 8, 15), mark);
        medarbejder = Controller.opretMedarbejder(1, "Snævar");
    }

    //Mark
    @Test
    void opretMark() {
        Mark ny = Controller.opretMark(2, "Mosevang");
        assertEquals(ny, Controller.findMark(2));
    }

    @Test
    void opretMarkMedEksisterendeIdAfvises() {
        assertThrows(IllegalArgumentException.class, () -> Controller.opretMark(1, "Dublet"));
    }

    @Test
    void opretMarkMedTomtNavnAfvises() {
        assertThrows(IllegalArgumentException.class, () -> Controller.opretMark(2, " "));
    }

    //MaltBatch
    @Test
    void opretMaltBatchUdenMarkAfvises() {
        assertThrows(IllegalArgumentException.class, () ->
                Controller.opretMaltBatch(2, "Irina", LocalDate.of(2020, 8, 1), null));
    }

    @Test
    void opretMaltBatchMedFremtidigHøstdatoAfvises() {
        assertThrows(IllegalArgumentException.class, () ->
                Controller.opretMaltBatch(2, "Irina", LocalDate.now().plusDays(1), mark));
    }

    //Medarbejder
    @Test
    void opretMedarbejderMedTomtNavnAfvises() {
        assertThrows(IllegalArgumentException.class, () -> Controller.opretMedarbejder(2, " "));
    }

    //Destillering
    @Test
    void registrerDestillering() {
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "Tørv", "", maltBatch, medarbejder);

        assertEquals(d, Controller.findDestillering(d.getNewMakeNr()));
    }

    @Test
    void registrerDestilleringUdenRygemateriale() {
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, null, "", maltBatch, medarbejder);

        assertNull(d.getRygemateriale());
    }

    @Test
    void newMakeNrStigerForHverDestillering() {
        Destillering d1 = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);
        Destillering d2 = Controller.registrerDestillering(LocalDate.of(2021, 3, 1),
                LocalDate.of(2021, 3, 3), 300, 67.0, "", "", maltBatch, medarbejder);

        assertEquals(1, d1.getNewMakeNr());
        assertEquals(2, d2.getNewMakeNr());
    }

    @Test
    void slutDatoFørStartDatoAfvises() {
        assertThrows(IllegalArgumentException.class, () ->
                Controller.registrerDestillering(LocalDate.of(2021, 2, 22),
                        LocalDate.of(2021, 2, 20), 400, 68.5, "", "", maltBatch, medarbejder));
    }

    @Test
    void ugyldigAlkoholprocentAfvises() {
        assertThrows(IllegalArgumentException.class, () ->
                Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                        LocalDate.of(2021, 2, 22), 400, 101, "", "", maltBatch, medarbejder));
    }

    //Fad
    @Test
    void opretFad() {
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - spansk");
        assertEquals(fad, Controller.findFad(65));
    }

    @Test
    void opretFadMedEksisterendeIdAfvises() {
        Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - fransk");
        assertThrows(IllegalArgumentException.class, () ->
                Controller.opretFad(65, 94, "Andet", "Bourbon", "Egetræ - fransk"));
    }

    @Test
    void fadMedStørrelse0Afvises() {
        assertThrows(IllegalArgumentException.class, () ->
                Controller.opretFad(65, 0, "Casknolia", "Sherry", "Egetræ - spansk"));
    }

    //Påfyldning
    @Test
    void registrerPåfyldning() {
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - spansk");
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);

        Påfyldning p = Controller.registrerPåfyldning(1, LocalDate.of(2021, 2, 28), 100, 63, fad, d);

        assertTrue(fad.getPåfyldninger().contains(p));
    }

    @Test
    void påfyldningStørreEndFadetAfvises() {
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - spansk");
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);

        assertThrows(IllegalArgumentException.class, () ->
                Controller.registrerPåfyldning(1, LocalDate.of(2021, 2, 28), 140, 63, fad, d));
    }

    @Test
    void flerePåfyldningerOverStørrelsenAfvises() {
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - spansk");
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);
        Controller.registrerPåfyldning(1, LocalDate.of(2021, 2, 28), 100, 63, fad, d);

        // 100 + 40 = 140 liter i et fad på 130 liter
        assertThrows(IllegalArgumentException.class, () ->
                Controller.registrerPåfyldning(2, LocalDate.of(2021, 3, 1), 40, 63, fad, d));
    }

    @Test
    void påfyldningTilPræcisFuldtFadAccepteres() {
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - spansk");
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);
        Controller.registrerPåfyldning(1, LocalDate.of(2021, 2, 28), 100, 63, fad, d);
        Controller.registrerPåfyldning(2, LocalDate.of(2021, 3, 1), 30, 63, fad, d);

        assertEquals(130, fad.getIndholdLiter());
    }

    @Test
    void påfyldningFørDestilleringensSlutdatoAfvises() {
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - spansk");
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);

        assertThrows(IllegalArgumentException.class, () ->
                Controller.registrerPåfyldning(1, LocalDate.of(2021, 2, 21), 100, 63, fad, d));
    }

    @Test
    void påfyldningUdenDatoAfvises() {
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - spansk");
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);

        assertThrows(IllegalArgumentException.class, () ->
                Controller.registrerPåfyldning(1, null, 100, 63, fad, d));
    }

    //Lager og reoler
    @Test
    void opretLagerMedKapacitet0Afvises() {
        assertThrows(IllegalArgumentException.class, () -> Controller.opretLager(1, "Sall", 0));
    }

    @Test
    void opretReolGiverAllePladserSomLedige() {
        Lager lager = Controller.opretLager(1, "Sall", 100);
        Controller.opretReol(lager, 1, 3, 4);

        assertEquals(12, Controller.findLedigePladser(lager).size());
    }

    @Test
    void opretSammeReolToGangeAfvises() {
        Lager lager = Controller.opretLager(1, "Sall", 100);
        Controller.opretReol(lager, 1, 3, 4);

        assertThrows(IllegalArgumentException.class, () -> Controller.opretReol(lager, 1, 2, 2));
    }

    @Test
    void reolOverKapacitetAfvises() {
        Lager lager = Controller.opretLager(1, "Sall", 10);

        assertThrows(IllegalStateException.class, () -> Controller.opretReol(lager, 1, 3, 4));
        assertEquals(0, lager.getAntalPladser());
    }

    @Test
    void reolMed0HylderAfvises() {
        Lager lager = Controller.opretLager(1, "Sall", 100);
        assertThrows(IllegalArgumentException.class, () -> Controller.opretReol(lager, 1, 0, 4));
    }

    @Test
    void opretReolUdenLagerAfvises() {
        assertThrows(IllegalArgumentException.class, () -> Controller.opretReol(null, 1, 3, 4));
    }

    //Fadplacering
    @Test
    void placerFad() {
        Lager lager = Controller.opretLager(1, "Sall", 100);
        Controller.opretReol(lager, 3, 2, 1);
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - spansk");

        Controller.placerFad(fad, lager, 3, 2, 1);

        assertEquals("Lager 1 (Sall), reol 3, hylde 2, plads 1", Controller.findFadPlacering(65));
    }

    @Test
    void placerFadPåIkkeEksisterendePladsAfvises() {
        Lager lager = Controller.opretLager(1, "Sall", 100);
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - spansk");

        assertThrows(IllegalArgumentException.class, () -> Controller.placerFad(fad, lager, 9, 9, 9));
    }

    @Test
    void toFadePåSammePladsAfvises() {
        Lager lager = Controller.opretLager(1, "Sall", 100);
        Controller.opretReol(lager, 1, 1, 1);
        Fad fad1 = Controller.opretFad(1, 130, "A", "Sherry", "Egetræ - spansk");
        Fad fad2 = Controller.opretFad(2, 130, "B", "Bourbon", "Egetræ - fransk");
        Controller.placerFad(fad1, lager, 1, 1, 1);

        assertThrows(IllegalArgumentException.class, () -> Controller.placerFad(fad2, lager, 1, 1, 1));
    }

    @Test
    void flereFadePåSammeHylde() {
        Lager lager = Controller.opretLager(1, "Sall", 100);
        Controller.opretReol(lager, 1, 1, 2);
        Fad fad1 = Controller.opretFad(1, 130, "A", "Sherry", "Egetræ - spansk");
        Fad fad2 = Controller.opretFad(2, 130, "B", "Bourbon", "Egetræ - fransk");

        Controller.placerFad(fad1, lager, 1, 1, 1);
        Controller.placerFad(fad2, lager, 1, 1, 2);

        assertEquals("Lager 1 (Sall), reol 1, hylde 1, plads 1", Controller.findFadPlacering(1));
        assertEquals("Lager 1 (Sall), reol 1, hylde 1, plads 2", Controller.findFadPlacering(2));
    }

    @Test
    void flytFadFrigørGammelPlads() {
        Lager lager = Controller.opretLager(1, "Sall", 100);
        Controller.opretReol(lager, 1, 1, 2);
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - spansk");

        Controller.placerFad(fad, lager, 1, 1, 1);
        Controller.placerFad(fad, lager, 1, 1, 2);

        assertTrue(lager.findLagerplacering(1, 1, 1).erLedig());
        assertFalse(lager.findLagerplacering(1, 1, 2).erLedig());
    }

    //Søgninger
    @Test
    void ledigePladserFalderNårFadPlaceres() {
        Lager lager = Controller.opretLager(1, "Sall", 100);
        Controller.opretReol(lager, 1, 1, 4);
        Fad fad = Controller.opretFad(1, 130, "A", "Sherry", "Egetræ - spansk");

        Controller.placerFad(fad, lager, 1, 1, 2);

        ArrayList<Lagerplacering> ledige = Controller.findLedigePladser(lager);
        assertEquals(3, ledige.size());
        assertFalse(ledige.contains(lager.findLagerplacering(1, 1, 2)));
    }

    @Test
    void findLedigePladserUdenLagerAfvises() {
        assertThrows(IllegalArgumentException.class, () -> Controller.findLedigePladser(null));
    }

    @Test
    void søgningIgnorererStoreOgSmåBogstaver() {
        Fad sherry = Controller.opretFad(1, 130, "A", "Sherry", "Egetræ - spansk");

        assertTrue(Controller.findFadeEfterTidligereIndhold("sherry").contains(sherry));
    }

    @Test
    void søgningFinderDelvistMatch() {
        Fad exSherry = Controller.opretFad(1, 130, "A", "Ex-sherry", "Egetræ - spansk");
        Fad bourbon = Controller.opretFad(2, 130, "B", "Bourbon", "Egetræ - fransk");

        ArrayList<Fad> resultat = Controller.findFadeEfterTidligereIndhold("sherry");

        assertTrue(resultat.contains(exSherry));
        assertFalse(resultat.contains(bourbon));
    }

    @Test
    void søgningUdenTrefferGiverTomListe() {
        Controller.opretFad(1, 130, "A", "Sherry", "Egetræ - spansk");
        assertTrue(Controller.findFadeEfterTidligereIndhold("rødvin").isEmpty());
    }

    @Test
    void søgningCrasherIkkeVedFadUdenTidligereIndhold() {
        Controller.opretFad(1, 130, "A", null, "Egetræ - spansk");
        Fad sherry = Controller.opretFad(2, 130, "B", "Sherry", "Egetræ - fransk");

        assertTrue(Controller.findFadeEfterTidligereIndhold("sherry").contains(sherry));
    }

    @Test
    void søgningMedTomtSøgeordAfvises() {
        assertThrows(IllegalArgumentException.class, () -> Controller.findFadeEfterTidligereIndhold(" "));
    }

    //Blanding og whisky
    @Test
    void tilføjTomtFadTilBlandingAfvises() {
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - spansk");
        Blanding b = Controller.opretBlanding(1, LocalDate.of(2026, 11, 1), "A", 500, 52.2);

        assertThrows(IllegalArgumentException.class, () -> Controller.tilføjFadTilBlanding(b, fad));
    }

    @Test
    void opretWhiskyMedTomBlandingAfvises() {
        Blanding b = Controller.opretBlanding(1, LocalDate.of(2026, 11, 1), "A", 500, 52.2);
        assertThrows(IllegalArgumentException.class, () ->
                Controller.opretWhisky(1, "A", 52.2, 0, "", "", b));
    }

    @Test
    void whiskyUdenBlandingAfvises() {
        assertThrows(IllegalArgumentException.class, () ->
                Controller.opretWhisky(1, "A", 48, 0, "", "", null));
    }

    @Test
    void whiskyMedNegativtVandAfvises() {
        Blanding b = Controller.opretBlanding(1, LocalDate.of(2026, 11, 1), "A", 500, 52.2);
        assertThrows(IllegalArgumentException.class, () ->
                Controller.opretWhisky(1, "A", 48, -1, "Dal", "", b));
    }

    @Test
    void whiskyMedVandUdenKildeAfvises() {
        Blanding b = Controller.opretBlanding(1, LocalDate.of(2026, 11, 1), "A", 500, 52.2);
        assertThrows(IllegalArgumentException.class, () ->
                Controller.opretWhisky(1, "A", 48, 5, " ", "", b));
    }

    //Historik
    @Test
    void visWhiskyHistorik() {
        Lager lager = Controller.opretLager(1, "Sall", 100);
        Controller.opretReol(lager, 3, 2, 1);
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - spansk");
        Controller.placerFad(fad, lager, 3, 2, 1);
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);
        Controller.registrerPåfyldning(1, LocalDate.of(2021, 2, 28), 100, 63, fad, d);
        Blanding b = Controller.opretBlanding(1, LocalDate.of(2026, 11, 1), "Første udgivelse", 500, 52.2);
        Controller.tilføjFadTilBlanding(b, fad);
        Controller.opretWhisky(1, "Første udgivelse", 52.2, 5, "Begravet dal under destilleriet",
                "Første whisky fra Sall", b);

        String historik = Controller.visWhiskyHistorik(1);

        assertTrue(historik.contains("Første udgivelse"));
        assertTrue(historik.contains("Begravet dal under destilleriet"));
        assertTrue(historik.contains("Fad #65"));
        assertTrue(historik.contains("reol 3, hylde 2, plads 1"));
        assertTrue(historik.contains("Snævar"));
        assertTrue(historik.contains("Stadsgård"));
    }

    @Test
    void visHistorikForUkendtWhiskyAfvises() {
        assertThrows(IllegalArgumentException.class, () -> Controller.visWhiskyHistorik(999));
    }

    //Treårsreglen
    @Test
    void fadPåPræcisTreÅrKanBrugesIBlanding() {
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - spansk");
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);
        Controller.registrerPåfyldning(1, LocalDate.of(2023, 11, 1), 100, 63, fad, d);
        Blanding b = Controller.opretBlanding(1, LocalDate.of(2026, 11, 1), "A", 500, 52.2);

        Controller.tilføjFadTilBlanding(b, fad);

        assertTrue(b.getFadList().contains(fad));
    }

    @Test
    void fadEnDagUnderTreÅrAfvises() {
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - spansk");
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);
        Controller.registrerPåfyldning(1, LocalDate.of(2023, 11, 2), 100, 63, fad, d);
        Blanding b = Controller.opretBlanding(1, LocalDate.of(2026, 11, 1), "A", 500, 52.2);

        assertThrows(IllegalArgumentException.class, () -> Controller.tilføjFadTilBlanding(b, fad));
    }

    @Test
    void senestePåfyldningAfgørOmFadetErKlar() {
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", "Egetræ - spansk");
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);
        Controller.registrerPåfyldning(1, LocalDate.of(2021, 3, 1), 50, 63, fad, d); // gammel
        Controller.registrerPåfyldning(2, LocalDate.of(2025, 6, 1), 50, 63, fad, d); // ny

        assertFalse(fad.erKlarTilTapning(LocalDate.of(2026, 11, 1)));
    }

    @Test
    void findFadeKlarTilTapning() {
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);
        Fad gammelt = Controller.opretFad(1, 130, "A", "Sherry", "Egetræ - spansk");
        Fad nyt = Controller.opretFad(2, 130, "B", "Bourbon", "Egetræ - fransk");
        Controller.registrerPåfyldning(1, LocalDate.of(2021, 3, 1), 100, 63, gammelt, d);
        Controller.registrerPåfyldning(2, LocalDate.now(), 100, 63, nyt, d);

        assertTrue(Controller.findFadeKlarTilTapning().contains(gammelt));
        assertFalse(Controller.findFadeKlarTilTapning().contains(nyt));
    }
}