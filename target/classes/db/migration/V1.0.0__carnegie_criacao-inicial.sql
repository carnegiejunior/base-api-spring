--drop table if exists user cascade;
--drop table if exists request cascade;
--drop table if exists request_state cascade;

create table user(
	id bigint auto_increment not null primary key,
	name varchar(25) not null,
	email varchar(250) not null,
	password varchar(25) not null,
	role varchar(15) not null
)  ENGINE=InnoDB charset=Utf8;

create table request(
	id bigint auto_increment not null primary key,
	creation_date datetime not null,
	description text,
	state varchar(12),
	subject varchar(75),
	user_id bigint not null references user(id)
) ENGINE=InnoDB charset=Utf8;

create table request_state(
	id bigint auto_increment not null primary key,
	realization_date datetime not null,
	state varchar(12),
	request_id bigint not null references request(id),
	user_id bigint not null references user(id)
) ENGINE=InnoDB charset=Utf8;