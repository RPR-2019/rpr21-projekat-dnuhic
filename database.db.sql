create table if not exists person (
    id int constraint person_pk primary key,
    name TEXT,
    surname TEXT,
    email TEXT constraint unique_email unique,
    password TEXT,
    admin int,
    picture TEXT
);

INSERT INTO person VALUES (1, "Admin", "Admin", "admin@admin.com", "password", 1, "/images/user-4250.png");