# TP4 : Hibernate et Persistance des Données

Application Java qui utilise Hibernate et MySQL pour gérer des **salles** et des **machines** (association One-to-Many), avec une architecture en couches : entités, DAO, services.

## Prérequis

- JDK 11 ou supérieur
- Maven
- MySQL démarré sur `localhost:3306` (utilisateur `root`, mot de passe vide : à modifier dans `hibernate.cfg.xml` si besoin)

La base `db_tp_hibernate` est créée automatiquement (`createDatabaseIfNotExist=true`) et les tables `salles` et `machines` aussi (`hbm2ddl.auto=update`).

## Structure

```
hibernate1
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   ├── dao        IDao<T>, SalleDao, MachineDao
    │   │   ├── entities   Salle, Machine
    │   │   ├── services   SalleService, MachineService
    │   │   ├── test       Test (classe main)
    │   │   └── util       HibernateUtil
    │   └── resources      hibernate.cfg.xml
    └── test/java/services SalleServiceTest, MachineServiceTest
```

## Étapes réalisées

1. `pom.xml` : Hibernate Core 5.6.15, MySQL Connector 8.0.33, JUnit 5 (+ moteur et plugin Surefire pour lancer les tests avec Maven).
2. `IDao<T>` : interface générique CRUD (`create`, `delete`, `update`, `getById`, `getAll`).
3. Entités `Salle` et `Machine` : `@OneToMany(mappedBy = "salle")` côté `Salle`, `@ManyToOne` + `@JoinColumn(name = "salle_id")` côté `Machine`.
4. `hibernate.cfg.xml` : connexion MySQL, dialecte, `hbm2ddl.auto=update`, `show_sql`, déclaration des entités.
5. `HibernateUtil` : une seule `SessionFactory` pour toute l'application.
6. `SalleDao` et `MachineDao` : implémentation avec `Session` et `Transaction` (rollback en cas d'erreur).
7. `SalleService` et `MachineService` : couche service qui délègue au DAO.
8. `test.Test` : insère une salle et une machine puis affiche les données.
9. Tests JUnit : `SalleServiceTest` (create, getById, update, delete) et `MachineServiceTest` (create + HQL).
10. HQL : `MachineDao.findByMarque(String)`.
11. Gestion des transactions : `try-with-resources` + `commit` / `rollback`.

## Lancer le projet

1. Démarrer MySQL.
2. Ouvrir le dossier dans IntelliJ IDEA (projet Maven) et recharger Maven.
3. Exécuter `test.Test` : les tables `salles` et `machines` sont créées et alimentées.
4. Lancer les tests : `mvn test` (MySQL doit être démarré).

## Remarques

- Dans le fichier XML, le caractère `&` de l'URL JDBC doit s'écrire `&amp;`.
- `Machine` contient `ref`, `marque` et `dateAchat` (le diagramme de classes montre `dateAchat`, l'énoncé montre `marque`).
