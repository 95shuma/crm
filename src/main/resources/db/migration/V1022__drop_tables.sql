DROP TABLE `admins`;
DROP TABLE `journal`;
DROP TABLE `patients`;
DROP TABLE `hospitals_doctors`;
DROP TABLE `doctors`;
DROP TABLE `registration_types`;
ALTER TABLE `registration_places`
RENAME TO  `places`;
DROP TABLE `hospitals`;