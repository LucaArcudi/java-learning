# Java course — examples and learning style

Questo file raccoglie gli esempi usati finora durante il percorso Java.

## Come viene usato il progetto

`Main.java` viene usato come sandbox temporanea durante il corso.

Il flusso tipico è:

1. viene spiegato un concetto alla volta;
2. viene proposto un esempio molto piccolo;
3. modifico direttamente `Main.java`;
4. compilo/eseguo;
5. a volte introduco volontariamente un errore per vedere cosa dice il compilatore;
6. chiarisco il comportamento osservato;
7. poi sostituisco il contenuto di `Main.java` con l'esempio successivo.

Non voglio che ogni esempio venga conservato come una classe "Demo" separata.
Questi esempi servono soprattutto a mostrare **stile, granularità e progressione del corso**.

Il mio background è PHP/backend, quindi:
- non servono spiegazioni da principiante assoluto;
- quando utile, confrontare Java con PHP;
- soffermarsi sulle differenze che possono creare errori a chi arriva da PHP;
- non anticipare molti argomenti insieme;
- prima capire un concetto, poi provarlo concretamente.

---

## 1. Static typing

```java
package com.luca;

public class Main {

    public static void main(String[] args) {

        int age = 32;
        double price = 19.99;
        boolean active = true;

        System.out.println(age);
        System.out.println(price);
        System.out.println(active);
    }
}
```

Errore volontario:

```java
int age = 32;
age = "trentadue";
```

---

## 2. Primitive types

```java
int users = 10;
long population = 8_000_000_000L;
double price = 12.50;
boolean enabled = true;
char grade = 'A';
```

---

## 3. `char` vs `String`

```java
char letter = 'A';
String name = "Luca";
```

Errore volontario:

```java
char letter = "A";
```

---

## 4. Primitive vs reference type

```java
int age = 32;
String name = "Luca";

System.out.println(age);
System.out.println(name);
```

Modello mentale usato:

```text
age
 ↓
32
```

```text
name
 ↓
reference
 ↓
String "Luca"
```

---

## 5. `null`

Non compila:

```java
int age = null;
```

Compila:

```java
String name = null;
```

Regola:

```text
primitive type   → non può essere null
reference type   → può essere null
```

---

## 6. Wrapper types

```java
int age = 32;
Integer otherAge = 32;
```

```java
Integer age = null;
```

è valido, mentre:

```java
int age = null;
```

non lo è.

Esempio completo:

```java
package com.luca;

public class Main {

    public static void main(String[] args) {

        int primitiveAge = 32;
        Integer referenceAge = 32;

        String name = "Luca";

        System.out.println(primitiveAge);
        System.out.println(referenceAge);
        System.out.println(name);

        referenceAge = null;
        name = null;

        System.out.println(referenceAge);
        System.out.println(name);
    }
}
```

Errore volontario:

```java
primitiveAge = null;
```

---

## 7. Conversioni numeriche implicite

```java
int x = 10;
long y = x;
double z = y;

System.out.println(x);
System.out.println(y);
System.out.println(z);
```

Concetto:

```text
int → long → double
```

Altro esempio:

```java
int age = 32;
double ageAsDouble = age;
```

`age` resta `int`; il valore viene convertito mentre viene assegnato alla nuova variabile `double`.

---

## 8. Conversione esplicita

Errore volontario:

```java
double x = 10.5;
int y = x;
```

Versione con cast:

```java
double x = 10.5;
int y = (int) x;

System.out.println(y);
```

Altro esempio:

```java
double price = 19.99;
int roundedPrice = (int) price;
```

Il cast tronca la parte decimale; non arrotonda.

---

## 9. `String` come reference type

```java
package com.luca;

public class Main {

    public static void main(String[] args) {

        String name = "Luca Arcudi";

        System.out.println(name);
        System.out.println(name.length());
        System.out.println(name.toUpperCase());
        System.out.println(name.startsWith("Luca"));
    }
}
```

Confronto con PHP:

```php
strlen($name);
strtoupper($name);
```

Java:

```java
name.length();
name.toUpperCase();
```

---

## 10. Immutabilità di `String`

```java
String name = "Luca";

name.toUpperCase();

System.out.println(name);
```

Risultato:

```text
Luca
```

Per usare il nuovo valore:

```java
name = name.toUpperCase();

System.out.println(name);
```

Risultato:

```text
LUCA
```

---

## 11. Concatenazione

```java
String firstName = "Luca";
String lastName = "Arcudi";

String fullName = firstName + " " + lastName;

System.out.println(fullName);
```

Con numero:

```java
int age = 32;

String text = "Età: " + age;

System.out.println(text);
```

Questo invece resta illegale:

```java
String age = 32;
```

---

## 12. `==` vs `.equals()`

```java
String a = "Luca";
String b = "Luca";

System.out.println(a == b);
```

Può stampare `true`, ma `==` sui reference type confronta le reference.

Per il contenuto:

```java
System.out.println(a.equals(b));
```

---

## 13. Prova esplicita con due `String`

```java
String a = "Luca";
String b = new String("Luca");

System.out.println(a == b);
System.out.println(a.equals(b));
```

Output:

```text
false
true
```

Esempio completo:

```java
package com.luca;

public class Main {

    public static void main(String[] args) {

        String a = "Luca";
        String b = "Luca";
        String c = new String("Luca");

        System.out.println(a == b);
        System.out.println(a == c);

        System.out.println(a.equals(b));
        System.out.println(a.equals(c));
    }
}
```

Output:

```text
true
false
true
true
```

È stato introdotto lo String Pool solo quanto necessario a spiegare questo comportamento.

---

## 14. `null` vs stringa vuota

```java
package com.luca;

public class Main {

    public static void main(String[] args) {

        String a = null;
        String b = "";

        System.out.println(a);
        System.out.println(b);
    }
}
```

Poi:

```java
System.out.println(b.length());
```

restituisce:

```text
0
```

---

## 15. `NullPointerException`

```java
String name = null;

System.out.println(name.length());
```

Compila, ma fallisce a runtime.

---

## 16. Controllo di `null`

```java
String name = null;

if (name != null) {
    System.out.println(name.length());
}
```

Oppure:

```java
if (name == null) {
    System.out.println("Nome assente");
}
```

---

## 17. `.equals()` e `null`

Versione rischiosa:

```java
String name = null;

if (name.equals("Luca")) {
    System.out.println("È Luca");
}
```

Versione sicura contro una costante:

```java
if ("Luca".equals(name)) {
    System.out.println("È Luca");
}
```

---

## 18. Esempio combinato su `null`

```java
package com.luca;

public class Main {

    public static void main(String[] args) {

        String firstName = null;
        String lastName = "";

        if (firstName == null) {
            System.out.println("firstName assente");
        }

        System.out.println("Lunghezza lastName: " + lastName.length());

        if ("Luca".equals(firstName)) {
            System.out.println("È Luca");
        } else {
            System.out.println("Non è Luca");
        }
    }
}
```

Poi veniva modificato:

```java
String firstName = "Luca";
```

per osservare la differenza.

---

## 19. `var`

```java
package com.luca;

public class Main {

    public static void main(String[] args) {

        var name = "Luca";
        var age = 32;
        var active = true;

        System.out.println(name);
        System.out.println(age);
        System.out.println(active);
    }
}
```

Tipi inferiti:

```text
name   → String
age    → int
active → boolean
```

Errore volontario:

```java
age = "32";
```

---

## 20. `var` richiede inferenza possibile

Errori volontari:

```java
var name;
```

```java
var name = null;
```

Versione valida:

```java
String name = null;
```

---

## 21. `var` e tipo fisso

```java
package com.luca;

public class Main {

    public static void main(String[] args) {

        var name = "Luca";
        var age = 32;
        var price = 10.5;

        name = "Mario";
        age = 33;
        price = 20.5;

        System.out.println(name);
        System.out.println(age);
        System.out.println(price);
    }
}
```

Errore volontario:

```java
age = 33.5;
```

Perché `age` è stato inferito come `int`.

---

## 22. Primitive: copia del valore

```java
package com.luca;

public class Main {

    public static void main(String[] args) {

        int a = 10;
        int b = a;

        b = 20;

        System.out.println(a);
        System.out.println(b);
    }
}
```

Output:

```text
10
20
```

---

## 23. Prima classe semplice `Person`

```java
package com.luca;

public class Person {

    String name;

}
```

Uso:

```java
Person a = new Person();
a.name = "Luca";

Person b = a;

System.out.println(a.name);
System.out.println(b.name);
```

---

## 24. Modifica dello stesso oggetto tramite due reference

```java
Person a = new Person();
a.name = "Luca";

Person b = a;

b.name = "Mario";

System.out.println(a.name);
System.out.println(b.name);
```

Output:

```text
Mario
Mario
```

---

## 25. Riassegnazione della seconda variabile

```java
Person a = new Person();
a.name = "Luca";

Person b = a;

b = new Person();
b.name = "Mario";

System.out.println(a.name);
System.out.println(b.name);
```

Output:

```text
Luca
Mario
```

---

## 26. Pass-by-value con primitive

```java
static void changeNumber(int number) {
    number = 99;
}
```

Uso:

```java
int x = 10;

changeNumber(x);

System.out.println(x);
```

Output:

```text
10
```

---

## 27. Pass-by-value con reference type mutabile

```java
static void changeName(Person person) {
    person.name = "Mario";
}
```

Uso:

```java
Person person = new Person();
person.name = "Luca";

changeName(person);

System.out.println(person.name);
```

Output:

```text
Mario
```

Il parametro riceve il valore della reference e quindi raggiunge lo stesso oggetto.

---

## 28. Prova che Java non è pass-by-reference

```java
static void replacePerson(Person person) {

    person = new Person();
    person.name = "Mario";
}
```

Uso:

```java
Person person = new Person();
person.name = "Luca";

replacePerson(person);

System.out.println(person.name);
```

Output:

```text
Luca
```

Conclusione raggiunta:

> Java è sempre pass-by-value. Per i reference type, il valore passato è la reference.

---

## 29. `String` + pass-by-value + immutabilità

```java
package com.luca;

public class Main {

    public static void main(String[] args) {

        String name = "Luca";

        changeName(name);

        System.out.println(name);
    }

    static void changeName(String name) {
        name = "Mario";
    }
}
```

Output:

```text
Luca
```

---

## 30. `String.toUpperCase()` dentro un metodo

```java
static void changeName(String name) {
    name.toUpperCase();
}
```

Non modifica la stringa.

Versione che usa il nuovo oggetto restituito:

```java
static void changeName(String name) {
    name = name.toUpperCase();

    System.out.println(name);
}
```

Dentro il metodo stampa:

```text
LUCA
```

ma la variabile originale nel chiamante continua a riferirsi a `"Luca"`.

---

## 31. Modello mentale raggiunto

```text
primitive
→ contiene un valore diretto

reference type mutabile
→ contiene una reference a un oggetto il cui stato può cambiare

reference type immutabile
→ contiene una reference a un oggetto il cui stato non può cambiare
```

Esempi di reference type immutabili menzionati:

```text
String
Integer
Long
Double
Boolean
BigInteger
BigDecimal
LocalDate
LocalDateTime
Instant
UUID
```

Esempi di reference type mutabili menzionati:

```text
Person
ArrayList
HashMap
StringBuilder
```

Questi tipi non sono ancora stati approfonditi; sono stati menzionati solo per chiarire il concetto di mutabilità.

---

## Stato del corso

Considerato completato:

```text
Type system: primitive, reference types, null, var, conversioni e pass-by-value.
```

Prossimo blocco della roadmap:

```text
Classi, interfacce, abstract class, composizione, ereditarietà e polimorfismo.
```

Da affrontare progressivamente, partendo da classi e oggetti senza anticipare tutto il resto.
