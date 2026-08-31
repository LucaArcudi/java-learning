package com.luca.exercises.exercise012.coworking.solution;

public class PrenotazioneIndividuale extends Prenotazione {

    private double suppl = 5;
    private boolean hasMonitor;

    protected PrenotazioneIndividuale(String codice, String nomeCliente, int numeroOre, double tariffaOraria,
            Calcolatore calcolatore, GeneratoreDesc generatoreDesc, boolean hasMonitor) {
        super(codice, nomeCliente, numeroOre, tariffaOraria, calcolatore, generatoreDesc);

        this.hasMonitor = hasMonitor;
    }

    protected PrenotazioneIndividuale(String codice, String nomeCliente, int numeroOre, double tariffaOraria,
            Calcolatore calcolatore, GeneratoreDesc generatoreDesc) {
        this(codice, nomeCliente, numeroOre, tariffaOraria, calcolatore, generatoreDesc, true);
    }

    public double getSuppl() {
        return this.suppl;
    }

    public boolean getHasMonitor() {
        return this.hasMonitor;
    }

    public String istruzioniAccessoInd(GeneratoreIstru gIst) {
        return this.istruzioniAccesso(gIst);
    }

    public String istruzioniAccessoAu(GeneratoreIstru gIst, String message) {
        return "";
    }

    public String istruzioniAccessoInd(GeneratoreIstru gIst, String message) {
        return message + " " + this.istruzioniAccesso(gIst);
    }

    @Override
    public String getNota() {
        return "Nota individuale" + " " + super.getNota();
    }

    public double getCostoFisso() {
        return 0.0;
    }
}
