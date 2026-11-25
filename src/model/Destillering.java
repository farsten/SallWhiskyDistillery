package model;

import java.time.LocalDate;

public class Destillering {
    private final int newMakeNr;
    private final LocalDate startDato;
    private final LocalDate slutDato;
    private double mængdeLiter;
    private double alkoholProcent;
    private String rygemateriale; //kan være null, hvis intet er røget
    private String kommentar;

    private final MaltBatch maltBatch;
    private final Medarbejder medarbejder;

    public Destillering(int newMakeNr, LocalDate startDato, LocalDate slutDato,
                        double mængdeLiter, double alkoholProcent, String rygemateriale,
                        String kommentar, MaltBatch maltBatch, Medarbejder medarbejder) {
        this.newMakeNr = newMakeNr;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.mængdeLiter = mængdeLiter;
        this.alkoholProcent = alkoholProcent;
        this.rygemateriale = rygemateriale;
        this.kommentar = kommentar;
        this.maltBatch = maltBatch;
        this.medarbejder = medarbejder;
    }

    public int getNewMakeNr() {
        return newMakeNr;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public double getMængdeLiter() {
        return mængdeLiter;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public String getRygemateriale() {
        return rygemateriale;
    }

    public String getKommentar() {
        return kommentar;
    }

    public MaltBatch getMaltBatch() {
        return maltBatch;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    public void setAlkoholProcent(double alkoholProcent) {
        this.alkoholProcent = alkoholProcent;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    @Override
    public String toString() {
        return "NM " + newMakeNr + " (" + startDato + " - " + slutDato + ")";
    }



}