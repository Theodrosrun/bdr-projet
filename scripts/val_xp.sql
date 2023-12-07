
DROP VIEW IF EXISTS VueFacturesPayees;

CREATE VIEW VueFacturesPayees AS
SELECT c.membre_id,
       SUM(f.montant) AS montant_total_factures,
       SUM(CASE WHEN f.datePaiement IS NOT NULL THEN f.montant ELSE 0 END) AS montant_paye,
       CASE WHEN SUM(CASE WHEN f.datePaiement IS NOT NULL THEN f.montant ELSE 0 END) = f.montant THEN true ELSE false END AS toutes_factures_payees
FROM contrat c
LEFT JOIN facture f ON c.contrat_id = f.contrat_id
GROUP BY c.membre_id, f.montant;

SELECT * FROM VueFacturesPayees;




CREATE VIEW VueMontantPaye AS
SELECT c.membre_id,
       SUM(CASE WHEN f.datePaiement IS NOT NULL THEN f.montant ELSE 0 END) AS montant_paye
FROM Contrat c
LEFT JOIN Facture f ON c.contrat_id = f.contrat_id
GROUP BY c.membre_id;




-- Création de la table SuppressionLog
CREATE TABLE SuppressionLog (
    user_id INT,
    type VARCHAR(255),
    date_suppression TIMESTAMP
);


CREATE OR REPLACE FUNCTION log_suppression()
RETURNS TRIGGER AS
$$
DECLARE
    entity_type TEXT;
BEGIN
    IF TG_TABLE_NAME = 'Membre' THEN
        entity_type = 'Membre';
    ELSIF TG_TABLE_NAME = 'Employe' THEN
        entity_type = 'Employe';
    END IF;

    INSERT INTO SuppressionLog (user_id, type, date_suppression)
    VALUES (CASE WHEN entity_type = 'Membre' THEN OLD.membre_id ELSE OLD.employe_id END, entity_type, CURRENT_TIMESTAMP);

    RETURN OLD;
END;
$$
LANGUAGE plpgsql;


CREATE TRIGGER suppression_trigger_membre
AFTER DELETE ON Membre
FOR EACH ROW
EXECUTE FUNCTION log_suppression();


CREATE TRIGGER suppression_trigger_employe
AFTER DELETE ON Employe
FOR EACH ROW
EXECUTE FUNCTION log_suppression();
