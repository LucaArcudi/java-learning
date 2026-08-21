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

## 2. Tooling Java, build e qualità del codice
