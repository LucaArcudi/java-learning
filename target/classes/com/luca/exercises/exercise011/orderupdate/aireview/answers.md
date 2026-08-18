# Review delle risposte finali

## 1. Perché la modifica fatta tramite la variabile dell’operatore è visibile dalla variabile originale?

### Valutazione

La risposta è corretta nel concetto. Va precisata soltanto la frase "reference della
variabile": la variabile non ha una reference a sé stessa, ma contiene una reference
all'oggetto.

Con l'assegnazione:

```java
var operatorView = draftOrder;
```

il valore contenuto in `draftOrder`, cioè la reference all'oggetto `Order`, viene
copiato in `operatorView`. Le due variabili contengono quindi una reference allo
stesso oggetto. Modificare quell'oggetto attraverso `operatorView` rende la modifica
visibile anche attraverso `draftOrder`.

Anche nell'assegnazione di una variabile primitiva viene copiato il valore. La
differenza è che, per un reference type, il valore copiato permette a entrambe le
variabili di raggiungere lo stesso oggetto.

## 2. Perché la riassegnazione del parametro nel secondo metodo non sostituisce la bozza originale?

### Valutazione

La risposta è parzialmente corretta: riconosce che viene creata una nuova istanza,
ma non spiega ancora perché la variabile originale non cambia. Inoltre, non è la
nuova istanza ad avere "una sua reference": è il parametro locale a ricevere la
reference al nuovo oggetto.

Quando `draftOrder` viene passato al metodo, Java copia nel parametro il valore
della sua reference. All'inizio la variabile del chiamante e il parametro del metodo
indicano lo stesso oggetto. Questa istruzione:

```java
order = new Order();
```

assegna una nuova reference soltanto al parametro locale. La variabile del chiamante
continua a contenere la reference alla bozza originale, quindi non viene sostituita.

## 3. Cosa succede alla parte decimale quando il totale viene convertito in `int`?

### Valutazione

La risposta è corretta. Il cast da `double` a `int` elimina la parte decimale senza
arrotondarla. Per esempio, `(int) 659.25` produce `659`. Dal solo valore `int` non
è possibile ricostruire la parte eliminata.
