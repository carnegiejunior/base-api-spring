alter table user add CONSTRAINT uq_user_email UNIQUE (email);
alter table user add CONSTRAINT uq_user_email_password UNIQUE (email,password);
