DROP SCHEMA IF EXISTS my_amazing_fitness CASCADE;
CREATE SCHEMA my_amazing_fitness;
COMMENT ON SCHEMA my_amazing_fitness IS 'my_amazing_fitness database';
SET search_path = my_amazing_fitness;

----------------------------------------------

-- Table

CREATE TABLE MyAmazingFitness (
    fitness_id INT PRIMARY KEY,
    numero INT NOT NULL,
    rue VARCHAR(255) NOT NULL,
    ville VARCHAR(255) NOT NULL,
    NPA INT NOT NULL
);

CREATE TABLE Personne (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    dateNaissance DATE NOT NULL,
    adresseMail VARCHAR(255) NOT NULL,
    numeroTelephone VARCHAR(255),
    IBAN VARCHAR(255),
    numero INT NOT NULL,
    rue VARCHAR(255) NOT NULL,
    ville VARCHAR(255) NOT NULL,
    NPA INT NOT NULL,
    pays VARCHAR(255) NOT NULL
);

CREATE TABLE Employe (
    id INT PRIMARY KEY,
    fitness_id INT NOT NULL,
    compte_id VARCHAR(255) UNIQUE, -- Trigger will create the account and set the compte_id and not null
    salaire DECIMAL(8,2) NOT NULL
);

CREATE TABLE PersonnelAdministratif (
    padministratif_id INT PRIMARY KEY
);

CREATE TABLE Administrateur (
    administrateur_id INT PRIMARY KEY
);

CREATE TABLE Membre (
    id INT PRIMARY KEY,
    compte_id VARCHAR(255) UNIQUE -- Trigger will create the account and set the compte_id and not null
);

CREATE TABLE Visiteur (
    visiteur_id INT PRIMARY KEY,
    fitness_id INT NOT NULL,
    visiteEffectuee BOOLEAN DEFAULT FALSE
);

CREATE TABLE Compte (
    username VARCHAR(255) PRIMARY KEY,
    motDePasse VARCHAR(255) NOT NULL,
    dateDeCreation DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE Passage (
    passage_id SERIAL PRIMARY KEY,
    membre_id INT NOT NULL,
    fitness_id INT NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Instructeur (
    instructeur_id INT PRIMARY KEY
);

CREATE TABLE Cours (
    cours_id SERIAL PRIMARY KEY,
    jour DATE NOT NULL,
    heure TIME NOT NULL,
    duree INT NOT NULL,
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

CREATE TABLE Salle (
    -- car il y a des lettres dans les noms de salles
    salle_id VARCHAR(255) NOT NULL,
    fitness_id INT NOT NULL,
    capaciteMax INT NOT NULL,
    surface VARCHAR(255),
    PRIMARY KEY(salle_id, fitness_id)
);

CREATE TABLE Machine (
    machine_id SERIAL PRIMARY KEY,
    fitness_id INT NOT NULL,
    salle_id VARCHAR(255),
    etat VARCHAR(255), -- neuf, usagé, abîmé, endommagé
    type_machine VARCHAR(255) NOT NULL
);

CREATE TABLE TypeMachine (
    type_machine VARCHAR(255) PRIMARY KEY
);

CREATE TABLE Contrat (
    contrat_id SERIAL PRIMARY KEY,
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

CREATE TABLE TypeAbonnement (
    nom VARCHAR(255) PRIMARY KEY
);

CREATE TABLE Abonnement (
    abo_id VARCHAR(255),
    prix DECIMAL(6,2) NOT NULL,
    typeAbonnement VARCHAR(255) NOT NULL,
    disponibilite BOOLEAN DEFAULT TRUE,
    PRIMARY KEY(abo_id)
);

CREATE TABLE Facture (
    facture_id INT NOT NULL,
    contrat_id INT NOT NULL,
    montant DECIMAL(8,2) NOT NULL,
    dateEcheance DATE NOT NULL,
    datePaiement DATE,
    moyenPaiement VARCHAR(255) NOT NULL,
    PRIMARY KEY (facture_id, contrat_id)
);

CREATE TABLE Progression (
    progression_id SERIAL PRIMARY KEY,
    date DATE,
    poids NUMERIC(4, 1),
    membre_id INT NOT NULL
);

CREATE TABLE SuppressionLog (
    user_id INT,
    type VARCHAR(255),
    date_suppression TIMESTAMP
);

----------------------------------------------

-- Foreign key

ALTER TABLE Employe
ADD FOREIGN KEY (id) REFERENCES Personne(id);

ALTER TABLE Employe
ADD FOREIGN KEY (fitness_id) REFERENCES MyAmazingFitness(fitness_id);

ALTER TABLE Employe
ADD FOREIGN KEY (compte_id) REFERENCES Compte(username);

ALTER TABLE PersonnelAdministratif
ADD FOREIGN KEY (padministratif_id) REFERENCES Employe(id);

ALTER TABLE Administrateur
ADD FOREIGN KEY (administrateur_id) REFERENCES Employe(id);

ALTER TABLE Membre
ADD FOREIGN KEY (id) REFERENCES Personne(id);

ALTER TABLE Membre
ADD FOREIGN KEY (compte_id) REFERENCES Compte(username);

ALTER TABLE Visiteur
ADD FOREIGN KEY (visiteur_id) REFERENCES Personne(id);

ALTER TABLE Visiteur
ADD FOREIGN KEY (fitness_id) REFERENCES MyAmazingFitness(fitness_id);

ALTER TABLE Passage
ADD FOREIGN KEY (membre_id) REFERENCES Membre(id);

ALTER TABLE Passage
ADD FOREIGN KEY (fitness_id) REFERENCES MyAmazingFitness(fitness_id);

ALTER TABLE Instructeur
ADD FOREIGN KEY (instructeur_id) REFERENCES Employe(id);

ALTER TABLE Cours
ADD FOREIGN KEY (instructeur_id) REFERENCES Instructeur(instructeur_id);

ALTER TABLE Cours
ADD FOREIGN KEY (typeCours) REFERENCES TypeCours(nom);

ALTER TABLE Cours
ADD FOREIGN KEY (fitness_id, salle_id) REFERENCES Salle(fitness_id, salle_id);

ALTER TABLE Cours
ADD FOREIGN KEY (abo_id) REFERENCES Abonnement(abo_id);

ALTER TABLE InstructeurTypeCours
ADD FOREIGN KEY (instructeur_id) REFERENCES Instructeur(instructeur_id);

ALTER TABLE InstructeurTypeCours
ADD FOREIGN KEY (typeCours) REFERENCES TypeCours(nom);

ALTER TABLE Salle
ADD FOREIGN KEY (fitness_id) REFERENCES MyAmazingFitness(fitness_id);

ALTER TABLE Machine
ADD FOREIGN KEY (fitness_id, salle_id) REFERENCES Salle(fitness_id, salle_id);

ALTER TABLE Machine
ADD FOREIGN KEY (type_machine) REFERENCES TypeMachine(type_machine);

ALTER TABLE Contrat
ADD FOREIGN KEY (membre_id) REFERENCES Membre(id);

ALTER TABLE ContratAbonnement
ADD FOREIGN KEY (contrat_id) REFERENCES Contrat(contrat_id);

ALTER TABLE ContratAbonnement
ADD FOREIGN KEY (abo_id) REFERENCES Abonnement(abo_id);

ALTER TABLE Abonnement
ADD FOREIGN KEY (typeAbonnement) REFERENCES TypeAbonnement(nom);

ALTER TABLE Facture
ADD FOREIGN KEY (contrat_id) REFERENCES Contrat(contrat_id);

ALTER TABLE Progression
ADD FOREIGN KEY (membre_id) REFERENCES Membre(id);

----------------------------------------------

-- View

-- View All accounts with their type
DROP VIEW IF EXISTS AccountView;
CREATE VIEW AccountView
AS
SELECT
    c.username,
    c.motDePasse,
    p.id,
    CASE
        WHEN m.id IS NOT NULL THEN 'Membre'
        WHEN pa.padministratif_id IS NOT NULL THEN 'PersonnelAdministratif'
        WHEN a.administrateur_id IS NOT NULL THEN 'Administrateur'
        WHEN i.instructeur_id IS NOT NULL THEN 'Instructeur'
        WHEN e.id IS NOT NULL THEN 'Employe'
    END AS userType
    FROM Compte c
LEFT JOIN Membre m ON c.username = m.compte_id
LEFT JOIN Employe e ON c.username = e.compte_id
LEFT JOIN PersonnelAdministratif pa ON e.id = pa.padministratif_id
LEFT JOIN Administrateur a ON e.id = a.administrateur_id
LEFT JOIN Instructeur i ON e.id = i.instructeur_id
INNER JOIN Personne p ON m.id = p.id OR e.id = p.id;

-- View of all courses that are happening this week
-- Seen that our hypothetical fitness center does not have a registration system to courses a table 'CoursOccurrence' is not needed
-- But we need a view to see all the courses that are happening this week
DROP VIEW IF EXISTS CourseWeekView;
CREATE VIEW CourseWeekView AS
SELECT
    c.cours_id,
    DATE(c.jour + (interval '7 days' * ((current_date - c.jour) / c.recurrence))) AS jour,
    c.heure,
    c.description,
    c.recurrence,
    c.instructeur_id,
    c.typecours,
    c.fitness_id,
    c.salle_id,
    c.abo_id
FROM Cours c
WHERE c.jour + (interval '7 days' * ((current_date - c.jour) / c.recurrence)) <= current_date
  AND c.jour + (interval '7 days' * (((current_date - c.jour) / c.recurrence) + 1)) > current_date
ORDER BY c.cours_id;

-- View of members and their contracts and memberships

DROP VIEW IF EXISTS MembreAbonnementView;
CREATE VIEW MembreAbonnementView AS
SELECT
    m.id AS membre_id,
    m.compte_id,
    c.contrat_id,
    c.dateDebut,
    c.dateFin,
    c.frequencePaiement,
    a.abo_id,
    a.prix,
    a.typeAbonnement
    FROM Membre m
INNER JOIN Contrat c ON m.id = c.membre_id
INNER JOIN ContratAbonnement ca ON c.contrat_id = ca.contrat_id
INNER JOIN Abonnement a ON ca.abo_id = a.abo_id;


DROP VIEW IF EXISTS VueFacturesPayees;
CREATE VIEW VueFacturesPayees
AS
SELECT c.membre_id,
       SUM(f.montant) AS montant_total_factures,
       SUM(CASE WHEN f.datePaiement IS NOT NULL THEN f.montant
           ELSE 0 END) AS montant_paye,
       CASE WHEN SUM(CASE WHEN f.datePaiement IS NOT NULL THEN f.montant
           ELSE 0 END) = SUM(f.montant) THEN true ELSE false END AS toutes_factures_payees
FROM contrat c
LEFT JOIN facture f ON c.contrat_id = f.contrat_id
GROUP BY c.membre_id;

SELECT * FROM VueFacturesPayees;


CREATE VIEW VueMontantPaye AS
SELECT c.membre_id,
       SUM(CASE WHEN f.datePaiement IS NOT NULL THEN f.montant ELSE 0 END) AS montant_paye
FROM Contrat c
LEFT JOIN Facture f ON c.contrat_id = f.contrat_id
GROUP BY c.membre_id;


----------------------------------------------

-- Trigger

-- Trigger creation of a new account when a new member or employee is created
CREATE OR REPLACE FUNCTION create_account()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
DECLARE
    username_suffix INTEGER := 1;
    new_username TEXT;
    new_compte_id TEXT;
    personne_nom TEXT;
    personne_prenom TEXT;
BEGIN
    SELECT nom, prenom INTO personne_nom, personne_prenom FROM Personne WHERE id = NEW.id;
    new_username := lower(CONCAT(personne_nom, '_', personne_prenom));
    WHILE EXISTS (SELECT 1 FROM Compte WHERE username = new_username) LOOP
            new_username := lower(CONCAT(personne_nom, '_', personne_prenom, username_suffix));
            username_suffix := username_suffix + 1;
    END LOOP;
    INSERT INTO Compte (username, motDePasse, dateDeCreation)
    VALUES (new_username, new_username, CURRENT_DATE) -- Password is the same as the username
    RETURNING new_username INTO new_compte_id;
    NEW.compte_id := new_compte_id;
    RETURN NEW;
END;
$$;

CREATE TRIGGER create_account_trigger_membre
    BEFORE INSERT ON Membre
    FOR EACH ROW
EXECUTE FUNCTION create_account();

CREATE TRIGGER create_account_trigger_employe
    BEFORE INSERT ON Employe
    FOR EACH ROW
EXECUTE FUNCTION create_account();



CREATE OR REPLACE FUNCTION log_suppression()
RETURNS TRIGGER AS
$$
DECLARE
    entity_type TEXT;
BEGIN
    IF TG_TABLE_NAME = 'Membre' THEN  entity_type = 'Membre';
    ELSIF TG_TABLE_NAME = 'Employe' THEN entity_type = 'Employe';
    END IF;

    INSERT INTO SuppressionLog (user_id, type, date_suppression)
    VALUES (CASE WHEN entity_type = 'Membre' THEN OLD.membre_id ELSE OLD.employe_id END, entity_type, CURRENT_TIMESTAMP);

    RETURN OLD;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER suppression_trigger_membre AFTER DELETE
ON Membre
FOR EACH ROW
EXECUTE FUNCTION log_suppression();

CREATE TRIGGER suppression_trigger_employe AFTER DELETE
ON Employe
FOR EACH ROW
EXECUTE FUNCTION log_suppression();

--- Deletion:
-- 1. Check les cascades
-- 2. We cannot delete a course if there are people registered to it. A view to check the number of people registered to a course and a trigger to check if the number is 0 before deleting the course.
--- Triggers to add:
-- 1. L'instructeur doit être expert dans le type de cours qu'il donne
-- 2. Lorsqu'on ajoute un membre ou un employé, on doit ajouter un compte avec un username et un mot de passe nomfamile_prenom
-- 3. Lorsqu'on crée un contrat, on doit créer le nombre de factures correspondant à la fréquence de paiement
--- Vues:
-- 1. Le nombre de personnes par heure dans le fitness (à faire avec les passages)
