package controller;

import model.Fad;
import model.Lager;
import model.Lagerplacering;
import storage.Storage;

public class Controller {

    public static Lager opretLager(int lagerId, String adresse, int kapacitet) {
        Lager lager = new Lager(lagerId, adresse, kapacitet);
        Storage.addLager(lager);
        return lager;
    }

    public static Lagerplacering opretLagerplacering(Lager lager, int reol, int hylde) {
        return lager.opretLagerplacering(reol, hylde);
    }

    //UC2: Registrér fad
    public static Fad opretFad(int fadId, int startMængde, String leverandør,
                               String tidligereIndhold, double slutMængde, String type) {
        if (findFad(fadId) != null) {
            throw new IllegalArgumentException("Fad nr. " + fadId + " findes allerede");
        }
        Fad fad = new Fad(fadId, startMængde, leverandør, tidligereIndhold, slutMængde, type);
        Storage.addFad(fad);
        return fad;
    }

    //UC7: Registrér fadplacering
    public static void placerFad(Fad fad, Lager lager, int reol, int hylde) {
        Lagerplacering placering = lager.findLagerplacering(reol, hylde);
        if (placering == null) {
            throw new IllegalArgumentException("Reol " + reol + ", hylde " + hylde + " findes ikke");
        }
        fad.setLagerplacering(placering);
    }

    public static Fad findFad(int fadId) {
        for (Fad fad : Storage.getFade()) {
            if (fad.getFadId() == fadId) {
                return fad;
            }
        }
        return null;
    }

    public static String findFadPlacering(int fadId) {
        Fad fad = findFad(fadId);
        if (fad == null) {
            return "Fad nr. " + fadId + " findes ikke";
        }
        return fad.getPlacering();
    }

    public static void startDestillering() {
        // TODO
    }

    public static void slutDestillering() {
        // TODO
    }
}