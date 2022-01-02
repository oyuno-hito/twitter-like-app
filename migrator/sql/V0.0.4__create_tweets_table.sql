CREATE TABLE IF NOT EXISTS `twitter_like_app`.`tweets` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `mesage` TEXT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` TIMESTAMP NULL,
    `user_id` INT NOT NULL,
    CONSTRAINT `fk_tweets_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `twitter_like_app`.`users` (`id`)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
)
ENGINE = InnoDB;
