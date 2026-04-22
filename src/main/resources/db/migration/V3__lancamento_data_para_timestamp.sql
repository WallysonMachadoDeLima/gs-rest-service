DO $$
BEGIN
    IF EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_name = 'obra_insumo_lancamento'
          AND column_name = 'data'
          AND data_type = 'date'
    ) THEN
        ALTER TABLE obra_insumo_lancamento
            ALTER COLUMN data TYPE TIMESTAMP USING data::timestamp;
    END IF;
END$$;
