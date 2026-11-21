package model;

import java.time.LocalDate;

public class Påfyldning {
    private int påfyldningId;
    private LocalDate dato;
    private double liter;
    private double alkoholprocent;

    private Destillering destillering;

    public Påfyldning(int påfyldningId, LocalDate dato, double liter,
                      double alkoholprocent, Destillering destillering) {
        this.påfyldningId = påfyldningId;
        this.dato = dato;
        this.liter = liter;
        this.alkoholprocent = alkoholprocent;
        this.destillering = destillering;
    }

    public int getPåfyldningId() {
        return påfyldningId;
    }

    public LocalDate getDato() {
        return dato;
    }

    public double getLiter() {
        return liter;
    }

    public double getAlkoholprocent() {
        return alkoholprocent;
    }

    public Destillering getDestillering() {
        return destillering;
    }
}
