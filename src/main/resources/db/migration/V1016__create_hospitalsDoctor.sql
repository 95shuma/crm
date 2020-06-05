CREATE TABLE `hospitals_doctor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `doctor_id` bigint NOT NULL,
  `hospital_id` bigint NOT NULL,
  `position_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_doc` (`doctor_id`),
  KEY `IDX_hosp` (`hospital_id`),
  KEY `IDX_position` (`position_id`),
  KEY `IDX_role` (`role_id`),
  CONSTRAINT `hospitals_doctor_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`),
  CONSTRAINT `hospitals_doctor_ibfk_2` FOREIGN KEY (`hospital_id`) REFERENCES `hospitals` (`id`),
  CONSTRAINT `hospitals_doctor_ibfk_3` FOREIGN KEY (`position_id`) REFERENCES `positions` (`id`),
  CONSTRAINT `hospitals_doctor_ibfk_4` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci