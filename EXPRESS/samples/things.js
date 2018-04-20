var express = require('express');
var router = express.Router();

router.get('/hello', function(req, res){
   res.send('GET route on things.');
});

router.post('/', function(req, res){
   res.send('POST route on things.');
});

router.all('/test', function(req, res){
    res.send("HTTP method doesn't have any effect on this route!");
});

router.get('/random.text', function (req, res) {
    res.send('random.text')
})

//This route path will match acd and abcd.
router.get('/ab?cd', function (req, res) {
    res.send('ab?cd')
})

//his route path will match abcd, abbcd, abbbcd, and so on.
router.get('/ab+cd', function (req, res) {
    res.send('ab+cd')
})

//This route path will match abcd, abxcd, abRANDOMcd, ab123cd, and so on.
router.get('/ab*cd', function (req, res) {
    res.send('ab*cd')
})

//This route path will match /abe and /abcde.
router.get('/ab(cd)?e', function (req, res) {
    res.send('ab(cd)?e')
})

//This route path will match anything with an “a” in it.
router.get(/a/, function (req, res) {
    res.send('/a/')
})

router.get(/.*fly$/, function (req, res) {
    res.send('/.*fly$/')
})

router.get('/flights/:from-:to', function (req, res) {
    res.send(req.params)
})

router.get('/plantae/:genus.:species', function (req, res) {
    res.send(req.params)
})

router.get('/things/:name/:id', function(req, res) {
    res.send('id: ' + req.params.id + ' and name: ' + req.params.name);
});

 router.get('/things/:id([0-9]{5})', function(req, res){
    res.send('id: ' + req.params.id);
 });

 //More than one callback function can handle a route (make sure you specify the next object)
router.get('/example/b', function (req, res, next) {
    console.log('the response will be sent by the next function ...')
    next()
  }, function (req, res) {
    res.send('Hello from B!')
})

//An array of callback functions can handle a route. For example:
var cb0 = function (req, res, next) {
    console.log('CB0')
    next()
}

var cb1 = function (req, res, next) {
    console.log('CB1')
    next()
}

var cb2 = function (req, res) {
    res.send('Hello from C!')
}
  
router.get('/example/c', [cb0, cb1, cb2])

var cb0 = function (req, res, next) {
    console.log('CB0')
    next()
}

var cb1 = function (req, res, next) {
    console.log('CB1')
    next()
}
  
router.get('/example/d', [cb0, cb1], function (req, res, next) {
    console.log('the response will be sent by the next function ...')
    next()
    }, function (req, res) {
    res.send('Hello from D!')
})

router.route('/book')
.get(function (req, res) {
    res.send('Get a random book')
})
.post(function (req, res) {
    res.send('Add a book')
})
.put(function (req, res) {
    res.send('Update the book')
})
router.get('*', function(req, res){
    res.send('Sorry, this is an invalid URL.');
});
//export this router to use in our index.js
module.exports = router;