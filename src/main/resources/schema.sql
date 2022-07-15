DROP TABLE IF EXISTS holiday;

CREATE TABLE holiday (
    id serial primary key,
--     id uuid primary key default gen_random_uuid(),
    date timestamp without time zone not null
);