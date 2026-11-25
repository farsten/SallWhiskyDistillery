package model;

import java.time.LocalDate;

public class MaltBatch {
    private int batchId;
    private String kornsort;
    private LocalDate høstDato;

    private Mark mark;

    public MaltBatch(int batchId, String kornsort, LocalDate høstDato, Mark mark) {
        this.batchId = batchId;
        this.kornsort = kornsort;
        this.høstDato = høstDato;
        this.mark = mark;
    }

    public int getBatchId() {
        return batchId;
    }

    public String getKornsort() {
        return kornsort;
    }

    public LocalDate getHøstDato() {
        return høstDato;
    }

    public Mark getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return  batchId +
                ", " + kornsort +
                ", " + høstDato + ", " + mark;
    }
}
