package com.luca.exercises.exercise012.coworking.aireview;

public interface MetodoAccesso {

    String generaIstruzioni();

    default String preparaMessaggio(Prenotazione prenotazione) {
        return "Accesso per " + prenotazione.getCodice() + ": " + this.generaIstruzioni();
    }
}
