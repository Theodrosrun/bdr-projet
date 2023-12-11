SET search_path = my_amazing_fitness;

SELECT P.nom,
       P.prenom
FROM Employe E
    JOIN Personne P ON E.employe_id = P.id
WHERE P.nom = 'Baker';

SELECT * FROM Employe WHERE salaire > 5000;

SELECT * FROM Cours WHERE jour = 'Lundi';

SELECT * FROM Abonnement WHERE prix < 50;

SELECT * FROM facture WHERE montant > 50;
