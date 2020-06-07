alter table `registration_places` change `groupCode` `group_code` INT after `code_place`;
alter table `journal` change `registrar_id` `registrar_id` BIGINT after `doctor_id`;
UPDATE patients SET `enabled` = '1';
UPDATE doctor SET `enabled` = '1';
UPDATE admins SET `enabled` = '1';