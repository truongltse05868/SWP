use KRS_SWR;

CREATE TABLE if not exists setting (
    setting_id INT AUTO_INCREMENT PRIMARY KEY,
    setting_name NVARCHAR(30) NOT NULL,
    `type` NVARCHAR(30) NOT NULL,
    `description` NVARCHAR(100),
    `status` bit default 0 NOT NULL
);

CREATE TABLE if not exists `user` (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name NVARCHAR(50) NOT NULL,
    `password` NVARCHAR(20) NOT NULL,
    email NVARCHAR(100) NOT NULL,
    full_name NVARCHAR(100) NOT NULL,
    phone NVARCHAR(15),
    gender ENUM('Male', 'Female', 'Other'),
    age TINYINT UNSIGNED,
    `status` bit default 0 NOT NULL, 
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES setting(setting_id)
);
CREATE TABLE if not exists `subject` (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_code NVARCHAR(10) NOT NULL,
    `name` NVARCHAR(30) NOT NULL,
    `description` NVARCHAR(100),
    `status` bit default 0 NOT NULL
);

CREATE TABLE if not exists `subject_manager` (
    subject_id INT,
    user_id INT, 
    speciality_id INT,
    FOREIGN KEY (subject_id) REFERENCES `subject`(subject_id),
    FOREIGN KEY (user_id) REFERENCES `user`(user_id),
    FOREIGN KEY (speciality_id) REFERENCES setting(setting_id)
);

CREATE TABLE if not exists `contact` (
    contact_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NOT NULL,
	phone NVARCHAR(15),
	`subject` NVARCHAR(50),
	message NVARCHAR(100),
    type_id int,
    `status` bit default 0 NOT NULL,
     FOREIGN KEY (type_id) REFERENCES setting(setting_id)
);

CREATE TABLE IF NOT EXISTS `post` (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    summary VARCHAR(100) NOT NULL,
    thumbnail_url VARCHAR(255),
    content TEXT,
    category_id INT,
    `status` BIT DEFAULT 0 NOT NULL,
    FOREIGN KEY (category_id) REFERENCES setting(setting_id)
);



