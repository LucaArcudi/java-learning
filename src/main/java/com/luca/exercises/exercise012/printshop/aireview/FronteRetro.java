package com.luca.exercises.exercise012.printshop.aireview;

public class FronteRetro implements ModalitaStampa {
    @Override
    public int calcolaFogliPerCopia(int pagine) {
        // La divisione intera conta le coppie; il resto aggiunge l'eventuale pagina dispari.
        return pagine / 2 + pagine % 2;
    }

    @Override
    public int getPrezzoPerFoglio() {
        return 16;
    }
}
