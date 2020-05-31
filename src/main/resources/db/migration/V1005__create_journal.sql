CREATE TABLE `journal` (
     `id` BIGINT auto_increment NOT NULL,
     `doctor_id` BIGINT NOT NULL,
     `patient_id` BIGINT NOT NULL,
     `date_time` TIMESTAMP NOT NULL,
     FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`),
     FOREIGN KEY (`patient_id`) REFERENCES `patients` (`id`),
     PRIMARY KEY (`id`)
);