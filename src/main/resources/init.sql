CREATE TABLE app
(
  uid             uuid         NOT NULL,
  name            varchar(100) NOT NULL UNIQUE,
  current_version varchar(11)  NOT NULL,
  update_url      varchar(150) NOT NULL,
  PRIMARY KEY (uid)
);

CREATE TABLE release
(
  uid_app  uuid         NOT NULL,
  version  varchar(11)  NOT NULL,
  overview varchar(300) NOT NULL
);

ALTER TABLE release
  ADD CONSTRAINT FK_app_TO_release
    FOREIGN KEY (uid_app)
    REFERENCES app (uid);