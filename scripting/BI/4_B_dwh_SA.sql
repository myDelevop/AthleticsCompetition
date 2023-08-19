-- CREATION OF DIMENSIONS

drop sequence country_ids_seq;
create sequence country_ids_seq start with 1;

drop table dwh_athl_2_sa_s_country;
create table dwh_athl_2_sa_s_country as
    select 
        country_ids_seq.nextval as country_ids, 
        idCountry as country_id,
        name,
        review,
        numGoldMedals,
        numSilverMedals,
        numBronzeMedals
    from dwh_athl_1_t_country;
   

drop sequence specialty_ids_seq;
create sequence specialty_ids_seq start with 1;

drop table dwh_athl_2_sa_s_specialty;
create table dwh_athl_2_sa_s_specialty as
    select 
        specialty_ids_seq.nextval as specialty_ids, 
        idSpecialty as specialty_id,
        name,
        record
    from specialty;



drop sequence participant_ids_seq;
create sequence participant_ids_seq start with 1;

drop table dwh_athl_2_sa_s_participant;
create table dwh_athl_2_sa_s_participant as
    select 
        participant_ids_seq.nextval as participant_ids, 
        P.idParticipant as participant_id,
        Country.country_ids as countryNation_ids,
        case when P.teamName is null then 'N/A' else P.teamName end as teamName,
        A.tax_code,
        A.name,
        A.surname,
        A.age,
        A.sex,
        A.weigth,
        A.heigth
    from dwh_athl_1_t_participant P
    join dwh_athl_1_t_team T
    on P.idParticipant = T.participant
        join dwh_athl_1_t_athlete A
            on t.athlete = a.tax_code
                left join dwh_athl_2_sa_s_country Country
                on P.nation = Country.country_id;


drop sequence race_ids_seq;
create sequence race_ids_seq start with 1;

drop table dwh_athl_2_sa_s_race;
create table dwh_athl_2_sa_s_race as
    select 
        race_ids_seq.nextval as race_ids,
        P.participant_ids,
        P.teamName,
        P.tax_code,
        Country.country_ids,
        S.specialty_ids,
        R.competition, 
        R.level_comp , 
        R.raceDate, 
        R.city,         
        C.description
    from dwh_athl_1_t_race R
    left join dwh_athl_1_t_competition C
    on R.competition = C.idCompetition
        left join dwh_athl_2_sa_participant P
        on R.participant = P.participant_id
            left join dwh_athl_2_sa_s_country Country
            on C.country = Country.country_id
                left join dwh_athl_2_sa_s_specialty S
                on C.specialty = S.specialty_id;        


drop sequence result_ids_seq;
create sequence result_ids_seq start with 1;

drop table dwh_athl_2_sa_s_result;
create table dwh_athl_2_sa_s_result as
    select 
        result_ids_seq.nextval as result_ids,
        P.participant_ids,
        C.idCompetition as competition,
        P.teamName,
        P.tax_code,
        Country.country_ids,
        S.specialty_ids,
        C.description,
        R.rank,
        R.result
    from dwh_athl_1_t_result R
    left join dwh_athl_1_t_competition C
    on R.competition = C.idCompetition
        left join dwh_athl_2_sa_participant P
        on R.participant = P.participant_id
            left join dwh_athl_2_sa_s_country Country
            on C.country = Country.country_id
                left join dwh_athl_2_sa_s_specialty S
                on C.specialty = S.specialty_id;       

-- CREATION OF FACTS 
drop table dwh_athl_2_sa_sf_result;
create table dwh_athl_2_sa_sf_result as
    select a.result_ids, a.participant_ids, a.competition, a.teamName, a.tax_code, a.country_ids, a.specialty_ids, a.rank, a.result
    from dwh_athl_2_sa_result a
    left join dwh_athl_2_sa_race ra
    on RA.competition = a.competition and RA.participant_ids = a.participant_ids
    -- where level_comp in (67,31) 
    order by a.competition, rank;
    
drop table dwh_athl_2_sa_sf_medals;
create table dwh_athl_2_sa_sf_medals as
    select RA.participant_ids, RA.specialty_ids, P.countrynation_ids as country_ids, RA.raceDate, RES.competition, rank,
        case when rank = 1 then 1 else 0 end as gold_medal,
        case when rank = 2 then 1 else 0 end as silver_medal,
        case when rank = 3 then 1 else 0 end as bronze_medal
    from dwh_athl_2_sa_result RES
        left join dwh_athl_2_sa_race RA
        on RA.competition = RES.competition and RA.participant_ids = RES.participant_ids
        left join dwh_athl_2_sa_s_participant P
        on RA.participant_ids = P.participant_ids
    -- where ra.level_comp in (67,31)
    order by RES.competition, rank;
    ;

/
     