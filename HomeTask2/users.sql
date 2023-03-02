create table users(
                      id serial primary key ,
                      firstName char(30),
                      phone char(14),
                      email char(30)
);

INSERT INTO users (firstname, phone, email)
VALUES ('Alex','+380111111111','example@gmail.com');

INSERT INTO users (firstname, phone, email)
VALUES ('Sophia','+380111441111','user@mail.com');

