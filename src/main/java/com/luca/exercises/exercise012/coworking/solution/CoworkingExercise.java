package com.luca.exercises.exercise012.coworking.solution;

public class CoworkingExercise {
    public static void main(String[] args) {

        // ## Consegna

        // Progetta tu classi, interfacce, nomi dei file e firme dei metodi. Il testo
        // descrive responsabilità e comportamento del programma, non una struttura da
        // ricopiare.

        // Scrivi la tua implementazione sotto:

        // ```text
        // src/main/java/com/luca/exercises/exercise012/coworking/solution/
        // ```

        // usando il package coerente con il percorso Maven.

        // Deve esserci un punto di ingresso con `main` che costruisca almeno:

        // - una prenotazione di una postazione con monitor;
        // - una prenotazione di una sala riunioni;
        // - i due diversi meccanismi di accesso.

        // Nel `main` mostra in modo leggibile:

        // - descrizione e costo finale di entrambe le prenotazioni;
        // - i due modi di confermare una prenotazione;
        // - la generazione delle istruzioni attraverso entrambi i meccanismi di
        // accesso;
        // - lo stato di disponibilità del meccanismo digitale;
        // - il numero complessivo di prenotazioni create.

        // Non è richiesto un testo di output identico a un modello: contano la
        // correttezza dei valori e il fatto che il comportamento sia verificabile
        // eseguendo il programma.

        // ---------------------------------------------

        // ## Regole di calcolo

        // - Postazione individuale: `ore * tariffa oraria`, più il supplemento fisso se
        // è richiesto il monitor.
        // - Sala riunioni: `ore * tariffa oraria`, più il costo fisso di preparazione.

        // Scegli tu valori e dati dimostrativi, purché dall'output sia possibile
        // controllare facilmente i calcoli.

        // ---------------------------------------------

        // ## Requisiti tecnici

        // La soluzione deve dimostrare in modo sensato:

        // - stato incapsulato e inizializzato tramite costruttori;
        // - almeno un overload di costruttore con constructor chaining e un overload di
        // metodo;
        // - stato e comportamento comuni condivisi tramite una classe astratta;
        // - specializzazione tramite ereditarietà e override;
        // - un comportamento comune `protected` riutilizzato dalle sottoclassi;
        // - un contatore `static` condiviso;
        // - un contratto con almeno due implementazioni concrete;
        // - un metodo `default` nell'interfaccia, usato realmente dal programma;
        // - una classe che implementa due interfacce;
        // - composizione verso il contratto, senza dipendere da una delle
        // implementazioni concrete;
        // - polimorfismo sia attraverso il tipo astratto sia attraverso il tipo
        // interfaccia.

        // Non è obbligatorio creare una classe per ogni punto dell'elenco: cerca una
        // struttura piccola nella quale ogni classe abbia una responsabilità
        // riconoscibile.

        Calcolatore calcInd = new CalcolatoreIndividuale();
        GeneratoreDesc genInd = new GeneratoreInd();

        PrenotazioneIndividuale preInd = new PrenotazioneIndividuale(
                "COD/I/1",
                "Paolo",
                4,
                4.5,
                calcInd,
                genInd,
                false);

        double totaleInd = 0.0;

        if (preInd.hasMonitor) {
            totaleInd = preInd.calcolatore.calcolaTotale(preInd.numeroOre, preInd.tariffaOraria, 0,
                    preInd.suppl);
        } else {
            totaleInd = preInd.calcolatore.calcolaTotale(preInd.numeroOre, preInd.tariffaOraria, 0,
                    0);
        }

        String descInd = preInd.generatoreDesc.generaDescrizione("descrizione prenotazione individuale");

        preInd.conferma("Operatore 1");

        System.out.println(totaleInd);
        System.out.println(descInd);
        System.out.println(preInd.operatore);
        System.out.println(preInd.nota);
        System.out.println(preInd.isConfirmed);

        if (preInd.isConfirmed) {

            GeneratoreIstru gIstruInd = new GeneratoreIstruInd();

            String istruzioni = preInd.istruzioniAccesso(gIstruInd);

            System.out.println(istruzioni);

        }

        System.out.println("------------------------------------------------------------");

        Calcolatore calcAu = new CalcolatoreAula();
        GeneratoreDesc genAu = new GeneratoreAu();

        PrenotazioneAula preAu = new PrenotazioneAula(
                "COD/A/1",
                "Marco",
                2,
                8.5,
                calcAu,
                genAu,
                5);

        double totaleAu = preAu.calcolatore.calcolaTotale(preAu.numeroOre, preAu.tariffaOraria, preAu.costoFisso, 0);

        String descAu = preAu.generatoreDesc.generaDescrizione("descrizione prenotazione aula");

        preAu.conferma("Operatore 2", "Overload conferma");

        System.out.println(totaleAu);
        System.out.println(descAu);
        System.out.println(preAu.operatore);
        System.out.println(preAu.nota);
        System.out.println(preAu.isConfirmed);

        if (preAu.isConfirmed) {

            GeneratoreIstru gIstruAu = new GeneratoreIstruAu();

            String istruzioni = preAu.istruzioniAccesso(gIstruAu);

            System.out.println(istruzioni);

        }

        System.out.println("------------------------------------------------------------");

        System.out.println(Prenotazione.contatorePre);

    }
}
