# Java — cose fatte finora

## 1. Java moderno — linguaggio principale

### 1. Type system: primitive, reference types, null, var, conversioni e pass-by-value.

- Tipizzazione statica di Java.
- Primitive types e reference types.
- Differenza tra valore primitivo e reference a un oggetto.
- `String` come reference type.
- Immutabilità di `String`.
- Differenza tra `null` e stringa vuota.
- `NullPointerException` e controlli base su `null`.
- Confronto tra reference con `==` e confronto del contenuto con `.equals()`.
- String Pool a livello essenziale.
- Wrapper types come `Integer`.
- `var` e inferenza locale del tipo.
- Conversioni numeriche implicite tra tipi compatibili.
- Cast espliciti quando una conversione può perdere informazione.
- Assegnazione di reference e condivisione dello stesso oggetto.
- Differenza tra modifica dello stato di un oggetto e riassegnazione di una variabile reference.
- Pass-by-value con primitive.
- Pass-by-value con reference types.
- Java è sempre pass-by-value; per i reference type, il valore passato è la reference.
- Differenza concettuale tra oggetti mutabili e immutabili.

### 2. Classi, interfacce, abstract class, composizione, ereditarietà e polimorfismo.

- Classe come insieme di stato e comportamento.
- Creazione di un'istanza tramite `new`.
- Campi d'istanza.
- Metodi d'istanza che leggono e modificano lo stato dell'oggetto.
- Differenza essenziale tra un metodo d'istanza e il metodo `static main`.
- Metodi `void` e metodi che restituiscono un valore.
- `this` come riferimento all'istanza corrente.
- Shadowing tra un campo e un parametro con lo stesso nome.
- Costruttori e inizializzazione dello stato durante la creazione di un oggetto.
- Differenza sintattica tra costruttore e metodo.
- Costruttore senza argomenti implicito e sua assenza quando viene dichiarato un costruttore esplicito.
- Modificatori di accesso `public` e `private`.
- Visibilità package-private ottenuta omettendo il modificatore.
- Modificatore `protected`: accesso consentito alle classi dello stesso package e alle sottoclassi.
- Differenza tra `protected` in Java e in PHP: in Java concede accesso anche alle classi non correlate dello stesso package.
- Accesso `protected` da una sottoclasse in un altro package tramite `this`, `super` o una reference appartenente al ramo della sottoclasse.
- Incapsulamento dello stato e accesso tramite l'API pubblica della classe.
- Overloading dei costruttori in base alla lista ordinata dei tipi dei parametri.
- Constructor chaining tramite `this(...)`.
- I nomi dei parametri non distinguono due firme sovraccaricate.
- Overloading dei metodi tramite numero, tipi o ordine dei parametri.
- Selezione dell'overload compatibile in base agli argomenti della chiamata.
- Campi `static` condivisi dalla classe anziché appartenere a ogni istanza.
- Metodi `static` invocati tramite il nome della classe.
- Assenza di `this` nel contesto statico.
- `main` come punto di ingresso statico invocabile senza creare un'istanza di `Main`.
- Composizione tra oggetti come relazione "has-a".
- Campo di una classe avente come tipo un'altra classe.
- Delega di un comportamento da un oggetto a un oggetto collaboratore.
- Gestione di una relazione opzionale tramite controllo di `null`.
- Ereditarietà come relazione "is-a" tramite `extends`.
- Ereditarietà dei metodi accessibili della superclasse.
- I campi `private` della superclasse non sono accessibili direttamente dalla sottoclasse.
- Chiamata al costruttore della superclasse tramite `super(...)`.
- I costruttori non vengono ereditati.
- Override di un metodo ereditato mantenendo la stessa firma.
- Uso di `@Override` per far verificare al compilatore l'intenzione di ridefinire un metodo.
- Chiamata all'implementazione della superclasse tramite `super.metodo()`.
- Differenza tra overloading e override.
- Polimorfismo: una variabile della superclasse può contenere un oggetto della sottoclasse.
- Distinzione tra tipo dichiarato della variabile e tipo reale dell'oggetto.
- Il tipo dichiarato determina i metodi accessibili in compilazione.
- Il tipo reale determina quale override viene eseguito a runtime.
- Classi astratte dichiarate con `abstract` e non istanziabili direttamente.
- Le classi astratte possono contenere stato, costruttori e metodi concreti condivisi.
- Metodi astratti privi di implementazione.
- Obbligo per una sottoclasse concreta di implementare i metodi astratti ereditati.
- Uso polimorfico di più sottoclassi concrete attraverso il tipo astratto comune.
- Interfacce come contratti di comportamento.
- Implementazione di un'interfaccia tramite `implements`.
- Implementazione pubblica dei metodi dichiarati dall'interfaccia con `@Override`.
- Implementazione di più interfacce nella stessa classe tramite una lista separata da virgole.
- Uso della stessa istanza attraverso variabili dichiarate con tipi interfaccia differenti.
- Metodi `default` nelle interfacce come comportamento concreto ereditabile dalle classi che le implementano.
- Chiamata di un metodo astratto dell'interfaccia da un metodo `default`, con dispatch verso l'implementazione dell'oggetto reale.
- Conflitto tra metodi `default` con la stessa firma ereditati da interfacce non correlate.
- Risoluzione esplicita del conflitto tramite override e chiamata `NomeInterfaccia.super.metodo()`.
- Dipendenza da un tipo interfaccia invece che dalle implementazioni concrete.
- Dispatch polimorfico tra implementazioni diverse della stessa interfaccia.
- Scelta pratica tra composizione, ereditarietà, classe astratta e interfaccia in base alle relazioni `has-a`, `is-a`, allo stato condiviso e al contratto richiesto.
- Uso combinato di composizione e interfacce per dipendere da collaboratori sostituibili.
- Preferenza per un contratto o per la composizione quando l'ereditarietà servirebbe soltanto a riutilizzare codice senza una reale relazione `is-a`.

#### Consolidamento OOD

- Ordine delle operazioni che coinvolgono più oggetti: verificare i possibili rifiuti prima delle modifiche, entro i confini di una simulazione sequenziale.
- Protezione delle transizioni di stato, consumo delle risorse una sola volta e rifiuto delle operazioni ripetute senza effetti indesiderati.
- Confronto tra contenuto delle stringhe con `.equals()` e identità degli oggetti con `==`.

### 3. Immutabilità, enum, record, sealed classes e pattern matching essenziale — in corso.

- `final` sulle variabili locali: una volta inizializzate non possono essere riassegnate.
- Distinzione tra dichiarazione e riassegnazione di una variabile locale; divieto di doppia dichiarazione nello stesso blocco, indipendentemente da `final`.
- Distinzione tra reference non riassegnabile e oggetto immutabile: `final` sulla variabile non impedisce di invocare operazioni che modificano l'oggetto.
- Campi d'istanza `final`: inizializzazione nel costruttore e divieto di successive riassegnazioni nei metodi.
- Differenza tra `private`, che limita l'accesso al campo, e `final`, che ne impedisce la riassegnazione dopo l'inizializzazione.
- Classi dichiarate `final`: divieto di ereditarietà; `final` sulla classe non rende automaticamente immutabili i suoi oggetti.
- Struttura di una semplice classe immutabile: classe `final`, campi `private final` di tipo immutabile inizializzati nel costruttore e assenza di operazioni che modifichino lo stato.
- Operazioni su oggetti immutabili che restituiscono una nuova istanza con alcuni dati diversi, lasciando invariato l'oggetto originale.
- Distinzione tra creazione di un nuovo valore e modifica di un oggetto esistente; verifica dell'identità delle istanze tramite `==`.
- Limite dei campi `private final` che contengono reference a oggetti mutabili: modifiche tramite reference esterne condivise possono cambiare i dati osservabili dall'oggetto contenitore, anche se la sua classe è `final` e non offre operazioni di modifica.
- Creazione di uno snapshot conservando un valore immutabile letto nel costruttore, anziché mantenere una reference all'oggetto mutabile di origine.
- Indipendenza tra tipo del parametro del costruttore e tipo del campo: il costruttore può estrarre e conservare soltanto i dati necessari.
- Copia difensiva nel costruttore: conservare un oggetto distinto da quello mutabile ricevuto per impedire modifiche indirette tramite la reference originale.
- Copia difensiva nei getter: restituire una copia di un oggetto interno mutabile per non esporre lo stato del contenitore a modifiche esterne.
- Distinzione tra copia della reference e creazione di un oggetto indipendente con gli stessi dati; un contenitore può essere immutabile pur usando internamente oggetti mutabili, se non li modifica e non ne condivide le reference con l'esterno.
- Dichiarazione di un `enum` per rappresentare un insieme predefinito di alternative e uso delle sue costanti attraverso il tipo dichiarato.
- Differenza tra una costante enum e una stringa: il tipo enum impedisce di assegnare testi arbitrari come valori.
- Confronto delle costanti dello stesso enum tramite `==`, grazie all'identità unica di ciascuna costante.
- Distinzione tra stati rappresentabili da un enum e regole di dominio sulle transizioni tra stati, che non vengono applicate automaticamente dall'enum.
- Campi d'istanza e metodi negli enum per associare dati alle singole costanti e consultarli.
- Costruttore privato di un enum e argomenti forniti nella dichiarazione delle costanti; impossibilità di creare ulteriori istanze tramite `new`.
- Ordine degli elementi nel corpo di un enum: costanti prima di campi, costruttori e metodi, con `;` a separare l'elenco dei valori dai membri successivi.
- Metodi d'istanza negli enum che esprimono regole basate sulla costante corrente e su una destinazione ricevuta come parametro.
- Uso di `this` negli enum per riferirsi alla costante su cui è chiamato il metodo.
- Verifica delle transizioni tra stati separata dalla loro applicazione e dai controlli sulle altre risorse del dominio.
- Dichiarazione di un `record` e dei suoi componenti per rappresentare una classe centrata sui dati.
- Generazione automatica dei campi `private final`, del costruttore canonico e degli accessori con il nome dei componenti, senza prefisso `get`.
- Record implicitamente `final` e possibilità di dichiarare metodi d'istanza aggiuntivi.
- Immutabilità superficiale dei record: i componenti non sono riassegnabili, ma eventuali oggetti mutabili contenuti non vengono automaticamente copiati o resi immutabili.
- `equals()` generato automaticamente nei record: confronto tra istanze dello stesso tipo in base ai componenti; confronto per contenuto per i componenti `String`.
- Distinzione tra identità tramite `==` e uguaglianza di valore tramite `equals()` per due record con gli stessi dati; differenza rispetto a una classe ordinaria senza override di `equals()`.
- Costruttore compatto dei record: parametri impliciti corrispondenti ai componenti e assegnazione automatica ai campi al termine del corpo.
- Normalizzazione dei parametri nel costruttore compatto prima della memorizzazione, senza assegnare direttamente i campi `final`.
- Uso di `String.trim()` per rimuovere gli spazi iniziali e finali dai dati ricevuti.
- Interfacce `sealed` e clausola `permits` per limitare i sottotipi diretti ammessi.
- Record che implementano un'interfaccia `sealed`: i record sono implicitamente `final` e chiudono i rispettivi rami della gerarchia.
- Rappresentazione di esiti alternativi con dati differenti mediante record distinti e un tipo comune; uso polimorfico del comportamento dichiarato nell'interfaccia.
- Distinzione tra le costanti predefinite di un enum e i tipi ammessi da una gerarchia `sealed`, dei quali si possono creare molte istanze con dati diversi.
- Pattern matching con `instanceof`: verifica del tipo reale e introduzione di una variabile del tipo riconosciuto, senza cast esplicito.
- La variabile introdotta dal pattern si riferisce allo stesso oggetto, senza crearne uno nuovo né cambiare il tipo dichiarato della variabile originale.
- Accesso ai dati specifici dei sottotipi attraverso le variabili del pattern nei rispettivi rami `if` / `else if`.
- Confronto con la forma tradizionale: controllo tramite `instanceof` seguito da cast esplicito a un tipo più specifico.
- Flow scoping delle variabili di pattern: disponibilità nei punti in cui il flusso garantisce che il riconoscimento sia riuscito.
- Pattern negato con uscita anticipata dal metodo e uso della variabile riconosciuta dopo il blocco `if`.
- Uso della variabile del pattern nella parte destra di `&&`, grazie alla valutazione condizionale del secondo operando.
- Pattern matching nello `switch` tramite casi per tipo e variabili disponibili nel rispettivo ramo.
- `switch` come espressione che produce un valore, restituito dal metodo tramite `return`; rami con `->` senza passaggio al caso successivo.
- Copertura esaustiva dei sottotipi di una gerarchia `sealed`, che permette di omettere `default` quando tutti i casi sono gestiti.
- Condizioni aggiuntive con `when` e ordine dei casi: caso condizionato prima del caso generale dello stesso tipo.
- Gestione esplicita di `null` nello `switch` tramite `case null`, distinta dal normale `default`.
- Record patterns per riconoscere un record ed estrarne direttamente i componenti, senza creare un nuovo oggetto.
- Associazione dei componenti nei record patterns secondo l'ordine della dichiarazione del record, non secondo i nomi scelti per le variabili.

## 2. Tooling Java, build e qualità del codice
