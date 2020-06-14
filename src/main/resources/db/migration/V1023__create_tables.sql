CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `inn` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `document_number` varchar(64) NOT NULL,
  `full_name` varchar(256) NOT NULL,
  `name` varchar(64) NOT NULL,
  `surname` varchar(64) NOT NULL,
  `middle_name` varchar(64) NOT NULL,
  `birth_date` date NOT NULL,
  `gender` varchar(64) NOT NULL,
  `place_id` bigint(20),
  `enabled` boolean NOT NULL default true,
  PRIMARY KEY (`id`),
  KEY `IDX_place` (`place_id`),
  KEY `IDX_inn` (`inn`),
  KEY `IDX_dokNum` (`document_number`),
  KEY `IDX_fullname` (`full_name`),
  KEY `IDX_name` (`name`),
  KEY `IDX_surname` (`surname`),
  KEY `IDX_middle_name` (`middle_name`),
  KEY `IDX_date` (`birth_date`),
  KEY `IDX_gender` (`gender`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`place_id`) REFERENCES `places` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `hospitals` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `place_id` bigint(20) NOT NULL,
  `address` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_name` (`name`),
  KEY `IDX_address` (`address`),
  KEY `IDX_place` (`place_id`),
  CONSTRAINT `FK_reg_place` FOREIGN KEY (`place_id`) REFERENCES `places` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `records_journal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doctor_id` bigint(20) NOT NULL,
  `patient_id` bigint(20) NOT NULL,
  `registrar_id` bigint(20),
  `hospital_id` bigint(20) NOT NULL,
  `date_time` timestamp NULL NOT NULL,
  `reason` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_registrar_id` (`registrar_id`),
  KEY `IDX_patient_id` (`patient_id`),
  KEY `IDX_doctor_id1` (`doctor_id`),
  KEY `IDX_hospital_id` (`hospital_id`),
  CONSTRAINT `records_journal_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `users` (`id`),
  CONSTRAINT `records_journal_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `users` (`id`),
  CONSTRAINT `records_journal_ibfk_3` FOREIGN KEY (`registrar_id`) REFERENCES `users` (`id`),
  CONSTRAINT `records_journal_ibfk_4` FOREIGN KEY (`hospital_id`) REFERENCES `hospitals` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `registrations_journal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `hospital_id` bigint(20),
  `position_id` bigint(20),
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_doc` (`user_id`),
  KEY `IDX_hosp` (`hospital_id`),
  KEY `IDX_position` (`position_id`),
  KEY `IDX_role` (`role_id`),
  CONSTRAINT `registrations_journal_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `registrations_journal_ibfk_2` FOREIGN KEY (`hospital_id`) REFERENCES `hospitals` (`id`),
  CONSTRAINT `registrations_journal_ibfk_3` FOREIGN KEY (`position_id`) REFERENCES `positions` (`id`),
  CONSTRAINT `registrations_journal_ibfk_4` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;