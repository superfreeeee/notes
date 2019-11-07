var http = require("http");
var url = require("url");
 
function start(route) {
  function onRequest(request, response) {
    var r_url = request.url
    var pathname = url.parse(r_url).pathname;
    console.log("Request for " + pathname + " received.");
 
    route(pathname);
 
    response.writeHead(200, {"Content-Type": "text/plain"});
    response.write("Hello World");
    response.end();
  }
 
  http.createServer(onRequest).listen(8888);
  console.log("Server has started.");
}
 
exports.start = start;