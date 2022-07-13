DROP TABLE IF EXISTS holiday;

CREATE TABLE holiday (
--     id INT PRIMARY KEY NOT NULL DEFAULT nextval('hol_seq'),
--     id SERIAL PRIMARY KEY,
    id uuid primary key default gen_random_uuid(),
    date timestamp without time zone not null
);