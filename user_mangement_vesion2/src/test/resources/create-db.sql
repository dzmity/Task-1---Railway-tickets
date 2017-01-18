--DROP TABLE countries IF EXISTS;
--DROP TABLE dual IF EXISTS;
--DROP TABLE regions IF EXISTS;

DROP SCHEMA PUBLIC CASCADE;

CREATE TABLE countries (
  COUNTRY_ID INTEGER NOT NULL PRIMARY KEY,
  COUNTRY_NAME VARCHAR(60) NOT NULL,
--  CONSTRAINT "COUNTRIES_PK" PRIMARY KEY ("COUNTRY_ID")
);


CREATE TABLE dual (
test  INTEGER NOT NULL
);

CREATE TABLE regions (
  REGION_ID INTEGER NOT NULL PRIMARY KEY,
  REGION_NAME VARCHAR(80) NOT NULL,
  COUNTRY_ID INTEGER NOT NULL,
);



 CREATE SEQUENCE COUNTRIES_SEQUENCE START WITH 278 INCREMENT BY 1;
 CREATE SEQUENCE REGIONS_SEQUENCE START WITH 1611 INCREMENT BY 1;
 


--CREATE TRIGGER COUNTRIES_ID_TRG 
--before insert on countries
--for each row
--begin
--  if :new.country_id is null then
--    select COUNTRIES_SEQUENCE.nextval into :new.country_id from dual;
--  end if;
--end;
