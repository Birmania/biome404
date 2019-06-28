# --- First database schema

# --- !Ups

create table UserDo (
  id                        INTEGER IDENTITY PRIMARY KEY,
  firstname                 varchar(255) NOT NULL,
  lastname                  varchar(255) NOT NULL,
);

# --- !Downs

drop table if exists UserDo;