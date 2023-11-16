// создать подключение
let socket = new WebSocket("ws://localhost:8080/ws");

// отправить сообщение из формы publish
document.forms.publish.onsubmit = function() {
    let outgoingMessage = this.message.value;

    socket.send(outgoingMessage);
    return false;
};

// обработчик входящих сообщений
socket.onmessage = function(event) {
    let incomingMessage = event.data;
    showMessage(incomingMessage);
};

// показать сообщение в div#subscribe
function showMessage(message) {
    let messageElem = document.createElement('div');
    messageElem.appendChild(document.createTextNode(message));
    document.getElementById('subscribe').appendChild(messageElem);
}