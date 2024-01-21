# My amazing fitness

Le projet consiste en le développement d'une base de données pour une salle de fitness, conçue pour enrichir l'expérience des utilisateurs et optimiser la gestion administrative, offrant des fonctionnalités telles que la réservation de cours, le suivi des paiements et la gestion des membres.

Le présent travail possède un [cahier des charges](https://github.com/Theodrosrun/bdr-projet/blob/main/docs/cahier_des_charges.pdf) dont le projet s'est inspiré pour modéliser la base de données.

## Modélisation relationnel

![Schéma relationnel](https://github.com/Theodrosrun/bdr-projet/blob/main/docs/modelisation_relationnel.png)

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

## Configuration de la connexion

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


## Lancement de l'application

**Clonage du dépôt et démarrage des services Docker** :
- Commencez par cloner le dépôt : git clone git@github.com:Theodrosrun/bdr-projet.git
- Assurez-vous que votre démon Docker ou Docker Desktop est en cours d'exécution
- Dans le dossier du projet, exécutez la commande suivante : docker compose up

Sur Mac et Linux:

- Ouvrez un terminal et exécutez la commande suivante : ./build-db.sh

Sur Windows:

- Ouvrez un terminal bash ou WSL et exécutez la commande suivante : 

**Accès à l'Application** :
- Vous pouvez maintenant accéder au site web Amazing Fitness: http://localhost:8080/home







