package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Blanding {
    private final int blandingId;
    private final LocalDate dato;
    private final String navn;
    private int liter;
    private double alkoholprocent;

    private ArrayList<Fad> fadList;

    public Blanding(int blandingId, LocalDate dato, String navn, int liter, double alkoholprocent) {
        this.blandingId = blandingId;
        this.dato = dato;
        this.navn = navn;
        this.liter = liter;
        this.alkoholprocent = alkoholprocent;
        this.fadList = new ArrayList<>();
    }

    public int getBlandingId() {
        return blandingId;
    }

    public LocalDate getDato() {
        return dato;
    }

    public String getNavn() {
        return navn;
    }

    public int getLiter() {
        return liter;
    }

    public double getAlkoholprocent() {
        return alkoholprocent;
    }

    public ArrayList<Fad> getFadList() {
        return fadList;
    }

    public void addFad(Fad fad) {
        fadList.add(fad);
    }

    @Override
    public String toString() {
        return "Blanding{" +
                "blandingId=" + blandingId +
                ", dato=" + dato +
                ", navn='" + navn + '\'' +
                ", liter=" + liter +
                ", alkoholprocent=" + alkoholprocent +
                ", fadList=" + fadList +
                '}';
    }
}
