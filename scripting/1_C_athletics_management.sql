-- Creation of country table
drop table country cascade constraints;
create table country (
	idCountry number primary key,
	flag blob,
	name varchar2(35),
	review varchar2(300),
	numGoldMedals number,
	numSilverMedals number,
	numBronzeMedals number
);

-- Creation of specialty table
drop table specialty cascade constraints;
create table specialty (
	idSpecialty number primary key,
	name varchar2(20),
	record number(10,4)
);


-- Creation of competition table
drop table competition cascade constraints;
create table competition (
	idCompetition number primary key,
	description varchar2(30),
	specialty number,
	country number,
	
	foreign key(specialty) references specialty(idSpecialty) on delete cascade,
	foreign key(country) references country(idCountry) on delete cascade
);


-- Creation of level table
drop table level_comp cascade constraints;
create table level_comp(
	idLevel number primary key,
	type varchar2(20)
);



-- Creation of athlete table
drop table athlete cascade constraints;
create table athlete (
	tax_code varchar2(16) primary key,
	name varchar2(25),
	surname varchar2(25),
	age number,
	sex char,
	weigth number(8,2),
	heigth number(8,2)
);

-- Creation of participant table
drop table participant cascade constraints;
create table participant (
	idParticipant number primary key,
	nation number,
	teamName varchar2(20),
	
	foreign key(nation) references country(idCountry) on delete cascade
);


-- Creation of team table 
drop table team cascade constraints;
create table team (
	participant number,
	athlete	varchar2(16),
	
	foreign key(participant) references participant(idParticipant) on delete cascade,
	foreign key(athlete) references athlete(tax_code) on delete cascade,
	primary key (participant, athlete)
);


-- Creation of race table
drop table race cascade constraints;
create table race (
	competition number,
	level_comp number,
	participant number,
	raceDate timestamp,
	city varchar2(30),
	
	foreign key(competition) references competition(idCompetition) on delete cascade,
	foreign key(participant) references participant(idParticipant) on delete cascade,
	foreign key(level_comp) references level_comp(idLevel) on delete cascade,

	primary key (competition, level_comp, participant)
);


-- Creation of result table
drop table result cascade constraints;
create table result (
	competition number,
	rank number,
	participant number,
	result number(10,4),
	
	foreign key(competition) references competition(idCompetition) on delete cascade,
	foreign key(participant) references participant(idParticipant) on delete cascade,
	primary key (competition, rank)
);

commit;