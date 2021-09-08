// WebSocket member for communication
const WebSocket = require('ws');
var ws = new WebSocket("ws://localhost:9000");

// common functions
function sleep(ms) {
    return new Promise((r) => setTimeout(r, ms));
}

async function snap() {
    // console.log("snap");
    await sleep(3000);
}

// WebSocket method for communication
ws.onopen = function(event) {
    
    console.log(event.data);
    while(true) {
        var now = new Date();
        snap();
        ws.send("Hi! server" + now);
    }
}

// Msg received from WebSocketServer
ws.onmessage = function(event) {
    
    console.log("Server: ", event.data);
}

// handle any errors in here
// refered https://stackoverflow.com/questions/19304157/getting-the-reason-why-websockets-closed-with-close-code-1006
ws.onerror = function(event) {
    console.log("Server error message: ", event.data);
    switch (event.code) {
        case '1006':
            console.log("Abnormal closure");
            break;
        default:
            break;
    }
}

ws.onclose = function(event) {
    // event: how to access this event data(members of class)
    // are there any docs for this?
    if(event.wasClean) {
        console.log('connection closed gracefully');
    } else {
        console.log('connection closed disgracefully: ', event.code);
    }
}

