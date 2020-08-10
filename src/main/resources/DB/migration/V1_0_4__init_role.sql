CREATE TABLE tbl_role(
   id serial PRIMARY KEY,
   role_name VARCHAR (255) UNIQUE NOT NULL,
   description VARCHAR (255) NULL
);