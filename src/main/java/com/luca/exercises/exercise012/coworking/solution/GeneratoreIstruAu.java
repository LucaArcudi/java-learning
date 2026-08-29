package com.luca.exercises.exercise012.coworking.solution;

public class GeneratoreIstruAu implements GeneratoreIstru, ConfermaDigitale {
    @Override
    public String generaIstruzioni() {

        if (this.isAvailable()) {
            return "CDT: 0000q23e";
        }

        return "Ritiro reception";
    }
}
