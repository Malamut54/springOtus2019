insert into authors (first_name, last_name) values ('Stephen', 'King');
insert into genres (genre_name) values ('horror');

insert into books (book_name, author_id, genre_id) VALUES ('Sunshine'
,(select id from authors where first_name = 'Stephen')
,(select id from genres where genre_name = 'horror')
);