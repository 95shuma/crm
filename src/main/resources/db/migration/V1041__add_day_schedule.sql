CREATE TABLE day_schedule (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  reg_journal_id BIGINT NOT NULL,
  day_time_start DATETIME NOT NULL,
  day_time_end DATETIME NOT NULL,
  INDEX FK_idx (reg_journal_id ASC) VISIBLE,
  INDEX IDX_day_time_start (day_time_start ASC) VISIBLE,
  INDEX IDX_day_time_end (day_time_end ASC) VISIBLE,
  FOREIGN KEY FK_reg_journal_id (reg_journal_id) REFERENCES registrations_journal (id));