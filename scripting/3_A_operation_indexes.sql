set serveroutput on;

drop table op_exec_time;
create table op_exec_time(
    operationNum number(1),
    indexedOperation number(1),
    startTimestamp timestamp,
    endTimestamp timestamp);

-- ****************************************************
-- ******************* OPERATION 2 ********************
-- ****************************************************

-- Print the list of participants for a given competition
-- Involved tables: RACE, PARTICIPANT, TEAM, ATHLETE
-- The candidate attributes for optimize this operation are: RACE.competition, RACE.participant, 
-- PARTICIPANT.idParticipant, TEAM.participant, TEAM.athlete, ATHLETE.TaxCode.

/
create or replace procedure op2_query(indexedOperation in number) is
    startTM timestamp;
    endTM timestamp;
    type tmp_todel_t is table of number; 
    tmp_todel tmp_todel_t;
begin

startTM := CURRENT_TIMESTAMP;

select 1 bulk collect
into tmp_todel
from ( 
    select distinct R.competition, P.teamname, A.*
    from race R
    join participant P
    on R.participant = P.idParticipant
        join team T
        on R.participant = T.participant
            join athlete A
            on t.athlete = a.tax_code
    where R.competition=794
    order by P.teamname);

endTM := CURRENT_TIMESTAMP;

--DBMS_OUTPUT.PUT(startTM);

insert into op_exec_time
values (2, indexedOperation, startTM, endTM);
--values (1, indexedOperation, (dbms_utility.get_time - n)/100, CURRENT_TIMESTAMP);
end;


/

create index race_competition_idx on RACE(competition);
create index race_participant_idx on RACE(participant);
-- create index participat_idParticipant_idx on PARTICIPANT(idParticipant);
create index team_participant_idx on TEAM(participant);
create index team_athlete_idx on TEAM(athlete);
-- create index athlete_taxcode_idx on ATHLETE(tax_code);


/
begin
    for idx in 1..23
        loop op2_query(1);
    end loop;
end;

/ 
drop index race_competition_idx;
drop index race_participant_idx;
drop index team_participant_idx;
drop index team_athlete_idx;


/
begin
    for idx in 1..23
        loop op2_query(0);
    end loop;
end;
/



-- ****************************************************
-- ******************* OPERATION 3 ********************
-- ****************************************************

-- Modification of the world record
-- Involved tables: RESULT, COMPETITION, SPECIALTY
-- The candidate attributes for optimize this operation are: RESULT.competition, RESULT.rank, COMPETITION.idComeptition, 
-- COMPETITION.specialty, SPECIALTY.idSpecialty


create or replace procedure op3_query(indexedOperation in number) is
    startTM timestamp;
    endTM timestamp;
begin

startTM := CURRENT_TIMESTAMP;

update specialty 
set record = 
(
    select S.record as dummy
    from result R
    join competition C 
    on R.competition=C.idCompetition
        join specialty S
        on c.specialty = S.idSpecialty
    where R.competition = 409 and R.rank=1
)
where 1=0;


endTM := CURRENT_TIMESTAMP;

--DBMS_OUTPUT.PUT(startTM);

insert into op_exec_time
values (3, indexedOperation, startTM, endTM);
--values (1, indexedOperation, (dbms_utility.get_time - n)/100, CURRENT_TIMESTAMP);
end;


/

create index result_competition_idx on result(competition);
create index result_rank_idx on result(rank);
-- create index competition_idCompetition_idx on competition(idCompetition);
create index competition_specialty_idx on competition(specialty);
-- create index specialty_idSpecialty_idx on specialty(idSpecialty);

/
begin
    for idx in 1..23
        loop op3_query(1);
    end loop;
end;

/ 
drop index result_competition_idx;
drop index result_rank_idx;
drop index competition_specialty_idx;

/
begin
    for idx in 1..23
        loop op3_query(0);
    end loop;
end;
/





-- ****************************************************
-- ******************* OPERATION 4 ********************
-- ****************************************************

-- Print all the rankings at the end of the competition
-- it is enough to print only the result table given the ID of the competition. 
-- So, the candidate attribute for optimize this operation is RESULT.Competition and 
-- RESULT.rank (for the ORDER BY clause)

create or replace procedure op4_query(indexedOperation in number) is
    startTM timestamp;
    endTM timestamp;
    type tmp_todel_t is table of number; 
    tmp_todel tmp_todel_t;
begin

startTM := CURRENT_TIMESTAMP;

select 1 bulk collect
into tmp_todel
from ( 
    select R.*
    from result R
    where R.competition=794
    order by R.competition, R.rank);

endTM := CURRENT_TIMESTAMP;

--DBMS_OUTPUT.PUT(startTM);

insert into op_exec_time
values (4, indexedOperation, startTM, endTM);
--values (1, indexedOperation, (dbms_utility.get_time - n)/100, CURRENT_TIMESTAMP);
end;


/
create index result_competition_idx on RESULT(competition);
create index result_rank_idx on RESULT(rank);

/
begin
    for idx in 1..23
        loop op4_query(1);
    end loop;
end;

/ 
drop index result_competition_idx;
drop index result_rank_idx;


/
begin
    for idx in 1..23
        loop op4_query(0);
    end loop;
end;

/

-- ****************************************************

select
    'Operation number: ' || operationNum as operation, 
    case when indexedOperation = 1 then 'true' else 'false' end as use_of_index, 
    count(*) as num_of_launches, 
    Sum(extract( second from endTimestamp - startTimestamp )) as seconds
from op_exec_time 
group by 
    operationNum, 
    indexedOperation, 
    'Operation number: ' || operationNum,
    case when indexedOperation = 1 then 'true' else 'false' end
order by operationNum, indexedOperation;




-- CREATION OF INDEXES AGAIN:

create index race_competition_idx on RACE(competition);
create index race_participant_idx on RACE(participant);
create index team_participant_idx on TEAM(participant);
create index team_athlete_idx on TEAM(athlete);
create index result_competition_idx on result(competition);
create index result_rank_idx on result(rank);
create index competition_specialty_idx on competition(specialty);
