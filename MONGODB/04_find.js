var mongodb=require("mongodb");
var mongoClient=mongodb.MongoClient;
var url="mongodb://localhost:27017";

mongoClient.connect(url,function(err,client){
    if(err){
        console.log(err);
    }
    else{
        console.log("connected")
        const db = client.db("fruits");
        const collection = db.collection('documents'); 
        
        collection.find().toArray(function(err,res){
            if(err){
                console.log(err);
            }
            else if(res.length){
                console.log(res);
            }
            else{
                console.log("no matches are found")
            }
        });
        client.close();
    }

})