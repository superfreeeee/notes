drop database if exists spring_demo3;

create database spring_demo3;

use spring_demo3;

drop table if exists `user`;

create table `user` (
  `id` int(10) not null auto_increment,
  `username` varchar(50) not null,
  `password` varchar(50) not null,
  primary key(`id`)
)engine=InnoDB auto_increment=1 default charset=utf8;

-- 
insert into user
(username, password)
values
("superfree", "superfree");

