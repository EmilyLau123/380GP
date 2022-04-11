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