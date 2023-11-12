import React, { useEffect } from 'react';
import styled from 'styled-components';
import SockJS from 'sockjs-client';
import { over } from 'stompjs';

const TaskContainer = styled.div`
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
`;

// let socket = new SockJS("/ws");
//     let stompClient = over(socket);
//     stompClient.connect({}, frame => {
//         console.log("connected")
//         stompClient.subscribe("/topic/greet", greet => {
//             console.log("1111subscribe")
//         })
//     })



const Task = ({ task }) => {

  useEffect(() => {
    const ws = new WebSocket("ws://localhost:3000/ws")
    ws.onmessage = (e) => {
      console.log()
    };
    
  });

  return (
    <TaskContainer>
      <h3>{task.title}</h3>
      <p>{task.description}</p>
      <p>Status: {task.status}</p>
      <ul id='messages'>
        {task.chatMessages.map((msg) => <li>{msg.content}</li>)}
      </ul>
    </TaskContainer>
  );
};

export default Task;
