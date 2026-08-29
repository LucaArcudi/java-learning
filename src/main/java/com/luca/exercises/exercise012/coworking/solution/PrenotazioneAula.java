package com.luca.exercises.exercise012.coworking.solution;

public class PrenotazioneAula extends Prenotazione {

    protected double costoFisso;

    protected PrenotazioneAula(String codice, String nomeCliente, int numeroOre, double tariffaOraria,
            Calcolatore calcolatore, GeneratoreDesc generatoreDesc, double costoFisso) {
        super(codice, nomeCliente, numeroOre, tariffaOraria, calcolatore, generatoreDesc);

        this.costoFisso = costoFisso;
    }
}
