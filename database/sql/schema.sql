CREATE TABLE `role` (
  `role_id` bigint PRIMARY KEY AUTO_INCREMENT,
  `role_name` varchar(50) UNIQUE NOT NULL,
  `description` varchar(255)
);

CREATE TABLE `user` (
  `user_id` bigint PRIMARY KEY AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `username` varchar(50) UNIQUE NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) UNIQUE NOT NULL,
  `phone` varchar(15) UNIQUE,
  `status` varchar(30) NOT NULL,
  `created_at` timestamp
);

CREATE TABLE `region` (
  `region_id` bigint PRIMARY KEY AUTO_INCREMENT,
  `state` varchar(100) NOT NULL,
  `district` varchar(100) NOT NULL,
  `mandal` varchar(100),
  `village` varchar(100)
);

CREATE TABLE `beneficiary` (
  `beneficiary_id` bigint PRIMARY KEY AUTO_INCREMENT,
  `user_id` bigint UNIQUE NOT NULL,
  `region_id` bigint NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `aadhaar_number` varchar(12) UNIQUE NOT NULL,
  `date_of_birth` date,
  `gender` varchar(20),
  `category` varchar(30),
  `annual_income` decimal(12,2),
  `occupation` varchar(100)
);

CREATE TABLE `scheme` (
  `scheme_id` bigint PRIMARY KEY AUTO_INCREMENT,
  `scheme_name` varchar(150) UNIQUE NOT NULL,
  `description` text,
  `grant_amount` decimal(12,2) NOT NULL,
  `income_limit` decimal(12,2),
  `start_date` date,
  `end_date` date,
  `active` boolean DEFAULT true
);

CREATE TABLE `application` (
  `application_id` bigint PRIMARY KEY AUTO_INCREMENT,
  `beneficiary_id` bigint NOT NULL,
  `scheme_id` bigint NOT NULL,
  `application_number` varchar(30) UNIQUE NOT NULL,
  `application_date` date NOT NULL,
  `status` varchar(30) NOT NULL,
  `eligibility_status` varchar(30) NOT NULL,
  `submitted_at` timestamp,
  `last_updated` timestamp,
  `remarks` text
);

CREATE TABLE `document` (
  `document_id` bigint PRIMARY KEY AUTO_INCREMENT,
  `application_id` bigint NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `document_type` varchar(50) NOT NULL,
  `file_path` varchar(255) NOT NULL,
  `uploaded_at` timestamp,
  `verification_status` varchar(30)
);

CREATE TABLE `verification` (
  `verification_id` bigint PRIMARY KEY AUTO_INCREMENT,
  `application_id` bigint NOT NULL,
  `verified_by` bigint NOT NULL,
  `verification_date` date,
  `status` varchar(30),
  `remarks` text
);

CREATE TABLE `approval` (
  `approval_id` bigint PRIMARY KEY AUTO_INCREMENT,
  `application_id` bigint NOT NULL,
  `approved_by` bigint NOT NULL,
  `approval_level` int,
  `status` varchar(30),
  `approval_date` timestamp,
  `remarks` text
);

CREATE TABLE `disbursement` (
  `disbursement_id` bigint PRIMARY KEY AUTO_INCREMENT,
  `application_id` bigint NOT NULL,
  `amount` decimal(12,2) NOT NULL,
  `transaction_reference` varchar(100) UNIQUE,
  `payment_method` varchar(30),
  `status` varchar(30),
  `disbursement_date` timestamp
);

CREATE TABLE `milestone` (
  `milestone_id` bigint PRIMARY KEY AUTO_INCREMENT,
  `application_id` bigint NOT NULL,
  `title` varchar(150),
  `description` text,
  `target_date` date,
  `completion_date` date,
  `status` varchar(30)
);

CREATE TABLE `audit_log` (
  `audit_id` bigint PRIMARY KEY AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `entity_name` varchar(100),
  `entity_id` bigint,
  `action_type` varchar(50),
  `performed_at` timestamp,
  `old_value` text,
  `new_value` text
);

ALTER TABLE `user` ADD FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);

ALTER TABLE `beneficiary` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

ALTER TABLE `beneficiary` ADD FOREIGN KEY (`region_id`) REFERENCES `region` (`region_id`);

ALTER TABLE `application` ADD FOREIGN KEY (`beneficiary_id`) REFERENCES `beneficiary` (`beneficiary_id`);

ALTER TABLE `application` ADD FOREIGN KEY (`scheme_id`) REFERENCES `scheme` (`scheme_id`);

ALTER TABLE `document` ADD FOREIGN KEY (`application_id`) REFERENCES `application` (`application_id`);

ALTER TABLE `verification` ADD FOREIGN KEY (`application_id`) REFERENCES `application` (`application_id`);

ALTER TABLE `verification` ADD FOREIGN KEY (`verified_by`) REFERENCES `user` (`user_id`);

ALTER TABLE `approval` ADD FOREIGN KEY (`application_id`) REFERENCES `application` (`application_id`);

ALTER TABLE `approval` ADD FOREIGN KEY (`approved_by`) REFERENCES `user` (`user_id`);

ALTER TABLE `disbursement` ADD FOREIGN KEY (`application_id`) REFERENCES `application` (`application_id`);

ALTER TABLE `milestone` ADD FOREIGN KEY (`application_id`) REFERENCES `application` (`application_id`);

ALTER TABLE `audit_log` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
