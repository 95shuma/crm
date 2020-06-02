ALTER TABLE `admins`
ADD COLUMN `enabled` BOOLEAN NULL AFTER `role_id`;

ALTER TABLE `doctor`
ADD COLUMN `enabled` BOOLEAN NULL AFTER `hospital_id`;

ALTER TABLE `patients`
ADD COLUMN `enabled` BOOLEAN NULL AFTER `hospital_id`;

alter table admins
change column role_id role_id BIGINT,
add index IDX_role (role_id),
add FOREIGN KEY FK_role (role_id) REFERENCES roles (id);