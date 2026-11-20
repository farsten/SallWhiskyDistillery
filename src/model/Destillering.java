package model;

import java.time.LocalDate;

public class Destillering {
    private final int destilleringsId;
    private final LocalDate startDato;
    private final LocalDate slutDato;
    private double alkoholProcent;
    private int newMakeNr;

    private final MaltBatch maltBatch;
    private Medarbejder medarbejder;

    public Destillering(int destilleringsId, LocalDate startDato, LocalDate slutDato,
                        double alkoholProcent, int newMakeNr, MaltBatch maltBatch, Medarbejder medarbejder) {
        this.destilleringsId = destilleringsId;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.alkoholProcent = alkoholProcent;
        this.newMakeNr = newMakeNr;
        this.maltBatch = maltBatch;
        this.medarbejder = medarbejder;
    }

    public int getDestilleringsId() {
        return destilleringsId;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public int getNewMakeNr() {
        return newMakeNr;
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
}
