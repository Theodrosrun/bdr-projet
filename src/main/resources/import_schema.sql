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
    visite_effectuee BOOLEAN DEFAULT FALSE
);

CREATE TABLE Compte (
    username VARCHAR(255) PRIMARY KEY,
    mot_de_passe VARCHAR(255) NOT NULL,
    moyen_paiement_pref_id INT,
    date_de_creation DATE NOT NULL DEFAULT CURRENT_DATE
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
    type_cours VARCHAR(255) NOT NULL,
    PRIMARY KEY (instructeur_id, type_cours)
);

CREATE TABLE Salle (
    -- car il y a des lettres dans les noms de salles
    salle_id VARCHAR(255) NOT NULL,
    fitness_id INT NOT NULL,
    capacite_max INT NOT NULL,
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
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    frequence_paiement INT NOT NULL DEFAULT 1 -- le nombre de mois pour payer
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
    type_abonnement VARCHAR(255) NOT NULL,
    disponibilite BOOLEAN DEFAULT TRUE,
    PRIMARY KEY(abo_id)
);

CREATE TABLE Facture (
    facture_id SERIAL,
    contrat_id INT NOT NULL,
    montant DECIMAL(8,2) NOT NULL,
    date_echeance DATE NOT NULL,
    payment_id INT DEFAULT NULL,
    PRIMARY KEY (facture_id, contrat_id)
);

CREATE TABLE Payement (
    payement_id SERIAL PRIMARY KEY,
    facture_id INT NOT NULL,
    contrat_id INT NOT NULL,
    date_payement DATE NOT NULL,
    moyen_paiement_id INT NOT NULL
);

CREATE TABLE MoyenPaiement (
    moyen_paiement_id SERIAL PRIMARY KEY,
    type_moyen_paiement VARCHAR(255) NOT NULL,
    compte_id VARCHAR(255) NOT NULL,
    info VARCHAR(255) NOT NULL
);

CREATE TABLE TypeMoyenPaiement (
    nom VARCHAR(255) PRIMARY KEY
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
ADD FOREIGN KEY (type_cours) REFERENCES TypeCours(nom);

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
ADD FOREIGN KEY (type_abonnement) REFERENCES TypeAbonnement(nom);

ALTER TABLE Facture
ADD FOREIGN KEY (contrat_id) REFERENCES Contrat(contrat_id);

ALTER TABLE Payement
ADD FOREIGN KEY (facture_id, contrat_id) REFERENCES Facture(facture_id, contrat_id);

ALTER TABLE Payement
ADD FOREIGN KEY (moyen_paiement_id) REFERENCES MoyenPaiement(moyen_paiement_id);

ALTER TABLE MoyenPaiement
ADD FOREIGN KEY (type_moyen_paiement) REFERENCES TypeMoyenPaiement(nom);

ALTER TABLE MoyenPaiement
ADD FOREIGN KEY (compte_id) REFERENCES Compte(username);

ALTER TABLE Progression
ADD FOREIGN KEY (membre_id) REFERENCES Membre(id);

ALTER TABLE Compte
ADD FOREIGN KEY (moyen_paiement_pref_id) REFERENCES MoyenPaiement(moyen_paiement_id);

----------------------------------------------

-- View

-- View All accounts combined with the types
DROP VIEW IF EXISTS AccountView;
CREATE VIEW AccountView
AS
SELECT
    c.username,
    c.mot_de_passe,
    c.moyen_paiement_pref_id,
    p.id,
    p.nom,
    p.prenom,
    p.dateNaissance,
    p.adresseMail,
    p.numeroTelephone,
    p.rue || ' ' || p.numero AS adresse,
    p.ville,
    p.NPA,
    p.pays,
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

-- à vérifer car j'ai modifié le sens de ce qui était écrit, je crois que c'était faux
-- View of all courses that are happening this week
-- Seen that our hypothetical fitness center does not have a registration system to courses a table 'CoursOccurrence' is not needed
-- But we need a view to see all the courses that are happening this week
DROP VIEW IF EXISTS CourseWeekView;
CREATE VIEW CourseWeekView AS
SELECT
    c.cours_id,
    CASE EXTRACT(DOW FROM (c.jour))
        WHEN 0 THEN 'dimanche'
        WHEN 1 THEN 'lundi'
        WHEN 2 THEN 'mardi'
        WHEN 3 THEN 'mercredi'
        WHEN 4 THEN 'jeudi'
        WHEN 5 THEN 'vendredi'
        WHEN 6 THEN 'samedi'
        END AS jour,
    c.heure,
    c.description,
    c.recurrence,
    c.instructeur_id,
    c.typecours,
    c.fitness_id,
    c.salle_id,
    c.abo_id
FROM Cours c
ORDER BY jour;

-- à vérifer car j'ai modifié le sens de ce qui était écrit, je crois que c'était faux
DROP VIEW IF EXISTS MemberCourseWeekView;
CREATE VIEW MemberCourseWeekView AS
SELECT
    c.cours_id,
    CASE EXTRACT(DOW FROM (c.jour))
        WHEN 0 THEN 'dimanche'
        WHEN 1 THEN 'lundi'
        WHEN 2 THEN 'mardi'
        WHEN 3 THEN 'mercredi'
        WHEN 4 THEN 'jeudi'
        WHEN 5 THEN 'vendredi'
        WHEN 6 THEN 'samedi'
        END AS jour,
    c.heure,
    c.description,
    c.recurrence,
    c.instructeur_id,
    c.typecours,
    c.fitness_id,
    c.salle_id,
    c.abo_id,
    m.id AS membre_id,
    m.compte_id
FROM Cours c
INNER JOIN ContratAbonnement ca ON c.abo_id = ca.abo_id
INNER JOIN Contrat co ON ca.contrat_id = co.contrat_id
INNER JOIN Membre m ON co.membre_id = m.id
ORDER BY jour;

-- View of members and their contracts and memberships
DROP VIEW IF EXISTS MembreAbonnementView;
CREATE VIEW MembreAbonnementView AS
SELECT
    m.id AS membre_id,
    m.compte_id,
    c.contrat_id,
    c.date_debut,
    c.date_fin,
    c.frequence_paiement,
    a.abo_id,
    a.prix,
    a.type_abonnement
    FROM Membre m
INNER JOIN Contrat c ON m.id = c.membre_id
INNER JOIN ContratAbonnement ca ON c.contrat_id = ca.contrat_id
INNER JOIN Abonnement a ON ca.abo_id = a.abo_id;

-- View instructors and their courses
DROP VIEW IF EXISTS InstructeurCoursView;
CREATE VIEW InstructeurCoursView AS
SELECT
    i.instructeur_id,
    c.cours_id,
    c.jour,
    c.heure,
    c.duree,
    c.description,
    c.recurrence,
    c.typeCours,
    c.fitness_id,
    c.salle_id,
    c.abo_id
FROM Instructeur i
INNER JOIN Cours c ON i.instructeur_id = c.instructeur_id;


DROP VIEW IF EXISTS MembreFactureView;
CREATE VIEW MembreFactureView AS
SELECT
    m.id AS membre_id,
    m.compte_id,
    ab.abo_id,
    c.contrat_id,
    f.facture_id,
    f.montant,
    f.date_echeance,
    f.payment_id
FROM Membre m
INNER JOIN Contrat c ON m.id = c.membre_id
INNER JOIN ContratAbonnement a ON c.contrat_id = a.contrat_id
INNER JOIN Abonnement ab ON a.abo_id = ab.abo_id
INNER JOIN Facture f ON c.contrat_id = f.contrat_id
ORDER BY f.date_echeance;

DROP VIEW IF EXISTS IntructeurTypeCoursView;
CREATE VIEW IntructeurTypeCoursView AS
SELECT
    p.prenom || ' ' || p.nom AS instructeur,
    c.username AS compte,
    itc.type_cours
FROM Instructeur i
INNER JOIN InstructeurTypeCours itc ON i.instructeur_id = itc.instructeur_id
INNER JOIN Personne p ON i.instructeur_id = p.id
INNER JOIN Employe e ON p.id = e.id
INNER JOIN Compte c ON e.compte_id = c.username;




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
    INSERT INTO Compte (username, mot_de_passe, date_de_creation)
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

CREATE OR REPLACE FUNCTION create_factures()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
DECLARE
    i INT := 0;
    frequence INT := (SELECT frequence_paiement FROM Contrat WHERE contrat_id = NEW.contrat_id);
    dateDebut DATE := (SELECT date_debut FROM Contrat WHERE contrat_id = NEW.contrat_id);
    abo_prix DECIMAL(8,2) := (SELECT prix FROM Abonnement WHERE abo_id = NEW.abo_id);
    montant_facture DECIMAL(8,2) := FLOOR((abo_prix / frequence) / 0.05) * 0.05;
    BEGIN
        WHILE i < frequence LOOP
            INSERT INTO Facture (contrat_id, montant, date_echeance)
            VALUES (NEW.contrat_id, montant_facture, dateDebut + (interval '1 month' * i));
            i := i + 1;
        END LOOP;
        RETURN NEW;
    END;
$$;

CREATE TRIGGER create_factures_trigger
    AFTER INSERT ON ContratAbonnement
    FOR EACH ROW
EXECUTE FUNCTION create_factures();



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
    VALUES (CASE WHEN entity_type = 'Membre' THEN OLD.id ELSE OLD.id END, entity_type, CURRENT_TIMESTAMP);

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
-- 4. A chaque fois qu'il y a une entité avec debut et fin, on doit vérifier que la date de fin est après la date de début
--- Vues:
-- 1. Le nombre de personnes par heure dans le fitness (à faire avec les passages)

--- TODO: Ajouter comme contrainte dans l'UML: Un employé doit avoir un moyen de paiement préféré (type et info)
