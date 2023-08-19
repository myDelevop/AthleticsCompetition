/* Save into log_db_logins login and logoff events by the user of DB */

DROP TABLE log_database;
CREATE TABLE log_database (
    event_date TIMESTAMP,
    db_user VARCHAR2(30),
    activity VARCHAR2(20),
    note VARCHAR2(80));
    
CREATE OR REPLACE TRIGGER trg_log_login
    AFTER LOGON ON SCHEMA
        BEGIN
            INSERT INTO log_database 
            VALUES(SYSDATE, USER, 'LOGIN', 'Login on the current database.');
        END;

/

CREATE OR REPLACE TRIGGER trg_log_logout
    -- TODO - this trigger doesn't work!  
    BEFORE LOGOFF ON SCHEMA
        BEGIN
            INSERT INTO log_database 
            VALUES(SYSDATE, USER, 'LOGOFF', 'Logout from the current database.');
        END;
/


/****************************************************************/

/*
In order to monitoring the changes in the tables, use or not history_updates?
At the moment we consider an updata as a delete of old row, followed by an insert of the new one.
DROP TABLE history_updates;
CREATE TABLE history_updates (
    event_date DATE,
    activity VARCHAR2(20),
    table_name VARCHAR2(20),
    field_name VARCHAR2(20),
    new_value VARCHAR2(80),
    old_value VARCHAR2(80)
);
*/

DROP TABLE history_user;
CREATE TABLE history_user AS 
SELECT current_timestamp as event_date, rpad(' ', 10, ' ') as activity, a.* 
FROM auth_user a
WHERE 1=0;


-- DROP TRIGGER trg_history_user;
CREATE OR REPLACE TRIGGER trg_history_user
AFTER INSERT OR UPDATE OR DELETE ON AUTH_USER
FOR EACH ROW
DECLARE
  log_action varchar2(30);
BEGIN
    IF INSERTING THEN
        INSERT INTO history_user(event_date, activity, iduser, tax_code, name, surname, username, email)
        VALUES(current_timestamp, 'INSERT', :new.iduser, :new.tax_code, :new.name, :new.surname, :new.username, :new.email);
    ELSIF DELETING THEN
        INSERT INTO history_user(event_date, activity, iduser, tax_code, name, surname, username, email)
        VALUES(current_timestamp, 'DELETE', :old.iduser, :old.tax_code, :old.name, :old.surname, :old.username, :old.email);
    ELSIF UPDATING THEN
        /*IF UPDATING('IDUSER') THEN
            -- TODO INSERIRE WARNING/ERRORS IN UNA TABELLA DI LOG??? QUESTO SAREBBE UN WARNING, DI SOLITO L'ID NON CAMBIA
            INSERT INTO history_updates(event_date, activity, table_name, field_name, new_value, old_value)
            VALUES (sysdate);
        ELSIF UPDATING('TAX_CODE') THEN
            SELECT * FROM DUAL;
        ELSIF UPDATING('NAME') THEN
            SELECT * FROM DUAL;
        ELSIF UPDATING('SURNAME') THEN
            SELECT * FROM DUAL;
        ELSIF UPDATING('USERNAME') THEN
            SELECT * FROM DUAL;
        ELSIF UPDATING('EMAI') THEN
            SELECT * FROM DUAL;
        ELSE 
            SELECT * FROM DUAL; -- ERROR1 //;
        END IF;*/
        
        -- 1 Delete of old row
        INSERT INTO history_user(event_date, activity, iduser, tax_code, name, surname, username, email)
        VALUES(current_timestamp, 'UPDATE-DEL', :old.iduser, :old.tax_code, :old.name, :old.surname, :old.username, :old.email);

        -- 2 Insert of new row
        INSERT INTO history_user(event_date, activity, iduser, tax_code, name, surname, username, email)
        VALUES(current_timestamp, 'UPDATE-INS', :new.iduser, :new.tax_code, :new.name, :new.surname, :new.username, :new.email);
    
    ELSE 
        DBMS_OUTPUT.PUT_LINE('ERROOOOOOR'); -- ERROR2
     END IF;

END;

/

DROP TABLE history_group;
CREATE TABLE history_group AS 
SELECT current_timestamp as event_date, rpad(' ', 10, ' ') as activity, a.* 
FROM auth_group a
WHERE 1=0;


-- DROP TRIGGER trg_history_user;
CREATE OR REPLACE TRIGGER trg_history_group
AFTER INSERT OR UPDATE OR DELETE ON AUTH_GROUP
FOR EACH ROW
DECLARE
  log_action varchar2(30);
BEGIN
    IF INSERTING THEN
        INSERT INTO history_group(event_date, activity, idGroup, groupName)
        VALUES(current_timestamp, 'INSERT', :new.idGroup, :new.groupName);
    ELSIF DELETING THEN
        INSERT INTO history_group(event_date, activity, idGroup, groupName)
        VALUES(current_timestamp, 'DELETE', :old.idGroup, :old.groupName);
    ELSIF UPDATING THEN
    
        -- 1 Delete of old row
        INSERT INTO history_group(event_date, activity, idGroup, groupName)
        VALUES(current_timestamp, 'UPDATE-DEL', :old.idGroup, :old.groupName);

        -- 2 Insert of new row
        INSERT INTO history_group(event_date, activity, idGroup, groupName)
        VALUES(current_timestamp, 'UPDATE-INS', :new.idGroup, :new.groupName);
    ELSE 
        DBMS_OUTPUT.PUT_LINE('ERROOOOOOR'); -- ERROR2
     END IF;

END;

/

DROP TABLE history_psw;
CREATE TABLE history_psw AS 
SELECT current_timestamp as event_date, rpad(' ', 10, ' ') as activity, a.* 
FROM auth_psw a
WHERE 1=0;


-- DROP TRIGGER trg_history_user;
CREATE OR REPLACE TRIGGER trg_history_psw
AFTER INSERT OR UPDATE OR DELETE ON AUTH_PSW
FOR EACH ROW
DECLARE
  log_action varchar2(30);
BEGIN
    IF INSERTING THEN
        INSERT INTO history_psw(event_date, activity, idUser, psw)
        VALUES(current_timestamp, 'INSERT', :new.idUser, :new.psw);
    ELSIF DELETING THEN
        INSERT INTO history_psw(event_date, activity, idUser, psw)
        VALUES(current_timestamp, 'DELETE', :old.idUser, :old.psw);
    ELSIF UPDATING THEN
    
        -- 1 Delete of old row
        INSERT INTO history_psw(event_date, activity, idUser, psw)
        VALUES(current_timestamp, 'UPDATE-DEL', :old.idUser, :old.psw);

        -- 2 Insert of new row
        INSERT INTO history_psw(event_date, activity, idUser, psw)
        VALUES(current_timestamp, 'UPDATE-INS', :new.idUser, :new.psw);
    ELSE 
        DBMS_OUTPUT.PUT_LINE('ERROOOOOOR'); -- ERROR2
     END IF;

END;

/

DROP TABLE history_user_group;
CREATE TABLE history_user_group AS 
SELECT current_timestamp as event_date, rpad(' ', 10, ' ') as activity, a.* 
FROM auth_user_group a
WHERE 1=0;


-- DROP TRIGGER trg_history_user;
CREATE OR REPLACE TRIGGER trg_history_user_gruoup
AFTER INSERT OR UPDATE OR DELETE ON AUTH_USER_GROUP
FOR EACH ROW
DECLARE
  log_action varchar2(30);
BEGIN
    IF INSERTING THEN
        INSERT INTO history_user_group(event_date, activity, idUser, idGroup)
        VALUES(current_timestamp, 'INSERT', :new.idUser, :new.idGroup);
    ELSIF DELETING THEN
        INSERT INTO history_user_group(event_date, activity, idUser, idGroup)
        VALUES(current_timestamp, 'DELETE', :old.idUser, :old.idGroup);
    ELSIF UPDATING THEN
    
        -- 1 Delete of old row
        INSERT INTO history_user_group(event_date, activity, idUser, idGroup)
        VALUES(current_timestamp, 'UPDATE-DEL', :old.idUser, :old.idGroup);

        -- 2 Insert of new row
        INSERT INTO history_user_group(event_date, activity, idUser, idGroup)
        VALUES(current_timestamp, 'UPDATE-INS', :new.idUser, :new.idGroup);
    ELSE 
        DBMS_OUTPUT.PUT_LINE('ERROOOOOOR'); -- ERROR2
     END IF;

END;

-- TODO USE AUDIT TO CHECK WHEN AUTH_PSW IS ACCESSED BY USER (BEFORE SELECT TRIGGER NOT EXISTS)


/
