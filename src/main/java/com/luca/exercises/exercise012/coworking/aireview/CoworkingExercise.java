package com.luca.exercises.exercise012.coworking.aireview;

public class CoworkingExercise {

    public static void main(String[] args) {
        Prenotazione postazione = new PrenotazionePostazione(
                "COD/P/1",
                "Paolo",
                4,
                4.5,
                5.0);

        Prenotazione sala = new PrenotazioneSala(
                "COD/S/1",
                "Marco",
                2,
                8.5,
                5.0);

        postazione.conferma("Operatore 1");
        sala.conferma("Operatore 2", "Preparare il proiettore");

        stampaPrenotazione(postazione);
        stampaPrenotazione(sala);

        MetodoAccesso reception = new AccessoReception();

        AccessoDigitale accessoDigitale = new AccessoDigitale("0321432490", true);
        MetodoAccesso digitale = accessoDigitale;
        VerificaDisponibilita disponibilitaDigitale = accessoDigitale;

        GestoreAccesso gestoreReception = new GestoreAccesso(reception);
        GestoreAccesso gestoreDigitale = new GestoreAccesso(digitale);

        System.out.println(gestoreReception.preparaIstruzioni(postazione));
        System.out.println(gestoreDigitale.preparaIstruzioni(sala));
        System.out.println("Servizio digitale disponibile: " + disponibilitaDigitale.isAvailable());
        System.out.println("Prenotazioni create: " + Prenotazione.getPrenotazioniCreate());
    }

    static void stampaPrenotazione(Prenotazione prenotazione) {
        System.out.println(prenotazione.generaDescrizione());
        System.out.println("Totale: " + prenotazione.calcolaTotale());
    }
}
