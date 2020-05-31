CREATE TABLE `patients` (
                           `id` LONG AUTO_INCREMENT NOT NULL PRIMARY KEY,
                           `inn` VARCHAR(64) NOT NULL,
                           `password` VARCHAR(64) NOT NULL,
                           `document_number` VARCHAR(64) NOT NULL,
                           `full_name` VARCHAR(64) NOT NULL,
                           `name` VARCHAR(64) NOT NULL,
                           `surname` VARCHAR(64) NOT NULL,
                           `middle_name` VARCHAR(64) NOT NULL,
                           `birth_date` DATE NOT NULL,
                           `gender` INT NOT NULL,
                           `role_id` LONG NOT NULL,
                           `registration_place_id` LONG NOT NULL,
                           `hospital_id` LONG NOT NULL
);