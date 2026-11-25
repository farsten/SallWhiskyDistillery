package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Lager {
    private final int lagerId;
    private final String adresse;
    private final int kapacitet;

    // reolnummer -> (hyldenummer -> (pladsnummer -> lagerplacering))
    private final Map<Integer, Map<Integer, Map<Integer, Lagerplacering>>> reoler;

    public Lager(int lagerId, String adresse, int kapacitet) {
        this.lagerId = lagerId;
        this.adresse = adresse;
        this.kapacitet = kapacitet;
        this.reoler = new TreeMap<>();
    }

    public int getLagerId() {
        return lagerId;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public Lagerplacering opretLagerplacering(int reol, int hylde, int plads) {
        if (findLagerplacering(reol, hylde, plads) != null) {
            throw new IllegalArgumentException("Reol " + reol + ", hylde " + hylde + ", plads " + plads + " findes allerede");
        }
        if (getAntalPladser() >= kapacitet) {
            throw new IllegalStateException("Lageret er fuldt (kapacitet " + kapacitet + ")");
        }

        Lagerplacering placering = new Lagerplacering(reol, hylde, plads, this);

        // Opret reolen, hvis den ikke findes
        if (!reoler.containsKey(reol)) {
            reoler.put(reol, new TreeMap<>());
        }
        Map<Integer, Map<Integer, Lagerplacering>> hylder = reoler.get(reol);

        // Opret hylden på reolen, hvis den ikke findes
        if (!hylder.containsKey(hylde)) {
            hylder.put(hylde, new TreeMap<>());
        }
        Map<Integer, Lagerplacering> pladser = hylder.get(hylde);

        pladser.put(plads, placering);
        return placering;
    }

    public Lagerplacering findLagerplacering(int reol, int hylde, int plads) {
        Map<Integer, Map<Integer, Lagerplacering>> hylder = reoler.get(reol);
        if (hylder == null) {
            return null;
        }
        Map<Integer, Lagerplacering> pladser = hylder.get(hylde);
        if (pladser == null) {
            return null;
        }
        return pladser.get(plads);
    }

    public int getAntalPladser() {
        int antal = 0;
        for (Map<Integer, Map<Integer, Lagerplacering>> hylder : reoler.values()) {
            for (Map<Integer, Lagerplacering> pladser : hylder.values()) {
                antal += pladser.size();
            }
        }
        return antal;
    }

    public ArrayList<Lagerplacering> getLedigePladser() {
        ArrayList<Lagerplacering> ledige = new ArrayList<>();
        for (Map<Integer, Map<Integer, Lagerplacering>> hylder : reoler.values()) {
            for (Map<Integer, Lagerplacering> pladser : hylder.values()) {
                for (Lagerplacering placering : pladser.values()) {
                    if (placering.erLedig()) {
                        ledige.add(placering);
                    }
                }
            }
        }
        return ledige;
    }
}