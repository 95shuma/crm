alter table journal
	add column hospital_id BIGINT after patient_id,
    add column registrar_id BIGINT after doctor_id,
	add column registration_type_id BIGINT after registrar_id,
    add column reason varchar(256),
	add FOREIGN KEY FK_registration_type (registration_type_id) REFERENCES registration_types(id),
    add FOREIGN KEY FK_doctor (registrar_id) REFERENCES doctor(id),
    add FOREIGN KEY FK_hospital (hospital_id) REFERENCES hospitals(id);