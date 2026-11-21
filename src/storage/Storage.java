package storage;

import model.*;

import java.lang.classfile.instruction.ArrayLoadInstruction;
import java.util.ArrayList;

public class Storage {
    private static final ArrayList<Lager> lagre = new ArrayList<>();
    private static final ArrayList<Fad> fade = new ArrayList<>();
    private static final ArrayList<Mark> marker = new ArrayList<>();
    private static final ArrayList<MaltBatch>  maltBatches = new ArrayList<>();
    private static final ArrayList<Destillering> destilleringer = new ArrayList<>();
    private static final ArrayList<Destillat>  destillater = new ArrayList<>();
    private static final ArrayList<Whisky>  whiskyer = new ArrayList<>();
    private static final ArrayList<Medarbejder>  medarbejdere = new ArrayList<>();
    private static final ArrayList<Blanding> blandinger = new ArrayList<>();

    //Lager
    public static ArrayList<Lager> getLagre() {
        return new ArrayList<>(lagre);
    }

    public static void addLager(Lager lager) {
        lagre.add(lager);
    }

    //Fad
    public static ArrayList<Fad> getFade() {
        return new ArrayList<>(fade);
    }

    public static void addFad(Fad fad) {
        fade.add(fad);
    }


    //Mark
    public static ArrayList<Mark> getMarker() {
        return new ArrayList<>(marker);
    }

    public static void addMark(Mark mark) {
        marker.add(mark);
    }

    //MaltBatch
    public static ArrayList<MaltBatch> getMaltBatch() {
        return new ArrayList<>(maltBatches);
    }
    
    public static void addMaltBatch(MaltBatch maltBatch) {
        maltBatches.add(maltBatch);
    }

    //Destillering
    public static ArrayList<Destillering> getDestilleringer() {
        return new ArrayList<>(destilleringer);
    }
    public static void addDestillering(Destillering destillering) {
        destilleringer.add(destillering);
    }
    
    //Destillat
    public static ArrayList<Destillat> getDestillater() {
        return new ArrayList<>(destillater);
    }
    public static void addDestillater(Destillat destillat) {
        destillater.add(destillat);
    }
    
    
    //Whisky
    public static ArrayList<Whisky> getWhiskyer() {
        return new ArrayList<>(whiskyer);
    }
    public static void addWhisky(Whisky whisky) {
        whiskyer.add(whisky);
    }
    
    //Medarbejdere
    public static ArrayList<Medarbejder> getMedarbejdere() {
        return new ArrayList<>(medarbejdere);
    }
    
    public static void addMedarbejdere(Medarbejder medarbejder) {
        medarbejdere.add(medarbejder);
    }


    //Blandinger
    public static ArrayList<Blanding> getBlandinger() {
        return new ArrayList<>(blandinger);
    }
    public static void addBlanding(Blanding blanding) {
        blandinger.add(blanding);
    }


}