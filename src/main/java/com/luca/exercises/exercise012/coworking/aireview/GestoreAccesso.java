package com.luca.exercises.exercise012.coworking.aireview;

public class GestoreAccesso {

    private MetodoAccesso metodoAccesso;

    public GestoreAccesso(MetodoAccesso metodoAccesso) {
        this.metodoAccesso = metodoAccesso;
    }

    public String preparaIstruzioni(Prenotazione prenotazione) {
        return this.metodoAccesso.preparaMessaggio(prenotazione);
    }
}
