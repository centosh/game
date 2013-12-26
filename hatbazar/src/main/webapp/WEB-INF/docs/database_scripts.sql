CREATE DATABASE `hatbazar`;
USE `hatbazar`;
DROP TABLE IF EXISTS`user`;
CREATE TABLE `user`(`id` INT AUTO_INCREMENT PRIMARY KEY, `name` VARCHAR (100), `address` VARCHAR (100), `email` VARCHAR (50), `phone` VARCHAR (15), `username` VARCHAR (25) NOT NULL UNIQUE KEY, `password` VARCHAR (20) NOT NULL);
DROP TABLE IF EXISTS`role`;
CREATE TABLE `role`(`id` INT AUTO_INCREMENT PRIMARY KEY , `role_name` VARCHAR (50) NOT NULL UNIQUE KEY);
ALTER TABLE `user` ADD COLUMN `created_on` TIMESTAMP DEFAULT 0;
ALTER TABLE `user` MODIFY `created_on` TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE `user`ADD COLUMN `changed_on` TIMESTAMP DEFAULT 0;

DROP TRIGGER IF EXISTS `update_user_trigger`;
DELIMITER //
CREATE TRIGGER `update_user_trigger` BEFORE UPDATE ON `user`
  FOR EACH ROW SET NEW.`changed_on`=NOW()
//
DELIMITER;

ALTER TABLE `user` change `password` `password` VARCHAR (32);
INSERT INTO `user` (`username`,`password`) VALUES ('sssh',MD5('b'));
UPDATE `user` SET `username`='h';

DROP TABLE IF EXISTS`user_role`;
CREATE TABLE `user_role` (`user_id` INT NOT NULL, `role_id` INT NOT NULL,
 FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
  FOREIGN KEY (`role_id`) REFERENCES `role`(`id`)
);

INSERT INTO `role`(`role_name`)VALUES ('USER_ADMIN'),('USER_NORMAL');
INSERT INTO `user_role`(`user_id`,`role_id`)VALUES ((SELECT `id` FROM `user` WHERE `name` LIKE '%Bhesh%' AND `address` LIKE '%Dhapasi%' LIMIT 1),(SELECT `id` FROM `role` WHERE `role_name` LIKE '%ADMIN%' LIMIT 1));

ALTER TABLE `item` ADD COLUMN `created_on` TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE `item` ADD COLUMN `changed_on` TIMESTAMP DEFAULT 0;

CREATE TABLE `item`(
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR (100),
  `category` VARCHAR (100),
  `added_by` INT,
  `price` DECIMAL,
  `status` tinyint(1),
  `contactPerson` VARCHAR (100),
  `contactPhone` VARCHAR (20),
  `details` TEXT,
  `created_on` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `changed_on` TIMESTAMP DEFAULT 0
);

DROP TRIGGER IF EXISTS `update_item_trigger`;
DELIMITER //
CREATE TRIGGER `update_item_trigger` BEFORE UPDATE ON `item`
FOR EACH ROW SET NEW.`changed_on`=NOW()
//
DELIMITER;