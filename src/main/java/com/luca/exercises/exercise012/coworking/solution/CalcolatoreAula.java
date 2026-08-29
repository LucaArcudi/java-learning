package com.luca.exercises.exercise012.coworking.solution;

public class CalcolatoreAula implements Calcolatore {

    public double calcolaTotale(int ore, double tariffa, double costoFisso, double suppl) {
        return ore * tariffa + costoFisso;
    }
}
