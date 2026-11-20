package model;

public class Lagerplacering {
    private final int reol;
    private final int hylde;
    private final Lager lager;
    private Fad fad;

    public Lagerplacering(int reol, int hylde, Lager lager) {
        this.reol = reol;
        this.hylde = hylde;
        this.lager = lager;
    }

    public int getReol() {
        return reol;
    }

    public int getHylde() {
        return hylde;
    }

    public Lager getLager() {
        return lager;
    }

    public Fad getFad() {
        return fad;
    }

    public boolean erLedig() {
        return fad == null;
    }

    void setFad(Fad fad) {
        this.fad = fad;
    }

    @Override
    public String toString() {
        return "Lager " + lager.getLagerId() + " (" + lager.getAdresse() + "), reol " + reol + ", hylde " + hylde;
    }
}