alter table `journal` change `doctor_id` `doctor_id` BIGINT;
alter table `journal` change `hospital_id` `hospital_id` BIGINT;
alter table `journal` change `registrar_id` `registrar_id` BIGINT;
alter table `journal` change `registration_type_id` `registration_type_id` BIGINT;
alter table `journal` change `reason` `reason` varchar(256);
alter table `journal` change `patient_id` `patient_id` BIGINT;
alter table `journal` change `date_time` `date_time` timestamp;