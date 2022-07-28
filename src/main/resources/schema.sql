DROP TABLE IF EXISTS other_services.holiday;

CREATE TABLE other_services.holiday (
    id serial primary key,
--     id uuid primary key default gen_random_uuid(),
    date timestamp without time zone not null
);