--create table user (id integer not null, birthday date, name varchar(255), primary key (id))

insert into user (id, birthday, name) values (1001, sysdate(), 'david');
insert into user (id, birthday, name) values (1002, sysdate(), 'pamela');
insert into user (id, birthday, name) values (1003, sysdate(), 'leticia');

insert into post (id, description, user_id) values (1100, 'teste', 1001);