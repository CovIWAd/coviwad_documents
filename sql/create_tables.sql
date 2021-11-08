CREATE TYPE document_type AS ENUM ('VACCINE', 'PCR', 'ANTIGENIC');

CREATE TABLE IF NOT EXISTS documents (
   document_id serial PRIMARY KEY NOT NULL,
   document_type document_type NOT NULL,
   file varchar NOT NULL,
   test_date timestamp  WITHOUT time zone,
   is_positive boolean DEFAULT FALSE,
   user_id varchar(36) NOT NULL
);