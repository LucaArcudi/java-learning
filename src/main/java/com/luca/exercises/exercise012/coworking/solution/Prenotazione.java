package com.luca.exercises.exercise012.coworking.solution;

abstract public class Prenotazione {

    protected String codice;
    protected String nomeCliente;
    protected int numeroOre;
    protected double tariffaOraria;

    protected String operatore;
    protected String nota;
    protected boolean isConfirmed = false;

    protected Calcolatore calcolatore;
    protected GeneratoreDesc generatoreDesc;

    protected static int contatorePre = 0;

    protected Prenotazione(String codice, String nomeCliente, int numeroOre, double tariffaOraria,
            Calcolatore calcolatore, GeneratoreDesc generatoreDesc) {
        this.codice = codice;
        this.nomeCliente = nomeCliente;
        this.numeroOre = numeroOre;
        this.tariffaOraria = tariffaOraria;
        this.calcolatore = calcolatore;
        this.generatoreDesc = generatoreDesc;

        contatorePre++;
    }

    protected void setOperatore(String operatore) {
        this.operatore = operatore;
    }

    protected void setNota(String nota) {
        this.nota = nota;
    }

    protected void setIsConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    protected void conferma(String operatore) {
        this.setOperatore(operatore);
        this.setIsConfirmed(true);
    }

    protected void conferma(String operatore, String nota) {
        this.setOperatore(operatore);
        this.setIsConfirmed(true);
        this.setNota(nota);
    }

    protected String istruzioniAccesso(GeneratoreIstru gIs) {
        return gIs.generaIstruzioni();
    }
}
