package com.luca.exercises.exercise012.printshop.solution;

public class Commessa {
    private String stato = "Da avviare";
    private String codice;
    private String nomeCliente;
    private int nPagineDocumento;
    private int nCopieDocumento;
    private ModalitaStampa modalitaStampa;
    private int prezzoTotaleCentesimi;
    private int fogliNecessari;
    private Stampante stampanteCompletamento;

    public Commessa(String codice, String nomeCliente, int nPagineDocumento, int nCopieDocumento,
            ModalitaStampa modalitaStampa) {

        this.codice = codice;
        this.nomeCliente = nomeCliente;
        this.nPagineDocumento = nPagineDocumento;
        this.nCopieDocumento = nCopieDocumento;
        this.modalitaStampa = modalitaStampa;

    }

    public String consultaPrentivo() {
        return " ";
    }

    public void cambiaModalitaStampa(ModalitaStampa modalitaStampa) {
        this.modalitaStampa = modalitaStampa;
    }

    public void avvia() {

    }

    public void riavvia() {

    }

    public void completa() {

    }

}
