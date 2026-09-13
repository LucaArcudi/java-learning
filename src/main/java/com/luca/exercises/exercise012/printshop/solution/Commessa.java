package com.luca.exercises.exercise012.printshop.solution;

public class Commessa {
    private String stato = "Da avviare";
    private String codice;
    private String nomeCliente;
    private int nPagineDocumento;
    private int nCopieDocumento;
    private ModalitaStampa modalitaStampa;
    private int prezzoTotaleCentesimi;
    private Stampante stampanteInUso;

    public Commessa(String codice, String nomeCliente, int nPagineDocumento, int nCopieDocumento,
            ModalitaStampa modalitaStampa) {

        this.codice = codice;
        this.nomeCliente = nomeCliente;
        this.nPagineDocumento = nPagineDocumento;
        this.nCopieDocumento = nCopieDocumento;
        this.modalitaStampa = modalitaStampa;

    }

    public String consultaPrentivo() {
        return "Prezzo totale: "
                + this.modalitaStampa.determinaCostoStampa(this.nPagineDocumento) * this.nCopieDocumento + "\n"
                + "Fogli necessari: "
                + this.modalitaStampa.determinaConsumoFogli(this.nPagineDocumento) * this.nCopieDocumento;
    }

    public int getFogliNecessari() {
        return this.modalitaStampa.determinaConsumoFogli(this.nPagineDocumento) * this.nCopieDocumento;
    }

    public int getPrezzoTotale() {
        return this.modalitaStampa.determinaCostoStampa(this.nPagineDocumento) * this.nCopieDocumento;
    }

    public String getCodice() {
        return this.codice;
    }

    public void cambiaModalitaStampa(ModalitaStampa modalitaStampa) {
        if (this.stato.equals("Da avviare")) {
            this.modalitaStampa = modalitaStampa;
        }
    }

    public boolean canAvvia() {
        if (this.stato.equals("Da avviare")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean avvia(Stampante stampante) {
        if (this.canAvvia() && stampante.canStampa() && ScortaFogli.disponibilitaSuff(this.getFogliNecessari())) {
            this.stato = "In stampa";
            this.prezzoTotaleCentesimi = this.getPrezzoTotale();
            ScortaFogli.rimuoviFogli(this.getFogliNecessari());
            stampante.stampa(this);
            this.stampanteInUso = stampante;
            return true;
        }
        return false;
    }

    public boolean completa() {
        if (this.stato.equals("In stampa")) {
            this.stato = "Completata";
            this.stampanteInUso.liberaStampante();
            this.stampanteInUso = null;
            return true;
        }
        return false;
    }

    public boolean annulla() {
        if (this.stato.equals("Da avviare")) {
            this.stato = "Annullata";
            return true;
        } else {
            return false;
        }
    }

}
