# Esercizio 012 — Prenotazioni di un coworking

## Scenario

Un coworking vuole una piccola applicazione Java per gestire le prenotazioni dei propri spazi.

Al momento offre due tipi di prenotazione:

- una postazione individuale, alla quale può essere aggiunto un monitor con un supplemento fisso;
- una sala riunioni, che prevede un costo fisso di preparazione oltre al costo orario.

Ogni prenotazione ha almeno un codice, il nome del cliente, un numero di ore e una tariffa oraria. Le informazioni comuni devono essere gestite in un solo punto, mentre ciascun tipo di spazio deve calcolare il proprio totale e produrre una descrizione adeguata.

Una prenotazione può essere confermata indicando l'operatore che l'ha gestita. Deve essere possibile aggiungere anche una nota usando una seconda forma della stessa operazione. Prima della conferma, operatore e nota sono assenti.

Dopo la conferma il cliente riceve le istruzioni di accesso. Il coworking può fornirle in due modi diversi:

- ritiro presso la reception;
- attivazione di un codice digitale temporaneo.

Il resto dell'applicazione non deve dipendere da uno di questi due meccanismi concreti. Il meccanismo digitale deve inoltre poter dichiarare se il servizio è disponibile, senza imporre questa capacità anche alla reception.

Non servono database, input da terminale o gestione di elenchi di prenotazioni.

## Consegna

Progetta tu classi, interfacce, nomi dei file e firme dei metodi. Il testo descrive responsabilità e comportamento del programma, non una struttura da ricopiare.

Scrivi la tua implementazione sotto:

```text
src/main/java/com/luca/exercises/exercise012/coworking/solution/
```

usando il package coerente con il percorso Maven.

Deve esserci un punto di ingresso con `main` che costruisca almeno:

- una prenotazione di una postazione con monitor;
- una prenotazione di una sala riunioni;
- i due diversi meccanismi di accesso.

Nel `main` mostra in modo leggibile:

- descrizione e costo finale di entrambe le prenotazioni;
- i due modi di confermare una prenotazione;
- la generazione delle istruzioni attraverso entrambi i meccanismi di accesso;
- lo stato di disponibilità del meccanismo digitale;
- il numero complessivo di prenotazioni create.

Non è richiesto un testo di output identico a un modello: contano la correttezza dei valori e il fatto che il comportamento sia verificabile eseguendo il programma.

## Regole di calcolo

- Postazione individuale: `ore * tariffa oraria`, più il supplemento fisso se è richiesto il monitor.
- Sala riunioni: `ore * tariffa oraria`, più il costo fisso di preparazione.

Scegli tu valori e dati dimostrativi, purché dall'output sia possibile controllare facilmente i calcoli.

## Requisiti tecnici

La soluzione deve dimostrare in modo sensato:

- stato incapsulato e inizializzato tramite costruttori;
- almeno un overload di costruttore con constructor chaining e un overload di metodo;
- stato e comportamento comuni condivisi tramite una classe astratta;
- specializzazione tramite ereditarietà e override;
- un comportamento comune `protected` riutilizzato dalle sottoclassi;
- un contatore `static` condiviso;
- un contratto con almeno due implementazioni concrete;
- un metodo `default` nell'interfaccia, usato realmente dal programma;
- una classe che implementa due interfacce;
- composizione verso il contratto, senza dipendere da una delle implementazioni concrete;
- polimorfismo sia attraverso il tipo astratto sia attraverso il tipo interfaccia.

Non è obbligatorio creare una classe per ogni punto dell'elenco: cerca una struttura piccola nella quale ogni classe abbia una responsabilità riconoscibile.

## Cassetta degli attrezzi — argomenti già studiati nel blocco

Puoi usare tutti i seguenti concetti.

### Classi e stato

- classi, istanze, campi e metodi d'istanza;
- costruttori, overloading dei costruttori e `this(...)`;
- `this`, compreso lo shadowing tra campo e parametro;
- metodi `void` e metodi con valore di ritorno;
- overloading dei metodi;
- membri `static` e differenza rispetto ai membri d'istanza;
- `public`, `private`, package-private e `protected`;
- incapsulamento e controllo di `null` per uno stato opzionale.

### Relazioni tra oggetti

- composizione e delega per una relazione *has-a*;
- ereditarietà con `extends` per una relazione *is-a*;
- `super(...)` e `super.metodo()`;
- classi e metodi `abstract`;
- override con `@Override`;
- metodi e stato comuni in una classe astratta.

### Interfacce e polimorfismo

- interfacce e `implements`;
- implementazione di più interfacce nella stessa classe;
- variabili dichiarate con un tipo astratto o un tipo interfaccia;
- differenza tra tipo dichiarato e tipo reale;
- dispatch dinamico degli override;
- metodi `default` e chiamata da essi a un metodo implementato dalla classe concreta;
- conflitti tra metodi `default` e risoluzione con `NomeInterfaccia.super.metodo()`;
- dipendenza da un'interfaccia per rendere sostituibile un collaboratore.

Restano naturalmente disponibili anche i concetti del blocco precedente: primitive e reference type, `String`, `null`, `var`, conversioni e pass-by-value.

## Vincoli

Non usare argomenti non ancora affrontati, in particolare:

- collection o array per conservare le prenotazioni;
- enum, record o sealed class;
- generics;
- eccezioni personalizzate;
- lambda o Stream API;
- override di `equals()`, `hashCode()` o `toString()`.

Non creare ancora la cartella `aireview`: la soluzione AI verrà aggiunta soltanto dopo che la tua implementazione e le risposte finali saranno state approvate.

## Criteri di superamento

L'esercizio è superato quando:

1. `mvn compile` termina con `BUILD SUCCESS`;
2. il programma dimostra tutti i comportamenti richiesti con valori corretti;
3. le scelte tra classe astratta, interfacce, ereditarietà e composizione rappresentano relazioni coerenti;
4. i campi sono incapsulati e il comportamento `protected` è usato dalle sottoclassi;
5. le due implementazioni del meccanismo di accesso sono intercambiabili senza modificare la classe che le usa;
6. il codice non introduce concetti appartenenti ai blocchi successivi;
7. le risposte alle domande finali dimostrano la comprensione delle scelte effettuate.

## Domande finali

Le domande non vengono definite nella traccia. Saranno formulate dopo che la tua implementazione avrà superato la code review, in modo che facciano riferimento alle scelte presenti davvero nel tuo codice. Eventuali scenari alternativi verranno indicati esplicitamente come ipotetici.

## Miglioramenti facoltativi — non valutati

Soltanto dopo aver completato i requisiti obbligatori, puoi eventualmente:

- aggiungere un terzo tipo di spazio;
- aggiungere un terzo meccanismo di accesso;
- gestire una prenotazione ancora non confermata con un messaggio specifico.

Questi miglioramenti non influiscono sulla valutazione dell'esercizio.
