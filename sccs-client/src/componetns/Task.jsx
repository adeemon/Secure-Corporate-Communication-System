import React, { useEffect } from 'react';
import styled from 'styled-components';
import SockJS from 'sockjs-client';
import { over } from 'stompjs';

const TaskContainer = styled.div`
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
`;

let stompClient = null;

const Task = ({ task }) => {

    const connect = (event) => {
        const sock = new SockJS("http://localhost:8080/ws");
        stompClient = over(sock);
        stompClient.connect({ }, onConnected, (e) => console.log("err" + e));
        event.preventDefault();
    }

    const onConnected = () => {
        // Subscribe to the Public Topic
        stompClient.subscribe(`/taskChat.${task.id}`, onMessageReceived);

        // Tell your username to the server
        stompClient.send(`/app/taskChat.${task.id}.send`,
            {},
            JSON.stringify({ sender: "username", type: 'JOIN' })
        )

    }

    const onMessageReceived = (msg) => {
        console.log(JSON.parse(msg.body))
    }

    const send = (event) => {
        let messageContent = document.getElementById("msg").value;

        // let chatMessage = {
        //     sender: "username",
        //     content: messageContent,
        //     type: 'CHAT'
        // };

        stompClient.send(`/app/taskChat.${task.id}.send`, {}, JSON.stringify(messageContent));

        event.preventDefault();
    }

    return (
        <TaskContainer>
            <h3>{task.title}</h3>
            <p>{task.description}</p>
            <p>Status: {task.status}</p>
            <ul id='messages'>
                {task.chatMessages.map((msg) => <li>{msg.content}</li>)}
            </ul>
            <input id="msg" placeholder="enter message" />
            <button type="button" onClick={connect}>connect</button>
            <button type="button" onClick={send}>send</button>
        </TaskContainer>
    );
};

export default Task;
