package com.luca.course;

public class Dimensioni {
    private int larghezza;
    private int lunghezza;

    public Dimensioni(int larghezza, int lunghezza) {
        this.larghezza = larghezza;
        this.lunghezza = lunghezza;
    }

    public int getLarghezza() {
        return this.larghezza;
    }

    public int getLunghezza() {
        return this.lunghezza;
    }

    public void reSize(int larghezza, int lunghezza) {
        this.larghezza = larghezza;
        this.lunghezza = lunghezza;
    }
}
