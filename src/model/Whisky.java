package model;

import java.util.ArrayList;
import java.util.Base64;

public class Whisky {
    private int produktId;
    private String navn;
    private double alkoholprocent;

    private Blanding blanding;

    public Whisky(int produktId, String navn, double alkoholprocent,  Blanding blanding) {
        this.produktId = produktId;
        this.navn = navn;
        this.alkoholprocent = alkoholprocent;
        this.blanding = blanding;
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

    public Blanding getBlanding() {
        return blanding;
    }
}
