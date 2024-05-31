USE KRS_SWR

-- Table to store settings such as roles, types, etc.
CREATE TABLE if not exists setting (
    setting_id INT AUTO_INCREMENT PRIMARY KEY,
    setting_name NVARCHAR(30) NOT NULL,
    `type` NVARCHAR(30) NOT NULL,
    `description` NVARCHAR(100),
    `status` bit DEFAULT 0 NOT NULL
);

-- Table to store user information
CREATE TABLE if not exists `user` (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name NVARCHAR(50) NOT NULL,
    `password` NVARCHAR(20) NOT NULL,
    email NVARCHAR(100) NOT NULL,
    full_name NVARCHAR(100) NOT NULL,
    phone NVARCHAR(15),
    gender ENUM('Male', 'Female', 'Other'),
    age TINYINT UNSIGNED,
    `status` bit DEFAULT 0 NOT NULL, 
    role_id INT,
    otp VARCHAR(6),
    otp_expiry TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES setting(setting_id)
);

-- Table to store subjects information
CREATE TABLE if not exists `subject` (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_code NVARCHAR(10) NOT NULL,
    `name` NVARCHAR(30) NOT NULL,
    `description` NVARCHAR(100),
    `status` bit DEFAULT 0 NOT NULL
);

-- Table to manage subjects and their related managers
CREATE TABLE if not exists `subject_manager` (
    subject_id INT,
    user_id INT, 
    speciality_id INT,
    FOREIGN KEY (subject_id) REFERENCES `subject`(subject_id),
    FOREIGN KEY (user_id) REFERENCES `user`(user_id),
    FOREIGN KEY (speciality_id) REFERENCES setting(setting_id)
);

-- Table to store contact information
CREATE TABLE if not exists `contact` (
    contact_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NOT NULL,
    phone NVARCHAR(15),
    `subject` NVARCHAR(50),
    message NVARCHAR(100),
    type_id INT,
    `status` bit DEFAULT 0 NOT NULL,
    FOREIGN KEY (type_id) REFERENCES setting(setting_id)
);

-- Table to store posts
CREATE TABLE IF NOT EXISTS `post` (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    title NVARCHAR(255),
    summary TEXT,
    thumbnail_url NVARCHAR(255),
    content TEXT,
    `status` bit DEFAULT 0 NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- Table to categorize posts
CREATE TABLE IF NOT EXISTS `post_category` (
    post_id INT NOT NULL,
    setting_id INT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES post(post_id),
    FOREIGN KEY (setting_id) REFERENCES setting(setting_id)
);

-- Table to store classes
CREATE TABLE IF NOT EXISTS `class` (
    class_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_id INT NOT NULL,
    `status` bit DEFAULT 0 NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id)
);

-- Table to store study sets
CREATE TABLE IF NOT EXISTS `study_set` (
    set_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    subject_id INT NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- Table to store terms and definitions related to a study set
CREATE TABLE IF NOT EXISTS `term` (
    term_id INT AUTO_INCREMENT PRIMARY KEY,
    set_id INT NOT NULL,
    term NVARCHAR(12),
    `definition` NVARCHAR(20),
    `status` BIT DEFAULT 0 NOT NULL,
    FOREIGN KEY (set_id) REFERENCES study_set(set_id)
);

-- Table to store the relationship between classes and users
CREATE TABLE IF NOT EXISTS `class_user` (
    user_id INT NOT NULL,
    class_id INT NOT NULL,
    teacher_user NVARCHAR(20),
    `status` bit DEFAULT 0 NOT NULL,
    FOREIGN KEY (class_id) REFERENCES class(class_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- Table to store lessons
CREATE TABLE IF NOT EXISTS `lesson` (
    lesson_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_id INT NOT NULL,
    title NVARCHAR(30),
    `status` bit DEFAULT 0 NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id)
);

-- Table to store exams
CREATE TABLE IF NOT EXISTS `exam` (
    exam_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_id INT NOT NULL,
    class_id INT NOT NULL,
    user_id INT NOT NULL,
    `status` bit DEFAULT 0 NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    FOREIGN KEY (class_id) REFERENCES class(class_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- Table to store questions
CREATE TABLE IF NOT EXISTS `question` (
    question_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_id INT NOT NULL,
    class_id INT NOT NULL,
    `status` bit DEFAULT 0 NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    FOREIGN KEY (class_id) REFERENCES class(class_id)
);

-- Table to store exam results
CREATE TABLE IF NOT EXISTS `exam_result` (
    result_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    exam_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (exam_id) REFERENCES exam(exam_id)
);

-- Table to store exam answers
CREATE TABLE IF NOT EXISTS `exam_answer` (
    answer_id INT AUTO_INCREMENT PRIMARY KEY,
    result_id INT NOT NULL,
    FOREIGN KEY (result_id) REFERENCES exam_result(result_id)
);

-- Table to link exams and questions
CREATE TABLE IF NOT EXISTS `exam_question` (
    exam_id INT NOT NULL,
    question_id INT NOT NULL,
    FOREIGN KEY (exam_id) REFERENCES exam(exam_id),
    FOREIGN KEY (question_id) REFERENCES question(question_id)
);

-- Table to store exam configurations
CREATE TABLE IF NOT EXISTS `exam_config` (
    exam_id INT NOT NULL,
    lesson_id INT NOT NULL,
    no_of_question INT,
    FOREIGN KEY (exam_id) REFERENCES exam(exam_id),
    FOREIGN KEY (lesson_id) REFERENCES lesson(lesson_id)
);

-- Table to store answer options for questions
CREATE TABLE IF NOT EXISTS `answer_option` (
    option_id INT AUTO_INCREMENT PRIMARY KEY,
    question_id INT NOT NULL,
    option_title NVARCHAR(255),
    correct_option BIT,
    FOREIGN KEY (question_id) REFERENCES question(question_id)
);

-- Table to store user selections during exams
CREATE TABLE IF NOT EXISTS `user_selection` (
    answer_id INT NOT NULL,
    question_id INT NOT NULL,
    option_id INT NOT NULL,
    user_id INT NOT NULL,
    selected_one BIT,
    correct_option BIT,
    FOREIGN KEY (answer_id) REFERENCES exam_answer(answer_id),
    FOREIGN KEY (question_id) REFERENCES question(question_id),
    FOREIGN KEY (option_id) REFERENCES answer_option(option_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);
