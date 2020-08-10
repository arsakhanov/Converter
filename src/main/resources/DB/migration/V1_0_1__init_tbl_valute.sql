CREATE TABLE tbl_valute(
  id VARCHAR(15) PRIMARY KEY NOT NULL,
  date Date,
  numcode INTEGER NOT NULL,
  charcode VARCHAR(255) NOT NULL,
  nominal INTEGER NOT NULL,
  name VARCHAR(255) NOT NULL,
  value NUMERIC
);