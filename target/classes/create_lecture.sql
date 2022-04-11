/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  emilylau
 * Created: Mar 26, 2022
 */


CREATE TABLE lectures (
    lecture_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    title VARCHAR(50) NOT NULL, 
    password VARCHAR(50) NOT NULL, PRIMARY KEY (username)
);

CREATE TABLE comment (
    comment_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    content VARCHAR(250),
    PRIMARY KEY (username),
    FOREIGN KEY (lecture_id) REFERENCES lectures(lecture_id)
);

INSERT INTO lectures VALUES (1, 'lecture 1',);
INSERT INTO user_roles(username, role) VALUES ('keith', 'ROLE_LECTURER'); 
INSERT INTO users VALUES ('john', 'johnpw');
INSERT INTO user_roles(username, role) VALUES ('john', 'ROLE_LECTURER');
INSERT INTO users VALUES ('emily', 'emilypw');
INSERT INTO user_roles(username, role) VALUES ('emily', 'ROLE_STUDENT');