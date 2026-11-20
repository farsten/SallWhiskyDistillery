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
    private Lager lager;

    public Fad(int fadId, int startMængde, String leverandør, String tidligereIndhold,
               double slutMængde, String type, Lager lager) {
        this.fadId = fadId;
        this.startMængde = startMængde;
        this.leverandør = leverandør;
        this.tidligereIndhold = tidligereIndhold;
        this.slutMængde = slutMængde;
        this.type = type;
        this.påfyldninger = new ArrayList<>();
        this.lager = lager;
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

    public Lager getLager() {
        return lager;
    }

}
