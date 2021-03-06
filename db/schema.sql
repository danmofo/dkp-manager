-- Database schema

drop schema if exists dkp_manager;
create schema dkp_manager;

use dkp_manager;

set foreign_key_checks=0;
drop table if exists dkp_manager.guild;
drop table if exists dkp_manager.class;
drop table if exists dkp_manager.player;
drop table if exists dkp_manager.dkp_history;
drop table if exists dkp_manager.dkp_decay_interval;
set foreign_key_checks=1;

create table guild (
	id int primary key auto_increment,
	name varchar(255) not null,
	uri varchar(255) not null,
	invite_code varchar(255) not null,
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
	dkp decimal(15, 5) not null default 0.00000,
	email varchar(255) not null unique,
	password varchar(255) not null,
	is_guild_master boolean default 0,
	forgotten_password_token varchar(255) not null,
	created timestamp not null default current_timestamp,
	updated timestamp null on update current_timestamp,	
	foreign key(guild_id) references guild(id),
	foreign key(class_id) references class(id)
);

-- Keeps a record of how much DKP has been awarded to players and when
create table dkp_history (
	id int primary key auto_increment,
	player_id int not null,
	dkp decimal(15, 5) not null default 0.00000,
	reason varchar(255) null,
	created timestamp not null default current_timestamp,
	-- add created column for when it was awarded.
	foreign key(player_id) references player(id)
);

create table dkp_decay_interval (
	guild_id int primary key,
	unit_name varchar(255) not null, -- hours, weeks, days, years
	unit_value int,
	dkp decimal(15, 5) not null default 0.00000,
	next_occurrence date not null,
	created timestamp not null default current_timestamp,
	updated timestamp null on update current_timestamp,
	foreign key(guild_id) references guild(id)
);

-- Create dummy data
insert into guild (name, uri, invite_code, created) values ("Dan's guild", 'dans-guild', 'd94c8bfa505cdb75', now());
insert into guild (name, uri, invite_code, created) values ("Another guild", 'another-guild', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild2", 'another-guild2', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild3", 'another-guild3', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild4", 'another-guild4', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild5", 'another-guild5', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild6", 'another-guild6', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild7", 'another-guild7', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild8", 'another-guild8', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild9", 'another-guild9', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild0", 'another-guild0', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild11", 'another-guild11', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild12", 'another-guild12', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild13", 'another-guild13', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild14", 'another-guild14', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild15", 'another-guild15', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild16", 'another-guild16', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild17", 'another-guild17', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild18", 'another-guild18', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild19", 'another-guild19', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild20", 'another-guild20', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild21", 'another-guild21', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild22", 'another-guild22', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild23", 'another-guild23', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild24", 'another-guild24', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild25", 'another-guild25', 'b9a6f2c3e7f6ec57', now());
insert into guild (name, uri, invite_code, created) values ("Another guild26", 'another-guild26', 'b9a6f2c3e7f6ec57', now());

insert into class (id) values ("Priest");
insert into class (id) values ("Hunter");
insert into class (id) values ("Rogue");
insert into class (id) values ("Warlock");
insert into class (id) values ("Mage");
insert into class (id) values ("Druid");
insert into class (id) values ("Shaman");
insert into class (id) values ("Paladin");
insert into class (id) values ("Warrior");

insert into player (guild_id, class_id, dkp, name, email, password, forgotten_password_token, is_guild_master, created) 
	values (1, 'Priest', 500, 'Danimal', 'danmofo@gmail.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', 'forgotten-password-token1', 1, now());
insert into player (guild_id, class_id, dkp, name, email, password, forgotten_password_token, created) 
	values (1, 'Hunter', 50, 'Huntard', 'danmofo2@gmail.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', 'forgotten-password-token2', now());
insert into player (guild_id, class_id, dkp, name, email, password, forgotten_password_token, created) 
	values (1, 'Warrior', 50, 'Warriorman', 'danmofo3@gmail.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', 'forgotten-password-token2', now());
insert into player (guild_id, class_id, dkp, name, email, password, forgotten_password_token, created) 
	values (1, 'Druid', 50, 'Leafman', 'danmofo4@gmail.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', 'forgotten-password-token2', now());
insert into player (guild_id, class_id, dkp, name, email, password, forgotten_password_token, created) 
	values (1, 'Shaman', 50, 'Totemman', 'danmofo5@gmail.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', 'forgotten-password-token2', now());
insert into player (guild_id, class_id, dkp, name, email, password, forgotten_password_token, created) 
	values (1, 'Warlock', 50, 'Dotman', 'danmofo6@gmail.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', 'forgotten-password-token2', now());
insert into player (guild_id, class_id, dkp, name, email, password, forgotten_password_token, created) 
	values (1, 'Mage', 50, 'Wizardman', 'danmofo7@gmail.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', 'forgotten-password-token2', now());
insert into player (guild_id, class_id, dkp, name, email, password, forgotten_password_token, created) 
	values (1, 'Druid', 50, 'Bearman', 'danmofo8@gmail.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', 'forgotten-password-token2', now());
insert into player (guild_id, class_id, dkp, name, email, password, forgotten_password_token, created) 
	values (1, 'Shaman', 50, 'Nicename', 'danmofo9@gmail.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', 'forgotten-password-token2', now());
insert into player (guild_id, class_id, dkp, name, email, password, forgotten_password_token, created) 
	values (1, 'Druid', 50, 'Nobrain', 'danmofo10@gmail.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', 'forgotten-password-token2', now());
insert into player (guild_id, class_id, dkp, name, email, password, forgotten_password_token, created) 
	values (1, 'Druid', 50, 'Lowbie', 'danmofo11@gmail.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', 'forgotten-password-token2', now());
insert into player (guild_id, class_id, dkp, name, email, password, forgotten_password_token, created) 
	values (1, 'Hunter', 50, 'Bliss', 'danmofo12@gmail.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', 'forgotten-password-token2', now());
insert into player (guild_id, class_id, dkp, name, email, password, forgotten_password_token, created) 
	values (2, 'Warlock', 400, 'Shadowman', 'test3@email.com','$2a$10$UClsNpkDaeFO7JMOhUNp.OorcpZ.3PTiAFCZ4ZSCwp/gKFjribspG', 'forgotten-password-token3', now());

insert into dkp_history (player_id, dkp, reason, created) values (1, 100, 'Onyxia raid', date_sub(now(), interval 2 day));
insert into dkp_history (player_id, dkp, reason, created) values (1, 150, 'Onyxia raid', date_sub(now(), interval 1 day));
insert into dkp_history (player_id, dkp, reason, created) values (1, 250, 'Onyxia raid', now());
insert into dkp_history (player_id, dkp, reason) values (2, 50, 'Molten Core raid');
insert into dkp_history (player_id, dkp, reason) values (3, 400, 'Molten Core raid');