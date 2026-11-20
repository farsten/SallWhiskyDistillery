package model;

public class Destillat {
    private int destillatId;
    private int newMakeNr;

    private Destillering destillering;

    public Destillat(int destillatId, int newMakeNr, Destillering destillering) {
        this.destillatId = destillatId;
        this.newMakeNr = newMakeNr;
        this.destillering = destillering;
    }

    public int getDestillatId() {
        return destillatId;
    }

    public int getNewMakeNr() {
        return newMakeNr;
    }

    public Destillering getDestillering() {
        return destillering;
    }
}
