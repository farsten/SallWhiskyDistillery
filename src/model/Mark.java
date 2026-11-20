package model;

public class Mark {
    private int markId;
    private String navn;

    public Mark(int markId, String navn) {
        this.markId = markId;
        this.navn = navn;
    }

    public int getMarkId() {
        return markId;
    }

    public String getNavn() {
        return navn;
    }

}
