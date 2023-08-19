-- CREATION OF DIMENSIONS
drop table dwh_athl_3_dm_l_country;
create table dwh_athl_3_dm_l_country as
    select *
    from dwh_athl_2_sa_s_country;
   

drop table dwh_athl_3_dm_l_specialty;
create table dwh_athl_3_dm_l_specialty as
    select *
    from dwh_athl_2_sa_s_specialty;



drop table dwh_athl_3_dm_l_participant;
create table dwh_athl_3_dm_l_participant as
    select *
    from dwh_athl_2_sa_s_participant;

drop table dwh_athl_3_dm_l_race;
create table dwh_athl_3_dm_l_race as
    select *
    from dwh_athl_2_sa_s_race;

drop table dwh_athl_3_dm_l_result;
create table dwh_athl_3_dm_l_result as
    select *
    from dwh_athl_2_sa_s_result;       

-- CREATION OF FACTS 
drop table dwh_athl_3_dm_f_result;
create table dwh_athl_3_dm_f_result as
    select *
    from dwh_athl_2_sa_sf_result;
    
    
drop table dwh_athl_3_dm_f_medals;
create table dwh_athl_3_dm_f_medals as    
    select *
    from dwh_athl_2_sa_sf_medals
;