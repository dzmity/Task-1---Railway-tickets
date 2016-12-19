CREATE TRIGGER COUNTRY_ID_TRG 
BEFORE INSERT ON countries REFERENCING NEW ROW AS NEWROW 
FOR EACH ROW
	BEGIN ATOMIC
	IF NEWROW.COUNTRY_ID is NULL THEN
--	SELECT COUNTRIES_SEQUENCE.NEXTVAL INTO NEWROW.COUNTRY_ID; 
--	select nexval(COUNTRIES_SEQUENCE);
--	VALUES NEXT VALUE FOR COUNTRIES_SEQUENCE;
	select NEXT VALUE FOR COUNTRIES_SEQUENCE INTO NEWROW.COUNTRY_ID from dual;
    END IF;   
END;
/;
SAVEPOINT SPS/;
