DO $$
DECLARE
    l_stmt TEXT;
BEGIN
    SELECT 'TRUNCATE TABLE ' || string_agg(quote_ident(table_schema) || '.' || quote_ident(table_name), ', ') || ' CASCADE;'
    INTO l_stmt
    FROM information_schema.tables
    WHERE table_schema = 'my_amazing_fitness';

    IF l_stmt IS NOT NULL THEN
        EXECUTE l_stmt;
    END IF;
END
$$;