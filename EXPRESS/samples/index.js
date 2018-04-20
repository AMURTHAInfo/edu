var express = require('express');
var things=require("./things.js");
var things1=require("./things1.js");
var app = express();

app.set('view engine', 'pug');
app.set('views','./views');

app.use(function(req, res, next){
    console.log("A new request received at " + Date.now());
    
    //This function call is very important. It tells that more processing is
    //required for the current request and is in the next middleware
    
    next();
});
app.use('/things', things); 
app.use('/things1', things1);

//Middleware function to log request protocol
app.use('/things', function(req, res, next){
    console.log("A request for things received at " + Date.now());
    next();
});
 
 // Route handler that sends the response
app.get('/things', function(req, res){
    res.send('Things');
});

//ExpressJS - Templating using pug
app.get('/first_template', function(req, res){
    res.render('view1');
});
app.get('/second_template', function(req, res){
    res.render('view2',{
        user: {name: "Ayush", age: "20"}
     });
});
app.get('/dynamic_view', function(req, res){
    res.render('view2', {
       name: "TutorialsPoint", 
       url:"http://www.tutorialspoint.com"
    });
})
app.get('/components', function(req, res){
    res.render('content');
});

app.listen(3000,function(){
    console.log('Example app listening on port 3000!');
});