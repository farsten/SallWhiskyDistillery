package model;

import java.util.ArrayList;

public class Fad {
    private int fadId;
    private int startMængde;
    private String leverandør;
    private String tidligereIndhold;
    private double slutMængde;
    private String type;

    private ArrayList<Påfyldning> påfyldninger;
    private Lagerplacering lagerplacering;

    public Fad(int fadId, int startMængde, String leverandør, String tidligereIndhold,
               double slutMængde, String type) {
        this.fadId = fadId;
        this.startMængde = startMængde;
        this.leverandør = leverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.slutMængde = slutMængde;
        this.type = type;
        this.påfyldninger = new ArrayList<>();
    }

    public int getFadId() {
        return fadId;
    }

    public int getStartMængde() {
        return startMængde;
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

    public String getType() {
        return type;
    }

    public ArrayList<Påfyldning> getPåfyldninger() {
        return påfyldninger;
    }

    public void addPåfyldning(Påfyldning påfyldning) {
        påfyldninger.add(påfyldning);
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
}