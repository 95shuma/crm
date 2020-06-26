CREATE TABLE `remedy_types` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`name` varchar(64) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pharm_groups` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`name` varchar(64) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `international_names` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`name` varchar(64) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `remedy_forms` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`name` varchar(64) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `measures` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`name` varchar(64) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `dosages` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`name` varchar(64) NOT NULL,
`measure_id` bigint(20) NOT NULL,
`quantity` int(20) NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `FK_measure` FOREIGN KEY (`measure_id`) REFERENCES `measures` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


drop table `remedies`;

CREATE TABLE remedies (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`type_id` bigint(20) DEFAULT NULL,
`pharm_group_id` bigint(20) DEFAULT NULL,
`international_name_id` bigint(20) NOT NULL,
`name`varchar(64) NOT NULL,
`dosage_id` bigint(20) NOT NULL,
`form_id` bigint(20) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `IDX_type_id` (`type_id`),
KEY `IDX_pharm_group_id` (`pharm_group_id`),
KEY `IDX_international_name_id` (`international_name_id`),
KEY `IDX_doze_id` (`dosage_id`),
KEY `IDX_form_id` (`form_id`),
CONSTRAINT `medicine_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `remedy_types` (`id`),
CONSTRAINT `medicine_ibfk_2` FOREIGN KEY (`pharm_group_id`) REFERENCES `pharm_groups` (`id`),
CONSTRAINT `medicine_ibfk_4` FOREIGN KEY (`international_name_id`) REFERENCES `international_names` (`id`),
CONSTRAINT `medicine_ibfk_5` FOREIGN KEY (`dosage_id`) REFERENCES `dosages` (`id`),
CONSTRAINT `medicine_ibfk_6` FOREIGN KEY (`form_id`) REFERENCES `remedy_forms` (`id`)
)






