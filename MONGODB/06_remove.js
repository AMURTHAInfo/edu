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
        var doc1={name:"Red Apple",colour:"Red"};
        var doc2={name:"Green Apple",colour:"Green"};
        collection.remove({name:"Red Apple"},function(err,res){
            if(err){
                console.log(err);
            }
            else{
                console.log("Updated successfully",res);
            }
        })
        client.close();
    }

})