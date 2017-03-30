/**
 * Created by ob on 17-3-27.
 */
var url = '127.0.0.1:8080';
var websocket;

function init() {
    if('WebSocket' in window) {
        websocket = new WebSocket('ws://' + url + '/websocket');
    }
    websocket.obclose = onClose;
    websocket.onerror = onError;
    websocket.onmessage = onMessage;
    websocket.onopen = onOpen;
}

function onOpen(event) {
    console.log('Websocket is open');
}

function onMessage(event) {
    console.log('messaging: ' + event.data);
}

function onClose(event) {
    console.log('Websocket is close');
}

function onError(event) {
    console.log(event);
}

function doSend() {
    console.log(websocket.readyState);
    if(websocket.readyState == WebSocket.OPEN){
        var msg = 'hello world';
        websocket.send(msg);
    } else {
        console.log('连接状态出错');
    }
}

function disconnect() {
    if(websocket != null){
        websocket.close();
        websocket = null;
    }
}

function connect() {
    disconnect();
    init();
}