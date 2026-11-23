package controller;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {



    private Mark mark;
    private MaltBatch maltBatch;
    private Medarbejder medarbejder;

    @BeforeEach
    void setUp() {
        Storage.ryd();
        mark = Controller.opretMark(1, "Stadsgaard");
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
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", 0, "Egetræ - spansk");
        assertEquals(fad, Controller.findFad(65));
    }

    @Test
    void opretFadMedEksisterendeIdAfvises() {
        Controller.opretFad(65, 130, "Casknolia", "Sherry", 0, "Egetræ - fransk");
        assertThrows(IllegalArgumentException.class, () ->
                Controller.opretFad(65, 94, "Andet", "Bourbon", 0, "Egetræ - fransk"));
    }

    //Påfyldning

    @Test
    void registrerPåfyldning() {
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", 0, "Egetræ - spansk");
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);

        Påfyldning p = Controller.registrerPåfyldning(1, LocalDate.of(2021, 2, 28), 100, 63, fad, d);

        assertTrue(fad.getPåfyldninger().contains(p));
    }

    @Test
    void påfyldningStørreEndFadetAfvises() {
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", 0, "Egetræ - spansk");
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);

        assertThrows(IllegalArgumentException.class, () ->
                Controller.registrerPåfyldning(1, LocalDate.of(2021, 2, 28), 140, 63, fad, d));
    }

    @Test
    void påfyldningFørDestilleringensSlutdatoAfvises() {
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", 0, "Egetræ - spansk");
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);

        assertThrows(IllegalArgumentException.class, () ->
                Controller.registrerPåfyldning(1, LocalDate.of(2021, 2, 21), 100, 63, fad, d));
    }

    //Fadplacering

    @Test
    void placerFad() {
        Lager lager = Controller.opretLager(1, "Sall", 100);
        Controller.opretLagerplacering(lager, 3, 2);
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", 0, "Egetræ - spansk");

        Controller.placerFad(fad, lager, 3, 2);

        assertEquals("Lager 1 (Sall), reol 3, hylde 2", Controller.findFadPlacering(65));
    }

    @Test
    void placerFadPaaIkkeEksisterendePladsAfvises() {
        Lager lager = Controller.opretLager(1, "Sall", 100);
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", 0, "Egetræ - spansk");

        assertThrows(IllegalArgumentException.class, () -> Controller.placerFad(fad, lager, 9, 9));
    }

    @Test
    void toFadePaaSammePladsAfvises() {
        Lager lager = Controller.opretLager(1, "Sall", 100);
        Controller.opretLagerplacering(lager, 1, 1);
        Fad fad1 = Controller.opretFad(1, 130, "A", "Sherry", 0, "Egetræ - spansk");
        Fad fad2 = Controller.opretFad(2, 130, "B", "Bourbon", 0, "Egetræ - fransk");
        Controller.placerFad(fad1, lager, 1, 1);

        assertThrows(IllegalArgumentException.class, () -> Controller.placerFad(fad2, lager, 1, 1));
    }

    //Blanding og whisky

    @Test
    void tilføjTomtFadTilBlandingAfvises() {
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", 0, "Egetræ - spansk");
        Blanding b = Controller.opretBlanding(1, LocalDate.of(2026, 11, 1), "A", 500, 52.2);

        assertThrows(IllegalArgumentException.class, () -> Controller.tilføjFadTilBlanding(b, fad));
    }

    @Test
    void opretWhiskyMedTomBlandingAfvises() {
        Blanding b = Controller.opretBlanding(1, LocalDate.of(2026, 11, 1), "A", 500, 52.2);
        assertThrows(IllegalArgumentException.class, () -> Controller.opretWhisky(1, "A", 52.2, b));
    }

    //Historik

    @Test
    void visWhiskyHistorik() {
        Lager lager = Controller.opretLager(1, "Sall", 100);
        Controller.opretLagerplacering(lager, 3, 2);
        Fad fad = Controller.opretFad(65, 130, "Casknolia", "Sherry", 0, "Egetræ - spansk");
        Controller.placerFad(fad, lager, 3, 2);
        Destillering d = Controller.registrerDestillering(LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 2, 22), 400, 68.5, "", "", maltBatch, medarbejder);
        Controller.registrerPåfyldning(1, LocalDate.of(2021, 2, 28), 100, 63, fad, d);
        Blanding b = Controller.opretBlanding(1, LocalDate.of(2026, 11, 1), "Første udgivelse", 500, 52.2);
        Controller.tilføjFadTilBlanding(b, fad);
        Controller.opretWhisky(1, "Første udgivelse", 52.2, b);

        String historik = Controller.visWhiskyHistorik(1);
        assertTrue(historik.contains("Første udgivelse"));
        assertTrue(historik.contains("Fad #65"));
        assertTrue(historik.contains("reol 3, hylde 2"));
        assertTrue(historik.contains("Snævar"));
        assertTrue(historik.contains("Stadsgaard"));
    }

    @Test
    void visHistorikForUkendtWhiskyAfvises() {
        assertThrows(IllegalArgumentException.class, () -> Controller.visWhiskyHistorik(999));
    }
}