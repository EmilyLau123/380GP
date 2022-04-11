/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  emilylau
 * Created: Mar 26, 2022
 */


CREATE TABLE polls (
    poll_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
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
