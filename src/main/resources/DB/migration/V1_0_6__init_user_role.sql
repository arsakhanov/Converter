CREATE TABLE tbl_user_role(
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  grant_date timestamp without time zone DEFAULT NOW(),
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT account_role_role_id_fkey FOREIGN KEY (role_id)
    REFERENCES tbl_role (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT account_role_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES tbl_user (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION
);