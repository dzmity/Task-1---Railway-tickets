CREATE TABLE dual (
test  INTEGER NOT NULL
);

CREATE TABLE countries (
  COUNTRY_ID INTEGER NOT NULL PRIMARY KEY,
  COUNTRY_NAME VARCHAR(60) NOT NULL,
--  CONSTRAINT "COUNTRIES_PK" PRIMARY KEY ("COUNTRY_ID")
);

 CREATE SEQUENCE COUNTRIES_SEQUENCE START WITH 238 INCREMENT BY 1;
 
SAVEPOINT SP;

--CREATE TRIGGER COUNTRIES_ID_TRG 
--before insert on countries
--for each row
--begin
--  if :new.country_id is null then
--    select COUNTRIES_SEQUENCE.nextval into :new.country_id from dual;
--  end if;
--end;
