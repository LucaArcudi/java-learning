package com.luca.exercises.exercise012.coworking.solution;

public interface GeneratoreIstru {
    public default String generaIstruzioni() {
        return "Ritiro reception";
    }
}
