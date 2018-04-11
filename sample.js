var jsdom = require('jsdom');
const { JSDOM } = jsdom;
const { window } = new JSDOM();
const { document } = (new JSDOM('')).window;
global.document = document;

var $ = jQuery = require('jquery')(window);
$("<h1>test passes</h1>").appendTo("body");
console.log($("body").html());

$("<h2>other test passes</h2>").appendTo("body");
console.log($("body").html());

$("<h3>third test passes</h3>").appendTo("body");
console.log(jQuery("body").html());