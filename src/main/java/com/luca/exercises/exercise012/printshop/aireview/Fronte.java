package com.luca.exercises.exercise012.printshop.aireview;

public class Fronte implements ModalitaStampa {
    @Override
    public int calcolaFogliPerCopia(int pagine) {
        return pagine;
    }

    @Override
    public int getPrezzoPerFoglio() {
        return 10;
    }
}
