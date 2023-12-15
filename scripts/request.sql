SET search_path = my_amazing_fitness;

SELECT P.nom,
       P.prenom
FROM Employe E
    JOIN Personne P ON E.employe_id = P.id
WHERE P.nom = 'Baker';

SELECT * FROM Employe WHERE salaire > 5000;

SELECT * FROM Abonnement WHERE prix < 50;

SELECT * FROM facture WHERE montant > 50;

SELECT * FROM passage WHERE membre_id = 4;

SELECT c.description, c.heure, c.jour, p.prenom
FROM Cours c
JOIN Instructeur i ON c.instructeur_id = i.instructeur_id
JOIN Personne p ON p.id = i.instructeur_id;

SELECT *
FROM Progression
WHERE membre_id = 6;

BEGIN;
UPDATE Personne
SET nom = 'Correctif'
WHERE id = 11; -- Remplace 11 par l'identifiant du membre que tu veux mettre à jour
UPDATE Compte
SET motDePasse = 'default'
WHERE username in (SELECT compte_id FROM employe WHERE employe_id = 11); -- Remplace 'employe1_username' par le nom d'utilisateur du compte correspondant au membre
COMMIT;

INSERT INTO Progression (progression_id, date, poids, membre_id)
VALUES (4, '2023-12-11', 90, 6);

BEGIN;

UPDATE Contrat
SET frequencePaiement = 2 -- Nouvelle fréquence de paiement
WHERE contrat_id = 1; -- Remplace 1 par l'identifiant du contrat que tu souhaites modifier

UPDATE facture
SET montant = 150.00 -- Nouveau montant de paiement
WHERE contrat_id = 1; -- Remplace 1 par l'identifiant du contrat que tu souhaites modifier

COMMIT;
