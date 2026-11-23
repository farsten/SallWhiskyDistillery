package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;

public class Controller {


    //UC1: Registrér mark
    public static Mark opretMark(int markId, String navn) {
        if (findMark(markId) != null) { //hvis marken ikke findes
            throw new IllegalArgumentException("Mark nr. " + markId + " findes allerede");
        }

        if (navn == null) { //hvis navnet ikke er givet
            throw new IllegalArgumentException("Ugyldigt navn angivet");
        }
        Mark mark = new Mark(markId, navn);
        Storage.addMark(mark);
        return mark;
    }

    //UC2: Opret maltBatch
    public static MaltBatch opretMaltBatch(int batchId, String kornsort, LocalDate høstDato, Mark mark) {
        if (findMaltBatch(batchId) != null) {
            throw new IllegalArgumentException("MaltBatch nr. " + batchId + " findes allerede");
        }

        if (kornsort.isEmpty()) {
            throw new IllegalArgumentException("Ugyldigt kornsort angivet");
        }

        if (høstDato == null) {
            throw new IllegalArgumentException("Vælg en høst dato");
        }

        if (mark == null) {
            throw new IllegalArgumentException("Der skal vælges en mark");
        }

        MaltBatch maltBatch = new MaltBatch(batchId, kornsort, høstDato, mark);
        Storage.addMaltBatch(maltBatch);

        return maltBatch;
    }


    // UC3: Registrér destillering
    public static Destillering registrerDestillering(LocalDate startDato, LocalDate slutDato, double mængdeLiter, double alkoholProcent, String rygemateriale, String kommentar, MaltBatch maltBatch, Medarbejder medarbejder) {
        if (maltBatch == null) {
            throw new IllegalArgumentException("Der skal vælges en maltbatch");
        }
        if (medarbejder == null) {
            throw new IllegalArgumentException("Der skal vælges en medarbejder");
        }
        if (startDato == null || slutDato == null) {
            throw new IllegalArgumentException("Start- og slutdato skal udfyldes");
        }
        if (slutDato.isBefore(startDato)) {
            throw new IllegalArgumentException("Slutdato kan ikke ligge før startdato");
        }
        if (mængdeLiter <= 0) {
            throw new IllegalArgumentException("Mængden skal være større end 0 liter");
        }
        if (alkoholProcent < 0 || alkoholProcent > 100) {
            throw new IllegalArgumentException("Alkoholprocent skal være mellem 0 og 100");
        }

        int newMakeNr = næsteNewMakeNr();
        Destillering destillering = new Destillering(newMakeNr, startDato, slutDato, mængdeLiter, alkoholProcent, rygemateriale, kommentar, maltBatch, medarbejder);
        Storage.addDestillering(destillering);
        return destillering;
    }
    //UC4: Registrér fad
    public static Fad opretFad(int fadId, int startMængde, String leverandør, String tidligereIndhold, double slutMængde, String type) {
        if (findFad(fadId) != null) { //hvis fad allerede findes
            throw new IllegalArgumentException("Fad nr. " + fadId + " findes allerede");
        }
        Fad fad = new Fad(fadId, startMængde, leverandør, tidligereIndhold, slutMængde, type);
        Storage.addFad(fad);
        return fad;
    }

    //UC5: Registrér påfyldning på fad
    public static Påfyldning registrerPåfyldning(int påfyldningId, LocalDate dato, double liter, double alkoholprocent, Fad fad, Destillering destillering) {
        if (fad == null) {
            throw new IllegalArgumentException("Der skal vælges et fad");
        }
        if (destillering == null) {
            throw new IllegalArgumentException("Der skal vælges en destillering");
        }

        if (liter <= 0) {
            throw new IllegalArgumentException("Liter kan ikke være 0 eller under");
        }

        if (alkoholprocent < 0 || alkoholprocent > 100) {
            throw new IllegalArgumentException("Alkoholprocent skal være mellem 0 og 100");
        }

        if (dato.isBefore(destillering.getSlutDato())) {
            throw new IllegalArgumentException("Angivet dato er før destilleringens slut dato");
        }

        if (fad.getSlutMængde() + liter <= fad.getStartMængde()) {
            double plads = fad.getStartMængde() - fad.getSlutMængde();
            throw new IllegalArgumentException("Fadet har kun plads til " + plads + " liter");
        }

        Påfyldning påfyldning = new Påfyldning(påfyldningId, dato, liter, alkoholprocent, destillering);
        fad.addPåfyldning(påfyldning);

        return påfyldning;
    }


    //UC6: Registrér fadplacering
    public static void placerFad(Fad fad, Lager lager, int reol, int hylde) {
        Lagerplacering placering = lager.findLagerplacering(reol, hylde);
        if (placering == null) { //hvis placeringen ikke findes
            throw new IllegalArgumentException("Reol " + reol + ", hylde " + hylde + " findes ikke");
        }
        fad.setLagerplacering(placering);
    }


    //UC7: Opret whiskeyprodukt
    public static Blanding opretBlanding(int blandingId, LocalDate dato, String navn, int liter, double alkoholprocent) {
        if (findBlanding(blandingId) != null) {
            throw new IllegalArgumentException("Blanding nr. " + blandingId + " findes allerede");
        }

        if (liter <= 0) {
            throw new IllegalArgumentException("Liter kan ikke være 0 eller under");
        }

        if (alkoholprocent < 0 || alkoholprocent > 100) {
            throw new IllegalArgumentException("Alkoholprocent skal være mellem 0 og 100");
        }

        Blanding blanding = new Blanding(blandingId, dato, navn, liter, alkoholprocent);
        Storage.addBlanding(blanding);
        return blanding;
    }

    public static void tilføjFadTilBlanding(Blanding blanding, Fad fad) {
        if (fad == null) {
            throw new IllegalArgumentException("Der skal vælges et fad");
        }
        if (blanding == null) {
            throw new IllegalArgumentException("Der skal vælges en blanding");
        }

        if (fad.getPåfyldninger().isEmpty()) {
            throw new IllegalArgumentException("Der er ingen påfyldninger på det valgte fad");
        }

        if (blanding.getFadList().contains(fad)) {
            throw new IllegalArgumentException("Fadet er allerede med i blandingen");
        }
        blanding.addFad(fad);
    }

    public static Whisky opretWhisky(int produktId, String navn, double alkoholprocent) {
        if (findWhisky(produktId) != null) {
            throw new IllegalArgumentException("Whisky nr. " + produktId + " findes allerede");
        }

        if (alkoholprocent < 0 || alkoholprocent > 100) {
            throw new IllegalArgumentException("Alkoholprocent skal være mellem 0 og 100");
        }

        Whisky whisky = new Whisky(produktId, navn, alkoholprocent);
        Storage.addWhisky(whisky);

        return whisky;
    }

    public static Lager opretLager(int lagerId, String adresse, int kapacitet) {
        Lager lager = new Lager(lagerId, adresse, kapacitet);
        Storage.addLager(lager);
        return lager;
    }

    public static Lagerplacering opretLagerplacering(Lager lager, int reol, int hylde) {
        return lager.opretLagerplacering(reol, hylde);
    }

    //UC8: Vis whiskyhistorik
    public static String visWhiskyHistorik(int produktId) {
        Whisky whisky = findWhisky(produktId);
        if (whisky == null) {
            throw new IllegalArgumentException("Whisky nr . " + produktId + " findes ikke");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WHISKY nr. ").append(whisky.getProduktId()).append(": ").
                append(whisky.getNavn()).append(" (").append(whisky.getAlkoholprocent()).append("%)\n");

        if (whisky.getBlandingList().isEmpty()) {
            stringBuilder.append("  Ingen blandinger registreret\n");
            return stringBuilder.toString();
        }

        for (Blanding blanding : whisky.getBlandingList()) {
            stringBuilder.append("\n  Blanding: ").append(blanding.getNavn()).append(", ").append(blanding.getDato()).append(", ").append(blanding.getLiter()).append(" liter").append(", ").append(blanding.getAlkoholprocent()).append("%\n");

            if (blanding.getFadList().isEmpty()) {
                stringBuilder.append("    Ingen fade registreret\n");
            }

            //MANGLER AT TILFØJE ALT MED FAD HISTORIK!!
        }
        return stringBuilder.toString();

    }

    //Opret medarbejder
    public static Medarbejder opretMedarbejder(int medarbejderId, String navn) {

        if (findMedarbejder(medarbejderId) != null) {
            throw new IllegalArgumentException("Medarbejder nr. " + medarbejderId + " findes allerede");
        }

        if (navn == null) {
            throw new IllegalArgumentException("Ugyldigt navn angivet");
        }

        Medarbejder medarbejder = new Medarbejder(medarbejderId, navn);
        Storage.addMedarbejdere(medarbejder);

        return medarbejder;
    }


    //Find metoder
    public static String findFadPlacering(int fadId) {
        Fad fad = findFad(fadId);
        if (fad == null) {
            return "Fad nr. " + fadId + " findes ikke";
        }
        return fad.getPlacering();
    }

    //Find metoder
    public static Mark findMark(int markId) {
        for (Mark mark : Storage.getMarker()) {
            if (mark.getMarkId() == markId) {
                return mark;
            }

        }
        return null;
    }

    public static Fad findFad(int fadId) {
        for (Fad fad : Storage.getFade()) {
            if (fad.getFadId() == fadId) {
                return fad;
            }
        }
        return null;
    }

    public static MaltBatch findMaltBatch(int maltBatchId) {
        for (MaltBatch maltBatch : Storage.getMaltBatch()) {
            if (maltBatch.getBatchId() == maltBatchId) {
                return maltBatch;
            }
        }
        return null;
    }

    public static Medarbejder findMedarbejder(int medarbejderId) {
        for (Medarbejder medarbejder : Storage.getMedarbejdere()) {
            if (medarbejder.getMedarbejderId() == medarbejderId) {
                return medarbejder;
            }
        }
        return null;
    }

    public static Destillering findDestillering(int newMakeNr) {
        for (Destillering d : Storage.getDestilleringer()) {
            if (d.getNewMakeNr() == newMakeNr) {
                return d;
            }
        }
        return null;
    }

    public static Blanding findBlanding(int blandingId) {
        for (Blanding blanding : Storage.getBlandinger()) {
            if (blanding.getBlandingId() == blandingId) {
                return blanding;
            }
        }
        return null;
    }

    public static Whisky findWhisky(int produktId) {
        for (Whisky whisky : Storage.getWhiskyer()) {
            if (whisky.getProduktId() == produktId) {
                return whisky;
            }
        }
        return null;
    }


    //newMakeNr
    private static int næsteNewMakeNr() {
        int højeste = 0;
        for (Destillering d : Storage.getDestilleringer()) {
            if (d.getNewMakeNr() > højeste) {
                højeste = d.getNewMakeNr();
            }
        }
        return højeste + 1;
    }

}