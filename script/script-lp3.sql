create database ftt;

use ftt;

create table artists (
	
    ID int NOT NULL PRIMARY KEY,
    NAME varchar(150),
    UF varchar(255)
);

create table draws (
	
    ID int NOT NULL PRIMARY KEY,
    ARTISTID int,
    IMG text,
    TITLE varchar(50),
    DESCRIP varchar(255),
    
    FOREIGN KEY(ARTISTID) REFERENCES artists (ID) on delete cascade
);