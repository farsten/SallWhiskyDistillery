package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Fad {
    private int fadId;
    private int størrelse;        // fadets maksimale indhold i liter
    private String leverandør;
    private String tidligereIndhold;
    private double slutMængde;    // måles når fadet tømmes, bruges til at beregne fordampning
    private String type;

    private ArrayList<Påfyldning> påfyldninger;
    private Lagerplacering lagerplacering;

    public Fad(int fadId, int størrelse, String leverandør, String tidligereIndhold, String type) {
        this.fadId = fadId;
        this.størrelse = størrelse;
        this.leverandør = leverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.type = type;
        this.påfyldninger = new ArrayList<>();
    }

    public int getFadId() {
        return fadId;
    }

    public int getStørrelse() {
        return størrelse;
    }

    public String getLeverandør() {
        return leverandør;
    }

    public String getTidligereIndhold() {
        return tidligereIndhold;
    }

    public double getSlutMængde() {
        return slutMængde;
    }

    public void setSlutMængde(double slutMængde) {
        this.slutMængde = slutMængde;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Påfyldning> getPåfyldninger() {
        return påfyldninger;
    }

    public void addPåfyldning(Påfyldning påfyldning) {
        påfyldninger.add(påfyldning);
    }

    // Det nuværende indhold er summen af alle påfyldninger
    public double getIndholdLiter() {
        double indhold = 0;
        for (Påfyldning påfyldning : påfyldninger) {
            indhold += påfyldning.getLiter();
        }
        return indhold;
    }

    // Et fad er klar til tapning, når den seneste påfyldning har lagret mindst 3 år på den givne dato
    public boolean erKlarTilTapning(LocalDate dato) {
        if (påfyldninger.isEmpty()) {
            return false;
        }

        LocalDate senestePåfyldning = påfyldninger.get(0).getDato();
        for (Påfyldning påfyldning : påfyldninger) {
            if (påfyldning.getDato().isAfter(senestePåfyldning)) {
                senestePåfyldning = påfyldning.getDato();
            }
        }
        return !senestePåfyldning.plusYears(3).isAfter(dato);
    }

    public Lagerplacering getLagerplacering() {
        return lagerplacering;
    }

    public Lager getLager() {
        if (lagerplacering == null) {
            return null;
        }
        return lagerplacering.getLager();
    }

    public void setLagerplacering(Lagerplacering nyPlacering) {
        if (nyPlacering != null && !nyPlacering.erLedig()) {
            throw new IllegalArgumentException("Pladsen er allerede optaget");
        }
        if (lagerplacering != null) {
            lagerplacering.setFad(null);
        }
        lagerplacering = nyPlacering;
        if (nyPlacering != null) {
            nyPlacering.setFad(this);
        }
    }

    public String getPlacering() {
        if (lagerplacering == null) {
            return "Fadet er ikke placeret";
        }
        return lagerplacering.toString();
    }

    @Override
    public String toString() {
        return "Fad{" +
                "fadId=" + fadId +
                ", størrelse=" + størrelse +
                ", leverandør='" + leverandør + '\'' +
                ", tidligereIndhold='" + tidligereIndhold + '\'' +
                ", slutMængde=" + slutMængde +
                ", type='" + type + '\'' +
                ", påfyldninger=" + påfyldninger +
                ", lagerplacering=" + lagerplacering +
                '}';
    }
}