ALTER TABLE work_schedule
DROP COLUMN lunch_end,
CHANGE COLUMN day_start time_start TIME NOT NULL ,
CHANGE COLUMN day_end time_end TIME NOT NULL ,
CHANGE COLUMN lunch_start day_of_week VARCHAR(10) NOT NULL;