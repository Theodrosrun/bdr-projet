DO $$
DECLARE
    l_stmt_tables TEXT;
    l_stmt_views TEXT;
BEGIN
    -- Pour les tables
    SELECT 'DROP TABLE IF EXISTS "' || string_agg(table_name, '", "') || '" CASCADE;'
    INTO l_stmt_tables
    FROM information_schema.tables
    WHERE table_schema = 'my_amazing_fitness' AND table_type = 'BASE TABLE';

    IF l_stmt_tables IS NOT NULL THEN
        EXECUTE l_stmt_tables;
    END IF;

    -- Pour les vues
    SELECT 'DROP VIEW IF EXISTS "' || string_agg(table_name, '", "') || '" CASCADE;'
    INTO l_stmt_views
    FROM information_schema.tables
    WHERE table_schema = 'my_amazing_fitness' AND table_type = 'VIEW';

    IF l_stmt_views IS NOT NULL THEN
        EXECUTE l_stmt_views;
    END IF;
END
$$;