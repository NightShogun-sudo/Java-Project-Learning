CREATE DATABASE SampleDBws;
GO

USE SampleDBws;
GO

CREATE TABLE Registration (
    UserName VARCHAR(30) PRIMARY KEY,
    Password VARCHAR(30) NOT NULL,
    LastName NVARCHAR(50) NOT NULL,
    isAdmin BIT NOT NULL
);

INSERT INTO Registration VALUES ('U001','123','Tom',1);
INSERT INTO Registration VALUES ('U002','456','David',0);
INSERT INTO Registration VALUES ('U003','789','John',0);
INSERT INTO Registration VALUES ('U004','012','Mark',1);
INSERT INTO Registration VALUES ('U005','134','Kate',0);

SELECT * FROM Registration;