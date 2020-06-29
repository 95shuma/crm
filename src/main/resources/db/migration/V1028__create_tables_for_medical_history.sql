CREATE TABLE diagnoses (
                          id BIGINT NOT NULL,
                          isd_code VARCHAR(256),
                          name VARCHAR(64),
                          position_id LONG,
                          PRIMARY KEY (id)
);
CREATE TABLE instrum_examinations (
                              id BIGINT NOT NULL,
                              name VARCHAR(64),
                              rate VARCHAR(256),
                              description VARCHAR(256),
                              PRIMARY KEY (id)
);
CREATE TABLE lab_examinations (
                          id BIGINT NOT NULL,
                          name VARCHAR(64),
                          rate VARCHAR(256),
                          PRIMARY KEY (id)
);
CREATE TABLE procedures (
                            id BIGINT NOT NULL,
                            name VARCHAR(64),
                            description VARCHAR(256),
                            PRIMARY KEY (id)
);
CREATE TABLE treatments (
                           id BIGINT NOT NULL,
                           remedy_id LONG,
                           remedies_note VARCHAR(256),
                           procedure_id LONG,
                           procedure_note VARCHAR(256),
                           type BOOLEAN,
                           medical_history_id LONG,
                           PRIMARY KEY (id)
);
CREATE TABLE sick_lists (
                                      id BIGINT NOT NULL,
                                      number BIGINT,
                                      start_date DATE,
                                      end_date DATE,
                                      medical_history_id LONG,
                                      PRIMARY KEY (id)
);
CREATE TABLE diagnose_results (
                                  id BIGINT NOT NULL,
                                  diagnose_id LONG,
                                  state BOOLEAN,
                                  medical_history_id LONG,
                                  PRIMARY KEY (id)
);
CREATE TABLE directions (
                            id BIGINT NOT NULL,
                            lab_examination_id LONG,
                            instrum_examination_id LONG,
                            position_id LONG,
                            medical_history_id LONG,
                            PRIMARY KEY (id)
);
CREATE TABLE examination_results (
                            id BIGINT NOT NULL,
                            lab_examination_id LONG,
                            lab_examination_result VARCHAR(256),
                            instrum_examination_id LONG,
                            medical_history_id LONG,
                            PRIMARY KEY (id)
);
CREATE TABLE medical_histories (
                                     id BIGINT NOT NULL,
                                     record_journal_id LONG,
                                     date DATE,
                                     type_of_visit BOOLEAN,
                                     complaint VARCHAR(512),
                                     direction_id LONG,
                                     examination_result_id LONG,
                                     diagnose_result_id LONG,
                                     recommendation VARCHAR(512),
                                     treatment_id LONG,
                                     sick_list_id LONG,
                                     PRIMARY KEY (id)
);