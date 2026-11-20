package model;

import java.util.ArrayList;

public class Whisky {
    private int produktId;
    private String navn;
    private double alkoholprocent;

    private ArrayList<Blanding> blandingList;

    public Whisky(int produktId, String navn, double alkoholprocent) {
        this.produktId = produktId;
        this.navn = navn;
        this.alkoholprocent = alkoholprocent;
        this.blandingList = new ArrayList<>();
    }

    public int getProduktId() {
        return produktId;
    }

    public String getNavn() {
        return navn;
    }

    public double getAlkoholprocent() {
        return alkoholprocent;
    }

    public ArrayList<Blanding> getBlandingList() {
        return blandingList;
    }
}
