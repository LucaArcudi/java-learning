package com.luca.exercises.exercise012.coworking.aireview;

public class AccessoDigitale implements MetodoAccesso, VerificaDisponibilita {

    private String codiceTemporaneo;
    private boolean disponibile;

    public AccessoDigitale(String codiceTemporaneo, boolean disponibile) {
        this.codiceTemporaneo = codiceTemporaneo;
        this.disponibile = disponibile;
    }

    @Override
    public String generaIstruzioni() {
        if (this.isAvailable()) {
            return "usa il codice temporaneo " + this.codiceTemporaneo;
        }

        return "servizio digitale non disponibile";
    }

    @Override
    public boolean isAvailable() {
        return this.disponibile;
    }
}
