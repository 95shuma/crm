# 1
ALTER TABLE registration_places
    ADD column groupCode int not null,
    MODIFY COLUMN code_place varchar(64) not null;

# 2
ALTER TABLE `hospitals`
    DROP FOREIGN KEY `hospitals_ibfk_1`;
ALTER TABLE `hospitals`
    DROP INDEX `IDX_region` , drop column region_id;

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE `hospitals`;

SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE `hospitals`
add column registration_place_id bigint not null after name,
add column address varchar (256) not null after registration_place_id,
ADD CONSTRAINT `FK_reg_place`
  FOREIGN KEY (`registration_place_id`)
  REFERENCES `registration_places` (`id`);

alter table hospitals
    add index IDX_name (name),
    add index IDX_address (address),
    add index IDX_registration_place (registration_place_id);

# 3
drop table regions;

# 4
SET FOREIGN_KEY_CHECKS = 0;

alter table admins
    change column role_id role_id BIGINT not null ;

SET FOREIGN_KEY_CHECKS = 1;

alter table admins
    add index IDX_inn (inn),
    add index IDX_dokNum (document_number),
    add index IDX_fullname (full_name),
    add index IDX_name (name),
    add index IDX_surname (surname),
    add index IDX_middle_name (middle_name),
    add index IDX_date (birth_date),
    add index IDX_gender (gender);


# 5
alter table doctor
    change column full_name full_name varchar(256) not null,
    change column middle_name middle_name varchar(64) not null,
    change column gender gender varchar(64) not null,
    change column registration_place_id registration_place_id bigint not null;

alter table doctor
    add index IDX_innD (inn),
    add index IDX_dokNumD (document_number),
    add index IDX_fullnameD (full_name),
    add index IDX_nameD (name),
    add index IDX_surnameD (surname),
    add index IDX_middle_nameD (middle_name),
    add index IDX_dateD (birth_date),
    add index IDX_genderD (gender);

# 6

alter table patients
    add index IDX_innP (inn),
    add index IDX_dokNumP (document_number),
    add index IDX_fullnameP (full_name),
    add index IDX_nameP (name),
    add index IDX_surnameP (surname),
    add index IDX_middle_nameP (middle_name),
    add index IDX_dateP (birth_date),
    add index IDX_genderP (gender);

alter table patients
    change column full_name full_name varchar(256) not null,
    change column role_id role_id BIGINT not null;

# 7

alter table journal
    add index IDX_registrar_id (registrar_id),
    add index IDX_patient_id (patient_id),
    add index IDX_doctor_id1 (doctor_id),
    add index IDX_registration_type_id(registration_type_id),
    add index IDX_hosp (hospital_id);

alter table journal
    change column registrar_id registrar_id BIGINT not null,
    change column registration_type_id registration_type_id BIGINT not null,
    change column hospital_id hospital_id BIGINT not null;

# 8


UPDATE roles SET name = REPLACE (name, "младший админ ЛПУ", "млад мед персонал");