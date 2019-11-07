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
	foreign key(player_id) references player(id)
);

-- Create dummy data
insert into guild (name, created) values ("Dan's guild", now());
insert into guild (name, created) values ("Another guild", now());

insert into class (id) values ("Priest");
insert into class (id) values ("Hunter");
insert into class (id) values ("Rogue");
insert into class (id) values ("Warlock");
insert into class (id) values ("Mage");
insert into class (id) values ("Druid");
insert into class (id) values ("Shaman");
insert into class (id) values ("Paladin");
insert into class (id) values ("Warrior");

insert into player (guild_id, class_id, dkp, name, created) values (1, 'Priest', 100, 'Danimal', now());
insert into player (guild_id, class_id, name, created) values (1, 'Hunter', 'Huntard', now());
insert into player (guild_id, class_id, name, created) values (2, 'Warlock', 'Shadowman', now());

insert into dkp_history (player_id, dkp) values (1, 100);
insert into dkp_history (player_id, dkp) values (2, 50);
insert into dkp_history (player_id, dkp) values (3, 400);