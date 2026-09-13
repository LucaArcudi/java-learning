package com.luca.exercises.exercise012.printshop.aireview;

public class ScortaFogli {
    private int disponibili;

    public ScortaFogli(int disponibili) {
        this.disponibili = disponibili;
    }

    public int getDisponibili() {
        return this.disponibili;
    }

    public boolean aggiungi(int quantita) {
        if (quantita <= 0) {
            return false;
        }

        this.disponibili = this.disponibili + quantita;
        return true;
    }

    boolean preleva(int quantita) {
        if (quantita <= 0 || quantita > this.disponibili) {
            return false;
        }

        this.disponibili = this.disponibili - quantita;
        return true;
    }
}
