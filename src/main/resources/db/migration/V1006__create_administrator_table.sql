create table admins (
id BIGINT NOT NULL,
inn varchar(64) NOT NULL,
password varchar(64) NOT NULL,
document_number varchar(64) NOT NULL,
full_name varchar(256) NOT NULL,
name varchar(64) NOT NULL,
surname varchar(64) NOT NULL,
middle_name varchar(64) NOT NULL,
birth_date DATE NOT NULL,
gender varchar(64) NOT NULL,
role_id LONG NOT NULL
);