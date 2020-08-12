ALTER TABLE week_schedule RENAME TO work_schedule ;

ALTER TABLE work_schedule
DROP COLUMN `date`;