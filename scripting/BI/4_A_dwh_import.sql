-- IMPORT OF COUNTRY TABLE INTO DWH
drop table dwh_athl_1_t_country;
create table dwh_athl_1_t_country as 
select * from country;

-- IMPORT OF SPECIALTY TABLE INTO DWH
drop table dwh_athl_1_t_specialty;
create table dwh_athl_1_t_specialty as 
select * from specialty;

-- IMPORT OF PARTICIPANT TABLE INTO DWH
drop table dwh_athl_1_t_participant;
create table dwh_athl_1_t_participant as 
select * from participant;

-- IMPORT OF TEAM TABLE INTO DWH
drop table dwh_athl_1_t_team;
create table dwh_athl_1_t_team as 
select * from team;

-- IMPORT OF ATHLETE TABLE INTO DWH
drop table dwh_athl_1_t_athlete;
create table dwh_athl_1_t_athlete as 
select * from athlete;


-- IMPORT OF RACE TABLE INTO DWH
drop table dwh_athl_1_t_race;
create table dwh_athl_1_t_race as 
select * from race;

-- IMPORT OF COMPETITION TABLE INTO DWH
drop table dwh_athl_1_t_competition;
create table dwh_athl_1_t_competition as 
select * from competition;

-- IMPORT OF RESULT TABLE INTO DWH
drop table dwh_athl_1_t_result;
create table dwh_athl_1_t_result as 
select * from result;

