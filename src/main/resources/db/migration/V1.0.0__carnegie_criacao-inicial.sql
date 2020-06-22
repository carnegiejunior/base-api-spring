drop table if exists users cascade;

create table users(
	id bigint auto_increment not null primary key,
	username varchar(25) not null,
	email varchar(250) not null,
	password varchar(100) not null,
	active boolean not null default false,
	registration_date_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	modification_date_time TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	role varchar(15) not null
)  ENGINE=InnoDB charset=Utf8;

alter table users add CONSTRAINT uq_users_username UNIQUE (username);
alter table users add CONSTRAINT uq_users_username_password UNIQUE (username,password);