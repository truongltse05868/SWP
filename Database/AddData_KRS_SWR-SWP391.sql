INSERT INTO setting (setting_name, `type`, `description`, `status`) 
VALUES
('Student', 'User role', 'Role assigned to students', 1),
('Teacher', 'User role', 'Role assigned to teachers', 1),
('Manager', 'User role', 'Role assigned to managers', 0),
('Admin', 'User role', 'Role assigned to administrators', 0),
('Guest', 'User role', 'Role assigned to guest users', 1),
('General Knowledge', 'Category type', 'Posts related to general knowledge topics', 1),
('Science', 'Category type', 'Posts related to Science topics', 1),
('Math', 'Category type', 'Posts related to Math topics', 1);

INSERT INTO `user` (user_name, `password`, email, full_name, phone, gender, age, `status`, role_id) 
VALUES
('alicej', 'password1', 'alice.johnson@example.com', 'Alice Johnson', '123-456-7890', 'Female', 30, 1, 1), -- Student
('bobb', 'password2', 'bob.brown@example.com', 'Bob Brown', '234-567-8901', 'Male', 25, 1, 2), -- Teacher
('charlied', 'password3', 'charlie.davis@example.com', 'Charlie Davis', '345-678-9012', 'Male', 28, 0, 3), -- Manager
('davidg', 'password4', 'david.green@example.com', 'David Green', '456-789-0123', 'Male', 35, 1, 4), -- Admin
('evaa', 'password5', 'eva.adams@example.com', 'Eva Adams', '567-890-1234', 'Other', 22, 1, 5); -- Guest

INSERT INTO `subject` (subject_code, `name`, `description`, `status`) 
VALUES
('MATH10', 'Mathematics', 'Basic Mathematics Course', 1),
('PHY101', 'Physics', 'Introduction to Physics', 1),
('CHEM101', 'Chemistry', 'Basic Chemistry Course', 1),
('BIO101', 'Biology', 'Introduction to Biology', 1),
('CS101', 'Computer Science', 'Fundamentals of Computer Science', 1);

INSERT INTO contact (full_name, email, phone, `subject`, message, type_id, `status`) VALUES
('John Doe', 'john.doe@example.com', '123-456-7890', 'Inquiry', 'I have a question about your services.', 6, 1), -- General Knowledge
('Jane Smith', 'jane.smith@example.com', '234-567-8901', 'Science Inquiry', 'Can you explain this science topic?', 7, 1), -- Science
('Alice Johnson', 'alice.johnson@example.com', '345-678-9012', 'Math Help', 'I need help with a math problem.', 8, 1), -- Math
('Bob Brown', 'bob.brown@example.com', '456-789-0123', 'Student Inquiry', 'I am a student with a question.', 1, 0), -- Student
('Eva Green', 'eva.green@example.com', '567-890-1234', 'Teacher Feedback', 'Feedback on teaching methods.', 2, 0); -- Teacher

INSERT INTO `post` (title, summary, thumbnail_url, content, category_id, `status`)
VALUES
('General Knowledge Post 1', 'Summary of General Knowledge Post 1', 'http://example.com/thumbnail1.jpg', 'Content of General Knowledge Post 1', 6, 1),
('Science Post 1', 'Summary of Science Post 1', 'http://example.com/thumbnail2.jpg', 'Content of Science Post 1', 7, 1),
('Math Post 1', 'Summary of Math Post 1', 'http://example.com/thumbnail3.jpg', 'Content of Math Post 1', 8, 1);
