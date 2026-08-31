# Review ragionata — Esercizio 012

## Scopo della review

Questa review considera entrambe le tappe dell'implementazione:

- `c2a52c9` — prima versione funzionante;
- `890b671` — versione completata per coprire i requisiti tecnici.

Non richiede un ulteriore refactoring. Lo scopo è capire perché alcune parti della seconda versione risultano forzate e come si sarebbe potuto costruire un modello più naturale usando soltanto gli argomenti studiati.

La versione finale compila con Java 25 e Maven. I calcoli producono i risultati corretti: `23.0` per la postazione con monitor e `22.0` per la sala. Funzionano inoltre conferma, contatore condiviso e generazione delle istruzioni di accesso.

## Cosa mostrava la prima versione

La prima versione aveva già individuato diverse relazioni corrette:

- `PrenotazioneIndividuale` e `PrenotazioneAula` erano specializzazioni di `Prenotazione`;
- `Calcolatore`, `GeneratoreDesc` e `GeneratoreIstru` erano contratti con implementazioni sostituibili;
- `Prenotazione` delegava la generazione delle istruzioni a un `GeneratoreIstru`;
- il generatore digitale controllava autonomamente la propria disponibilità e produceva il codice digitale;
- `GeneratoreIstruAu` implementava due interfacce.

Il limite principale era l'esposizione dello stato tramite campi `protected`. Il `main` leggeva direttamente ore, tariffa, operatore, nota e collaboratori. Inoltre mancavano constructor chaining e uso polimorfico del tipo astratto `Prenotazione`.

## Cosa ha migliorato il secondo commit

Il secondo commit ha introdotto correttamente:

- campi `private` nella gerarchia delle prenotazioni;
- accesso allo stato attraverso metodi;
- un metodo `static` per leggere il contatore;
- overload del costruttore con chiamata `this(...)`;
- variabili dichiarate come `Prenotazione` che contengono oggetti delle due sottoclassi;
- override con chiamata a `super.getNota()`;
- riutilizzo nelle sottoclassi del comportamento `protected` per le istruzioni.

Questi cambiamenti dimostrano i meccanismi Java richiesti. Il problema non è la loro correttezza sintattica, ma il punto del modello nel quale sono stati inseriti.

## Perché la classe astratta è diventata artificiale

Per dichiarare entrambe le variabili come `Prenotazione`, il secondo commit ha aggiunto alla classe astratta tutti i metodi necessari al `main`:

- `getHasMonitor()` e `getSuppl()`, che riguardano soltanto la postazione;
- `getCostoFisso()`, che riguarda soltanto la sala;
- un metodo di accesso specifico per la postazione e uno specifico per la sala.

Di conseguenza ogni sottoclasse è obbligata a implementare operazioni che non la riguardano. La sala restituisce `false`, `0` o una stringa vuota per gli elementi della postazione; la postazione fa lo stesso per gli elementi della sala.

Questo è il segnale importante: una superclasse non dovrebbe descrivere l'unione di tutto ciò che sanno fare le sottoclassi. Dovrebbe contenere soltanto stato e comportamenti sensati per ogni tipo appartenente alla gerarchia.

L'uso polimorfico non richiede di poter raggiungere ogni dettaglio delle sottoclassi attraverso il tipo astratto. Richiede invece un comportamento comune il cui risultato cambia in base all'oggetto reale.

In questo dominio i comportamenti naturalmente comuni sono, per esempio:

- calcolare il totale della prenotazione;
- produrre la descrizione della prenotazione.

Entrambe le operazioni hanno senso per qualsiasi `Prenotazione`, ma ciascuna sottoclasse può eseguirle usando il proprio stato. Chiamandole attraverso una variabile `Prenotazione`, Java sceglierebbe a runtime l'override dell'oggetto reale. Non servirebbero quindi metodi fittizi che restituiscono valori neutri.

## Dove dovrebbe stare il calcolo

Nella soluzione attuale il `main`:

1. chiede alla prenotazione ore, tariffa, supplemento o costo fisso;
2. recupera il suo `Calcolatore`;
3. decide quali valori passare e quali sostituire con zero;
4. invoca il calcolo.

Il risultato numerico è corretto, ma la responsabilità rimane nel chiamante. Inoltre il contratto `Calcolatore` riceve contemporaneamente `costoFisso` e `suppl`: ogni implementazione deve ignorare uno dei due parametri.

Per questo esercizio la soluzione più semplice sarebbe lasciare a ogni tipo di prenotazione il calcolo basato sul proprio stato:

- la postazione conosce ore, tariffa, presenza del monitor e supplemento;
- la sala conosce ore, tariffa e costo di preparazione.

Il calcolo diventerebbe così un comportamento polimorfico della prenotazione. Un `Calcolatore` separato avrebbe senso se rappresentasse una regola realmente sostituibile per la stessa prenotazione, non soltanto un modo per spostare fuori una formula che appartiene già al tipo concreto.

## Come trattare la descrizione

La soluzione stampa una descrizione per entrambi i tipi, quindi il comportamento osservabile è presente. Tuttavia il `main` passa al generatore una stringa già completa:

```java
generaDescrizione("descrizione prenotazione aula")
```

Il generatore non usa codice, cliente, ore o altri dati della prenotazione. Cambiando lo stato dell'oggetto, quella descrizione rimarrebbe invariata.

Anche qui la descrizione può essere un comportamento comune dichiarato dalla classe astratta e specializzato dalle sottoclassi. La superclasse può fornire tramite un metodo `protected` la parte basata sui dati comuni; ogni sottoclasse può richiamarla e aggiungere i propri dati. In questo modo `protected`, ereditarietà, `super` e override collaborano per una necessità del dominio, invece di essere dimostrati con un override artificiale di `getNota()`.

Un generatore separato sarebbe utile soltanto in presenza di formati davvero intercambiabili. Nell'esercizio esiste una sola forma di descrizione per ciascun tipo, quindi aggiunge indirezione senza risolvere un problema reale.

## Perché `getNota()` non è un buon comportamento polimorfico

L'override di `getNota()` è tecnicamente corretto, compresa la chiamata a `super.getNota()`. Dimostra il dispatch dinamico quando viene chiamato tramite una variabile `Prenotazione`.

Dal punto di vista del dominio, però, la nota non cambia significato tra una sala e una postazione. Aggiungere i prefissi `Nota aula` e `Nota individuale` serve soltanto a rendere visibile l'override. Calcolo e descrizione erano occasioni più naturali per dimostrare lo stesso meccanismo.

## Accesso reception e accesso digitale

In questo punto la prima versione era più coerente della seconda.

Nel primo commit `GeneratoreIstruAu.generaIstruzioni()` controllava `isAvailable()` e restituiva il codice digitale. Il `main` chiedeva istruzioni attraverso il contratto e non ricostruiva il comportamento.

Nel secondo commit il generatore digitale restituisce invece `Ritiro reception`; il `main` riconosce direttamente `GeneratoreIstruAu`, controlla la disponibilità e sostituisce il risultato con un codice scritto nel chiamante. Il contratto non rende quindi più intercambiabili le due implementazioni.

La direzione naturale è:

- un'interfaccia comune rappresenta la capacità di generare istruzioni;
- reception e digitale implementano quel contratto in modo diverso;
- il componente che richiede le istruzioni dipende soltanto dall'interfaccia e delega;
- una seconda interfaccia rappresenta esclusivamente la capacità di dichiarare la disponibilità;
- soltanto il meccanismo digitale implementa entrambe le interfacce.

Se serve osservare entrambe le capacità, la stessa istanza digitale può essere usata attraverso due variabili con tipi interfaccia differenti. Non occorre aggiungere `isAvailable()` al contratto della reception e non occorre dichiarare il chiamante con il tipo concreto.

La richiesta di rendere esplicita la disponibilità nell'output aveva spinto il codice nella direzione sbagliata. La prima implementazione dimostrava già la logica tramite la scelta tra codice digitale e fallback; cambiare soltanto la frase stampata non aggiungeva valore tecnico.

## Una struttura concettuale più naturale

Senza entrare in un'implementazione completa, le responsabilità potrebbero essere organizzate così:

```text
Prenotazione (astratta)
├── stato comune privato
├── conferma con le due varianti
├── contatore statico
├── parte comune della descrizione, protetta
├── calcolo del totale, specializzato
└── descrizione, specializzata
│
├── PrenotazioneIndividuale
│   └── monitor e supplemento
│
└── PrenotazioneAula
    └── costo di preparazione

Gestione accesso
└── contiene un collaboratore del tipo interfaccia
    ├── accesso tramite reception
    └── accesso digitale
        └── implementa anche il controllo di disponibilità
```

In questa struttura:

- l'ereditarietà rappresenta la relazione *is-a* tra i tipi di prenotazione;
- il polimorfismo astratto viene mostrato chiamando totale e descrizione;
- il metodo `protected` serve davvero alle sottoclassi;
- la composizione rappresenta la relazione *has-a* con il meccanismo di accesso;
- il polimorfismo tramite interfaccia permette di sostituire reception e digitale;
- il metodo `default` può fornire una parte comune delle istruzioni e delegare alla specifica implementazione;
- la seconda interfaccia aggiunge la disponibilità soltanto al digitale;
- il constructor chaining può fornire un valore predefinito per una variante di costruzione sensata.

## Altre osservazioni utili

- Rendere i campi `private` nel secondo commit è corretto. Non era però necessario esporre ogni dettaglio con un getter: quando il comportamento vive nell'oggetto, il chiamante non deve recuperare tutti i dati per eseguirlo al suo posto.
- `conferma(...)` è `protected`, ma il `main` riesce a chiamarlo perché si trova nello stesso package. Questo dimostra una particolarità importante di Java, ma un'operazione che rappresenta l'API normale della prenotazione sarebbe più chiaramente esposta come operazione pubblica.
- I metodi delle interfacce sono già pubblici implicitamente; scrivere `public` è valido ma ridondante.
- `@Override` sui metodi che implementano un'interfaccia non è indispensabile per l'esecuzione, ma rende esplicita l'intenzione e permette al compilatore di verificare la firma.
- Le abbreviazioni come `Au`, `Ind`, `Istru`, `Desc`, `suppl` e `preAu` rendono più difficile leggere le responsabilità. Nomi completi sarebbero più utili di commenti separatori o della copia della traccia nel `main`.

## Valutazione conclusiva

L'esercizio ha comunque svolto la sua funzione didattica. Nei due commit sono stati provati concretamente:

- incapsulamento;
- costruttori, overload e constructor chaining;
- classe astratta, ereditarietà, `super` e override;
- `protected` e accesso nello stesso package;
- membri `static`;
- interfacce, metodi `default` e implementazione multipla;
- composizione e delega;
- polimorfismo tramite classe astratta e interfaccia.

Il punto da portare avanti non è una particolare lista di classi, ma questo criterio: un requisito tecnico è ben inserito quando coincide con una responsabilità reale del dominio. Quando per soddisfarlo compaiono metodi che restituiscono `0`, `false` o stringhe vuote soltanto perché una sottoclasse non sa cosa farsene, l'astrazione scelta sta probabilmente chiedendo troppo ai propri tipi.

Non è richiesta un'ulteriore riscrittura di questa soluzione.
