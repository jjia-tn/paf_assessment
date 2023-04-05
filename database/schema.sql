create database bgg;

use bgg;

create table user (
	user_id 	varchar(8) not null,
    username	varchar(32) not null,
    name		varchar(32),
    constraint 	user_pk primary key (user_id)
);

create table task (
	task_id 	int not null auto_increment,
    description varchar(50) not null,
    priority 	int not null,
    due_date	date not null,
    user_id		varchar(8),
    constraint 	task_pk primary key (task_id),
    constraint	task_user_fk foreign key (user_id) references user (user_id)
);