package com.luca.exercises.exercise012.coworking.aireview;

public abstract class Prenotazione {

    private String codice;
    private String nomeCliente;
    private int numeroOre;
    private double tariffaOraria;
    private String operatore;
    private String nota;

    private static int prenotazioniCreate = 0;

    protected Prenotazione(String codice, String nomeCliente, int numeroOre, double tariffaOraria) {
        this.codice = codice;
        this.nomeCliente = nomeCliente;
        this.numeroOre = numeroOre;
        this.tariffaOraria = tariffaOraria;
        prenotazioniCreate++;
    }

    public void conferma(String operatore) {
        this.operatore = operatore;
    }

    public void conferma(String operatore, String nota) {
        this.conferma(operatore);
        this.nota = nota;
    }

    protected double calcolaCostoOrario() {
        return this.numeroOre * this.tariffaOraria;
    }

    protected String generaDescrizioneBase() {
        String descrizione = this.codice
                + " - Cliente: " + this.nomeCliente
                + " - Ore: " + this.numeroOre
                + " - Tariffa oraria: " + this.tariffaOraria;

        if (this.operatore == null) {
            return descrizione + " - Non confermata";
        }

        descrizione = descrizione + " - Confermata da: " + this.operatore;

        if (this.nota != null) {
            descrizione = descrizione + " - Nota: " + this.nota;
        }

        return descrizione;
    }

    public String getCodice() {
        return this.codice;
    }

    public static int getPrenotazioniCreate() {
        return prenotazioniCreate;
    }

    public abstract double calcolaTotale();

    public abstract String generaDescrizione();
}
