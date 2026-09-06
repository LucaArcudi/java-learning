package com.luca.exercises.exercise012.coworking.aireview;

public class PrenotazionePostazione extends Prenotazione {

    private boolean monitorRichiesto;
    private double supplementoMonitor;

    public PrenotazionePostazione(
            String codice,
            String nomeCliente,
            int numeroOre,
            double tariffaOraria,
            boolean monitorRichiesto,
            double supplementoMonitor) {
        super(codice, nomeCliente, numeroOre, tariffaOraria);
        this.monitorRichiesto = monitorRichiesto;
        this.supplementoMonitor = supplementoMonitor;
    }

    public PrenotazionePostazione(
            String codice,
            String nomeCliente,
            int numeroOre,
            double tariffaOraria,
            double supplementoMonitor) {
        this(codice, nomeCliente, numeroOre, tariffaOraria, true, supplementoMonitor);
    }

    @Override
    public double calcolaTotale() {
        double totale = this.calcolaCostoOrario();

        if (this.monitorRichiesto) {
            totale = totale + this.supplementoMonitor;
        }

        return totale;
    }

    @Override
    public String generaDescrizione() {
        String monitor = "senza monitor";

        if (this.monitorRichiesto) {
            monitor = "con monitor";
        }

        return this.generaDescrizioneBase() + " - Postazione individuale " + monitor;
    }
}
