package com.luca.exercises.exercise012.coworking.solution;

public class PrenotazioneIndividuale extends Prenotazione {

    protected double suppl = 5;
    protected boolean hasMonitor;

    protected PrenotazioneIndividuale(String codice, String nomeCliente, int numeroOre, double tariffaOraria,
            Calcolatore calcolatore, GeneratoreDesc generatoreDesc, boolean hasMonitor) {
        super(codice, nomeCliente, numeroOre, tariffaOraria, calcolatore, generatoreDesc);

        this.hasMonitor = hasMonitor;
    }
}
