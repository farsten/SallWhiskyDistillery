package model;

import java.time.LocalDate;

public class Påfyldning {
    private int påfyldningId;
    private LocalDate dato;
    private double liter;
    private double alkoholprocent;

    private Destillat destillat;

    public Påfyldning(int påfyldningId, LocalDate dato, double liter,
                      double alkoholprocent, Destillering destillat) {
        this.påfyldningId = påfyldningId;
        this.dato = dato;
        this.liter = liter;
        this.alkoholprocent = alkoholprocent;
        this.destillat = destillat;
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

    public Destillat getDestillat() {
        return destillat;
    }
}
