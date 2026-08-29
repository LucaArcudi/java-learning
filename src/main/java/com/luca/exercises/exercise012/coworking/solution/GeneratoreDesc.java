package com.luca.exercises.exercise012.coworking.solution;

public interface GeneratoreDesc {

    public default String generaDescrizione(String desc) {
        return desc;
    }
}
