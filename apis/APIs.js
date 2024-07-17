var express = require("express");
var mysql = require('mysql');
var url = require('url');
var srv = express();
const bodyParser = require('body-parser');
const {JSON} = require("mysql/lib/protocol/constants/types");
srv.use(express.json());
srv.use(bodyParser.json());


var mysqlcon = mysql.createConnection({host: "localhost", user: "root", password: "", database : "Fasa7ni"});

mysqlcon.connect( function (err)
{
    if (err)
    {
        console.log("Error in Connection");
        throw err;
    }
    else
        console.log("Connected!");
});

srv.get('/Fetch_Places', function(req, res)
{
    console.log("Getting Places from DB...");
    var q = url.parse(req.url, true).query;
    var Category=q.Cat;

    if(Category=="All")
        var Retrieve_Query= "SELECT * FROM Places P INNER JOIN ADDRESS A ON P.Place_Name = A.Place_Name";
    else if(Category == "Near")
        var Retrieve_Query= "SELECT * FROM Places P INNER JOIN ADDRESS A ON P.Place_Name = A.Place_Name INNER JOIN USER U ON A.AREA = U.AREA WHERE A.AREA = ?" ;
    else
        var Retrieve_Query= "SELECT P.*, A.Address FROM Places P INNER JOIN Place_Cats PC ON P.Place_Name=PC.Place_Name INNER JOIN ADDRESS A ON P.PLACE_NAME = A.PLACE_NAME WHERE PC.Cat_Name=?" ;

    mysqlcon.query(Retrieve_Query,[Category], function (err, result)
    {
        if (err)
        {
            console.log("Retrieval Failed.");
            throw err;
        }
        else
        {
            console.log("Places Recieved Successfully.");
            res.send(result);
        }
    });

});

srv.get("/Fetch_Places_Socials", function (req, res) {
  console.log("Getting Places Socials from DB...");
  var q = url.parse(req.url, true).query;
  var name = q.name;
  var Retrieve_Query =
    "SELECT S.Account_Name,S.SM_Type FROM Places P INNER JOIN Social_Media S ON P.Place_Name = S.Place_Name WHERE S.Place_Name = ?";
  console.log("Retrieved Query", Retrieve_Query);

  mysqlcon.query(Retrieve_Query, [name], function (err, result) {
    if (err) {
      console.log("Retrieval Failed.");
      throw err;
    } else {
      console.log("Place Socials Recieved Successfully.");
      res.send(result);
    }
  });
});

//added sarah
srv.get("/Fetch_Places_Tags", function (req, res) {
  console.log("Getting Places Tags from DB...");
  var q = url.parse(req.url, true).query;
  var name = q.name;
  var Retrieve_Query =
    "SELECT T.Tag_Name FROM Places P INNER JOIN Place_Tags T ON P.Place_Name = T.Place_Name WHERE T.Place_Name = ?";

  mysqlcon.query(Retrieve_Query, [name], function (err, result) {
    if (err) {
      console.log("Retrieval Failed.");
      throw err;
    } else {
      console.log("Place Tags Recieved Successfully.");
      res.send(result);
    }
  });
});


srv.get('/Fetch_Fosa7', function(req, res)
{
    console.log("Getting Fosa7 from DB...");
    var q = url.parse(req.url, true).query;
    var Type=q.Type;

    if(Type=="All")
        var Retrieve_Query="SELECT * FROM Fosa7" ;
    else if (Type=="0" || Type=="1")
        var Retrieve_Query="SELECT * FROM Fosa7 WHERE Is_Public=?" ;
    else
        var Retrieve_Query="SELECT * FROM Fosa7 WHERE Place_Name=?" ;

    mysqlcon.query(Retrieve_Query,[Type], function (err, result)
    {
        if (err)
        {
            console.log("Retrieval Failed.");
            throw err;
        }
        else
        {
            console.log("Fosa7 Recieved Successfully.");
            res.send(result);
        }
    });

});

srv.get('/Fetch_Friends', function(req, res)
{

    console.log("Getting Friends from DB...");
    var q = url.parse(req.url, true).query;
    var Reciever=q.Reciever_USERNAME;

    var Retrieve_Query="SELECT Username,Accepted FROM Friend_Requests FR INNER JOIN User U ON FR.Requester_USERNAME=U.USERNAME WHERE Reciever_USERNAME=?" ;


    mysqlcon.query(Retrieve_Query,[Reciever], function (err, result)
    {
        if (err)
        {
            console.log("Retrieval Failed.");
            throw err;
        }
        else
        {
            console.log("Friends Recieved Successfully.");
            res.send(result);
        }
    });

});


srv.get('/Fetch_My_Fosa7', function(req, res)
{

    console.log("Getting my Fosa7 from DB...");
    var q = url.parse(req.url, true).query;
    var User=q.Username;

    var Retrieve_Query="SELECT F.* FROM Fosa7 F LEFT OUTER JOIN Fosa7_Requests FR ON F.Host_USERNAME=FR.Host_USERNAME AND F.Fos7a_Name=FR.Fos7a_Name AND F.Fos7a_Date=FR.Fos7a_Date AND F.Fos7a_Time = FR.Fos7a_Time WHERE F.Host_USERNAME=? OR (FR.Requester_USERNAME=? AND FR.Accepted=1)" ;


    mysqlcon.query(Retrieve_Query,[User,User], function (err, result)
    {
        if (err)
        {
            console.log("Retrieval Failed.");
            throw err;
        }
        else
        {
            console.log("My Fosa7 Recieved Successfully.");
            res.send(result);
        }
    });

});

srv.post('/Create_Fos7a', function(req, res)
{

    console.log("Adding Fos7a to DB...");
    const {Fos7a_Name, Host_USERNAME,Description,Capacity,Fos7a_Time,Fos7a_Date,Image,Is_Public,Cat_Name} = req.body;

    var Insert_Query="INSERT INTO Fosa7 (Fos7a_Name, Host_USERNAME,Description,Capacity,Fos7a_Time,Fos7a_Date,Image,Is_Public) VALUES (?,?,?,?,?,?,?,?)" ;

    mysqlcon.query(Insert_Query,[Fos7a_Name, Host_USERNAME,Description,Capacity,Fos7a_Time,Fos7a_Date,Image,Is_Public],function (err, result)
    {
        if (err)
        {
            console.log("Insertion Failed.");
            throw err;
        }
        else
        {
            console.log("Fos7a Inserted Successfully.");
            res.send(result);
        }
    });
    var Insert_Query2="INSERT INTO Fosa7_Cats (Fos7a_Name, Host_USERNAME,Fos7a_Time,Fos7a_Date,Cat_Name) VALUES (?,?,?,?,?)" ;

    mysqlcon.query(Insert_Query2,[Fos7a_Name, Host_USERNAME,Fos7a_Time,Fos7a_Date,Cat_Name],function (err, result)
    {
        if (err)
        {
            console.log("Insertion Failed.");
            throw err;
        }
        else
        {
            console.log("Fos7a Cat Inserted Successfully.");
            res.send(result);
        }
    });

});

srv.post('/Request_Fos7a', function(req, res) {

    console.log("Adding Request to DB...");
    const {
        Requester_USERNAME,
        Host_USERNAME,
        Fos7a_Name,
        Fos7a_Date,
        Fos7a_Time,
    } = req.body;

    var Insert_Query = "INSERT INTO Fosa7_Requests (Accepted, Requester_USERNAME, Host_USERNAME, Fos7a_Name, Fos7a_Date,Fos7a_Time) VALUES (?,?,?,?,?,?)";

    mysqlcon.query(Insert_Query, [0,Requester_USERNAME, Host_USERNAME, Fos7a_Name, Fos7a_Date,Fos7a_Time], function (err, result) {
        if (err) {
            console.log("Insertion Failed.");
            throw err;
        } else {
            console.log("Request Inserted Successfully.");
            res.send(result);
        }
    });

});

srv.get('/Accept_Fos7a', function(req, res)
{

    console.log("Accepting Request...");
    var q = url.parse(req.url, true).query;

    var Requester_USERNAME = q.Requester_USERNAME;
    var Host_USERNAME = q.Host_USERNAME;
    var Fos7a_Name = q.Fos7a_Name;
    var Fos7a_Date = q.Fos7a_Date;
    var Fos7a_Time = q.Fos7a_Time;

    var Retrieve_Query= "UPDATE FOSA7_REQUESTS SET ACCEPTED = 1 WHERE Requester_USERNAME = ? AND Host_USERNAME = ? AND Fos7a_Name = ? AND Fos7a_DATE = ? AND Fos7a_Time = ?" ;


    mysqlcon.query(Retrieve_Query,[Requester_USERNAME,Host_USERNAME, Fos7a_Name, Fos7a_Date, Fos7a_Time], function (err, result)
    {
        if (err)
        {
            console.log("Update Failed.");
            throw err;
        }
        else
        {
            console.log("Request Accepted Successfully.");
            res.send(result);
        }
    });

});

srv.post('/Request_Friend', function(req, res)
{

    console.log("Adding Request to DB...");
    const {
        Requester_USERNAME,
        Reciever_USERNAME,
    } = req.body;

    var Insert_Query = "INSERT INTO Friend_Requests (Accepted, Requester_USERNAME, Reciever_USERNAME) VALUES (?,?,?)";

    mysqlcon.query(Insert_Query, [0,Requester_USERNAME, Reciever_USERNAME], function (err, result)
    {
        if (err)
        {
            console.log("Insertion Failed.");
            throw err;
        }
        else
        {
            console.log("Request Inserted Successfully.");
            res.send(result);
        }
    });

});

srv.get('/Accept_Friend', function(req, res)
{

    console.log("Accepting Request...");
    var q = url.parse(req.url, true).query;

    var Requester_USERNAME = q.Requester_USERNAME;
    var Reciever_USERNAME = q.Reciever_USERNAME;

    var Retrieve_Query= "UPDATE Friend_Requests SET ACCEPTED = 1 WHERE Requester_USERNAME = ? AND Reciever_USERNAME = ?" ;


    mysqlcon.query(Retrieve_Query,[Requester_USERNAME,Reciever_USERNAME], function (err, result)
    {
        if (err)
        {
            console.log("Update Failed.");
            throw err;
        }
        else
        {
            console.log("Request Accepted Successfully.");
            res.send(result);
        }
    });

});

srv.post('/Add_User', function(req, res)
{

    console.log("Adding User to DB...");
    const [
        Username,
        Pass,
        Phone
    ] = req.body;

    var Insert_Query = "INSERT INTO User (Username, Pass, Phone) VALUES (?,?,?)";

    mysqlcon.query(Insert_Query, [Username,Pass, Phone], function (err, result)
    {
        if (err)
        {
            console.log("Insertion Failed.");
            throw err;
        }
        else
        {
            console.log("User Inserted Successfully.");
            res.send(result);
        }
    });
});

srv.post('/Login', function(req, res)
{
    console.log("Verifying User Details...");
    const [
        Username,
        Pass
     ] = req.body;
    console.log(Username,Pass);

    var Checking_Query = "SELECT Username, Pass FROM User WHERE Username=? AND Pass=?";

    mysqlcon.query(Checking_Query, [Username,Pass], function (err, result)
    {
        if (err)
        {
            console.log("Insertion Failed.");
            throw err;
        }
        else
        {
            console.log("User Inserted Successfully.");
            console.log(result);
            res.send(result);
        }
    });
});

srv.get('/Update_User', function(req, res)
{

    console.log("Accepting Request...");
    var q = url.parse(req.url, true).query;
    var Username = q.Username;
    var FirstName = q.FirstName;
    var LastName = q.LastName;
    var Phone = q.Phone;
    var ProfilePic = q.ProfilePic;
    var BirthDate = q.BirthDate;
    var Area = q.Area;

    var Retrieve_Query= "UPDATE User SET FirstName = ?, LastName = ?, Phone = ?, Username = ?, ProfilePic = ?, BirthDate = ?, Area = ?" ;


    mysqlcon.query(Retrieve_Query,[FirstName, LastName, Phone, Username, ProfilePic, BirthDate, Area], function (err, result)
    {
        if (err)
        {
            console.log("Update Failed.");
            throw err;
        }
        else
        {
            console.log("Updated Successfully.");
            res.send(result);
        }
    });

});

srv.get('/Fetch_User', function(req, res)
{

    console.log("Accepting Request...");
    var q = url.parse(req.url, true).query;
    var Username = q.Username;


    var Retrieve_Query= "SELECT* FROM User WHERE  Username = ?" ;


    mysqlcon.query(Retrieve_Query,[Username], function (err, result)
    {
        if (err)
        {
            console.log("Update Failed.");
            throw err;
        }
        else
        {
            console.log("Updated Successfully.");
            res.send(result);
        }
    });

});

srv.get('/Add_Interests', function(req, res)
{
    console.log("Accepting Request...");
    var q = url.parse(req.url, true).query;
    var Username = q.Username;
    var Interests = q.Interests;
    const arr_interests = Interests.split(",");

    var Delete_Query = "DELETE FROM User_Interests WHERE Username = ?";

    mysqlcon.query(Delete_Query,[Username], function (err, result)
    {
        if (err)
        {
            console.log("Delete Failed.");
            throw err;
        }
        else
            console.log("Deleted Successfully.");
    });

    var Insert_Query = "INSERT INTO User_Interests (Interest, Username) VALUES (?,?)";

    for (let i = 0; i < arr_interests.length; i++)
    {
        mysqlcon.query(Insert_Query,[arr_interests[i], Username], function (err, result)
        {
            if (err)
            {
                console.log("Insertion Failed.");
                throw err;
            }
            else
                console.log("Inserted Successfully.");
        });
    }
    res.send("Done");
});

srv.get('/Search', function(req, res)
{
    let arr_res = [];
    console.log("search started...");
    var q = url.parse(req.url, true).query;
    var Type = q.Type;
    var Name = q.Name;

    var List_Query = "SELECT * FROM Places WHERE Place_Name LIKE ?";
    var Event_Query = "SELECT * FROM Fosa7 WHERE Fos7a_Name LIKE ?";
    var Friend_Query = "SELECT * FROM User WHERE FirstName LIKE ? OR LastName LIKE ?";

    var Target_Query;

    if (Type == "Home")
    {
        mysqlcon.query(List_Query,[Name+'%'], function (err, result)
        {
            if (err)
            {
                console.log("List Search Failed.");
                throw err;
            }
            else
            {
                arr_res.push(result);
                console.log("Successful.");
            }
        });

        mysqlcon.query(Event_Query,[Name+'%'], function (err, result)
        {
            if (err)
            {
                console.log("Event Search Failed.");
                throw err;
            }
            else
            {
                arr_res.push(result);
                console.log("Successful.");
                res.send(arr_res);
            }
        });
    }
    else
    {
        if (Type == "Listat")
            Target_Query = List_Query;
        else if (Type == "Events")
            Target_Query = Event_Query;
        else
            Target_Query = Friend_Query;

        mysqlcon.query(Target_Query,[Name+'%', Name+'%'], function (err, result)
        {
            if (err)
            {
                console.log("Search Failed.");
                throw err;
            }
            else
            {
                arr_res = result;
                console.log("Successful.");
                res.send(arr_res);
            }
        });
    }

});

srv.post("/sendSMS", function (req, res) {
    console.log("send SMS");
    const { phone, body } = req.body;

    const sql =
        "INSERT INTO message (phone, body, timestamp, status) VALUES (?, ?, CURRENT_TIMESTAMP, 0)";
    mysqlcon.query(sql, [phone, body], (err, result) => {
        if (err) {
            console.error("Error inserting message:", err);
            return res.status(500).send("Internal server error");
        }
        console.log("1 record inserted");
        res.sendStatus(200);
    });
});

srv.get("/getSMS", function (req, res) {
    console.log("get SMS");

    var sql =
        "SELECT id,phone,body FROM message WHERE status = 0 ORDER BY timestamp ASC LIMIT 1"; // sql command to get data from database

    // execute sql command
    mysqlcon.query(sql, function (err, result) {
        if (err) {
            res.send("0");
            throw err;
        }

        console.log("SMS received");
        res.send(result);
    });
});

srv.get("/smsSent", function (req, res) {
    console.log("sms is sent successfully");

    var q = url.parse(req.url, true).query;
    var id = q.id;

    var sql = "UPDATE message SET status = 1 WHERE id=?"; // sql command to update the sent status of a record

    mysqlcon.query(sql, [id], function (err) {
        if (err) throw err;
        console.log("the status of " + id + " set to 1");
        res.send("Status updated");
    });
});


srv.listen(4000, function () {console.log("Server is running on port 4000");});