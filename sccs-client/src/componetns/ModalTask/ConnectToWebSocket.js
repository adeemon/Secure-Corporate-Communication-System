import SockJS from "sockjs-client";
import { over } from 'stompjs';

const connectToWebSocket = (id) => {
    const socket = new SockJS('http://localhost:8080/ws');

    const stompClient = over(socket);

    console.log(id);

    stompClient.connect({}, () => {stompClient.subscribe(`/taskChat.${id}`, onMessageReceived);}, (e) => {});
    // stompClient.subscribe(`/taskChat.${id}`, () => console.log("subscribed"));

    const onConnected = () => {
        // Subscribe to the Public Topic
        stompClient.subscribe(`/taskChat.${id}`, onMessageReceived);

        // Tell your username to the server
        // stompClient.send(`/app/taskChat.${task.id}.send`,
        //     {},
        //     JSON.stringify({ sender: "username", type: 'JOIN' })
        // )

    }

    const onMessageReceived = (msg) => {
        console.log(JSON.parse(msg.body))
    }

    return stompClient;
};

export default connectToWebSocket;