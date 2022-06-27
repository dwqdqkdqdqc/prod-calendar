DROP TABLE calendar IF EXISTS;

CREATE TABLE calendar (
    id SERIAL PRIMARY KEY,
    year INT NOT NULL,
    month INT NOT NULL,
    day INT NOT NULL
);