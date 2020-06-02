drop table if exists user cascade;
create table user(
	id integer auto_increment not null primary key,
	name varchar(25) not null,
	email varchar(250) not null,
	password varchar(25) not null
);