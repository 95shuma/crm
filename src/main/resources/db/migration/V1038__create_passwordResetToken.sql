CREATE TABLE `password_reset_token`(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(64),
    user_id BIGINT,
    expiry_date DATETIME,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
)