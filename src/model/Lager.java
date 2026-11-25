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

    // K16: Opret en reol med et antal hylder, hvor hver hylde har et antal pladser.
// Alle pladser oprettes som ledige.
    public void opretReol(int reol, int antalHylder, int pladserPrHylde) {
        if (reoler.containsKey(reol)) {
            throw new IllegalArgumentException("Reol " + reol + " findes allerede");
        }
        if (antalHylder <= 0 || pladserPrHylde <= 0) {
            throw new IllegalArgumentException("Antal hylder og pladser skal være større end 0");
        }

        int nyePladser = antalHylder * pladserPrHylde;
        if (getAntalPladser() + nyePladser > kapacitet) {
            int ledigKapacitet = kapacitet - getAntalPladser();
            throw new IllegalStateException("Lageret har kun plads til " + ledigKapacitet + " pladser mere");
        }

        Map<Integer, Map<Integer, Lagerplacering>> hylder = new TreeMap<>();
        for (int hylde = 1; hylde <= antalHylder; hylde++) {
            Map<Integer, Lagerplacering> pladser = new TreeMap<>();
            for (int plads = 1; plads <= pladserPrHylde; plads++) {
                pladser.put(plads, new Lagerplacering(reol, hylde, plads, this));
            }
            hylder.put(hylde, pladser);
        }
        reoler.put(reol, hylder);
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