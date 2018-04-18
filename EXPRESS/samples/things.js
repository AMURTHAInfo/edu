var express = require('express');
var router = express.Router();

router.get('/hello', function(eq, res){
   res.send('GET route on things.');
});

router.post('/', function(req, res){
   res.send('POST route on things.');
});

router.all('/test', function(req, res){
     res.send("HTTP method doesn't have any effect on this route!");
});

 router.get('/things/:name/:id', function(req, res) {
    res.send('id: ' + req.params.id + ' and name: ' + req.params.name);
 });

 router.get('/things/:id([0-9]{5})', function(req, res){
    res.send('id: ' + req.params.id);
 });
 router.get('*', function(req, res){
    res.send('Sorry, this is an invalid URL.');
 });
//export this router to use in our index.js
module.exports = router;