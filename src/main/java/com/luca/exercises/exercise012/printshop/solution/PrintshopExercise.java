package com.luca.exercises.exercise012.printshop.solution;

public class PrintshopExercise {
    public static void main(String[] args) {

        ModalitaStampa fronte = new Fronte();
        ModalitaStampa fronteRetro = new FronteRetro();

        Commessa commessa = new Commessa("0001", "Luca", 5, 3, fronte);

        System.out.println(commessa.consultaPrentivo());

        commessa.cambiaModalitaStampa(fronteRetro);

        System.out.println(commessa.consultaPrentivo());

        System.out.println("-------------------------------------------------------");

        Stampante stampanteA = new Stampante("000A", "Operativa");
        Stampante stampanteB = new Stampante("000B", "Fuori servizio");

        ScortaFogli.aggiungiFogli(500);

        if (commessa.avvia(stampanteA)) {
            System.out.println("Ok");
        } else {
            System.out.println("Ko");

        }

        int scorta = ScortaFogli.getDisponibilita();

        System.out.println(scorta);

    }
}
