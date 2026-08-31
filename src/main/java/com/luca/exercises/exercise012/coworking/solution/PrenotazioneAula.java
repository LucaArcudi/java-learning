package com.luca.exercises.exercise012.coworking.solution;

public class PrenotazioneAula extends Prenotazione {

    private double costoFisso;

    protected PrenotazioneAula(String codice, String nomeCliente, int numeroOre, double tariffaOraria,
            Calcolatore calcolatore, GeneratoreDesc generatoreDesc, double costoFisso) {
        super(codice, nomeCliente, numeroOre, tariffaOraria, calcolatore, generatoreDesc);

        this.costoFisso = costoFisso;
    }

    public double getCostoFisso() {
        return this.costoFisso;
    }

    public String istruzioniAccessoAu(GeneratoreIstru gIst, String message) {
        return message + " " + this.istruzioniAccesso(gIst);
    }

    // -------------------------

    public String istruzioniAccessoAu(GeneratoreIstru gIst) {
        return this.istruzioniAccesso(gIst);
    }

    public String istruzioniAccessoInd(GeneratoreIstru gIst, String message) {
        return "";
    }

    // -----------------------
    @Override
    public String getNota() {
        return "Nota aula" + " " + super.getNota();
    }

    public boolean getHasMonitor() {
        return false;
    }

    public double getSuppl() {
        return 0.0;
    }
}
