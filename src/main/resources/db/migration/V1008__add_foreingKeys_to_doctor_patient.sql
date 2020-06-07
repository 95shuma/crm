alter table doctor drop column role_id;
alter table doctor drop column registration_place_id;
alter table doctor drop column hospital_id;

alter table doctor
    add column role_id BIGINT after gender,
    add column registration_place_id BIGINT after role_id,
	add column hospital_id BIGINT after registration_place_id,
	add FOREIGN KEY FK_role (role_id) REFERENCES roles(id),
    add FOREIGN KEY FK_registration_place (registration_place_id) REFERENCES registration_places(id),
    add FOREIGN KEY FK_hospital (hospital_id) REFERENCES hospitals(id);

alter table patients
drop column role_id;
alter table patients
drop column registration_place_id;
alter table patients
drop column hospital_id;

alter table patients
    add column role_id BIGINT after gender,
    add column registration_place_id BIGINT after role_id,
	add column hospital_id BIGINT after registration_place_id,

	add FOREIGN KEY FK_role (role_id) REFERENCES roles(id),
    add FOREIGN KEY FK_registration_place (registration_place_id) REFERENCES registration_places(id),
    add FOREIGN KEY FK_hospital (hospital_id) REFERENCES hospitals(id);