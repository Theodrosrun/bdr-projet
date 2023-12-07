-- Note: Most of VARCHAR(255) are not necessary, we will see if we can reduce them later
DROP SCHEMA IF EXISTS my_amazing_fitness CASCADE;
CREATE SCHEMA my_amazing_fitness;
COMMENT ON SCHEMA my_amazing_fitness IS 'my_amazing_fitness database';
SET search_path = my_amazing_fitness;

----------------------------------------------

CREATE TABLE MyAmazingFitness (
    fitness_id INT PRIMARY KEY,
    numero INT NOT NULL,
    rue VARCHAR(255) NOT NULL,
    ville VARCHAR(255) NOT NULL,
    NPA INT NOT NULL
);

----------------------------------------------

CREATE TABLE Personne (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    dateNaissance DATE NOT NULL,
    adresseMail VARCHAR(255) NOT NULL,
    numeroTelephone VARCHAR(255),
    numero INT NOT NULL,
    rue VARCHAR(255) NOT NULL,
    ville VARCHAR(255) NOT NULL,
    NPA INT NOT NULL,
    pays VARCHAR(255) NOT NULL
);


CREATE TABLE Employe (
    employe_id INT PRIMARY KEY,
    fitness_id INT NOT NULL,
    compte_id VARCHAR(255) NOT NULL UNIQUE,
    salaire DECIMAL(8,2) NOT NULL
);

CREATE TABLE PersonnelAdministratif (
    padministratif_id INT PRIMARY KEY
);

CREATE TABLE Administrateur (
    administrateur_id INT PRIMARY KEY
);

CREATE TABLE Membre (
    membre_id INT PRIMARY KEY,
    compte_id VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE Visiteur (
    visiteur_id INT PRIMARY KEY,
    fitness_id INT NOT NULL,
    visiteEffectuee BOOLEAN DEFAULT FALSE
);

----------------------------------------------

CREATE TABLE Compte (
    username VARCHAR(255) PRIMARY KEY,
    motDePasse VARCHAR(255) NOT NULL,
    dateDeCreation DATE NOT NULL,
    IBAN VARCHAR(255)
);

----------------------------------------------

CREATE TABLE Passage (
    passage_id INT PRIMARY KEY,
    membre_id INT NOT NULL,
    fitness_id INT NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE Instructeur (
    instructeur_id INT PRIMARY KEY
);

CREATE TABLE Cours (
    cours_id INT PRIMARY KEY,
    jour DATE NOT NULL,
    heure TIME NOT NULL,
    description VARCHAR(255),
    recurrence INT NOT NULL,
    instructeur_id INT NOT NULL,
    typeCours VARCHAR(255) NOT NULL,
    fitness_id INT NOT NULL,
    salle_id VARCHAR(255) NOT NULL,
    abo_id VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE TypeCours (
    nom VARCHAR(255) PRIMARY KEY
);

-- Relation M:N --> il faut créer une table associative
CREATE TABLE InstructeurTypeCours (
    instructeur_id INT NOT NULL,
    typeCours VARCHAR(255) NOT NULL,
    PRIMARY KEY (instructeur_id, typeCours)
);

----------------------------------------------

CREATE TABLE Salle (
    -- car il y a des lettres dans les noms de salles
    salle_id VARCHAR(255) NOT NULL,
    fitness_id INT NOT NULL,
    capaciteMax INT NOT NULL,
    surface VARCHAR(255),
    PRIMARY KEY(salle_id, fitness_id)
);


CREATE TABLE Machine (
    machine_id INT PRIMARY KEY,
    fitness_id INT NOT NULL,
    salle_id VARCHAR(255),
    etat VARCHAR(255), -- neuf, usagé, abîmé, endommagé
    type_machine VARCHAR(255) NOT NULL
);


CREATE TABLE TypeMachine (
    type_machine VARCHAR(255) PRIMARY KEY
);

----------------------------------------------

CREATE TABLE Contrat (
    contrat_id INT PRIMARY KEY,
    membre_id INT NOT NULL,
    dateDebut DATE NOT NULL,
    dateFin DATE NOT NULL,
    frequencePaiement INT NOT NULL DEFAULT 1 -- le nombre de mois pour payer
);

-- Relation M:N --> il faut créer une table associative
CREATE TABLE ContratAbonnement (
    contrat_id INT NOT NULL,
    abo_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (contrat_id, abo_id)
);

CREATE TABLE Abonnement (
    abo_id VARCHAR(255),
    prix DECIMAL(4,2) NOT NULL,
    disponibilite BOOLEAN DEFAULT TRUE,
    PRIMARY KEY(abo_id)
);

CREATE TABLE Facture (
    facture_id INT NOT NULL,
    contrat_id INT NOT NULL,
    montant DECIMAL(6,2) NOT NULL,
    dateEcheance DATE NOT NULL,
    datePaiement DATE NOT NULL,
    moyenPaiement VARCHAR(255) NOT NULL,
    PRIMARY KEY (facture_id, contrat_id)
);

----------------------------------------------

CREATE TABLE Progression (
    progression_id INT NOT NULL PRIMARY KEY,
    date DATE,
    poids NUMERIC(5, 2),
    membre_id INT NOT NULL
);

--- Employe ---

ALTER TABLE Employe
ADD FOREIGN KEY (employe_id) REFERENCES Personne(id);

ALTER TABLE Employe
ADD FOREIGN KEY (fitness_id) REFERENCES MyAmazingFitness(fitness_id);

ALTER TABLE Employe
ADD FOREIGN KEY (compte_id) REFERENCES Compte(username);

--- PersonnelAdministratif ---

ALTER TABLE PersonnelAdministratif
ADD FOREIGN KEY (padministratif_id) REFERENCES Employe(employe_id);

--- Administratif ---

ALTER TABLE Administrateur
ADD FOREIGN KEY (administrateur_id) REFERENCES Employe(employe_id);

--- Membre ---

ALTER TABLE Membre
ADD FOREIGN KEY (membre_id) REFERENCES Personne(id);

ALTER TABLE Membre
ADD FOREIGN KEY (compte_id) REFERENCES Compte(username);

--- Visiteur ---

ALTER TABLE Visiteur
ADD FOREIGN KEY (visiteur_id) REFERENCES Personne(id);

ALTER TABLE Visiteur
ADD FOREIGN KEY (fitness_id) REFERENCES MyAmazingFitness(fitness_id);

--- Passage ---

ALTER TABLE Passage
ADD FOREIGN KEY (membre_id) REFERENCES Membre(membre_id);

ALTER TABLE Passage
ADD FOREIGN KEY (fitness_id) REFERENCES MyAmazingFitness(fitness_id);

--- Instructeur ---

ALTER TABLE Instructeur
ADD FOREIGN KEY (instructeur_id) REFERENCES Employe(employe_id);

--- Cours ---

ALTER TABLE Cours
ADD FOREIGN KEY (instructeur_id) REFERENCES Instructeur(instructeur_id);

ALTER TABLE Cours
ADD FOREIGN KEY (typeCours) REFERENCES TypeCours(nom);

ALTER TABLE Cours
ADD FOREIGN KEY (fitness_id, salle_id) REFERENCES Salle(fitness_id, salle_id);

ALTER TABLE Cours
ADD FOREIGN KEY (abo_id) REFERENCES Abonnement(abo_id);

--- InstructeurTypeCours ---

ALTER TABLE InstructeurTypeCours
ADD FOREIGN KEY (instructeur_id) REFERENCES Instructeur(instructeur_id);

ALTER TABLE InstructeurTypeCours
ADD FOREIGN KEY (typeCours) REFERENCES TypeCours(nom);

--- Salle ---

ALTER TABLE Salle
ADD FOREIGN KEY (fitness_id) REFERENCES MyAmazingFitness(fitness_id);

--- Machine ---

ALTER TABLE Machine
ADD FOREIGN KEY (fitness_id, salle_id) REFERENCES Salle(fitness_id, salle_id);

ALTER TABLE Machine
ADD FOREIGN KEY (type_machine) REFERENCES TypeMachine(type_machine);

--- Contrat ---

ALTER TABLE Contrat
ADD FOREIGN KEY (membre_id) REFERENCES Membre(membre_id);

--- ContratAbonnement ---

ALTER TABLE ContratAbonnement
ADD FOREIGN KEY (contrat_id) REFERENCES Contrat(contrat_id);

ALTER TABLE ContratAbonnement
ADD FOREIGN KEY (abo_id) REFERENCES Abonnement(abo_id);

--- Facture ---

ALTER TABLE Facture
ADD FOREIGN KEY (contrat_id) REFERENCES Contrat(contrat_id);

--- Progression ---

ALTER TABLE Progression
ADD FOREIGN KEY (membre_id) REFERENCES Membre(membre_id);