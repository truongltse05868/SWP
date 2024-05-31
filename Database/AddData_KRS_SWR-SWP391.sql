INSERT INTO setting (setting_name, `type`, `description`, `status`)
VALUES 
    ('Admin', 'Role', 'Administrator role', 1),
    ('Teacher', 'Role', 'Teacher role', 1),
    ('Student', 'Role', 'Student role', 1),
    ('Math', 'Speciality', 'Mathematics speciality', 1),
    ('Science', 'Speciality', 'Science speciality', 1);
INSERT INTO `user` (user_name, `password`, email, full_name, phone, gender, age, `status`, role_id, otp, otp_expiry)
VALUES 
    ('john_doe', 'password123', 'john@example.com', 'John Doe', '1234567890', 'Male', 30, 1, 1, '123456', '2024-06-01 12:00:00'),
    ('jane_smith', 'password456', 'jane@example.com', 'Jane Smith', '0987654321', 'Female', 25, 1, 2, '654321', '2024-06-01 12:00:00');
INSERT INTO `subject` (subject_code, `name`, `description`, `status`)
VALUES 
    ('MATH101', 'Mathematics', 'Basic Mathematics', 1),
    ('SCI101', 'Science', 'Basic Science', 1);
INSERT INTO `subject_manager` (subject_id, user_id, speciality_id)
VALUES 
    (1, 1, 4), -- Assuming subject_id 1 is Mathematics and speciality_id 4 is Math
    (2, 2, 5); -- Assuming subject_id 2 is Science and speciality_id 5 is Science
INSERT INTO `contact` (full_name, email, phone, `subject`, message, type_id, `status`)
VALUES 
    ('Alice Brown', 'alice@example.com', '1122334455', 'Inquiry', 'I have a question.', 1, 1),
    ('Bob Green', 'bob@example.com', '5566778899', 'Support', 'I need help.', 1, 1);
INSERT INTO `post` (title, summary, thumbnail_url, content, `status`, user_id)
VALUES 
    ('First Post', 'This is a summary of the first post.', 'http://example.com/thumbnail1.jpg', 'This is the content of the first post.', 1, 1),
    ('Second Post', 'This is a summary of the second post.', 'http://example.com/thumbnail2.jpg', 'This is the content of the second post.', 1, 2);
INSERT INTO `post_category` (post_id, setting_id)
VALUES 
    (1, 1), -- Assuming setting_id 1 corresponds to a category
    (2, 2); -- Assuming setting_id 2 corresponds to another category
INSERT INTO `class` (subject_id, `status`)
VALUES 
    (1, 1), -- Assuming subject_id 1 is Mathematics
    (2, 1); -- Assuming subject_id 2 is Science
INSERT INTO `study_set` (user_id, subject_id)
VALUES 
    (1, 1), -- Assuming user_id 1 is associated with Mathematics
    (2, 2); -- Assuming user_id 2 is associated with Science
INSERT INTO `term` (set_id, term, `definition`, `status`)
VALUES 
    (1, 'Algebra', 'Branch of math', 1),
    (1, 'Biology', 'Study of life', 1);
INSERT INTO `class_user` (user_id, class_id, teacher_user, `status`)
VALUES 
    (1, 1, 'Prof. John', 1),
    (2, 2, 'Prof. Jane', 1);
INSERT INTO `lesson` (subject_id, title, `status`)
VALUES 
    (1, 'Introduction to Algebra', 1),
    (2, 'Introduction to Biology', 1);
INSERT INTO `exam` (subject_id, class_id, user_id, `status`)
VALUES 
    (1, 1, 1, 1), -- Assuming subject_id 1, class_id 1, and user_id 1
    (2, 2, 2, 1); -- Assuming subject_id 2, class_id 2, and user_id 2
INSERT INTO `question` (subject_id, class_id, `status`)
VALUES 
    (1, 1, 1), -- Assuming subject_id 1 and class_id 1
    (2, 2, 1); -- Assuming subject_id 2 and class_id 2
INSERT INTO `exam_result` (user_id, exam_id)
VALUES 
    (1, 1), -- Assuming user_id 1 and exam_id 1
    (2, 2); -- Assuming user_id 2 and exam_id 2
INSERT INTO `exam_answer` (result_id)
VALUES 
    (1), -- Assuming result_id 1
    (2); -- Assuming result_id 2
INSERT INTO `exam_question` (exam_id, question_id)
VALUES 
    (1, 1), -- Assuming exam_id 1 and question_id 1
    (2, 2); -- Assuming exam_id 2 and question_id 2
INSERT INTO `exam_config` (exam_id, lesson_id, no_of_question)
VALUES 
    (1, 1, 10), -- Assuming exam_id 1 and lesson_id 1
    (2, 2, 15); -- Assuming exam_id 2 and lesson_id 2
INSERT INTO `answer_option` (question_id, option_title, correct_option)
VALUES 
    (1, 'Option 1 for Question 1', 1), -- Assuming question_id 1
    (1, 'Option 2 for Question 1', 0), 
    (2, 'Option 1 for Question 2', 1), -- Assuming question_id 2
    (2, 'Option 2 for Question 2', 0);
INSERT INTO `user_selection` (answer_id, question_id, option_id, user_id, selected_one, correct_option)
VALUES 
    (1, 1, 1, 1, 1, 1), -- Assuming answer_id 1, question_id 1, option_id 1, and user_id 1
    (2, 2, 3, 2, 1, 1); -- Assuming answer_id 2, question_id 2, option_id 3, and user_id 2
