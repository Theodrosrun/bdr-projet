SET search_path = my_amazing_fitness;

INSERT INTO MyAmazingFitness (fitness_id, numero, rue, ville, NPA)
VALUES (1, 123, 'Rue de la Paix', 'Villeville', 12345);

INSERT INTO Personne (id, nom, prenom, dateNaissance, adresseMail, numeroTelephone, numero, rue, ville, NPA, pays)
VALUES (1, 'Doe', 'John', '1990-05-20', 'john.doe@example.com', '+123456789', 123, 'Rue des Lilas', 'Villefort', 54321, 'Paysland'),
       (2, 'Smith', 'Emma', '1985-09-12', 'emma.smith@example.com', '+987654321', 456, 'Avenue des Roses', 'Villeneuve', 98765, 'Terreville'),
       (3, 'Garcia', 'Sophie', '1992-12-04', 'sophie.garcia@example.com', '+1122334455', 789, 'Rue du Paradis', 'Edenville', 13579, 'Nuageland'),
       (4, 'Martin', 'Lucas', '1988-07-18', 'lucas.martin@example.com', '+9988776655', 111, 'Avenue des Bois', 'Forêtville', 24680, 'Vertland'),
       (5, 'Lopez', 'Eva', '1995-03-27', 'eva.lopez@example.com', '+6677889900', 222, 'Chemin des Vignes', 'Vinbourg', 75319, 'Vinland'),
       (6, 'Brown', 'Oliver', '1999-10-10', 'oliver.brown@example.com', '+1122998877', 333, 'Rue des Moulins', 'Moulinville', 46802, 'Moulinland'),
       (7, 'Lee', 'Sophia', '1994-01-15', 'sophia.lee@example.com', '+8877665544', 444, 'Avenue des Oiseaux', 'Aviary', 14703, 'Skyland'),
       (8, 'Garcia', 'Hugo', '1980-11-30', 'hugo.garcia@example.com', '+1122445566', 555, 'Boulevard des Neiges', 'Gelville', 36912, 'Glaceland'),
       (9, 'Roberts', 'Chloe', '1997-08-08', 'chloe.roberts@example.com', '+3366998855', 666, 'Rue du Château', 'Châteauville', 98765, 'Castleland'),
       (10, 'Nguyen', 'Thomas', '1993-06-22', 'thomas.nguyen@example.com', '+4477993366', 777, 'Place du Marché', 'Marchébourg', 77550, 'Marketland'),
       (11, 'Baker', 'Sophie', '1991-04-17', 'sophie.baker@example.com', '+4477112233', 888, 'Avenue de la Plage', 'Plageville', 12345, 'Beachland'),
       (12, 'Kumar', 'Raj', '1983-12-01', 'raj.kumar@example.com', '+1122003344', 999, 'Rue du Soleil', 'Soleilbourg', 45678, 'Sunnyland'),
       (13, 'Andersen', 'Emma', '1998-09-29', 'emma.andersen@example.com', '+3344556677', 101, 'Chemin du Lac', 'Lacville', 78901, 'Lakeville'),
       (14, 'Gomez', 'Diego', '1996-05-12', 'diego.gomez@example.com', '+1122334455', 202, 'Avenue des Montagnes', 'Montville', 23456, 'Mountainland'),
       (15, 'Harris', 'Lily', '1986-02-23', 'lily.harris@example.com', '+9988776655', 303, 'Rue des Roses', 'Roseville', 56789, 'Roseland'),
       (16, 'Müller', 'Max', '1994-11-05', 'max.muller@example.com', '+6677889900', 404, 'Chemin de la Prairie', 'Prébourg', 34567, 'Meadowland'),
       (17, 'Smith', 'Michael', '1990-07-04', 'michael.smith@example.com', '+1122998877', 505, 'Avenue des Arbres', 'Arbreville', 67890, 'Treeland'),
       (18, 'Garcia', 'Julia', '1999-03-18', 'julia.garcia@example.com', '+8877665544', 606, 'Rue des Champs', 'Champbourg', 98765, 'Fieldland'),
       (19, 'Brown', 'Daniel', '1989-10-30', 'daniel.brown@example.com', '+1122445566', 707, 'Boulevard de la Rivière', 'Rivièrebourg', 43210, 'Riverland'),
       (20, 'Jones', 'Sophia', '1995-08-15', 'sophia.jones@example.com', '+3366998855', 808, 'Place de Lavoie', 'Égliseville', 13579, 'Churchland');

INSERT INTO Visiteur (visiteur_id, fitness_id, visiteEffectuee)
VALUES (1, 1, true),
       (2, 1, false),
       (3, 1, true),
       (4, 1, false),
       (5, 1, true);

INSERT INTO Compte (username, motDePasse, dateDeCreation, IBAN)
VALUES ('employe1_username', 'password1', '2023-01-01', 'IBAN123'),
       ('employe2_username', 'password2', '2023-01-02', 'IBAN456'),
       ('employe3_username', 'password3', '2023-01-03', 'IBAN789'),
       ('employe4_username', 'password4', '2023-01-04', 'IBAN101'),
       ('employe5_username', 'password5', '2023-01-05', 'IBAN112'),
       ('employe6_username', 'password6', '2023-01-06', 'IBAN113'),
       ('employe7_username', 'password7', '2023-01-07', 'IBAN114'),
       ('employe8_username', 'password8', '2023-01-08', 'IBAN115'),
       ('employe9_username', 'password9', '2023-01-09', 'IBAN116'),
       ('employe10_username', 'password10', '2023-01-10', 'IBAN117');

INSERT INTO Employe (employe_id, fitness_id, compte_id, salaire)
VALUES (11, 1, 'employe1_username', 5000.00),
       (12, 1, 'employe2_username', 4800.00),
       (13, 1, 'employe3_username', 5200.00),
       (14, 1, 'employe4_username', 5100.00),
       (15, 1, 'employe5_username', 4900.00);

INSERT INTO Administrateur (administrateur_id)
VALUES (13); -- Remplacez par l'ID de l'administrateur souhaité

INSERT INTO PersonnelAdministratif (padministratif_id)
VALUES (14),(15); -- Remplacez par l'ID du personnel administratif souhaité

INSERT INTO Membre (membre_id, compte_id)
VALUES (6,  'employe6_username'),
       (7,  'employe7_username'),
       (8,  'employe8_username'),
       (9,  'employe9_username'),
       (10, 'employe10_username');

-- Création de la séquence pour générer des valeurs uniques pour passage_id
DROP SEQUENCE IF EXISTS passage_id_sequence;
CREATE SEQUENCE passage_id_sequence START 1;
-- Insertions dans la table Passage basées sur les conditions de visiteEffectuee dans la table Visiteur
INSERT INTO Passage (passage_id, membre_id, fitness_id, timestamp)
SELECT nextval('passage_id_sequence'), m.membre_id, 1, CURRENT_TIMESTAMP
FROM Membre m;

INSERT INTO Instructeur (instructeur_id)
VALUES (11);

INSERT INTO TypeCours (nom)
VALUES ('Yoga'),
       ('Pilates'),
       ('Cardio');

INSERT INTO Salle (salle_id, fitness_id, capaciteMax, surface)
VALUES
    ('Salle A', 1, 20, '30m²'),
    ('Salle B', 1, 15, '25m²'),
    ('Salle C', 1, 25, '40m²');

INSERT INTO Abonnement (abo_id, prix, disponibilite)
VALUES
    ('Basic', 150.00, true),
    ('Intermediate', 50, true),
    ('Advanced', 50.00, true);

INSERT INTO Cours (cours_id, jour, heure, description, recurrence, instructeur_id, typeCours, fitness_id, salle_id, abo_id)
VALUES
    (1, '2023-12-01', '09:00:00', 'Yoga class', 7, 11, 'Yoga', 1, 'Salle A', 'Basic'),
    (2, '2023-12-02', '14:00:00', 'Pilates session', 7, 11, 'Pilates', 1, 'Salle B', 'Intermediate'),
    (3, '2023-12-03', '10:00:00', 'Cardio workout', 7, 11, 'Cardio', 1, 'Salle C', 'Advanced');

INSERT INTO TypeMachine (type_machine)
VALUES
    ('Tapis de course'),
    ('Vélo elliptique'),
    ('Rameur');

INSERT INTO Machine (machine_id, fitness_id, salle_id, etat, type_machine)
VALUES
    (1, 1, 'Salle A', 'neuf', 'Tapis de course'),
    (2, 1, 'Salle B', 'usagé', 'Vélo elliptique'),
    (3, 1, 'Salle C', 'abîmé', 'Rameur');

INSERT INTO Progression (progression_id, date, poids, membre_id)
VALUES
    (1, '2023-12-01', 75.5, 6),
    (2, '2023-12-02', 72.3, 7),
    (3, '2023-12-03', 80.0, 8);

INSERT INTO InstructeurTypeCours (instructeur_id, typeCours)
VALUES
    (11, 'Yoga'),
    (11, 'Pilates'),
    (11, 'Cardio');

INSERT INTO Contrat (contrat_id, membre_id, dateDebut, dateFin, frequencePaiement)
VALUES
    (1, 6, '2023-01-01', '2023-12-31', 1),
    (2, 7, '2023-02-15', '2023-12-15', 1),
    (3, 8, '2023-03-10', '2023-09-10', 3);

INSERT INTO ContratAbonnement (contrat_id, abo_id)
VALUES
    (1, 'Basic'),
    (2, 'Intermediate'),
    (3, 'Advanced');

INSERT INTO Facture (facture_id, contrat_id, montant, dateEcheance, datePaiement, moyenPaiement)
VALUES
    (1, 1, 500.00, '2024-02-01', '2023-02-15', 'Carte bancaire'),
    (2, 2, 300.00, '2024-03-01', '2023-03-15', 'Virement'),
    (3, 3, 200.00, '2024-04-01', NULL, 'Chèque');
