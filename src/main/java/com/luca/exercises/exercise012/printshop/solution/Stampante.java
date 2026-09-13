package com.luca.exercises.exercise012.printshop.solution;

public class Stampante {
    private String codice;
    private String stato;
    private String disponibilita;
    private Commessa commessaInEecuzione;

    public Stampante(String codice, String stato, String disponibilita) {
        this.codice = codice;
        this.stato = stato;
        this.disponibilita = disponibilita;
    }

    public Stampante(String codice, String stato) {
        this(codice, stato, "Libera");
    }

    public String getCodice() {
        return this.codice;
    }

    public boolean canStampa() {
        if (this.disponibilita.equals("Libera") && this.stato.equals("Operativa")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean stampa(Commessa commessa) {

        if (this.canStampa()) {

            this.disponibilita = "Occupata";

            this.commessaInEecuzione = commessa;

            return true;
        }

        return false;
    }

    public void liberaStampante() {
        this.commessaInEecuzione = null;
        this.disponibilita = "Libera";
    }
}
