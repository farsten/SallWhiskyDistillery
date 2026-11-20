package model;

public class Lager {
    private final int lagerId;
    private final String placering;
    private final int kapacitet;

    public Lager(int lagerId, String placering, int kapacitet) {
        this.lagerId = lagerId;
        this.placering = placering;
        this.kapacitet = kapacitet;
    }

    public int getLagerId() {
        return lagerId;
    }

    public String getPlacering() {
        return placering;
    }

    public int getKapacitet() {
        return kapacitet;
    }
}
