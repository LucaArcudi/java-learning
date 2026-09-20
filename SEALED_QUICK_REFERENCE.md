# `sealed`, `non-sealed` e `final` — promemoria Java 25

## Le tre scelte

Questi modificatori regolano l'estensione di un tipo, non la mutabilità dei suoi oggetti.

| Modificatore | Effetto sulla gerarchia |
| --- | --- |
| `final` | Chiude il ramo: nessuna sottoclasse. |
| `sealed` | Ammette soltanto i sottotipi diretti autorizzati. |
| `non-sealed` | Riapre un ramo: ulteriori sottotipi seguono le normali regole di ereditarietà. |

Una classe ordinaria che estende direttamente una classe `sealed` o implementa direttamente un'interfaccia `sealed` deve scegliere uno dei tre. Non possono essere combinati sulla stessa classe. [Specifica Java 25](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.1.1.2).

## Esempio: un ramo chiuso e uno aperto

```java
abstract sealed class Documento permits Fattura, Lettera {}

final class Fattura extends Documento {}

non-sealed class Lettera extends Documento {}

class LetteraCommerciale extends Lettera {}
```

- `Fattura` non può avere sottoclassi.
- `LetteraCommerciale` è un `Documento`, ma attraverso `Lettera`: non va nel `permits` di `Documento`.
- Per limitare anche i sottotipi di `Lettera`, la dichiareremmo `sealed` con un proprio elenco di tipi ammessi.

Quindi una radice `sealed` non rende necessariamente chiusa tutta la gerarchia: un ramo `non-sealed` può restare aperto.

## `permits`: autorizzare non significa ereditare

`permits` elenca i sottotipi diretti ammessi; questi devono comunque dichiarare `extends` o `implements`.

Nel progetto attuale, senza moduli espliciti, devono stare nello stesso package del tipo `sealed`. Stesso package non significa stesso file: possiamo mantenere un file per tipo.

`permits` può essere omesso quando i sottotipi diretti sono dichiarati nello stesso file e il compilatore può ricavarli. Non basta che siano nello stesso package. [Specifica Java 25](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.1.6).

## Interfacce e record

Un'interfaccia può essere `sealed` o `non-sealed`, ma non `final`. Un'interfaccia che estende direttamente un'interfaccia `sealed` deve scegliere tra `sealed` e `non-sealed`. [Specifica Java 25](https://docs.oracle.com/javase/specs/jls/se25/html/jls-9.html#jls-9.1.1.4).

Versione ridotta della gerarchia usata nel corso:

```java
sealed interface EsitoStampa permits StampaAvviata, StampaRifiutata {}

record StampaAvviata(String codiceStampante) implements EsitoStampa {}

record StampaRifiutata(String motivo) implements EsitoStampa {}
```

I record sono implicitamente `final`: entrambi i rami sono chiusi, senza scrivere il modificatore. Non possono essere `sealed` o `non-sealed`. [Specifica Java 25](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.10).

Possiamo creare molti esiti dello stesso tipo con dati diversi:

```java
EsitoStampa primo = new StampaAvviata("S001");
EsitoStampa secondo = new StampaAvviata("S002");
EsitoStampa terzo = new StampaRifiutata("Carta insufficiente");
```

Il limite riguarda i tipi ammessi, non il numero di oggetti.

## Distinzioni da ricordare

- `non-sealed` richiede un genitore diretto `sealed`: non è un sinonimo da applicare a qualsiasi classe aperta.
- `sealed` non implica `abstract`: una classe può limitare le sottoclassi ed essere comunque istanziabile.
- `final` sulla classe impedisce l'ereditarietà; su una variabile o un campo impedisce la riassegnazione. Nessuno dei due rende automaticamente immutabile l'oggetto.

In sintesi: `final` chiude, `sealed` seleziona, `non-sealed` riapre.
