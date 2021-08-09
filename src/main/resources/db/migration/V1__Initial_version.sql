CREATE SEQUENCE SEQ_PLATFORM_USER START 1;
CREATE TABLE PLATFORM_USER
(
    ID INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('SEQ_PLATFORM_USER'),
    USERNAME VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    NAME VARCHAR,
    SURNAME VARCHAR,
    EMAIL VARCHAR ,
    ROLE_ID INTEGER ,
    POINTS INTEGER ,
    ACTIVE BOOL,
    AVATAR_URL VARCHAR (255),
    CREATION_DATE TIMESTAMP
);

ALTER SEQUENCE SEQ_PLATFORM_USER OWNED BY PLATFORM_USER.ID;

CREATE SEQUENCE SEQ_LESSON_GROUP START 1;
CREATE TABLE LESSON_GROUP
(
    ID INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('SEQ_LESSON_GROUP'),
    NAME VARCHAR(255),
    ACCESS_LEVEL INTEGER
);

ALTER SEQUENCE SEQ_LESSON_GROUP OWNED BY LESSON_GROUP.ID;

CREATE SEQUENCE SEQ_LESSON START 1;
CREATE TABLE LESSON
(
    ID INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('SEQ_LESSON'),
    LESSON_GROUP_ID INTEGER,
    LESSON_NAME VARCHAR(255),
    CONSTRAINT LESSON_GROUP_ID_FK
        FOREIGN KEY(LESSON_GROUP_ID)
            REFERENCES LESSON_GROUP(ID)
            ON DELETE SET NULL
);

ALTER SEQUENCE SEQ_LESSON OWNED BY LESSON.ID;

