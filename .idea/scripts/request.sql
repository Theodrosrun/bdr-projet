SET search_path = my_amazing_fitness;

-- Sélectionne le nom et le prénom des employés dont le nom de famille est 'Baker'
SELECT P.nom,
       P.prenom
FROM Employe E
    JOIN Personne P ON E.employe_id = P.id
WHERE P.nom = 'Baker';

-- Sélectionne tous les détails des employés dont le salaire est supérieur à 5000
SELECT *
FROM Employe
WHERE salaire > 5000;

-- Sélectionne tous les détails des cours qui se déroulent le 1er décembre 2023
SELECT *
FROM Cours
WHERE jour = '2023-12-01';

-- Sélectionne tous les détails des abonnements dont le prix est inférieur à 50
SELECT *
FROM Abonnement
WHERE prix < 50;

-- Sélectionne tous les détails des factures dont le montant est supérieur à 50
SELECT *
FROM facture
WHERE montant > 50;

-- Sélectionne la description, l'heure, le jour des cours et le prénom de l'instructeur pour chaque cours
SELECT C.description,
       C.heure,
       C.jour,
       P.prenom
FROM Cours C
    JOIN Instructeur I ON C.instructeur_id = I.instructeur_id
    JOIN Personne P ON P.id = I.instructeur_id;

-- Sélectionne tous les détails des progressions pour le membre avec l'identifiant 6
SELECT *
FROM Progression
WHERE membre_id = 6;

-- Début d'une transaction pour mettre à jour les informations de contrat et de facture
BEGIN;
-- Met à jour la fréquence de paiement du contrat avec l'identifiant 1
UPDATE Contrat
SET frequencePaiement = 2 -- Nouvelle fréquence de paiement
WHERE contrat_id = 1; -- ID contrat à modifier

-- Met à jour le montant de la facture pour le contrat avec l'identifiant 1
UPDATE facture
SET montant = 150.00 -- Nouveau montant de paiement
WHERE contrat_id = 1; -- ID contrat à modifier
COMMIT;
-- Termine la transaction
