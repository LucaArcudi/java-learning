package com.luca.exercises.exercise012.printshop.aireview;

public class Commessa {
    private String codice;
    private String cliente;
    private int pagine;
    private int copie;
    private ModalitaStampa modalita;
    private String stato = "Da avviare";
    private Stampante stampanteInUso;

    public Commessa(String codice, String cliente, int pagine, int copie, ModalitaStampa modalita) {
        this.codice = codice;
        this.cliente = cliente;
        this.pagine = pagine;
        this.copie = copie;
        this.modalita = modalita;
    }

    public String getCodice() {
        return this.codice;
    }

    public String getCliente() {
        return this.cliente;
    }

    public String getStato() {
        return this.stato;
    }

    public Stampante getStampanteInUso() {
        return this.stampanteInUso;
    }

    public int getFogliNecessari() {
        // Ogni copia comincia su un foglio nuovo.
        return this.modalita.calcolaFogliPerCopia(this.pagine) * this.copie;
    }

    public int getPrezzoTotale() {
        // Tariffe fisse e modalità bloccata dopo l'avvio: il totale resta invariato.
        return this.getFogliNecessari() * this.modalita.getPrezzoPerFoglio();
    }

    public boolean puoEssereAvviata() {
        return "Da avviare".equals(this.stato);
    }

    public boolean isInStampa() {
        return "In stampa".equals(this.stato);
    }

    public boolean cambiaModalita(ModalitaStampa modalita) {
        if (!this.puoEssereAvviata()) {
            return false;
        }

        this.modalita = modalita;
        return true;
    }

    public boolean annulla() {
        if (!this.puoEssereAvviata()) {
            return false;
        }

        this.stato = "Annullata";
        return true;
    }

    // Operazioni interne al package, coordinate da CentroStampa.
    boolean iniziaStampa(Stampante stampante) {
        if (!this.puoEssereAvviata()) {
            return false;
        }

        this.stampanteInUso = stampante;
        this.stato = "In stampa";
        return true;
    }

    boolean terminaStampa() {
        if (!this.isInStampa()) {
            return false;
        }

        this.stato = "Completata";
        this.stampanteInUso = null;
        return true;
    }
}
