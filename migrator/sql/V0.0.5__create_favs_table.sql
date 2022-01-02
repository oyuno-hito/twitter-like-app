CREATE TABLE IF NOT EXISTS `twitter_like_app`.`favs` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` TIMESTAMP NULL,
    `user_id` INT NOT NULL,
    `tweet_id` BIGINT NOT NULL,
    CONSTRAINT `fk_favs_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `twitter_like_app`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_favs_tweet_id`
    FOREIGN KEY (`tweet_id`)
    REFERENCES `twitter_like_app`.`tweets` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
    UNIQUE INDEX `uk_favs_user_id_tweet_id`(`user_id`, `tweet_id`)
)
ENGINE = InnoDB;
