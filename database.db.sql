create table if not exists person (
    id int constraint person_pk primary key,
    name TEXT,
    surname TEXT,
    email TEXT,
    password TEXT
);

create table if not exists admin (
    id int constraint admin_pk primary key,
);

create table if not exists inspector (
    id int constraint inspector_pk primary key,
);

