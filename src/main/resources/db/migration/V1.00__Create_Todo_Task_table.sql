CREATE TABLE `todo_task` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(1000) NOT NULL,
  `description` TEXT NULL,
  `priority` ENUM('LOW', 'MEDIUM', 'HIGH') NOT NULL,
  `status` ENUM('TODO', 'DOING', 'DONE') NOT NULL,
  `is_deleted` TINYINT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));
