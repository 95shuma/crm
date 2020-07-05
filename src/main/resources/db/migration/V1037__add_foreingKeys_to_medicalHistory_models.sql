drop table diagnose_results;
drop table directions;
drop table examination_results;
drop table sick_lists;
drop table treatments;
drop table medical_histories;
alter table procedures change column id id BIGINT AUTO_INCREMENT NOT NULL;
alter table diagnoses change column id id BIGINT AUTO_INCREMENT NOT NULL;
alter table lab_examinations change column id id BIGINT AUTO_INCREMENT NOT NULL;
alter table instrum_examinations change column id id BIGINT AUTO_INCREMENT NOT NULL;


CREATE TABLE medical_histories (
                                   id BIGINT NOT NULL AUTO_INCREMENT,
                                   date DATE,
                                   type_of_visit BOOLEAN,
                                   complaint VARCHAR(512),
                                   recommendation VARCHAR(512),
                                   PRIMARY KEY (id)
);
CREATE TABLE treatments (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        remedy_id BIGINT,
                        remedies_note VARCHAR(256),
                        procedure_id BIGINT,
                        procedure_note VARCHAR(256),
                        type BOOLEAN,
                        medical_history_id BIGINT,
                        PRIMARY KEY (id),
                        FOREIGN KEY (`remedy_id`) REFERENCES `remedies` (`id`),
                        FOREIGN KEY (`procedure_id`) REFERENCES `procedures` (`id`),
                        FOREIGN KEY (`medical_history_id`) REFERENCES `medical_histories` (`id`)
);
CREATE TABLE diagnose_results (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      diagnose_id BIGINT,
                      state BOOLEAN,
                      medical_history_id BIGINT,
                      PRIMARY KEY (id),
                      FOREIGN KEY (`diagnose_id`) REFERENCES `diagnoses` (`id`),
                      FOREIGN KEY (`medical_history_id`) REFERENCES `medical_histories` (`id`)
);
CREATE TABLE directions (
                    id BIGINT NOT NULL AUTO_INCREMENT,
                    lab_examination_id BIGINT,
                    instrum_examination_id BIGINT,
                    position_id BIGINT,
                    medical_history_id BIGINT,
                    PRIMARY KEY (id),
                    FOREIGN KEY (`lab_examination_id`) REFERENCES `lab_examinations` (`id`),
                    FOREIGN KEY (`instrum_examination_id`) REFERENCES `instrum_examinations` (`id`),
                    FOREIGN KEY (`position_id`) REFERENCES `positions` (`id`),
                    FOREIGN KEY (`medical_history_id`) REFERENCES `medical_histories` (`id`)
);
CREATE TABLE examination_results (
                     id BIGINT NOT NULL AUTO_INCREMENT,
                     lab_examination_id BIGINT,
                     lab_examination_result VARCHAR(256),
                     instrum_examination_id BIGINT,
                     instrum_examination_result VARCHAR(256),
                     general_state VARCHAR(256),
                     medical_history_id BIGINT,
                     PRIMARY KEY (id),
                     FOREIGN KEY (`lab_examination_id`) REFERENCES `lab_examinations` (`id`),
                     FOREIGN KEY (`instrum_examination_id`) REFERENCES `instrum_examinations` (`id`),
                     FOREIGN KEY (`medical_history_id`) REFERENCES `medical_histories` (`id`)
);
CREATE TABLE sick_lists (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            number BIGINT,
                            start_date DATE,
                            end_date DATE,
                            medical_history_id BIGINT,
                            PRIMARY KEY (id),
                            FOREIGN KEY (`medical_history_id`) REFERENCES `medical_histories` (`id`)
);
