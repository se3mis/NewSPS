INSERT INTO "PRODMIS"."SPSTDESTHMT" (APP_NO,STD_NO,JOBNAME,CONTINGENCY,DESCRIPTION,TOTAL_COST,SECDEPOSIT,PROJECTTYPE,DEPT_ID,STATUS,ENTRY_BY,ENTRY_DATE,APPROVED_BY,REJECTED_BY,REJECTED_DATE,REJ_RESON_CE,PLANING_BY,PLANING_DATE,POST_DEPTID,REBATE_COST,CEBCOST,TOCONPAY,CON_COST,APPROVED_DATE) VALUES ('550/553/BULK/2013/282','550/553/BULK/2013/282',null,null,'63kVA 33kV S/S,  1.2km 33kV Racoon Cond., DDLO Set 01,  Fusie Switch 02 Nos.',4646790,78800,null,'550.00',10,'ee1comsbp',{d '2013-02-15'},'dgmsbp',null,null,null,'pecomsab',{d '2013-04-17'},'550.20',0,2124800,2521990,4646790,'17-APR-13');
INSERT INTO "PRODMIS"."SPSTDESTHMT" (APP_NO,STD_NO,JOBNAME,CONTINGENCY,DESCRIPTION,TOTAL_COST,SECDEPOSIT,PROJECTTYPE,DEPT_ID,STATUS,ENTRY_BY,ENTRY_DATE,APPROVED_BY,REJECTED_BY,REJECTED_DATE,REJ_RESON_CE,PLANING_BY,PLANING_DATE,POST_DEPTID,REBATE_COST,CEBCOST,TOCONPAY,CON_COST,APPROVED_DATE) VALUES ('550/558/BULK/2013/392','550/558/BULK/2013/392',' ',null,'100kVA S/S, 0.015km Racoon',1464795,125000,null,'550.00',10,'ee1comsbp',{d '2013-02-27'},'dgmsbp',null,null,null,'pecomsab',{d '2013-04-24'},'550.20',0,0,1464795,1464795,'24-APR-13');


 alter table SPSTDESTHMT modify APP_NO varchar(25);
  alter table SPSTDESTDMT modify APP_NO varchar(25);
    alter table SPSTDESTHMT modify DESCRIPTION varchar(150);
    alter table SPSTDESTDMT modify STD_NO varchar(25);
     alter table SPESTEDY_CONS modify REFERENCE_NO varchar(25);
     alter table estimate_referencebs modify SESTIMATE_NO varchar(25);
     
     alter table SPESTEDY_CONS modify DESCRIPTION varchar(200);
     select count(*) from appestlim where application_type='DE' and user_level='DGM' and status_to=30
update appestlim set status_to=22 where application_type='DE' and user_level='DGM' and status_to=30
     alter table Pegschdmt modify NODEDES varchar(100);
     
     alter table SPSTDESTHMT modify JOBNAME varchar(150);
     alter table APPLICANT modify LAST_NAME varchar(60);
      alter table sppeginfomation modify name varchar(150);
     
     CREATE TABLE "TSTMIS"."PROVINCE_DETAIL_MASTER"
(
    DEPT_ID char(6) NOT NULL,
    CODE varchar2(4) NOT NULL,
    CODE_NAME varchar2(20),
    CODE_TYPE varchar2(15),
    
    CONSTRAINT PK_PROVINCE_DETAIL_MASTER PRIMARY KEY (DEPT_ID,CODE,CODE_TYPE)
)
;

alter table sppointdmt modify estimate_qty float(10);
create table  sppointdmtall as (select * from sppointdmt) 
alter table sppointdmt add estimate_qty1 number(10,2);
alter table sppointdmt rename column estimate_qty1 TO estimate_qty;
alter table sppointdmt rename column estimate_qty TO estimate_qtyold;

alter table pcinitialdata add VARIANCE_N number(10,2);
alter table pcinitialdata add DETAIL_COST number(15,2);
alter table pcinitialdata add DISTRICT varchar(30);
alter table pcinitialdata add SERVICEDEPONAME varchar(30);
alter table pcinitialdata add ES_ALLOCATED_DATE date;
alter table pcinitialdata add ELECTORATE varchar(30);
alter table pcinitialdata add DISTRICT varchar(30);
 alter table APPROVAL modify reason varchar(500);
 alter table pcesthtt modify reject_reason varchar(800);
 alter table SPESTEDY modify DESCRIPTION   varchar(200);
 
 alter table pcinitialdata modify AREA_CODE varchar(40);
 
  alter table wiring_land_detail_con modify SCHEME_NO varchar(200);
  
   alter table wiring_land_detail_con modify REPCONTACT varchar(200);
  alter table wiring_land_detail_con modify REP2CONTACT varchar(200);
   alter table wiring_land_detail_con modify REPRESENTATIVE varchar(200);
  //not done yet
   CREATE TABLE "TSTMIS"."SPS_REPORT_CONFIGURATIONS"
(
    ID number NOT NULL,
    TYPE varchar2(10) NOT NULL,
    REGION varchar2(20) NOT NULL,
    VALUE varchar2(15),
    
    CONSTRAINT PK_SPS_REPORT_CONFIGURATIONS PRIMARY KEY (DEPT_ID,CODE,CODE_TYPE)
)
;


CREATE TABLE "TSTMIS"."SPS_USER_VALIDATION_LEVEL"
(
    dept_id varchar2(6) NOT NULL,
    jobType varchar2(50) NOT NULL,
    UserlevelFrom varchar2(10) NOT NULL,
    UserlevelToList varchar2(200) NOT NULL,
   
    
    CONSTRAINT PK_SPS_USER_VALIDATION_LEVEL PRIMARY KEY (DEPT_ID,jobType)
)
;


select st.std_no,st.status from spstdesthmt st , applications app where st.std_no=app.application_no and app.application_type='RI' and st.dept_id=app.dept_id and st.status=60

update spstdesthmt set status=60 where std_no in (select std_no from spstdesthmt st , applications app where st.std_no=app.application_no and app.application_type='RI' and st.dept_id=app.dept_id and st.status=50)


CREATE TABLE "TSTMIS"."PROVINCE_BRANCH"
(
    dept_id char(6) NOT NULL,
    
    brnach_name varchar2(10) NOT NULL,
    branch_code varchar2(200) NOT NULL,
    parent_id varchar(6) NOT NULL,
    
    CONSTRAINT PK_PROVINCE_BRANCH PRIMARY KEY (dept_id,branch_code)
)
;

ALTER TABLE PROVINCE_BRANCH
ADD CONSTRAINT FK_PROVINCE_BRANCH_deptId
FOREIGN KEY (DEPT_ID)
REFERENCES GLDEPTM(DEPT_ID)

;

alter table PROVINCE_BRANCH add commercial_Id char(6);


CREATE TABLE "TSTMIS"."PROVINCE_BRANCH_JOBTYPE"
(
    dept_id char(6) NOT NULL,
    
   
    branch_code varchar2(200) NOT NULL,
    jobType varchar(50) NOT NULL,
    jobTypeCode varchar(6) NOT NULL,
    CONSTRAINT PK_PROVINCE_BRANCH_JOBTYPE PRIMARY KEY (dept_id,branch_code,jobTypeCode)
)
;

ALTER TABLE PROVINCE_BRANCH_JOBTYPE
ADD CONSTRAINT FK_PROVINCE_JOBTYPE_deptId
FOREIGN KEY (DEPT_ID)
REFERENCES GLDEPTM(DEPT_ID)

;


alter table PROVINCE_BRANCH modify BRNACH_NAME varchar(20) ;
alter table PROVINCE_BRANCH_JOBTYPE add estimate_type varchar(4) ;
alter table PROVINCE_BRANCH modify BRNACH_NAME varchar(50) ;
INSERT INTO "TSTMIS"."PROVINCE_BRANCH" (DEPT_ID,BRNACH_NAME,BRANCH_CODE,PARENT_ID,COMMERCIAL_ID) 
VALUES ('510.00' /*not nullable*/,'s' /*not nullable*/,'COMMERCIAL' /*not nullable*/,'WPS1' /*not nullable*/,null);


INSERT INTO "TSTMIS"."PROVINCE_BRANCH_JOBTYPE" (DEPT_ID,BRANCH_CODE,JOBTYPE,JOBTYPECODE)
 VALUES ('510.00' /*not nullable*/,'COMMERCIAL' /*not nullable*/,'Bulk Supply' /*not nullable*/,'BS' /*not nullable*/);
INSERT INTO "TSTMIS"."PROVINCE_BRANCH_JOBTYPE" (DEPT_ID,BRANCH_CODE,JOBTYPE,JOBTYPECODE)
 VALUES ('510.00' /*not nullable*/,'COMMERCIAL' /*not nullable*/,'Land' /*not nullable*/,'LN' /*not nullable*/);
INSERT INTO "TSTMIS"."PROVINCE_BRANCH_JOBTYPE" (DEPT_ID,BRANCH_CODE,JOBTYPE,JOBTYPECODE)
 VALUES ('510.00' /*not nullable*/,'COMMERCIAL' /*not nullable*/,'Rural Electrification' /*not nullable*/,'RE' /*not nullable*/);
INSERT INTO "TSTMIS"."PROVINCE_BRANCH_JOBTYPE" (DEPT_ID,BRANCH_CODE,JOBTYPE,JOBTYPECODE)
 VALUES ('510.00' /*not nullable*/,'COMMERCIAL' /*not nullable*/,'Rural Electrification Project' /*not nullable*/,'RI' /*not nullable*/);
INSERT INTO "TSTMIS"."PROVINCE_BRANCH_JOBTYPE" (DEPT_ID,BRANCH_CODE,JOBTYPE,JOBTYPECODE)
 VALUES ('510.00' /*not nullable*/,'PLANNING' /*not nullable*/,'Planning and Development' /*not nullable*/,'PL' /*not nullable*/);
 
 
INSERT INTO "TSTMIS"."PROVINCE_BRANCH" (DEPT_ID,BRNACH_NAME,BRANCH_CODE,PARENT_ID,COMMERCIAL_ID) 
VALUES ('520.00' /*not nullable*/,'s' /*not nullable*/,'COMMERCIAL' /*not nullable*/,'SP' /*not nullable*/,null);


INSERT INTO "TSTMIS"."PROVINCE_BRANCH_JOBTYPE" (DEPT_ID,BRANCH_CODE,JOBTYPE,JOBTYPECODE)
 VALUES ('520.00' /*not nullable*/,'COMMERCIAL' /*not nullable*/,'Bulk Supply' /*not nullable*/,'BS' /*not nullable*/);
INSERT INTO "TSTMIS"."PROVINCE_BRANCH_JOBTYPE" (DEPT_ID,BRANCH_CODE,JOBTYPE,JOBTYPECODE)
 VALUES ('520.00' /*not nullable*/,'COMMERCIAL' /*not nullable*/,'Land' /*not nullable*/,'LN' /*not nullable*/);
INSERT INTO "TSTMIS"."PROVINCE_BRANCH_JOBTYPE" (DEPT_ID,BRANCH_CODE,JOBTYPE,JOBTYPECODE)
 VALUES ('520.00' /*not nullable*/,'COMMERCIAL' /*not nullable*/,'Rural Electrification' /*not nullable*/,'RE' /*not nullable*/);
INSERT INTO "TSTMIS"."PROVINCE_BRANCH_JOBTYPE" (DEPT_ID,BRANCH_CODE,JOBTYPE,JOBTYPECODE)
 VALUES ('520.00' /*not nullable*/,'COMMERCIAL' /*not nullable*/,'Rural Electrification Projects' /*not nullable*/,'RI' /*not nullable*/);
INSERT INTO "TSTMIS"."PROVINCE_BRANCH_JOBTYPE" (DEPT_ID,BRANCH_CODE,JOBTYPE,JOBTYPECODE)
 VALUES ('520.00' /*not nullable*/,'PLANNING' /*not nullable*/,'Planning and Development' /*not nullable*/,'PL' /*not nullable*/);
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 /////////////////////////
 
 
 CREATE TABLE "PRODMIS"."SPPEGGINGDMT"
(
   LINESECTIONTYPEID varchar2(200) NOT NULL,
   POLETYPEID char(100),
   POINTTYPEID varchar2(200),
   RES_TYPE char(20),
   RES_CD char(20) NOT NULL,
   RES_CAT decimal(20),
   RES_NAME char(150),
   UOM char(4),
   ESTIMATE_QTYOLD number(10,4),
   TOLERANCE decimal(22),
   UNIT_PRICE float(8),
   DEPT_ID varchar2(6),
   ESTIMATE_QTY number(10,4),
   CONSTRAINT PK_SPPEGGINGDMT PRIMARY KEY (LINESECTIONTYPEID,RES_CD,DEPT_ID)
)
;


CREATE TABLE "PRODMIS"."SPPEGINFORMATION"
(
   ID varchar2(25) NOT NULL,
   NAME varchar2(150),
   DESCRIPTION varchar2(150),
   DEPT_ID varchar2(6) NOT NULL,
   PARENT_ID varchar2(25) NOT NULL,
   CONSTRAINT SYS_SPPEGINFORMATION PRIMARY KEY (ID,PARENT_ID,DEPT_ID)
)
;

select count(*) from SPPEGGINGDMT-11,363
select count(*) from SPPEGINFORMATION -1,972

alter table SPPEGGINGDMT modify ESTIMATE_QTY number(10,4);