
/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  emilylau
 * Created: Mar 26, 2022
 */

CREATE TABLE users (
    username VARCHAR(50) NOT NULL, 
    password VARCHAR(50) NOT NULL, 
    fullname VARCHAR(50) NOT NULL, 
    phone VARCHAR(50) NOT NULL, 
    address VARCHAR(50) NOT NULL, 
    PRIMARY KEY (username)
);

CREATE TABLE user_roles (
    user_role_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_role_id),
    FOREIGN KEY (username) REFERENCES users(username)
);

INSERT INTO users (username, password,fullname,phone,address) VALUES ('keith', '{noop}keithpw','keithLee','12341234','HK');
INSERT INTO user_roles(username, role) VALUES ('keith', 'ROLE_LECTURER'); 
INSERT INTO users (username, password,fullname,phone,address) VALUES ('emily', '{noop}emilypw','emilyLau','45264323','JP');
INSERT INTO user_roles(username, role) VALUES ('emily', 'ROLE_STUDENT'); 

CREATE TABLE lectures (
    lecture_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    title VARCHAR(50) NOT NULL, 
    link VARCHAR(50) NOT NULL, 
    PRIMARY KEY (lecture_id)
);

CREATE TABLE comment (
    comment_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    lecture_id INTEGER NOT NULL,
    username VARCHAR(50) NOT NULL,
    content VARCHAR(250),
    PRIMARY KEY (username),
    FOREIGN KEY (lecture_id) REFERENCES lectures(lecture_id)
);

INSERT INTO lectures VALUES ('keith', 'lecture 1','link');

CREATE TABLE polls (
    poll_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL, 
    question VARCHAR(50) NOT NULL, 
    option1 VARCHAR(50) NOT NULL,
    option2 VARCHAR(50) NOT NULL,
    option3 VARCHAR(50) NOT NULL,
    option4 VARCHAR(50) NOT NULL,
    PRIMARY KEY (poll_id)
);

CREATE TABLE votes (
    poll_id INTEGER NOT NULL,
    username VARCHAR(50) NOT NULL,
    voteOption INTEGER NOT NULL,
    FOREIGN KEY (poll_id) REFERENCES polls(poll_id)
);

CREATE TABLE comments (
    poll_id INTEGER NOT NULL,
    comment_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    content VARCHAR(250),
    PRIMARY KEY (comment_id),
    FOREIGN KEY (poll_id) REFERENCES polls(poll_id)
);

INSERT INTO polls (username, question, option1, option2, option3, option4) VALUES ('keith', 'how are you?','Good', 'fine', 'bad', 'perfer not to say');