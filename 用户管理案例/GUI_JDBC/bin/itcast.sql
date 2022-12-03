/*建库*/
CREATE DATABASE itcast;
USE itcast;
/*建表*/
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
/*添加数据*/
INSERT INTO USER(username,age,pwd,address) VALUES('admin',18,'123456','北京市昌平区建材城西路金燕龙办公楼一层');
