/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  emilylau
 * Created: Mar 29, 2022
 */
CREATE TRIGGER my_trigger
AFTER DELETE ON polls
REFERENCING OLD AS oldRow
FOR EACH ROW MODE DB2SQL
DELETE FROM comment
WHERE poll_id = ;
