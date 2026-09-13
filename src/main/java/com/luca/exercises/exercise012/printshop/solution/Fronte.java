package com.luca.exercises.exercise012.printshop.solution;

public class Fronte implements ModalitaStampa {

    public int determinaPrezzoFoglio() {
        return 10;
    }

    public int determinaConsumoFogli(int nPagine) {
        return (int) nPagine;
    };

    public int determinaCostoStampa(int nPagine) {
        return this.determinaConsumoFogli(nPagine) * this.determinaPrezzoFoglio();
    };
}
