# Centro stampa — dalla richiesta alla stampa completata

Consolidamento OOD del punto 1.2 della roadmap: classi, interfacce, composizione, ereditarietà e polimorfismo.

## Scenario

Un piccolo centro stampa riceve commesse dai clienti. Ogni commessa richiede un certo numero di copie di un documento e può essere stampata solo fronte oppure fronte-retro.

Prima di avviare il lavoro, l'operatore consulta il preventivo e può cambiare modalità di stampa. Per avviarlo sceglie una stampante: servono una macchina disponibile e abbastanza carta nella scorta condivisa. Una stampa occupa la macchina fino a quando l'operatore ne dichiara il completamento.

Devi modellare questo funzionamento. Nomi, classi, relazioni e operazioni pubbliche sono una tua scelta.

## Requisiti funzionali

### 1. Commessa e preventivo

Ogni commessa nasce da avviare e ha un codice, il nome del cliente, il numero di pagine del documento, il numero di copie richieste e una modalità di stampa iniziale.

- Codice, cliente, pagine e copie rimangono invariati durante la vita della commessa.
- La modalità può cambiare soltanto finché la commessa è da avviare, mantenendo la stessa commessa e lo stesso codice.
- Deve essere possibile conoscere il numero di fogli necessari e il prezzo totale. Consultarli non consuma carta e non occupa una stampante.
- Il preventivo riflette la modalità attualmente scelta; dopo l'avvio modalità, fogli richiesti e prezzo rimangono invariati.

Le tariffe sono fisse per tutto il programma e sono espresse in centesimi interi:

| Modalità | Consumo per copia del documento | Prezzo |
| --- | --- | --- |
| Solo fronte | Un foglio per ogni pagina | 10 centesimi per foglio |
| Fronte-retro | Un foglio ogni due pagine; una pagina dispari finale richiede un altro foglio | 16 centesimi per foglio, anche se l'ultimo retro resta bianco |

Ogni copia deve iniziare su un foglio nuovo: non puoi utilizzare il retro libero dell'ultima pagina di una copia per iniziare quella successiva.

Esempio di controllo: **3 copie di un documento di 5 pagine** richiedono 15 fogli e costano 150 centesimi solo fronte; richiedono 9 fogli e costano 144 centesimi fronte-retro.

### 2. Stampanti e carta

- Ogni stampante ha un codice ed è operativa oppure fuori servizio. Questa condizione viene stabilita alla creazione e rimane fissa nella simulazione.
- Una stampante può essere impegnata con una sola commessa per volta; all'inizio è libera. Ogni stampante operativa supporta entrambe le modalità.
- Tutte le stampanti attingono alla stessa scorta di carta. La quantità disponibile non può diventare negativa.
- L'operatore può rifornire la scorta aggiungendo un numero positivo di fogli. Quantità nulle o negative vengono rifiutate e lasciano la scorta invariata.

### 3. Avvio di una stampa

L'operatore sceglie esplicitamente la commessa e la stampante. L'avvio è consentito soltanto se:

- la commessa è ancora da avviare;
- la stampante è operativa e libera;
- la scorta contiene almeno tutti i fogli necessari.

Quando l'avvio riesce, la commessa passa in stampa, la stampante scelta risulta occupata e dalla scorta vengono sottratti una sola volta i fogli necessari. Deve rimanere noto quale stampante sta eseguendo quella commessa.

Quando l'avvio viene rifiutato, commessa, stampanti e scorta rimangono come prima del tentativo. Deve essere distinguibile un avvio riuscito da uno rifiutato; il testo del messaggio e il modo di rappresentare l'esito sono liberi.

Una commessa rifiutata perché mancano carta o disponibilità della macchina può essere riprovata quando le condizioni lo permettono. Una commessa già in stampa non può partire una seconda volta, nemmeno su un'altra macchina.

### 4. Completamento e annullamento

- Può essere completata soltanto una commessa in stampa. Il completamento libera esattamente la stampante che la stava eseguendo e non cambia la scorta di carta.
- Può essere annullata soltanto una commessa ancora da avviare. L'annullamento non cambia né carta né stampanti.
- Una commessa completata o annullata rimane consultabile, ma non può essere riaperta, riavviata o modificata.
- Un tentativo non consentito, compreso ripetere un completamento o un annullamento, viene rifiutato senza modificare lo stato. In particolare, completare di nuovo una vecchia commessa non deve liberare una stampante ormai impegnata in un altro lavoro.

## Confini della simulazione

- Le azioni vengono eseguite una alla volta. L'avvio e il completamento sono due azioni esplicite: non devi simulare durata, attese o stampa in background.
- Consideriamo soltanto i rifiuti descritti sopra; dopo un avvio riuscito non ci sono inceppamenti, guasti o stampe parziali.
- Per i dati iniziali puoi assumere codici non vuoti e distinti, clienti presenti, pagine e copie positive, scorta non negativa e riferimenti obbligatori presenti. Usa numeri piccoli. Rimangono da gestire i tentativi non consentiti durante le operazioni.
- Il documento è descritto dal numero di pagine: non occorre leggere file. Il prezzo è soltanto un preventivo: non occorre gestire pagamenti.
- Non servono code automatiche, elenchi, ricerca per codice, database, interfacce grafiche o input da terminale. Nelle prove scegli direttamente commesse e stampanti.

## Casi da verificare nell'implementazione

Puoi organizzare prove indipendenti oppure una sequenza, indicando chiaramente le condizioni di partenza.

| Caso | Comportamento atteso |
| --- | --- |
| Preventivo e cambio modalità prima dell'avvio | I valori dell'esempio delle 5 pagine sono corretti in entrambe le modalità. La commessa mantiene la propria identità; carta e stampanti non cambiano. |
| Avvio con carta esattamente sufficiente | Riesce, la scorta arriva a zero e la stampante diventa occupata. |
| Avvio senza carta sufficiente, poi rifornimento | Il primo tentativo lascia tutto invariato; dopo un rifornimento sufficiente la stessa commessa può partire. Un rifornimento nullo o negativo non cambia la scorta. |
| Stampante fuori servizio oppure occupata da un'altra commessa | L'avvio viene rifiutato senza sottrarre carta o alterare la commessa già in stampa. |
| Secondo avvio della stessa commessa su una stampante diversa e libera | Rifiutato; la seconda macchina resta libera, la prima resta occupata e la carta non diminuisce ulteriormente. |
| Completamento e riuso della stampante | La prima commessa termina, la macchina può avviarne una seconda; ripetere il completamento della prima non modifica la seconda né libera la macchina. |
| Annullamento prima dell'avvio | Riesce senza consumo di risorse; ulteriori annullamenti, avvii o cambi di modalità sono rifiutati. |
| Operazioni incompatibili con la fase corrente | Completare una commessa mai avviata, annullarne una in stampa e cambiare modalità dopo l'avvio sono rifiutati senza effetti. Una commessa completata non può essere riavviata, annullata o modificata. |

I casi possono essere verificati nel punto di ingresso oppure con prove separate sui metodi: non serve completarli tutti nel `main`. Valutiamo la logica: non c'è un formato o testo di output obbligatorio.

## Obiettivi tecnici e criteri di progettazione

- Inizializzare lo stato essenziale tramite costruttori e mantenerlo incapsulato.
- Assegnare calcoli e regole agli oggetti responsabili, con operazioni pubbliche che mantengano valido il loro stato.
- Gestire le collaborazioni e le transizioni senza applicare le regole di dominio nel `main`.
- Permettere il cambio di modalità mantenendo invariato il flusso che avvia e completa la stampa; il chiamante delega i calcoli senza distinguere le modalità concrete.
- Motivare le relazioni e l'eventuale coordinamento separato. La struttura deve essere comprensibile partendo dalle responsabilità.

## Strumenti Java disponibili

Riferimento: i primi due blocchi di `JAVA_PROGRESS.md`. Scegli quelli adatti al modello che progetti:

- primitive e reference type, `String`, `null`, `var`, conversioni, pass-by-value e condivisione dello stesso oggetto tramite reference;
- classi, campi, metodi, valori di ritorno, `this`, costruttori, overload e constructor chaining con `this(...)`;
- `public`, `private`, package-private, `protected`, incapsulamento e membri `static`;
- composizione, delega e collaboratori ricevuti tramite costruttori o metodi;
- ereditarietà, `super`, classi e metodi astratti, override con `@Override`;
- interfacce, implementazione multipla, metodi `default` e risoluzione dei loro conflitti;
- polimorfismo tramite superclassi e interfacce, distinzione tra tipo dichiarato e reale.

Usa Java 25 e Maven. Rimani entro questi argomenti: niente enum, record, collections, generics, eccezioni, lambda, Stream API, concorrenza, framework o librerie aggiuntive. Non servono array per gestire le commesse; `String[] args` del `main` resta naturalmente utilizzabile. Non sono richiesti override di `equals()`, `hashCode()` o `toString()`.

## Consegna e chiusura

Progetta e implementa autonomamente la soluzione sotto:

```text
src/main/java/com/luca/exercises/exercise012/printshop/solution/
```

con package `com.luca.exercises.exercise012.printshop.solution` e un punto di ingresso con `main`. Scegli tu nomi e numero dei file.

L'esercizio è concluso quando:

1. `mvn compile` riesce con Java 25 e le verifiche sui metodi o sul programma coprono i casi indicati;
2. comportamento e organizzazione rispettano i requisiti e i confini della traccia.

Quando dichiari finito l'esercizio, l'AI verifica il codice e prepara direttamente una versione di confronto in `aireview/`, senza modificare la tua soluzione. Il confronto sulle due implementazioni e gli eventuali approfondimenti avvengono su tua richiesta, senza ulteriori passaggi obbligatori.

Non sono richieste estensioni facoltative. Questa traccia fissa il perimetro della valutazione.
