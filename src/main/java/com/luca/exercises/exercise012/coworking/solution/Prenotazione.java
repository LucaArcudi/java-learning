package com.luca.exercises.exercise012.coworking.solution;

abstract public class Prenotazione {

    private String codice;
    private String nomeCliente;
    private int numeroOre;
    private double tariffaOraria;

    private String operatore;
    private String nota;
    private boolean isConfirmed = false;

    private Calcolatore calcolatore;
    private GeneratoreDesc generatoreDesc;

    private static int contatorePre = 0;

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

    public String getCodice() {
        return this.codice;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public int getNumeroOre() {
        return this.numeroOre;
    }

    public double getTariffaOraria() {
        return this.tariffaOraria;
    }

    public String getOperatore() {
        return this.operatore;
    }

    public String getNota() {
        return this.nota;
    }

    public boolean getIsConfirmed() {
        return this.isConfirmed;
    }

    public Calcolatore getCalcolatore() {
        return this.calcolatore;
    }

    public GeneratoreDesc getGeneratoreDesc() {
        return this.generatoreDesc;
    }

    public static int getContatorePre() {
        return Prenotazione.contatorePre;
    }

    public abstract boolean getHasMonitor();

    public abstract double getSuppl();

    public abstract double getCostoFisso();

    public abstract String istruzioniAccessoInd(GeneratoreIstru gIst, String message);

    public abstract String istruzioniAccessoAu(GeneratoreIstru gIst, String message);

}
