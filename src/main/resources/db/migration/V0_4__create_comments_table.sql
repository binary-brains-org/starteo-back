create extension if not exists "uuid-ossp";

create table "comment" (
    id varchar default uuid_generate_v4(),
    content TEXT  not null,
    user_id varchar constraint user_id_fk references "user"(id),
    idea_id varchar constraint idea_id_fk references "idea"(id)
);