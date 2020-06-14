CREATE TABLE diseases (
            id BIGINT NOT NULL,
            name VARCHAR(256) NOT NULL,
            PRIMARY KEY (id)
);
CREATE TABLE examinations (
                         id BIGINT NOT NULL,
                         name VARCHAR(256) NOT NULL,
                         PRIMARY KEY (id)
);
CREATE TABLE remedies (
                         id BIGINT NOT NULL,
                         name VARCHAR(256) NOT NULL,
                         PRIMARY KEY (id)
)