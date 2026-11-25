package controller;

import model.*;
import org.jetbrains.annotations.Nullable;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {


    //UC1: Registrér mark
    public static Mark opretMark(int markId, String navn) {
        if (findMark(markId) != null) { //hvis marken allerede findes
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

        if (kornsort == null || kornsort.isBlank()) {
            throw new IllegalArgumentException("Ugyldigt kornsort angivet");
        }

        if (høstDato == null) {
            throw new IllegalArgumentException("Vælg en høst dato");
        }

        if (høstDato.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Kan ikke vælge høst dato i fremtiden");
        }

        if (mark == null) {
            throw new IllegalArgumentException("Der skal vælges en mark");
        }

        MaltBatch maltBatch = new MaltBatch(batchId, kornsort, høstDato, mark);
        Storage.addMaltBatch(maltBatch);

        return maltBatch;
    }


    // UC3: Registrér destillering
    public static Destillering registrerDestillering(LocalDate startDato, LocalDate slutDato, double mængdeLiter, double alkoholProcent, @Nullable String rygemateriale, String kommentar, MaltBatch maltBatch, Medarbejder medarbejder) {
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
    public static Fad opretFad(int fadId, int størrelse, String leverandør, String tidligereIndhold, String type) {
        if (findFad(fadId) != null) {
            throw new IllegalArgumentException("Fad nr. " + fadId + " findes allerede");
        }
        if (størrelse <= 0) {
            throw new IllegalArgumentException("Fadets størrelse skal være større end 0 liter");
        }
        Fad fad = new Fad(fadId, størrelse, leverandør, tidligereIndhold, type);
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

        if (dato == null) {
            throw new IllegalArgumentException("Vælg en dato for påfyldningen");
        }
        if (dato.isBefore(destillering.getSlutDato())) {
            throw new IllegalArgumentException("Angivet dato er før destilleringens slut dato");
        }
        if (fad.getIndholdLiter() + liter > fad.getStørrelse()) {
            double plads = fad.getStørrelse() - fad.getIndholdLiter();
            throw new IllegalArgumentException("Fadet har kun plads til " + plads + " liter");
        }

        Påfyldning påfyldning = new Påfyldning(påfyldningId, dato, liter, alkoholprocent, destillering);
        fad.addPåfyldning(påfyldning);

        return påfyldning;
    }


    //UC6: Registrér fadplacering
    public static void placerFad(Fad fad, Lager lager, int reol, int hylde, int plads) {
        if (lager == null) {
            throw new IllegalArgumentException("Der skal vælges et lager");
        }
        if (fad == null) {
            throw new IllegalArgumentException("Der skal vælges et fad");
        }
        Lagerplacering placering = lager.findLagerplacering(reol, hylde, plads);
        if (placering == null) { //hvis placeringen ikke findes
            throw new IllegalArgumentException("Reol " + reol + ", hylde " + hylde + ", plads " + plads + " findes ikke");
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

        if (dato == null) {
            throw new IllegalArgumentException("Vælg en dato for blandingen");
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

        if (!fad.erKlarTilTapning(blanding.getDato())) {
            throw new IllegalArgumentException("Fad nr. " + fad.getFadId() + " har ikke lagret i mindst 3 år");
        }

        if (blanding.getFadList().contains(fad)) {
            throw new IllegalArgumentException("Fadet er allerede med i blandingen");
        }
        blanding.addFad(fad);
    }

    public static Whisky opretWhisky(int produktId, String navn, double alkoholprocent, double vandLiter,
                                     String vandKilde, String beskrivelse, Blanding blanding) {
        if (findWhisky(produktId) != null) {
            throw new IllegalArgumentException("Whisky nr. " + produktId + " findes allerede");
        }
        if (alkoholprocent < 0 || alkoholprocent > 100) {
            throw new IllegalArgumentException("Alkoholprocent skal være mellem 0 og 100");
        }
        if (vandLiter < 0) {
            throw new IllegalArgumentException("Mængden af vand kan ikke være negativ");
        }
        if (vandLiter > 0 && (vandKilde == null || vandKilde.isBlank())) {
            throw new IllegalArgumentException("Angiv hvor vandet kommer fra");
        }
        if (blanding == null) {
            throw new IllegalArgumentException("Der skal vælges en blanding");
        }
        if (blanding.getFadList().isEmpty()) {
            throw new IllegalArgumentException("Der er ingen fad i blandingen");
        }

        Whisky whisky = new Whisky(produktId, navn, alkoholprocent, vandLiter, vandKilde, beskrivelse, blanding);
        Storage.addWhisky(whisky);
        return whisky;
    }

    public static Lager opretLager(int lagerId, String adresse, int kapacitet) {
        Lager lager = new Lager(lagerId, adresse, kapacitet);
        Storage.addLager(lager);
        return lager;
    }

    public static void opretReol(Lager lager, int reol, int antalHylder, int pladserPrHylde) {
        if (lager == null) {
            throw new IllegalArgumentException("Der skal vælges et lager");
        }
        lager.opretReol(reol, antalHylder, pladserPrHylde);
    }

    //UC8: Vis whiskyhistorik
    public static String visWhiskyHistorik(int produktId) {
        Whisky whisky = findWhisky(produktId);
        if (whisky == null) {
            throw new IllegalArgumentException("Whisky nr. " + produktId + " findes ikke");
        }

        String historik = "Whisky nr. " + whisky.getProduktId() + ": " + whisky.getNavn()
                + " (" + whisky.getAlkoholprocent() + "%)\n";
        if (whisky.getVandLiter() > 0) {
            historik += "Fortyndet med " + whisky.getVandLiter() + " liter vand fra " + whisky.getVandKilde() + "\n";
        } else {
            historik += "Ikke fortyndet (cask strength)\n";
        }
        if (whisky.getBeskrivelse() != null && !whisky.getBeskrivelse().isBlank()) {
            historik += "Beskrivelse: " + whisky.getBeskrivelse() + "\n";
        }
        Blanding blanding = whisky.getBlanding();
        historik += "\nBlanding: " + blanding.getNavn() + ", " + blanding.getDato()
                + ", " + blanding.getLiter() + " liter\n";

        for (Fad fad : blanding.getFadList()) {
            historik += "  Fad #" + fad.getFadId() + ": " + fad.getType()
                    + ", tidligere " + fad.getTidligereIndhold()
                    + ", fra " + fad.getLeverandør() + "\n";
            historik += "  Placering: " + fad.getPlacering() + "\n";

            for (Påfyldning påfyldning : fad.getPåfyldninger()) {
                Destillering d = påfyldning.getDestillering();
                MaltBatch batch = d.getMaltBatch();

                historik += "    " + påfyldning.getLiter() + " liter påfyldt " + påfyldning.getDato()
                        + " fra NM " + d.getNewMakeNr() + "\n";
                historik += "    Destilleret af " + d.getMedarbejder().getNavn()
                        + " af " + batch.getKornsort()
                        + " fra marken " + batch.getMark().getNavn() + "\n";
            }
        }
        return historik;
    }

    //Opret medarbejder
    public static Medarbejder opretMedarbejder(int medarbejderId, String navn) {

        if (findMedarbejder(medarbejderId) != null) {
            throw new IllegalArgumentException("Medarbejder nr. " + medarbejderId + " findes allerede");
        }

        if (navn == null || navn.isBlank()) { //isBlank() sørger for at hvis der
            // bliver skrevet et navn med whitepsace, at den fanger det som et ugyldigt navn
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

    public static ArrayList<Fad> findFadeKlarTilTapning() { //find fad, som er klar til tapning
        ArrayList<Fad> klar = new ArrayList<>();
        for (Fad fad : Storage.getFade()) {
            if (fad.erKlarTilTapning(LocalDate.now())) {
                klar.add(fad);
            }
        }
        return klar;
    }

    //søg fade efter tidligere indhold
    public static ArrayList<Fad> findFadeEfterTidligereIndhold(String søgeord) {
        if (søgeord == null || søgeord.isBlank()) {
            throw new IllegalArgumentException("Ugyldigt søgeord indtastet");
        }

        ArrayList<Fad> resultat = new ArrayList<>();
        søgeord = søgeord.trim().toLowerCase();
        for (Fad fad : Storage.getFade()) {
            String indhold = fad.getTidligereIndhold();
            if (indhold != null && indhold.trim().toLowerCase().contains(søgeord) ) {
                resultat.add(fad);
            }
        }
        return resultat;
    }

    // k15: ledige pladser på et lager
    public static ArrayList<Lagerplacering> findLedigePladser(Lager lager) {
        if (lager == null) {
            throw new IllegalArgumentException("Ugyldigt lager");
        }
        return new ArrayList<>(lager.getLedigePladser());
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