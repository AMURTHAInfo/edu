var mongodb=require("mongodb");
var mongoClient=mongodb.MongoClient;
var url="mongodb://localhost:27017/fruits";

mongoClient.connect(url,function(err,db){
    if(err){
        console.log(err);
    }
    else{
        console.log("connected")
        db.close();
    }

})