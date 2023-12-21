# My amazing fitness

Le projet consiste en le développement d'une base de données pour une salle de fitness, conçue pour enrichir l'expérience des utilisateurs et optimiser la gestion administrative, offrant des fonctionnalités telles que la réservation de cours, le suivi des paiements et la gestion des membres.

---

## Environnement
 
- Intellij IDEA (Environnement de développement)
- Docker (Conteneurisation)
- Java Temurin 17 (Kit de développement Java)
- Tomcat 10.1.17 (Serveur d'application web)

---

## Dépendances

- **Pilote JDBC PostgreSQL (Connection/intéraction avec base de données)**
    - Group ID: `org.postgresql`
    - Artifact ID: `postgresql`
    - Version: `42.3.1`

---

## Configuration de connexion

### Configuration pour l'utilisateur BDR :
- Host : localhost
- Port : 5432
- Database : bdr
- **User : bdr**
- **Password : bdr**

### Configuration pour le super-utilisateur :
- Host : localhost
- Port : 5432
- Database : postgres
- **User : postgres**
- **Password : root**

---

## Lancer l'application

---

## Exécution de scripts

Des scripts pré-définis sont disponibles pour créer le schéma, le remplir, le vider ou le supprimer.

![Exécution des scriptsl](https://github.com/Theodrosrun/bdr-projet/blob/main/docs/tutorial/run_configuration.png)

---

## Modélisation relationnel

![Schéma relationnel](https://github.com/Theodrosrun/bdr-projet/blob/main/docs/modelisation_relationnel.png)