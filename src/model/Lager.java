package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Lager {
    private final int lagerId;
    private final String adresse;
    private final int kapacitet;

    // reolnummer -> (hyldenummer -> lagerplacering) håber det gør det mere overskueligt
    private final Map<Integer, Map<Integer, Lagerplacering>> reoler;

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

    public Lagerplacering opretLagerplacering(int reol, int hylde) {
        if (findLagerplacering(reol, hylde) != null) {
            throw new IllegalArgumentException("Reol " + reol + ", hylde " + hylde + " findes allerede");
        }
        if (getAntalPladser() >= kapacitet) {
            throw new IllegalStateException("Lageret er fuldt (kapacitet " + kapacitet + ")");
        }

        Lagerplacering placering = new Lagerplacering(reol, hylde, this);

        if (!reoler.containsKey(reol)) {
            reoler.put(reol, new TreeMap<>());
        }
        reoler.get(reol).put(hylde, placering);
        return placering;
    }

    public Lagerplacering findLagerplacering(int reol, int hylde) {
        Map<Integer, Lagerplacering> hylder = reoler.get(reol);
        if (hylder == null) {
            return null;
        }
        return hylder.get(hylde);
    }

    public int getAntalPladser() {
        int antal = 0;
        for (Map<Integer, Lagerplacering> hylder : reoler.values()) {
            antal += hylder.size();
        }
        return antal;
    }

    public ArrayList<Lagerplacering> getLedigePladser() {
        ArrayList<Lagerplacering> ledige = new ArrayList<>();
        for (Map<Integer, Lagerplacering> hylder : reoler.values()) {
            for (Lagerplacering placering : hylder.values()) {
                if (placering.erLedig()) {
                    ledige.add(placering);
                }
            }
        }
        return ledige;
    }
}