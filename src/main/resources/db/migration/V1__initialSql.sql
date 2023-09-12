create table users
(
    id       serial
        constraint user_table_pk
            primary key,
    user_name     varchar(64) not null,
    user_lastname  varchar(64),
    password varchar(300),
    document int(12),
    balance float(254)
);

create table Company
(
    id       serial
        constraint user_table_pk
            primary key,
    company_name     varchar(64) not null,
    password varchar(300),
    NIT int(64),
    balance float(254)
);

