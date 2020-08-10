CREATE TABLE tbl_history(
  id serial PRIMARY KEY,
  user_id Integer not null,
  original_currency VARCHAR(255) NOT NULL,
  target_currency VARCHAR(255) NOT NULL,
  initial_amount VARCHAR(255),
  amount_received VARCHAR(255),
  date DATE,
  CONSTRAINT user_id_fk FOREIGN KEY(user_id)
  REFERENCES tbl_user (id)
)