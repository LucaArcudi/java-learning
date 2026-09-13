package com.luca.exercises.exercise012.printshop.solution;

public class FronteRetro implements ModalitaStampa {

    public int determinaPrezzoFoglio() {
        return 16;
    }

    public int determinaConsumoFogli(int nPagine) {

        double fogli = 0;

        if (nPagine % 2 == 0) {
            fogli = nPagine / 2;
        }

        if (nPagine % 2 != 0) {
            fogli = (double) nPagine / 2 + 0.5;
        }

        return (int) fogli;
    };

    public int determinaCostoStampa(int nPagine) {
        return this.determinaConsumoFogli(nPagine) * this.determinaPrezzoFoglio();
    };

}
