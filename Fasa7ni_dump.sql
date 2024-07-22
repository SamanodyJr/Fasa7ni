DROP DATABASE Fasa7ni;
CREATE DATABASE Fasa7ni;
USE Fasa7ni;
DROP TABLE IF EXISTS Tags;
CREATE TABLE Tags (
  Tag_Name varchar(100) NOT NULL,
  PRIMARY KEY (Tag_Name)
) ;

INSERT INTO Tags VALUES ('Active'),('Adventure'),('Arcade'),('Asian'),('Basketball'),('Billiards'),('Boba'),('Bowling'),('Breakfast'),('Burger'),('Church'),('Cinema'),('Coffee'),('Concert'),('Cultural'),('Dessert'),('Dine in'),('Drive Thru'),('Entertainment'),('Escape Rooms'),('Fancy'),('Fitness'),('Football'),('Fun'),('Grill'),('Handball'),('Historic'),('Horror'),('Horses'),('Ice Cream'),('Italian'),('Juices'),('Karaoke'),('Koshary'),('Landmark'),('Laser Tag'),('Mexican'),('Mosque'),('Museum'),('Music'),('Oriental'),('Padel'),('Paintball'),('Palaces'),('Park'),('Party'),('Pasta'),('Picnics'),('Pizza'),('Rowing'),('Seafood'),('Shami'),('Shawerma'),('Smoothies'),('Squash'),('Stand Up Comedy'),('Steak'),('Sushi'),('Swimming'),('Synagogue'),('Table tennis'),('Take away'),('Tennis'),('Theatre'),('Track'),('Volleyball'),('Yoga'),('Zoo');

DROP TABLE IF EXISTS User;

CREATE TABLE User (
  FirstName varchar(255) DEFAULT NULL,
  LastName varchar(255) DEFAULT NULL,
  Area varchar(255) DEFAULT NULL,
  Phone varchar(11) DEFAULT NULL,
  Username varchar(200),
  Pass varchar(255) DEFAULT NULL,
  ProfilePic varchar(255) DEFAULT 'default.png',
  BirthDate varchar(10) DEFAULT "",
  NationalID varchar(255) DEFAULT NULL,
  PRIMARY KEY (Username)
);

INSERT INTO User VALUES ('Fasa7ni','Tsaw7ni',NULL,'01119992223','Fasa7_7aba','Hello','\"\"','2024-07-13',NULL),('Hussien','Heggi','New Cairo','01288952276','heggo','heJJi','Heggi.png','2002-04-12','30212041201217'),('Mariam','ElGhobary','Sheikh Zayed','01099367800','mariamghobz','ghobzzz','Mariam.png','2003-11-24','30402452010085'),('Nour','Abdalla','Maadi','01277887710','nouryasser1','nour123','Nour.jpeg','2002-12-04','30212040102622'),('Sarah','ElSamanody','Katameya','01122786679','samanodyjr','soso@sasa','Sarah.png','2004-02-22','30402220106085'),('Eslam','taw','Zamalek','01227111420','taw','tawtawtaw','Taw.png','1990-01-01','30402520166085');



DROP TABLE IF EXISTS Places;

CREATE TABLE Places (
  Place_Name varchar(255) NOT NULL,
  Description text,
  Phone varchar(255) DEFAULT NULL,
  OpeningTime varchar(6) DEFAULT NULL,
  ClosingTime varchar(6) DEFAULT NULL,
  WorkingDays varchar(255) DEFAULT NULL,
  PlacePic varchar(255) DEFAULT NULL,
  PRIMARY KEY (Place_Name)
);

INSERT INTO Places VALUES ('Adrenaline Park-Laser Tag','Adrenalin is the largest paintball field in North Africa and one of the best venues world wide for scenario and missions based games, which makes you experience a real battle in a safe and fun environment.','1225001008','11:00','21:00','Sunday-Saturday','Adrenaline_Park-Laser_Tag.png'),('Alexandria Bibliotheca','The Bibliotheca Alexandrina is a major library and cultural center on the shore of the Mediterranean Sea in Alexandria, Egypt. It is a commemoration of the Library of Alexandria, once one of the largest libraries worldwide, which was lost in antiquity.','34839999','10:00','19:00','Saturday-Thursday','bibliotecha.png'),('B-URN','It\'s simple. B-URN is more than just a workout. It\'s a place, it\'s a feeling, it\'s a moment, it\'s a community of like minded humans moving their bodies, elevating their minds, and having a great time doing it in the process. B-URN offers a multitude of workout classes: Versaclimber, Megaformer, and Mat. Don\'t want to workout? No problem, come hang at the B-AR, or shop our comfy line of clothing. Don\'t feel like doing that either? It\'s all good, come surrender on our happy bench and let your worries melt away.','1097067096','08:00','22:00','Sunday-Saturday','B-URN.png'),('BeFit 360','In 2013, Aly Mazhar, an athlete, and coach founded BeFit 360, during a time where the whole idea of Functional Training was still new, yet our aim was clear; helping people reach their full potential no matter what their story is.\n\nMazhar had one mission: providing people with a better quality of life through his passion; health & fitness.\n\nStarting with a single training session per day on a gym rooftop, then grew into the leading and fastest growing entity in the Egyptian market.','1101360360','07:00','23:00','Sunday-Saturday','BeFit_360.png'),('Bounce Egypt','BOUNCEinc is the biggest Australian entertainment phenomenon in the world. For all ages starting 3 yrs old. The Cairo Festival City BOUNCE venue features the first-ever Ninja Warrior track in Egypt along with different types of trampolines and the BIG BAG. The park also includes dodge-ball, basketball on the trampolines and a lot more.','1156599700','10:00','00:00','Sunday-Saturday','Bounce_Egypt.png'),('BRGR','Savor the taste of signature sliders, crafted with fresh ingredients and bursting with flavor. Our menu offers classic and unique options for all tastes. Experience the delicious magic of our sliders now!','1066664800','11:00','00:00','Sunday-Saturday','BRGR.png'),('Brown Nose Coffee','This business roasts coffee beans and provides barista training and support to partners.\nThey aim to source the finest coffee beans from across the globe and roast them to perfection.\nCustomers can subscribe to receive freshly roasted coffee beans delivered right to their door.','1050868132','08:00','00:00','Sunday-Saturday','Brown_Nose_Coffee.png'),('Bunker','Bowling, Arcade, Adrenaline Games, Raceway, Local Fashion Retail, F&B and Art Exhibition. One stop destination for all sorts of Entertainment. Game night just got a whole lot better','1554834515','14:00','03:00','Sunday-Saturday','Bunker.png'),('C.O.D.E Paintball Egypt','Call of Duty, Egypt (CODE) Paintball is a paintball field for everyone in new cairo - Cairo - Egypt . From the first time player, to the most experienced player, we offer field to accommodate everyone\'s needs. Call of Duty Egypt paintball has developed professional paintball facility located in new cairo - Cairo - Egypt, Paintball is a game - Paintball is a sport - Paintball is a team building - Paintball is a recreation... Come and join CODE. to feel the adrenaline rush, feel the fun and accomplish your mission, unleash your energy...','1092988861','12:00','22:00','Sunday-Saturday','C.O.D.E_Paintball_Egypt.png'),('Cairo Tower','The Cairo Tower or ElGezira tower or ( Brag El qahari) in Arabic language is consider one of the most prominent features of the Egyptian capital .Its partially open lattice-work design is intended to evoke a lotus plant. Cairo tower was built from 1956 to 1961 during the presidency of Gamal Abdel Nasser with Soviet assistance. The Cairo Tower Height is 187m high, it is higher than the great pyramid of Giza by 50 meters','227365112','09:00','01:00','Sunday-Saturday','Cairo_Tower.png'),('Citadel of Salah El-Din','The Citadel of Cairo or Citadel of Saladin is a medieval Islamic-era fortification in Cairo, Egypt, built by Salah ad-Din and further developed by subsequent Egyptian rulers.','225121735','08:00','16:30','Sunday-Saturday','Citadel_of_Salah_El-Din.png'),('Go!Padel','A missed opportunity is never a lost point. That\'s what padel is all about! This sport has been around for many years. It is extensively played in Spain and Latin America. It is now spreading rapidly across Europe and other continents, finally GO! Padel is bringing the world\'s fastest growing sport to Egypt\'s ravishing Katameya Heights. Padel is great for players of all ages and skills, as it is both quick and easy to pick up. You can get the grasp of it within the first 20 minutes of playing and find it easy to achieve a level of proficiency. GO! Padel is not just a new wave, it simply livens up your life!','1159005500','10:00','00:00','Sunday-Saturday','padel.png'),('Nile Kayak Club','We are operating in Cairo, Alexandria and Aswan.\nLeading recreational kayak, SUP rides and water games but also managing racing kayak and SUP training.\nLeading excursions over the Nile from Aswan (more details in Aswan Kayak Excursions).\nAnd managing watersports events.\nYearly in November we organize Aswan Water Sports Festival, where we host SUP EuroTour competition.','1010013335','06:00','17:00','Friday-Saturday','Nile_Kayak_Club.png'),('Osana','Osana family wellness is based in a charming restored villa in El Sarayat, Maadi. We offer holistic and healing activities for people of all ages. These include: yoga, Pilates and other client-based barefoot classes, as well as treatments such as homeopathy, massage and Reiki. We also offer pre and postnatal workshops, classes and support, as well as baby, child and family classes. All this is completed with a gorgeous wholefood cafe, wonderful garden & outdoor play area, and a carefully sourced boutique. We look forward to greeting you soon at Osana.','1220221100','08:00','20:00','Sunday-Saturday','Osana.png'),('Sizzler','Sizzler is the first specialized Steakhouse in Cairo since 2014. With our first branch in Maadi,\nwe have decided to get closer to the pulsing energy of Cairo, extending our branches to eight other different locations: City Stars, CFC, Mall of Egypt, Point 90, Mohandessin,\nMall of Arabia, & City Center Almaza. Distinguished with our talent to details,we take our rightful place at the very heart of our customers.','225202630','12:00','12:00','Sunday-Saturday','Sizzler.png'),('Spectra','As the team of Spectra Restaurant & Cafe, we aim to promote the food industry through the branches we establish and through the new dishes\nthat we are proud and honored to present to you through our branches. Spectra is one of the most famous restaurants and cafes in Egypt that\nprovide fresh and fine-dining food, for more than 20 years serving food that suits the tastes of our customers, and 15 branches to be the nearest\nrestaurants for our customers to share every moment with them. We always strive to provide a friendly atmosphere with high-quality food to enjoy our customers.','19491','12:00','00:00','Sunday-Saturday','Spectra.png'),('The Egyptian Museum in Cairo','The Museum of Egyptian Antiquities, commonly known as the Egyptian Museum, located in Cairo, Egypt, houses the largest collection of Egyptian antiquities in the world. It houses over 120,000 items, with a representative amount on display.',NULL,'09:00','17:00','Sunday-Saturday','The_Egyptian_Museum_in_Cairo.png'),('The Great Pyramid of Giza','The Great Pyramid of Giza is the largest Egyptian pyramid. It served as the tomb of pharaoh Khufu, who ruled during the Fourth Dynasty of the Old Kingdom.',NULL,'08:00','16:30','Sunday-Saturday','The_Great_Pyramid_of_Giza.png'),('The House','The House\" is the first Escape House in Egypt, It is a game for fun','1027579279','14:00','00:00','Sunday-Saturday','The_House.png'),('What the Crust','We\'ve gone to great lengths to bring real Neapolitan technique and tradition to Cairo, and we\'re Africa\'s first and only AVPN affiliate pizzeria.\nThis means that we make something truly authentic; from handmade Neapolitan ovens and top quality local and imported Italian ingredients, to our AVPN-certified pizzaiola and highly-coveted official AVPN affiliation - everything that goes into making a pizza is exactly as it would be in Naples, and has been for generations. The end result is a pizza probably unlike anything you\'ve had in Egypt before.','1005764631','03:00','23:00','Sunday-Saturday','What_the_Crust.png');


DROP TABLE IF EXISTS Categories;
CREATE TABLE Categories (
  Cat_Name varchar(100) NOT NULL,
  PRIMARY KEY (Cat_Name)
);

INSERT INTO Categories VALUES ('Activities'),('Food'),('Sightseeing'),('Sports');

DROP TABLE IF EXISTS Fosa7;

CREATE TABLE Fosa7 (
  Fos7a_Name varchar(255) NOT NULL,
  Host_Username varchar(200) NOT NULL,
  Place_Name varchar(255) DEFAULT NULL,
  Description text,
  Capacity int DEFAULT NULL,
  Fos7a_Time varchar(10) NOT NULL,
  Fos7a_Date varchar(10) NOT NULL,
  Pic varchar(255) DEFAULT NULL,
  Is_Public tinyint(1) DEFAULT NULL,
  PRIMARY KEY (Host_Username,Fos7a_Name,Fos7a_Date,Fos7a_Time),
  KEY Place_Name (Place_Name),
  CONSTRAINT fosa7_ibfk_1 FOREIGN KEY (Host_Username) REFERENCES User (Username),
  CONSTRAINT fosa7_ibfk_2 FOREIGN KEY (Place_Name) REFERENCES Places (Place_Name)
);
INSERT INTO Fosa7 VALUES ('Laser Tag','heggo','Adrenaline Park-Laser Tag','Yalla Laser Tagggg, we\'ve been wanting to do this for so long, now\'s the time engzo baa',10,'18:00','2024-07-25','Adrenaline_Park-Laser_Tag.png',0),('One of the 7 wonders?','nouryasser1','The Great Pyramid of Giza','If you are either Egyptian or non-Egyptian and you\'re interested in historic landmarks, feel free to take part of this fos7a. We can explore the surrounding necropolis, including the Sphinx, and discuss together the Egyptian mythology and beliefs about the afterlife.',15,'10:00','2024-07-19','The_Great_Pyramid_of_Giza.png',1),('Padel?','samanodyjr','Go!Padel','This for anyone who loves padel, even if you\'re not a pro. If you can join and we can do it as a weekly sport activity',8,'19:00','2024-07-20','padel.png',1);

DROP TABLE IF EXISTS Address;
CREATE TABLE Address (
 Place_Name varchar(255) NOT NULL,
 Address varchar(255) NOT NULL,
 Area varchar(255) NOT NULL,
  PRIMARY KEY (Place_Name,Address),
  CONSTRAINT address_ibfk_1 FOREIGN KEY (Place_Name) REFERENCES Places (Place_Name)
);

INSERT INTO Address VALUES ('Adrenaline Park-Laser Tag','Dandy Mall, 28km, Cairo - Alex Desert Rd, Giza Governorate','Giza'),('Alexandria Bibliotheca','Bab Sharqi, Alexandria Governorate 21526','Alexandria'),('B-URN','New Giza Golf Club House, 6th of October','Sheikh Zayed'),('BeFit 360','27FP+WV, Al Abageyah, El Mokattam, Cairo Governorate 4415201','Mokattam'),('Bounce Egypt','Nasr City, Cairo Governorate 11765','Nasr City'),('BRGR','The drive by the waterway, New Cairo 1, Cairo Governorate 4720110','New Cairo'),('Brown Nose Coffee','5A By The Waterway, New Cairo 3, Cairo Governorate','New Cairo'),('Bunker','One Golden Square Mall, El Nasr, Lower Ground level, street branched from 90th road, New Cairo 3, Cairo Governorate','New Cairo'),('C.O.D.E Paintball Egypt','3F7Q+VHV, Second New Cairo, Cairo Governorate 4750301','New Cairo'),('Cairo Tower','Kasr Al Nile, Zamalek, Cairo Governorate 4270024','Zamalek'),('Citadel of Salah El-Din','27H6+G27, Privet Entrance Bel Kalaa, Al Abageyah, El Khalifa, Cairo Governorate 4252360','Cairo'),('Go!Padel','Katameya heights, New Cairo 1, Cairo Governorate 4721010','Katameya'),('Nile Kayak Club','Kornish Al Nile, Al Isaweyah, El Basatin, Cairo Governorate 4220004','6th of October'),('Osana','4 Al Nadi, Street, Maadi, Cairo Governorate 4212220','Maadi'),('Sizzler','Open Air Mall, New Cairo 3, Cairo Governorate 4771203','Madinaty'),('Spectra','From El-Khalifa El-Maamoun، 9 El-Tahawy, Manshîyet el Bakri, Heliopolis, Cairo Governorate','Heliopolis'),('The Egyptian Museum in Cairo','El-Tahrir Square, Ismailia, Qasr El Nil, Cairo Governorate 4272083','Downtown'),('The Great Pyramid of Giza','Al Haram, Nazlet El-Semman, Al Haram, Giza Governorate 3512201','Giza'),('The House','Street 279, Ezbet Fahmy, El Basatin, Cairo Governorate 4234006','Cairo'),('What the Crust','275 Mokarrar Street, Ezbet Fahmy, El Basatin, Cairo275 Mokarrar Street, Ezbet Fahmy, El Basatin, Cairo','Cairo');


DROP TABLE IF EXISTS Fosa7_Cats;

CREATE TABLE Fosa7_Cats (
  Cat_Name varchar(100) NOT NULL,
  Host_Username varchar(200) NOT NULL,
  Fos7a_Name varchar(255) NOT NULL,
  Fos7a_Date varchar(10) NOT NULL,
  Fos7a_Time varchar(10) NOT NULL,
  PRIMARY KEY (Host_Username,Fos7a_Name,Fos7a_Date,Fos7a_Time,Cat_Name),
  CONSTRAINT fosa7_cats_ibfk_1 FOREIGN KEY (Host_Username, Fos7a_Name, Fos7a_Date, Fos7a_Time) REFERENCES Fosa7 (Host_Username, Fos7a_Name, Fos7a_Date, Fos7a_Time),
  CONSTRAINT fosa7_cats_ibfk_2 FOREIGN KEY (Cat_Name) REFERENCES Categories (Cat_Name)
);


INSERT INTO Fosa7_Cats VALUES ('Activities','Heggo','Laser Tag','2024-07-25','18:00'),('Sightseeing','nouryasser1','One of the 7 wonders?','2024-07-19','10:00'),('Sports','samanodyjr','Padel?','2024-07-20','19:00');

DROP TABLE IF EXISTS Fosa7_Requests;

CREATE TABLE Fosa7_Requests (
  Accepted tinyint(1) DEFAULT NULL,
  Requester_Username varchar(200) NOT NULL,
  Host_Username varchar(200) NOT NULL,
  Fos7a_Name varchar(255) NOT NULL,
  Fos7a_Date varchar(10) NOT NULL,
  Fos7a_Time varchar(10) NOT NULL,
  PRIMARY KEY (Requester_Username,Host_Username,Fos7a_Name,Fos7a_Date,Fos7a_Time),
  CONSTRAINT fosa7_requests_ibfk_1 FOREIGN KEY (Requester_Username) REFERENCES User (Username),
  CONSTRAINT fosa7_requests_ibfk_2 FOREIGN KEY (Host_Username, Fos7a_Name, Fos7a_Date, Fos7a_Time) REFERENCES Fosa7 (Host_Username, Fos7a_Name, Fos7a_Date, Fos7a_Time)
);

INSERT INTO Fosa7_Requests VALUES (0,'heggo','nouryasser1','One of the 7 wonders?','2024-07-19','10:00'),(0,'heggo','samanodyjr','Padel?','2024-07-20','19:00'),(0,'mariamghobz','nouryasser1','One of the 7 wonders?','2024-07-19','10:00'),(1,'mariamghobz','samanodyjr','Padel?','2024-07-20','19:00'),(0,'nouryasser1','heggo','Laser Tag','2024-07-25','18:00'),(1,'nouryasser1','samanodyjr','Padel?','2024-07-20','19:00'),(0,'samanodyjr','nouryasser1','One of the 7 wonders?','2024-07-19','10:00'),(1,'taw','heggo','Laser Tag','2024-07-25','18:00'),(1,'taw','nouryasser1','One of the 7 wonders?','2024-07-19','10:00'),(1,'taw','samanodyjr','Padel?','2024-07-20','19:00');

DROP TABLE IF EXISTS Fosa7_Tags;

CREATE TABLE Fosa7_Tags (
  Tag_Name varchar(100) NOT NULL,
  Host_Username varchar(200) NOT NULL,
  Fos7a_Name varchar(255) NOT NULL,
  Fos7a_Date varchar(10) NOT NULL,
  Fos7a_Time varchar(10) NOT NULL,
  PRIMARY KEY (Tag_Name,Host_Username,Fos7a_Name,Fos7a_Date,Fos7a_Time),
  CONSTRAINT fosa7_tags_ibfk_1 FOREIGN KEY (Tag_Name) REFERENCES Tags (Tag_Name),
  CONSTRAINT fosa7_tags_ibfk_2 FOREIGN KEY (Host_Username, Fos7a_Name, Fos7a_Date, Fos7a_Time) REFERENCES Fosa7 (Host_Username, Fos7a_Name, Fos7a_Date, Fos7a_Time)
);

DROP TABLE IF EXISTS Friend_Requests;

CREATE TABLE Friend_Requests (
  Accepted tinyint(1) DEFAULT '1',
  Requester_Username varchar(200) NOT NULL,
  Reciever_Username varchar(200) NOT NULL,
  PRIMARY KEY (Requester_Username,Reciever_Username),
  KEY Reciever_Username (Reciever_Username),
  CONSTRAINT friend_requests_ibfk_1 FOREIGN KEY (Requester_Username) REFERENCES User (Username),
  CONSTRAINT friend_requests_ibfk_2 FOREIGN KEY (Reciever_Username) REFERENCES User (Username)
);

INSERT INTO Friend_Requests VALUES (0,'heggo','mariamghobz'),(1,'nouryasser1','heggo'), (1,'heggo','nouryasser1'),(0,'nouryasser1','mariamghobz'),(0,'nouryasser1','taw'),(0,'samanodyjr','heggo'), (1,'samanodyjr','mariamghobz'), (1,'mariamghobz', 'samanodyjr'),(0,'taw','heggo'), (0,'taw','mariamghobz'),(1,'taw','samanodyjr'), (1,'samanodyjr','taw');

DROP TABLE IF EXISTS message;

CREATE TABLE message (
  id int NOT NULL AUTO_INCREMENT,
  phone varchar(11) NOT NULL,
  body varchar(160) NOT NULL,
  timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  status tinyint(1) DEFAULT '0',
  PRIMARY KEY (id)
) ;

DROP TABLE IF EXISTS Place_Cats;

CREATE TABLE Place_Cats (
  Place_Name varchar(255) NOT NULL,
  Cat_Name varchar(100) NOT NULL,
  PRIMARY KEY (Place_Name,Cat_Name),
  KEY Cat_Name (Cat_Name),
  CONSTRAINT place_cats_ibfk_1 FOREIGN KEY (Place_Name) REFERENCES Places (Place_Name),
  CONSTRAINT place_cats_ibfk_2 FOREIGN KEY (Cat_Name) REFERENCES Categories (Cat_Name)
);

INSERT INTO Place_Cats VALUES ('Adrenaline Park-Laser Tag','Activities'),('Bounce Egypt','Activities'),('Bunker','Activities'),('C.O.D.E Paintball Egypt','Activities'),('The House','Activities'),('BRGR','Food'),('Brown Nose Coffee','Food'),('Sizzler','Food'),('Spectra','Food'),('What the Crust','Food'),('Alexandria Bibliotheca','Sightseeing'),('Cairo Tower','Sightseeing'),('Citadel of Salah El-Din','Sightseeing'),('The Egyptian Museum in Cairo','Sightseeing'),('The Great Pyramid of Giza','Sightseeing'),('B-URN','Sports'),('BeFit 360','Sports'),('Go!Padel','Sports'),('Nile Kayak Club','Sports'),('Osana','Sports');

DROP TABLE IF EXISTS Place_Tags;

CREATE TABLE Place_Tags (
  Tag_Name varchar(100) NOT NULL,
  Place_Name varchar(255) NOT NULL,
  PRIMARY KEY (Tag_Name,Place_Name),
  KEY Place_Name (Place_Name),
  CONSTRAINT place_tags_ibfk_1 FOREIGN KEY (Tag_Name) REFERENCES Tags (Tag_Name),
  CONSTRAINT place_tags_ibfk_2 FOREIGN KEY (Place_Name) REFERENCES Places (Place_Name)
);

INSERT INTO Place_Tags VALUES ('Adventure','Adrenaline Park-Laser Tag'),('Entertainment','Adrenaline Park-Laser Tag'),('Cultural','Alexandria Bibliotheca'),('Historic','Alexandria Bibliotheca'),('Fitness','B-URN'),('Yoga','B-URN'),('Fitness','BeFit 360'),('Track','BeFit 360'),('Adventure','Bounce Egypt'),('Fun','Bounce Egypt'),('Burger','BRGR'),('Smoothies','BRGR'),('Breakfast','Brown Nose Coffee'),('Coffee','Brown Nose Coffee'),('Arcade','Bunker'),('Bowling','Bunker'),('Adventure','C.O.D.E Paintball Egypt'),('Paintball','C.O.D.E Paintball Egypt'),('Cultural','Cairo Tower'),('Landmark','Cairo Tower'),('Historic','Citadel of Salah El-Din'),('Mosque','Citadel of Salah El-Din'),('Padel','Go!Padel'),('Squash','Go!Padel'),('Rowing','Nile Kayak Club'),('Swimming','Nile Kayak Club'),('Fitness','Osana'),('Yoga','Osana'),('Dine in','Sizzler'),('Steak','Sizzler'),('Dine in','Spectra'),('Pasta','Spectra'),('Historic','The Egyptian Museum in Cairo'),('Museum','The Egyptian Museum in Cairo'),('Historic','The Great Pyramid of Giza'),('Landmark','The Great Pyramid of Giza'),('Escape Rooms','The House'),('Horror','The House'),('Dine in','What the Crust'),('Pizza','What the Crust');


DROP TABLE IF EXISTS Social_Media;

CREATE TABLE Social_Media (
  Place_Name varchar(255) NOT NULL,
  Account_Name varchar(255) NOT NULL,
  SM_Type varchar(255) DEFAULT NULL,
  PRIMARY KEY (Place_Name,Account_Name),
  CONSTRAINT social_media_ibfk_1 FOREIGN KEY (Place_Name) REFERENCES Places (Place_Name)
);

INSERT INTO Social_Media VALUES ('Adrenaline Park-Laser Tag','Adrenalin Park, Egypt','Facebook'),('Adrenaline Park-Laser Tag','adrenalinegypt','Instagram'),('Alexandria Bibliotheca','bibalexofficial','Instagram'),('Alexandria Bibliotheca','Bibliotheca Alexandrina - ????? ??????????','Facebook'),('B-URN','b_urnegypt','Instagram'),('B-URN','B-URN','Facebook'),('BeFit 360','BeFit 360','Facebook'),('BeFit 360','befit.360','Instagram'),('Bounce Egypt','bounceegypt','Instagram'),('Bounce Egypt','BOUNCEinc','Facebook'),('BRGR','The BRGR Truck','Facebook'),('BRGR','thebrgrtruck','Instagram'),('Brown Nose Coffee','Brown Nose Coffee','Facebook'),('Brown Nose Coffee','brownnosecoffee','Instagram'),('Bunker','Not a bunker','Facebook'),('Bunker','notabunker','Instagram'),('C.O.D.E Paintball Egypt','c.o.d.e_paintball','Instagram'),('C.O.D.E Paintball Egypt','C.O.D.E. Paintball, Egypt','Facebook'),('Cairo Tower','Cairo Tower - ??? ???????','Facebook'),('Cairo Tower','cairotower','Instagram'),('Citadel of Salah El-Din','Salah Al-Din Citadel','Facebook'),('Citadel of Salah El-Din','salahaldincitadel','Instagram'),('Go!Padel','Go Padel','Facebook'),('Go!Padel','gopadelegypt','Instagram'),('Nile Kayak Club','Nile Kayak Club','Facebook'),('Nile Kayak Club','nilekayakclub','Instagram'),('Osana','Osana Family Wellness','Facebook'),('Osana','osanafamilywellness','Instagram'),('Sizzler','Sizzler Steak House','Facebook'),('Sizzler','sizzlersteakhouse','Instagram'),('Spectra','Spectra Restaurant & Cafe','Facebook'),('The Egyptian Museum in Cairo','egyptian.museum','Instagram'),('The Egyptian Museum in Cairo','The Egyptian Museum ?????? ??????? ?????? ??????','Facebook'),('The Great Pyramid of Giza','pyramids_of_egypt','Instagram'),('The Great Pyramid of Giza','The Great Pyramid of Giza','Facebook'),('The House','The House','Facebook'),('The House','thehouse.eg','Instagram'),('What the Crust','What the Crust','Facebook'),('What the Crust','what_the_crust','Instagram');


DROP TABLE IF EXISTS User_Interests;

CREATE TABLE User_Interests (
  Interest varchar(255) NOT NULL,
  Username varchar(200) NOT NULL,
  PRIMARY KEY (Interest,Username),
  KEY Username (Username),
  CONSTRAINT user_interests_ibfk_1 FOREIGN KEY (Interest) REFERENCES Tags (Tag_Name),
  CONSTRAINT user_interests_ibfk_2 FOREIGN KEY (Username) REFERENCES User (Username)
);

INSERT INTO User_Interests VALUES ('Adventure','mariamghobz'),('Burger','mariamghobz'),('Horror','mariamghobz'),('Padel','mariamghobz'),('Pizza','mariamghobz'),('Burger','samanodyjr'),('Coffee','samanodyjr'),('Padel','samanodyjr'),('Pizza','samanodyjr'),('Track','samanodyjr'),('Yoga','samanodyjr');