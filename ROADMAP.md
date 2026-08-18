# Roadmap Java

## 1. Java moderno — linguaggio principale

- [x] Type system: primitive, reference types, null, var, conversioni e pass-by-value.
- [ ] Classi, interfacce, abstract class, composizione, ereditarietà e polimorfismo.
- [ ] Immutabilità, enum, record, sealed classes e pattern matching essenziale.
- [ ] `equals()`, `hashCode()`, `toString()` e identità degli oggetti.
- [ ] Collections: `List`, `Set`, `Map` e principali implementazioni.
- [ ] Generics: tipi parametrizzati, wildcard e bounded generics quando servono.
- [ ] Exceptions: checked/unchecked, custom exceptions, try-with-resources.
- [ ] Lambda, functional interfaces e Stream API.
- [ ] `Optional`, `java.time`, `Path`/`Files` e I/O moderno.
- [ ] Concurrency Java: `Thread`/`Executor`, `CompletableFuture`, concurrent collections e virtual threads.
- [ ] JVM essentials: heap, stack, GC, JIT, class loading, profiling e troubleshooting di base.

## 2. Tooling Java, build e qualità del codice

- [ ] Maven: lifecycle, dependency management, plugin, scope, packaging e multi-module basics.
- [ ] Gradle: saper leggere e usare un progetto esistente, senza renderlo una priorità.
- [ ] Logging applicativo: API, livelli, configurazione per ambiente e lettura degli stack trace.
- [ ] Debugging da IDE.
- [ ] JUnit: unit test, parameterized test e test organization.
- [ ] Mockito: mocking mirato e consapevole.
- [ ] Integration test e Testcontainers.

## 3. Spring Framework e Spring Boot

- [ ] IoC e Dependency Injection.
- [ ] Bean lifecycle, configuration e auto-configuration.
- [ ] Spring MVC e REST controller.
- [ ] Validation, exception handling e configuration properties.
- [ ] Profiles e gestione della configurazione.
- [ ] Strutturazione `controller → service → repository` senza trasformarla in dogma.

## 4. Persistence e sicurezza applicativa in Spring

- [ ] JDBC come base concettuale.
- [ ] JPA/Hibernate e Spring Data JPA.
- [ ] Entity lifecycle, lazy/eager loading, N+1 e query custom.
- [ ] Transaction boundaries applicative e comportamento di `@Transactional`.
- [ ] Locking ottimistico/pessimistico dal punto di vista ORM.
- [ ] Pagination e gestione efficiente del persistence layer.
- [ ] Database migration con Flyway o Liquibase.
- [ ] Spring Security: authentication, authorization, filter chain, sessioni, JWT, OAuth2/OIDC, RBAC, CORS e CSRF.
