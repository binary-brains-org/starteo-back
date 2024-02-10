create extension if not exists "uuid-ossp";

CREATE TABLE IF NOT EXISTS "idea"(
    id  VARCHAR     CONSTRAINT idea_pk  PRIMARY KEY  DEFAULT uuid_generate_v4(),
    name    VARCHAR     NOT NULL,
    status VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    image TEXT,
    creation_datetime timestamp not null ,
    updated_datetime timestamp not null,
    founder varchar constraint user_id_fk references "user"(id)
);