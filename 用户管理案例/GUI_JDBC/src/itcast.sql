/*����*/
CREATE DATABASE itcast;
USE itcast;
/*����*/
CREATE TABLE USER(
 id INT PRIMARY KEY AUTO_INCREMENT,
 username VARCHAR(50),
 age INT,
 pwd VARCHAR(50),
 address VARCHAR(100)
);
/*drop table if exists user*/
SHOW TABLES;
DESC USER;
/*�������*/
INSERT INTO USER(username,age,pwd,address) VALUES('admin',18,'123456','�����в�ƽ�����ĳ���·�������칫¥һ��');
