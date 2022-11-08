create database if not exists todos;

use todos;



create table if not exists priorities
(
    id int primary key auto_increment,
    name varchar(50)
);

create table if not exists categories
(
    id int primary key auto_increment,
    name varchar(50)
);


create table if not exists items
(
    id int primary key auto_increment,
    title varchar(50),
    description text,
    start_date date,
    end_date date,
    priority_id int,
    category_id int,
    FOREIGN KEY (priority_id) REFERENCES priorities(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);