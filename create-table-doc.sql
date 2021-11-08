DROP TYPE document_type;
DROP TABLE documents;

CREATE TYPE document_type AS ENUM ('VACCINE', 'PCR', 'ANTIGENIC');

CREATE TABLE documents (
   document_id serial PRIMARY KEY NOT NULL,
   document_type document_type NOT NULL,
   file BYTEA NOT NULL,
   test_date timestamp  WITHOUT time zone,
   user_id varchar(36) NOT NULL REFERENCES user_entity(id)
);