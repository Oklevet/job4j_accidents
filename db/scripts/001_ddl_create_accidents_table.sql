CREATE TABLE if not exists accident_type (
  id serial primary key,
  "name" text unique
);


CREATE TABLE if not exists accidents (
  id serial primary key,
  "name" text,
  "text" text,
  address text,
  type_id int REFERENCES accident_type(id) NOT NULL
);


CREATE TABLE if not exists rules (
  id serial primary key,
  "name" text unique
);


create table if not exists accident_rules(
    id   serial primary key,
    accidents_id int REFERENCES accidents(id)    NOT NULL,
    rule_id int REFERENCES rules(id)    NOT NULL,
    unique (accidents_id, rule_id)
);