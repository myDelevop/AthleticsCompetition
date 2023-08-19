-- Creation of users table 
drop table auth_user cascade constraints;
create table auth_user (
  idUser integer primary key,
  tax_code varchar2(16),
  name varchar2(20),
  surname varchar2(20),
  username varchar2(20) unique,
  email varchar2(45) unique
); 

-- Relationship 1-1 with user for psw management
drop table auth_psw cascade constraints;
create table auth_psw (
  idUser integer primary key,
  psw varchar2(20),

  foreign key(idUser) references auth_user(idUser) on delete cascade
); 

-- Creation of groups table
drop table auth_group cascade constraints;
create table auth_group (
  idGroup integer primary key,
  groupName varchar2(20) unique,
  groupDescription varchar2(40)
); 

-- Relationship N-M between users and groups
drop table auth_user_group cascade constraints;
create table auth_user_group (
  idUser integer,
  idGroup integer,  
  primary key(idUser, idGroup),
  foreign key(idUser) references auth_user(idUser) on delete cascade,
  foreign key(idGroup) references auth_group(idGroup) on delete cascade
);

commit;
