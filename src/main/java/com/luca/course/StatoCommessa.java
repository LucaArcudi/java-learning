package com.luca.course;

public enum StatoCommessa {

    DA_AVVIARE("Da avviare"), IN_STAMPA("In stampa"), COMPLETATA("Completata"), ANNULLATA("Annullata");

    private final String descrizione;

    private StatoCommessa(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public boolean puoPassareA(StatoCommessa destinazione) {
        if (this == DA_AVVIARE && (destinazione == IN_STAMPA || destinazione == ANNULLATA)) {
            return true;
        } else if (this == IN_STAMPA && destinazione == COMPLETATA) {
            return true;
        }
        return false;
    }
}
