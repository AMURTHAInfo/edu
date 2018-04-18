var express = require('express');
var router = express.Router();

router.get('/', function(eq, res){
   res.send('GET route on things.');
});

router.post('/', function(req, res){
   res.send('POST route on things.');
});

router.all('/test', function(req, res){
     res.send("HTTP method doesn't have any effect on this route!");
});

//export this router to use in our index.js
module.exports = router;