package model;

public class Medarbejder {
    private int medarbejderId;
    private String navn;

    public Medarbejder(int medarbejderId, String navn) {
        this.medarbejderId = medarbejderId;
        this.navn = navn;
    }

    public int getMedarbejderId() {
        return medarbejderId;
    }

    public String getNavn() {
        return navn;
    }

    @Override
    public String toString() {
        return "Medarbejder{" +
                "medarbejderId=" + medarbejderId +
                ", navn='" + navn + '\'' +
                '}';
    }
}
