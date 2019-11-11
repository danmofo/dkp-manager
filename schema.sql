-- Database schema

drop schema if exists dkp_manager;
create schema dkp_manager;

use dkp_manager;

set foreign_key_checks=0;
drop table if exists dkp_manager.guild;
drop table if exists dkp_manager.class;
drop table if exists dkp_manager.player;
drop table if exists dkp_manager.dkp_history;
set foreign_key_checks=1;

create table guild (
	id int primary key auto_increment,
	name varchar(255) not null,
	uri varchar(255) not null,
	created timestamp not null default current_timestamp,
	updated timestamp null on update current_timestamp
);

-- E.g. hunter, priest, etc.
create table class (
	id varchar(255) primary key
);

-- Basically a user
create table player (
	id int primary key auto_increment,
	guild_id int not null,
	class_id varchar(255) not null,
	name varchar(255) not null,
	dkp decimal not null default 0.00,
	email varchar(255) not null unique,
	password varchar(255) not null,
	created timestamp not null default current_timestamp,
	updated timestamp null on update current_timestamp,	
	foreign key(guild_id) references guild(id),
	foreign key(class_id) references class(id)
);

-- Keeps a record of how much DKP has been awarded to players and when
create table dkp_history (
	id int primary key auto_increment,
	player_id int not null,
	dkp decimal not null default 0.00,
	created timestamp not null default current_timestamp,
	-- add created column for when it was awarded.
	foreign key(player_id) references player(id)
);

-- Create dummy data
insert into guild (name, uri, created) values ("Dan's guild", 'dans-guild', now());
insert into guild (name, uri, created) values ("Another guild", 'another-guild', now());

insert into class (id) values ("Priest");
insert into class (id) values ("Hunter");
insert into class (id) values ("Rogue");
insert into class (id) values ("Warlock");
insert into class (id) values ("Mage");
insert into class (id) values ("Druid");
insert into class (id) values ("Shaman");
insert into class (id) values ("Paladin");
insert into class (id) values ("Warrior");

insert into player (guild_id, class_id, dkp, name, email, password, created) values (1, 'Priest', 500, 'Danimal', 'test@email.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', now());
insert into player (guild_id, class_id, dkp, name, email, password, created) values (1, 'Hunter', 50, 'Huntard', 'test2@email.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', now());
insert into player (guild_id, class_id, dkp, name, email, password, created) values (2, 'Warlock', 400, 'Shadowman', 'test3@email.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', now());

insert into dkp_history (player_id, dkp, created) values (1, 100, date_sub(now(), interval 2 day));
insert into dkp_history (player_id, dkp, created) values (1, 150, date_sub(now(), interval 1 day));
insert into dkp_history (player_id, dkp, created) values (1, 250, now());
insert into dkp_history (player_id, dkp) values (2, 50);
insert into dkp_history (player_id, dkp) values (3, 400);