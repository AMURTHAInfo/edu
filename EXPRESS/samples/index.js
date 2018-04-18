var express = require('express');
var things=require("./things.js");
var things1=require("./things1.js");
var app = express();

app.use('/things', things); 
app.use('/things1', things1);
app.listen(3000,function(){
    console.log('Example app listening on port 3000!');
});