USE kbank;

INSERT INTO customer(firstName, lastName, gender, dateOfBirth, address, email, phone) VALUES("Peter", "Lappin", 'm', "1999-05-09", "9 Bells Row Court", "ptrlappin@aol.com", "07764374818");
INSERT INTO customer(firstName, lastName, gender, dateOfBirth, address, email, phone) VALUES("Christopher", "O Hagan", 'm', "1999-03-14", "45 Random Lane", "cohagan@gmail.com", "07429825764");
INSERT INTO customer(firstName, lastName, gender, dateOfBirth, address, email, phone) VALUES("Michael","Dunwoody", 'm', "1998-10-12", "64 Zoo Lane", "mdunwoody@hotmail.com", "07283219283");
INSERT INTO customer(firstName, lastName, gender, dateOfBirth, address, email, phone) VALUES("Peter", "Gordon", 'm', "1999-12-30", "23 Upper Crescent", "pgordon12@gmail.com", "07283740193");
INSERT INTO customer(firstName, lastName, gender, dateOfBirth, address, email, phone) VALUES("Sarah", "Quinn", 'f', "1998-04-23", "44 Standard Street", "squinnie@gmail.com", "07827501948");

INSERT INTO account(customerID, balance) VALUES(3, 100.00);
INSERT INTO account(customerID, balance) VALUES(4, 250.00);  
INSERT INTO account(customerID, balance) VALUES(2, 50.00);  
INSERT INTO account(customerID, balance) VALUES(5, 0.00);  
INSERT INTO account(customerID, balance) VALUES(1, 10000.00);  
INSERT INTO account(customerID, balance) VALUES(1, 500.00);  


