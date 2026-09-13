package com.luca.exercises.exercise012.printshop.aireview;

public class Stampante {
    private String codice;
    private boolean operativa;
    private Commessa commessaInEsecuzione;

    public Stampante(String codice, boolean operativa) {
        this.codice = codice;
        this.operativa = operativa;
    }

    public String getCodice() {
        return this.codice;
    }

    public boolean isOperativa() {
        return this.operativa;
    }

    public boolean isLibera() {
        return this.commessaInEsecuzione == null;
    }

    public boolean isDisponibile() {
        return this.isOperativa() && this.isLibera();
    }

    public Commessa getCommessaInEsecuzione() {
        return this.commessaInEsecuzione;
    }

    boolean occupa(Commessa commessa) {
        if (!this.isDisponibile()) {
            return false;
        }

        this.commessaInEsecuzione = commessa;
        return true;
    }

    boolean libera(Commessa commessa) {
        // Qui conta l'identità: deve essere proprio la commessa attualmente in stampa.
        if (commessa == null || this.commessaInEsecuzione != commessa) {
            return false;
        }

        this.commessaInEsecuzione = null;
        return true;
    }
}
