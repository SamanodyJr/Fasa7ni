drop database Fasa7ni;
create database Fasa7ni;
use Fasa7ni;
CREATE TABLE User (
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Area VARCHAR(255),
    Phone VARCHAR(11),
    Username VARCHAR(255) PRIMARY KEY,
    Pass VARCHAR(255),
    ProfilePic TEXT, 
    BirthDate DATE,
    NationalID VARCHAR(255),
    Email VARCHAR(255) 
);
CREATE TABLE Friend_Requests (
    Accepted BOOLEAN DEFAULT true,
    Requester_Username VARCHAR(255),
    Reciever_Username VARCHAR(255),
    FOREIGN KEY (Requester_Username) REFERENCES User(Username),
    FOREIGN KEY (Reciever_Username) REFERENCES User(Username),
    PRIMARY KEY (Requester_Username, Reciever_Username)
);

CREATE TABLE Tags (
    Tag_Name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE User_Interests(
    Interest VARCHAR(255),
    Username VARCHAR(255),
    FOREIGN KEY (Interest) REFERENCES Tags(Tag_Name),
    FOREIGN KEY (Username) REFERENCES User(Username),
    PRIMARY KEY (Interest, Username)
);



CREATE TABLE Places (
    Place_Name VARCHAR(255) PRIMARY KEY,
    Description TEXT,
    Phone VARCHAR(255),
    OpeningTime TIME,
    ClosingTime TIME,
    WorkingDays VARCHAR(255),
    PlacePic TEXT
);

CREATE TABLE Fosa7 (
    Fos7a_Name VARCHAR(255),
    Host_Username VARCHAR(255),
    Place_Name VARCHAR(255),
    Description TEXT,
    Capacity INT,
    Fos7a_Time TIME,
    Fos7a_Date DATE,
    Image TEXT,
    Is_Public BOOLEAN,
    FOREIGN KEY (Host_Username) REFERENCES User(Username),
	FOREIGN KEY (Place_Name) REFERENCES Places(Place_Name),
    PRIMARY KEY (Host_Username, Fos7a_Name ,Fos7a_Date,Fos7a_Time)
);

CREATE TABLE Fosa7_Requests (
    Accepted BOOLEAN,
    Requester_Username VARCHAR(255),
    Host_Username VARCHAR(255),
    Fos7a_Name VARCHAR(255),
    Fos7a_Date DATE,
    Fos7a_Time TIME,
    FOREIGN KEY (Requester_Username) REFERENCES User(Username),
	FOREIGN KEY (Host_Username, Fos7a_Name, Fos7a_Date, Fos7a_Time) REFERENCES Fosa7(Host_Username, Fos7a_Name, Fos7a_Date, Fos7a_Time),
    PRIMARY KEY (Requester_Username, Host_Username, Fos7a_Name ,Fos7a_Date,Fos7a_Time)
);

CREATE TABLE Fosa7_Tags(
    Tag_Name VARCHAR(255),
    Host_Username VARCHAR(255),
    Fos7a_Name VARCHAR(255),
    Fos7a_Date DATE,
    Fos7a_Time TIME,
    FOREIGN KEY (Tag_Name) REFERENCES Tags(Tag_Name),
  	FOREIGN KEY (Host_Username, Fos7a_Name, Fos7a_Date, Fos7a_Time) REFERENCES Fosa7(Host_Username, Fos7a_Name, Fos7a_Date, Fos7a_Time),
    PRIMARY KEY (Tag_Name, Host_Username, Fos7a_Name ,Fos7a_Date,Fos7a_Time) 
);

CREATE TABLE Place_Tags(
    Tag_Name VARCHAR(255),
    Place_Name VARCHAR(255),
    FOREIGN KEY (Tag_Name) REFERENCES Tags(Tag_Name),
    FOREIGN KEY (Place_Name) REFERENCES Places(Place_Name),
    PRIMARY KEY (Tag_Name, Place_Name)
);

CREATE TABLE Categories (
    Cat_Name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE Fosa7_Cats (
    Cat_Name VARCHAR(255),
    Host_Username VARCHAR(255),
    Fos7a_Name VARCHAR(255),
    Fos7a_Date DATE,
    Fos7a_Time TIME,
	FOREIGN KEY (Host_Username, Fos7a_Name, Fos7a_Date, Fos7a_Time) REFERENCES Fosa7(Host_Username, Fos7a_Name, Fos7a_Date, Fos7a_Time),
    FOREIGN KEY (Cat_Name) REFERENCES Categories(Cat_Name),
    PRIMARY KEY (Host_Username ,Fos7a_Name, Fos7a_Date,Fos7a_Time, Cat_Name )

);


CREATE TABLE Place_Cats (
    Place_Name VARCHAR(255),
    Cat_Name VARCHAR(255),
    FOREIGN KEY (Place_Name ) REFERENCES Places(Place_Name),
    FOREIGN KEY (Cat_Name ) REFERENCES Categories(Cat_Name),
    PRIMARY KEY (Place_Name,Cat_Name )
);
CREATE TABLE Social_Media(
    Place_Name VARCHAR(255),
    Account_Name VARCHAR(255),
    SM_Type VARCHAR(255),
    FOREIGN KEY (Place_Name ) REFERENCES Places(Place_Name),
    PRIMARY KEY (Place_Name, Account_Name  )
);

CREATE TABLE Address(
    Place_Name VARCHAR(255),
    Address VARCHAR(255),
    FOREIGN KEY (Place_Name ) REFERENCES Places(Place_Name),
    PRIMARY KEY (Place_Name, Address  )
);

-- INSERT INTO Categories (Cat_Name) VALUES ("Sports");
-- INSERT INTO Categories (Cat_Name) VALUES ("Food");

-- INSERT INTO Places (Place_Name) VALUES ("AUC");
-- INSERT INTO Places (Place_Name) VALUES ("Hameed");

-- INSERT INTO Place_Cats (Place_Name,Cat_Name) VALUES ("AUC","Sports");
-- INSERT INTO Place_Cats (Place_Name,Cat_Name) VALUES ("Hameed","Food");

-- INSERT INTO User (Email) VALUES ("Mariam");
-- INSERT INTO User (Email) VALUES ("Heggi");
-- INSERT INTO User (Email) VALUES ("Sarah");
-- INSERT INTO User (Email) VALUES ("Nour");
-- INSERT INTO User (Email) VALUES ("Tawfik");

-- INSERT INTO Fosa7 (Fos7a_Name,Is_Public,Host_Email,Fos7a_Date,Fos7a_Time) VALUES ("Ahlan",true,"Mariam",current_date(),current_time());
-- INSERT INTO Fosa7 (Fos7a_Name,Is_Public,Host_Email,Fos7a_Date,Fos7a_Time) VALUES ("Wasahlan",false,"Mariam",current_date(),current_time());
-- INSERT INTO Fosa7 (Fos7a_Name,Is_Public,Host_Email,Fos7a_Date,Fos7a_Time) VALUES ("Epic Fos7a",false,"Heggi",current_date(),current_time());

-- INSERT INTO Friend_Requests (Reciever_Email,Requester_Email) VALUES ("Mariam","Heggi");
-- INSERT INTO Friend_Requests (Reciever_Email,Requester_Email) VALUES ("Mariam","Tawfik");

-- INSERT INTO Fosa7_Requests(Requester_Email,Host_Email,Fos7a_Name,Fos7a_Date,Fos7a_Time) VALUES ("Mariam","Heggi","Epic Fos7a",current_date(),current_time());

-- ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'SQLZammar_79';
-- FLUSH PRIVILEGES;