package model;

public class Whisky {
    private int produktId;
    private String navn;
    private double alkoholprocent;
    private double vandLiter; //"hvor meget vand den er fortyndet med, hvor vandet kommer fra"
    private String vandKilde;   // tom hvis der ikke er tilsat vand
    private String beskrivelse;

    private Blanding blanding;

    public Whisky(int produktId, String navn, double alkoholprocent, double vandLiter,
                  String vandKilde, String beskrivelse, Blanding blanding) {
        this.produktId = produktId;
        this.navn = navn;
        this.alkoholprocent = alkoholprocent;
        this.vandLiter = vandLiter;
        this.vandKilde = vandKilde;
        this.beskrivelse = beskrivelse;
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

    public double getVandLiter() {
        return vandLiter;
    }

    public String getVandKilde() {
        return vandKilde;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public Blanding getBlanding() {
        return blanding;
    }

    @Override
    public String toString() {
        return "Whisky{" +
                "produktId=" + produktId +
                ", navn='" + navn + '\'' +
                ", alkoholprocent=" + alkoholprocent +
                ", vandLiter=" + vandLiter +
                ", vandKilde='" + vandKilde + '\'' +
                ", beskrivelse='" + beskrivelse + '\'' +
                ", blanding=" + blanding +
                '}';
    }
}