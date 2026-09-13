package com.luca.exercises.exercise012.printshop.solution;

public class ScortaFogli {

    private static int disponibilita;

    public static boolean aggiungiFogli(int q) {
        if (q > 0) {
            disponibilita = disponibilita + q;
            return true;
        }
        return false;
    }

    public static boolean disponibilitaSuff(int q) {
        if (disponibilita - q >= 0) {
            return true;
        }
        return false;
    }

    public static boolean rimuoviFogli(int q) {
        if (disponibilitaSuff(q)) {
            disponibilita = disponibilita - q;
            return true;
        }
        return false;
    }

    public static int getDisponibilita() {
        return disponibilita;
    }
}
