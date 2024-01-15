# My amazing fitness

Le projet consiste en le développement d'une base de données pour une salle de fitness, conçue pour enrichir l'expérience des utilisateurs et optimiser la gestion administrative, offrant des fonctionnalités telles que la réservation de cours, le suivi des paiements et la gestion des membres.

Le présent travail possède un [cahier des charges](https://github.com/Theodrosrun/bdr-projet/blob/main/docs/cahier_des_charges.pdf) dont le projet s'est inspiré pour modéliser la base de données.

## Modélisation relationnel

![Schéma relationnel](https://github.com/Theodrosrun/bdr-projet/blob/main/docs/modelisation_relationnel.png)

## Exécution de scripts

Des scripts pré-définis sont disponibles pour créer le schéma, le remplir, le vider ou le supprimer.

![Exécution des scriptsl](https://github.com/Theodrosrun/bdr-projet/blob/main/docs/tutorial/run_configuration.png)

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

---

### 1. Classe SQLManager :  

La classe SQLManager joue un rôle fondamental dans la gestion des connexions à la base de données. Ses principales démarches incluent :  

**Établissement de la Connexion** : La classe SQLManager est responsable de l'établissement de la connexion à la base de données. Elle utilise les paramètres de connexion, tels que le nom d'utilisateur, le mot de passe et l'URL de la base de données, pour créer une connexion avec le système de gestion de base de données (SGBD) sous-jacent.  
**Exécution des Requêtes SQL** : Une fois la connexion établie, la classe SQLManager permet l'exécution de requêtes SQL. Elle offre des méthodes pour effectuer des opérations telles que l'insertion, la mise à jour, la suppression et la sélection de données.  
**Gestion des Ressources** : La classe gère également les ressources liées à la connexion, garantissant la fermeture appropriée de la connexion après son utilisation pour éviter les fuites de ressources.  

### 2. Classe UserController :  

La classe UserController agit comme un middleware entre l'interface utilisateur et la base de données. Ses principales démarches comprennent :  

**Gestion des Requêtes Utilisateur** : La classe UserController intercepte les requêtes provenant de l'interface utilisateur. Elle valide et traite ces requêtes avant de les transmettre à la classe SQLManager pour exécution.  
**Traitement des Réponses de la BDD** : Après l'exécution des requêtes par la classe SQLManager, UserController traite les résultats et prépare les données pour une présentation adéquate à l'interface utilisateur.  
**Validation des Données** : La classe UserController inclut des mécanismes de validation pour s'assurer que les données soumises par l'utilisateur respectent les contraintes de la base de données, contribuant ainsi à maintenir l'intégrité des données.  

### 3. Classe CookieManager :  

La classe CookieManager du package ch.heigvd.utils.web offre des fonctionnalités de gestion des cookies pour les applications web basées sur Jakarta Servlet. Ses principales responsabilités incluent :  

**Authentification par Cookies** : Les méthodes mustLogin et isLogged permettent de déterminer si l'utilisateur doit se connecter ou s'il est déjà connecté en vérifiant la présence des cookies "username" et "password" dans la requête HTTP.
**Génération et Gestion des Cookies de Connexion** : Les méthodes setCookie sont responsables de la création de cookies de connexion avec le nom, la valeur, et la durée de vie spécifiés. Ces cookies sont utilisés pour indiquer l'état de connexion de l'utilisateur au serveur. La méthode deleteCookie permet de supprimer ces cookies lorsqu'ils ne sont plus nécessaires.
**Récupération de Cookies** : La méthode getCookie facilite la récupération d'un cookie spécifique à partir de la requête HTTP.

---

## Lancement de l'application
