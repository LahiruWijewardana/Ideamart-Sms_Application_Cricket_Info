CREATE DATABASE cricinfo;

USE cricinfo;

CREATE TABLE Users
(Id INT AUTO_INCREMENT PRIMARY KEY,
msisdn VARCHAR(11) NOT NULL,
register ENUM("0","1","2") NOT NULL,
addTime DATETIME
);

INSERT INTO cricinfo.Users (msisdn, register, addTime) VALUES ('94772933664', '1', '2017-11-16 09:38:25');
INSERT INTO cricinfo.Users (msisdn, register, addTime) VALUES ('94778033449', '0', '2017-11-16 09:38:25');
INSERT INTO cricinfo.Users (msisdn, register, addTime) VALUES ('94771122336', '1', '2017-11-16 09:53:41');
INSERT INTO cricinfo.Users (msisdn, register, addTime) VALUES ('94776351232', '1', '2017-11-16 09:56:09');
INSERT INTO cricinfo.Users (msisdn, register, addTime) VALUES ('94770105946', '1', '2017-11-16 09:58:17');
INSERT INTO cricinfo.Users (msisdn, register, addTime) VALUES ('94775470513', '1', '2017-11-16 10:00:44');