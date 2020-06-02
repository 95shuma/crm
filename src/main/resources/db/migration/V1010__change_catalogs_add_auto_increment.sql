ALTER TABLE journal
DROP FOREIGN KEY journal_ibfk_5,
DROP FOREIGN KEY journal_ibfk_3;
ALTER TABLE journal
DROP INDEX FK_hospital ,
DROP INDEX FK_registration_type ;
;

ALTER TABLE patients
DROP FOREIGN KEY `patients_ibfk_3`,
DROP FOREIGN KEY `patients_ibfk_2`,
DROP FOREIGN KEY `patients_ibfk_1`;
ALTER TABLE patients
DROP INDEX `FK_hospital` ,
DROP INDEX `FK_registration_place` ,
DROP INDEX `FK_role` ;
;
ALTER TABLE `doctor`
DROP FOREIGN KEY `doctor_ibfk_3`,
DROP FOREIGN KEY `doctor_ibfk_2`,
DROP FOREIGN KEY `doctor_ibfk_1`;
ALTER TABLE `doctor`
DROP INDEX `FK_hospital` ,
DROP INDEX `FK_registration_place` ,
DROP INDEX `FK_role` ;
;

ALTER TABLE `hospitals`
DROP FOREIGN KEY `hospitals_ibfk_1`;
ALTER TABLE `hospitals`
DROP INDEX `IDX_region` ;
;

ALTER TABLE hospitals
CHANGE COLUMN id id BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE positions
CHANGE COLUMN id id BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE regions
CHANGE COLUMN id id BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE registration_places
CHANGE COLUMN id id BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE registration_types
CHANGE COLUMN id id BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE roles
CHANGE COLUMN id id BIGINT(20) NOT NULL AUTO_INCREMENT ;


alter table hospitals
add index IDX_region (region_id),
add FOREIGN KEY FK_region (region_id) REFERENCES regions (id);

alter table journal
add FOREIGN KEY FK_registration_type (registration_type_id) REFERENCES registration_types(id),
add FOREIGN KEY FK_hospital (hospital_id) REFERENCES hospitals(id);

alter table doctor
    add FOREIGN KEY FK_role (role_id) REFERENCES roles(id),
    add FOREIGN KEY FK_registration_place (registration_place_id) REFERENCES registration_places(id),
    add FOREIGN KEY FK_hospital (hospital_id) REFERENCES hospitals(id);

alter table patients
    add FOREIGN KEY FK_role (role_id) REFERENCES roles(id),
    add FOREIGN KEY FK_registration_place (registration_place_id) REFERENCES registration_places(id),
    add FOREIGN KEY FK_hospital (hospital_id) REFERENCES hospitals(id);