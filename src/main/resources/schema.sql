CREATE TABLE `User` (
                        `user_id` bigint NOT NULL AUTO_INCREMENT,
                        `first_name` varchar(45) NOT NULL,
                        `last_name` varchar(45) DEFAULT NULL,
                        `email_Id` varchar(45) NOT NULL,
                        `user_name` varchar(45) NOT NULL,
                        `password` varchar(100) NOT NULL,
                        `phone_number` bigint (10) DEFAULT NULL,
                        `address` varchar(100) DEFAULT NULL,
                        `created_date` timestamp(6) NULL DEFAULT NULL,
                        `updated_date` timestamp(6) NULL DEFAULT NULL,
                        PRIMARY KEY (`user_id`)
) ENGINE = InnoDB;

CREATE TABLE `Pet` (
                       `pet_id` bigint NOT NULL AUTO_INCREMENT,
                       `pet_name` varchar(45) DEFAULT NULL,
                       `pet_type` varchar(45) DEFAULT NULL,
                       `location` varchar(100) DEFAULT NULL,
                       `breed` varchar(45) DEFAULT NULL,
                       `age` int DEFAULT NULL,
                       `description` varchar(100) DEFAULT NULL,
                       `size` int DEFAULT NULL,
                       `color` varchar(45) DEFAULT NULL,
                       `req_flag` varchar(45) DEFAULT NULL,
                       `user_id` bigint DEFAULT NULL,
                       `created_date` timestamp(6) NULL DEFAULT NULL,
                       `updated_date` timestamp(6) NULL DEFAULT NULL,
                       `pet_image` BIGINT,
                       `archive_flag` int DEFAULT NULL,
                       `flag` varchar(45) DEFAULT NULL,
                       PRIMARY KEY (`pet_id`),
                       FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`)
) ENGINE = InnoDB;

CREATE TABLE `luckyPaws`.`Events` (
                                      `event_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
                                      `title` VARCHAR(100) NOT NULL,
                                      `event_description` VARCHAR(100) NULL,
                                      `event_date` TIMESTAMP(6) NOT NULL,
                                      `event_location` VARCHAR(100) NOT NULL,
                                      `user_id` BIGINT(10) NULL,
                                      `created_date` TIMESTAMP(6) NULL,
                                      `updated_date` TIMESTAMP(6) NULL,
                                      `updated_user_id` BIGINT(10) NULL,
                                      PRIMARY KEY (`event_id`),
                                      FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`)
                                  ) ENGINE = InnoDB;

CREATE TABLE `luckyPaws`.`Event_Participants` (
                                                  `participant_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
                                                  `event_id` BIGINT(10) NULL,
                                                  `user_id` BIGINT(10) NULL,
                                                  `created_date` TIMESTAMP(6) NULL,
                                                  `updated_date` TIMESTAMP(6) NULL,
                                                  PRIMARY KEY (`participant_id`),
                                                  FOREIGN KEY (`event_id`) REFERENCES `Events`(`event_id`),
                                                  FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`)
                                                  ) ENGINE = InnoDB;

CREATE TABLE `luckyPaws`.`Pet_Adoption` (
                                            `pet_adoption_id` BIGINT(10) NOT NULL,
                                            `pet_id` BIGINT(10) NULL,
                                            `status` VARCHAR(45) NULL,
                                            `created_date` TIMESTAMP(6) NULL,
                                            `user_id` BIGINT(10) NULL,
                                            PRIMARY KEY (`pet_adoption_id`),
                                            FOREIGN KEY (`pet_id`) REFERENCES `Pet`(`pet_id`),
                                            FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`)
                                        ) ENGINE = InnoDB;
