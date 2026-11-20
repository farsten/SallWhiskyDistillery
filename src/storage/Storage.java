package storage;

import model.Fad;
import model.Lager;

import java.util.ArrayList;

public class Storage {
    private static final ArrayList<Lager> lagre = new ArrayList<>();
    private static final ArrayList<Fad> fade = new ArrayList<>();

    public static ArrayList<Lager> getLagre() {
        return new ArrayList<>(lagre);
    }

    public static void addLager(Lager lager) {
        lagre.add(lager);
    }

    public static ArrayList<Fad> getFade() {
        return new ArrayList<>(fade);
    }

    public static void addFad(Fad fad) {
        fade.add(fad);
    }
}