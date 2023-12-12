SET search_path = my_amazing_fitness;

SELECT P.nom,
       P.prenom
FROM Employe E
    JOIN Personne P ON E.employe_id = P.id
WHERE P.nom = 'Baker';

SELECT *
FROM Employe
WHERE salaire > 5000;

SELECT *
FROM Cours
WHERE jour = 'Lundi';

SELECT *
FROM Abonnement
WHERE prix < 50;

SELECT *
FROM facture
WHERE montant > 50;

SELECT C.description,
       C.heure,
       C.jour,
       P.prenom
FROM Cours C
    JOIN Instructeur I ON C.instructeur_id = I.instructeur_id
    JOIN Personne P ON P.id = I.instructeur_id;

SELECT *
FROM Progression
WHERE membre_id = 6;

BEGIN;

UPDATE Contrat
SET frequencePaiement = 2 -- Nouvelle fréquence de paiement
WHERE contrat_id = 1; -- Remplace 1 par l'identifiant du contrat que tu souhaites modifier

UPDATE facture
SET montant = 150.00 -- Nouveau montant de paiement
WHERE contrat_id = 1; -- Remplace 1 par l'identifiant du contrat que tu souhaites modifier

COMMIT;
