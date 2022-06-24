CREATE TABLE app
(
  id              bigint       NOT NULL,
  name            varchar(100) NOT NULL UNIQUE,
  current_version varchar(11)  NOT NULL,
  update_url      varchar(150) NOT NULL,
  uid             varchar      NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE release
(
  id_app   bigint       NOT NULL,
  version  varchar(11)  NOT NULL,
  overview varchar(300) NOT NULL
);

ALTER TABLE release
  ADD CONSTRAINT FK_app_TO_release
    FOREIGN KEY (id_app)
    REFERENCES app (id);
