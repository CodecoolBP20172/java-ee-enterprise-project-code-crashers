ALTER TABLE IF EXISTS ONLY public.users DROP CONSTRAINT IF EXISTS pk_users_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.users DROP CONSTRAINT IF EXISTS fk_membership_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.membership DROP CONSTRAINT IF EXISTS pk_membership_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.book DROP CONSTRAINT IF EXISTS pk_book_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.book DROP CONSTRAINT IF EXISTS fk_author_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.book DROP CONSTRAINT IF EXISTS fk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.book DROP CONSTRAINT IF EXISTS fk_genre_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.author DROP CONSTRAINT IF EXISTS pk_author_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.category DROP CONSTRAINT IF EXISTS pk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.genre DROP CONSTRAINT IF EXISTS pk_genre_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.rent DROP CONSTRAINT IF EXISTS pk_rent_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.rent DROP CONSTRAINT IF EXISTS fk_users_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.rent DROP CONSTRAINT IF EXISTS fk_copy_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.copy DROP CONSTRAINT IF EXISTS pk_copy_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.copy DROP CONSTRAINT IF EXISTS fk_book_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.rating DROP CONSTRAINT IF EXISTS pk_rent_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.rating DROP CONSTRAINT IF EXISTS fk_users_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.rating DROP CONSTRAINT IF EXISTS fk_book_id CASCADE;

DROP TABLE IF EXISTS public.users;
DROP SEQUENCE IF EXISTS public.users_id_seq;
CREATE TABLE users (
    id serial NOT NULL,
    name varchar(60),
    password varchar(60),
    membership_id INT
);

DROP TABLE IF EXISTS public.membership;
DROP SEQUENCE IF EXISTS public.membership_id_seq;
CREATE TABLE membership (
    id serial NOT NULL,
    type varchar,
    book_limit INT
);


DROP TABLE IF EXISTS public.book;
DROP SEQUENCE IF EXISTS public.book_id_seq;
CREATE TABLE book (
    id serial NOT NULL,
    title varchar,
    author_id INT,
    category_id INT,
    picture_url varchar,
    year INT CHECK (year > 999 AND year < 10000),
    description text
);

DROP TABLE IF EXISTS public.author;
DROP SEQUENCE IF EXISTS public.author_id_seq;
CREATE TABLE author (
    id serial NOT NULL,
    name varchar
);

DROP TABLE IF EXISTS public.category;
DROP SEQUENCE IF EXISTS public.category_id_seq;
CREATE TABLE category (
    id SERIAL NOT NULL,
    name varchar
);

DROP TABLE IF EXISTS public.genre;
DROP SEQUENCE IF EXISTS public.genre_id_seq;
CREATE TABLE genre (
    id SERIAL NOT NULL,
    name varchar
);

DROP TABLE IF EXISTS public.book_genre;
CREATE TABLE book_genre (
  book_id INT NOT NULL,
  genre_id INT NOT NULL
);

DROP TABLE IF EXISTS public.rent;
DROP SEQUENCE IF EXISTS public.rent_id_seq;
CREATE TABLE rent (
    id SERIAL NOT NULL,
    users_id INT,
    copy_id INT,
    date_start DATE,
    date_end DATE,
    status varchar
);

DROP TABLE IF EXISTS public.copy;
DROP SEQUENCE IF EXISTS public.copy_id_seq;
CREATE TABLE copy (
    id SERIAL NOT NULL,
    book_id INT
);

DROP TABLE IF EXISTS public.rating;
DROP SEQUENCE IF EXISTS public.rating_id_seq;
CREATE TABLE rating (
  id SERIAL NOT NULL,
  users_id INT,
  book_id INT,
  rating INT CHECK (rating > 0 AND rating < 6),
  review TEXT
);

ALTER TABLE ONLY users
    ADD CONSTRAINT pk_users_id PRIMARY KEY (id);

ALTER TABLE ONLY membership
    ADD CONSTRAINT pk_membership_id PRIMARY KEY (id);

ALTER TABLE ONLY book
    ADD CONSTRAINT pk_book_id PRIMARY KEY (id);

ALTER TABLE ONLY author
    ADD CONSTRAINT pk_author_id PRIMARY KEY (id);

ALTER TABLE ONLY category
    ADD CONSTRAINT pk_category_id PRIMARY KEY (id);

ALTER TABLE ONLY genre
    ADD CONSTRAINT pk_genre_id PRIMARY KEY (id);

ALTER TABLE ONLY rent
    ADD CONSTRAINT pk_rent_id PRIMARY KEY (id);

ALTER TABLE ONLY copy
    ADD CONSTRAINT pk_copy_id PRIMARY KEY (id);

ALTER TABLE ONLY rating
  ADD CONSTRAINT pk_rating_id PRIMARY KEY (id);


ALTER TABLE ONLY book
    ADD CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES author(id);

ALTER TABLE ONLY book
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES category(id);

ALTER TABLE ONLY book_genre
  ADD CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES book(id);

ALTER TABLE ONLY book_genre
  ADD CONSTRAINT fk_genre_id FOREIGN KEY (genre_id) REFERENCES genre(id);

ALTER TABLE ONLY rent
    ADD CONSTRAINT fk_users_id FOREIGN KEY (users_id) REFERENCES users(id);

ALTER TABLE ONLY rent
    ADD CONSTRAINT fk_copy_id FOREIGN KEY (copy_id) REFERENCES copy(id);

ALTER TABLE ONLY copy
    ADD CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES book(id);

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_membership_id FOREIGN KEY (membership_id) REFERENCES membership(id);

ALTER TABLE ONLY rating
  ADD CONSTRAINT fk_users_id FOREIGN KEY (users_id) REFERENCES users(id);

ALTER TABLE ONLY rating
  ADD CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES book(id);
