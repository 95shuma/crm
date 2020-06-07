ALTER TABLE `doctor`
    ADD COLUMN `position_id` LONG AFTER `hospital_id`;

alter table `doctor`
    change column `full_name` `full_name` varchar(256);

alter table `patients`
    change column `full_name` `full_name` varchar(256);

alter table `doctor`
    change column `gender` `gender` varchar(64);
alter table `doctor`
    drop column `position_id`;

ALTER TABLE `doctor` DROP FOREIGN KEY `doctor_ibfk_1`;
ALTER TABLE `doctor` DROP COLUMN `role_id`;

ALTER TABLE `doctor` DROP FOREIGN KEY `doctor_ibfk_3`;
ALTER TABLE `doctor` DROP COLUMN `hospital_id`;
