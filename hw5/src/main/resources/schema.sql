drop table if exists books cascade ;
drop table if exists authors cascade ;
drop table if exists genres cascade ;

create table authors
(
  id serial primary key,
  first_name varchar (255) not null,
  last_name varchar (255) not null
);

create table genres
(
  id serial primary key,
  genre_name varchar (255) not null
);

CREATE TABLE books
(
  id serial primary key,
  book_name varchar (255) not null,
  author_id integer,
  genre_id integer,
  foreign key (author_id) references authors(id),
  foreign key (genre_id) references genres(id)
);