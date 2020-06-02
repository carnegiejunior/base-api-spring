alter table request change column user_id owner_id bigint not null;
alter table request add foreign key(owner_id) references user(id);

alter table request_state change column user_id owner_id bigint not null;
alter table request_state add foreign key(owner_id) references user(id);