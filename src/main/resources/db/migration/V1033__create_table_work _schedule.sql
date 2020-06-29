CREATE TABLE work_schedule (
  id BIGINT NOT NULL AUTO_INCREMENT,
  date DATE NOT NULL,
  reg_journal_id BIGINT NOT NULL,
  day_start DATETIME NOT NULL,
  day_end DATETIME NOT NULL,
  lunch_start DATETIME NOT NULL,
  lunch_end DATETIME NOT NULL,
  PRIMARY KEY (id),
  INDEX FK_idx (reg_journal_id ASC) VISIBLE,
  CONSTRAINT FK_reg_journal_id
    FOREIGN KEY (reg_journal_id)
    REFERENCES registrations_journal (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);