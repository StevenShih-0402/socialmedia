--------------------------------------------------------
--  已建立檔案 - 星期二-3月-25-2025   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table COMMENTS
--------------------------------------------------------

  CREATE TABLE "TOM"."COMMENTS" 
   (	"COMMENT_ID" NUMBER, 
	"USER_ID" NUMBER, 
	"POST_ID" NUMBER, 
	"CONTENT" VARCHAR2(200 BYTE), 
	"CREATED_AT" TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index COMMENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TOM"."COMMENT_PK" ON "TOM"."COMMENTS" ("COMMENT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Trigger TRG_COMMENTS_ID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "TOM"."TRG_COMMENTS_ID" 
BEFORE INSERT ON COMMENTS
FOR EACH ROW
BEGIN
    IF :NEW.COMMENT_ID IS NULL THEN
        SELECT SEQ_COMMENTS.NEXTVAL INTO :NEW.COMMENT_ID FROM DUAL;
    END IF;
END;
/
ALTER TRIGGER "TOM"."TRG_COMMENTS_ID" ENABLE;
--------------------------------------------------------
--  Constraints for Table COMMENTS
--------------------------------------------------------

  ALTER TABLE "TOM"."COMMENTS" MODIFY ("COMMENT_ID" NOT NULL ENABLE);
  ALTER TABLE "TOM"."COMMENTS" ADD CONSTRAINT "COMMENT_PK" PRIMARY KEY ("COMMENT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table COMMENTS
--------------------------------------------------------

  ALTER TABLE "TOM"."COMMENTS" ADD CONSTRAINT "FK_USER_COMMENT" FOREIGN KEY ("USER_ID")
	  REFERENCES "TOM"."USERS" ("USER_ID") ENABLE;
  ALTER TABLE "TOM"."COMMENTS" ADD CONSTRAINT "FK_POST_COMMENT" FOREIGN KEY ("POST_ID")
	  REFERENCES "TOM"."POSTS" ("POST_ID") ON DELETE CASCADE ENABLE;
