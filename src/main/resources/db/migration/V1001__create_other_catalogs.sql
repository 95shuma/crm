CREATE TABLE roles (
  id BIGINT NOT NULL,
  name VARCHAR(256) NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE registration_places (
  id BIGINT NOT NULL,
  name VARCHAR(256) NOT NULL,
  code_place VARCHAR(256) NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE registration_types (
  id BIGINT NOT NULL,
  name VARCHAR(256) NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE positions (
  id BIGINT NOT NULL,
  name VARCHAR(256) NOT NULL,
  PRIMARY KEY (id)
);