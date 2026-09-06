package com.luca.exercises.exercise012.coworking.aireview;

public class PrenotazioneSala extends Prenotazione {

    private double costoPreparazione;

    public PrenotazioneSala(
            String codice,
            String nomeCliente,
            int numeroOre,
            double tariffaOraria,
            double costoPreparazione) {
        super(codice, nomeCliente, numeroOre, tariffaOraria);
        this.costoPreparazione = costoPreparazione;
    }

    @Override
    public double calcolaTotale() {
        return this.calcolaCostoOrario() + this.costoPreparazione;
    }

    @Override
    public String generaDescrizione() {
        return this.generaDescrizioneBase()
                + " - Sala riunioni"
                + " - Preparazione: " + this.costoPreparazione;
    }
}
