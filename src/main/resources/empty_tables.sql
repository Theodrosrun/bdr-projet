DO $$
DECLARE
    l_stmt TEXT;
BEGIN
    -- Construction de la commande TRUNCATE pour toutes les tables dans le schéma
    SELECT 'TRUNCATE TABLE ' || string_agg(quote_ident(table_schema) || '.' || quote_ident(table_name), ', ') || ' CASCADE;'
    INTO l_stmt
    FROM information_schema.tables
    WHERE table_schema = 'my_amazing_fitness' AND table_type = 'BASE TABLE'; -- Filtrer pour inclure uniquement les tables

    -- Exécution de la commande TRUNCATE, si nécessaire
    IF l_stmt IS NOT NULL THEN
        EXECUTE l_stmt;
    END IF;
END
$$;
