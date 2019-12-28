delete from users;
INSERT INTO users (id, email, password, name,address) VALUES
(10, 'admin@gmail.com', 'admin', 'Admin','Chch'),
(20, 'david@gmail.com', 'david', 'David','AU'),
(30, 'ron@gmail.com', 'ron', 'Ron','QT');
insert into posts(id, title, content, created_on, updated_on) values
(10, 'Introducing SpringBoot', 'SpringBoot is awesome', '2017-05-10', null),
(20, 'Securing Web applications', 'This post will show how to use SpringSecurity',
'2017-05-20', null),
(30, 'Introducing Spring Social', 'Developing social web applications using Spring Social',
'2017-05-24', null);
insert into comments(id, post_id, name, email, content, created_on, updated_on) values
(10, 10, 'John','john@gmail.com', 'This is cool', '2017-05-10', null),
(20, 10, 'Rambo','rambo@gmail.com', 'Thanks for awesome tips', '2017-05-20', null),
(30, 20, 'Paul', 'paul@gmail.com', 'Nice post buddy.', '2017-05-24', null);