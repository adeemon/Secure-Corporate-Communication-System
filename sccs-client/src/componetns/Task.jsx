import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import SockJS from 'sockjs-client';
import { over } from 'stompjs';
import { jwtDecode } from 'jwt-decode';

const TaskContainer = styled.div`
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
`;

let stompClient = null;

const Task = ({ task }) => {

    const [isAdmin, setIsAdmin] = useState(jwtDecode(sessionStorage.getItem("access_token")).authorities === "ROLE_ADMIN");

    const [status, setStatus] = useState(task.status);

    const [assignees, setAssignees] = useState(task.assignees);

    const [users, setUsers] = useState([]);

    const [selectedUserOption, setSelectedUserOption] = useState('');

    const [chatMessages, setChatMessages] = useState(task.chatMessages);

    useEffect(() => {
        fetch(`http://localhost:8080/user`, {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${sessionStorage.getItem("access_token")}`
            },
            method: "GET",
            credentials: "include"
        })
            .then(response => response.json())
            .then(data => setUsers(data))
    }, [users]);

    const connect = (event) => {
        const sock = new SockJS("http://localhost:8080/ws");
        stompClient = over(sock);
        stompClient.connect({}, onConnected, (e) => console.log("err" + e));
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
        let messageContent = document.getElementById("msg" + task.id).value;

        stompClient.send(`/app/taskChat.${task.id}.send`, {}, JSON.stringify(messageContent));

        event.preventDefault();
    }

    const setInProgressStatus = () => {
        fetch(`http://localhost:8080/tasks/${task.id}/updateStatus`, {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${sessionStorage.getItem("access_token")}`
            },
            method: "PUT",
            body: JSON.stringify({ "status": "IN_PROGRESS" }),
            credentials: "include"
        })
            .then(response => response.json())
            .then(data => setStatus(data.status))
        // .then(data => { console.log(data); setTasks([...tasks, data]) })
    };

    const setDoneStatus = () => {
        fetch(`http://localhost:8080/tasks/${task.id}/updateStatus`, {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${sessionStorage.getItem("access_token")}`
            },
            method: "PUT",
            body: JSON.stringify({ "status": "DONE" }),
            credentials: "include"
        })
            .then(response => response.json())
            .then(data => setStatus(data.status))
    };

    const handleSelectChange = (event) => {
        // event.preventDefault();
        setSelectedUserOption(event.target.value);
        console.log(selectedUserOption);
    };

    const addAssigneeToTask = () => {
        fetch(`http://localhost:8080/tasks/${task.id}/addAssignee`, {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${sessionStorage.getItem("access_token")}`
            },
            method: "PUT",
            body: JSON.stringify({ "username": selectedUserOption }),
            credentials: "include"
        })
            .then(response => response.json())
            .then(data => setAssignees(data.assignees))
    }

    return (
        <TaskContainer>
            <h3>{task.title}</h3>
            <p>{task.description}</p>
            <p>Status: {task.status}</p>
            <ul id={'assignees' + task.id}>
                {task?.assignees?.map(assignee => <li key={assignee.id}>{assignee.username}</li>)}
            </ul>
            {isAdmin ?
                <div>
                    <select name={"assignees" + task.id} id={"assignees" + task.id} onChange={handleSelectChange}>
                        {users?.map(user => <option key={user.id} value={user.username}>{user.username}</option>)}
                    </select>
                    <button type='button' onClick={addAssigneeToTask}>add assignee</button>
                </div>
                : null}
            <ul id={'messages' + task.id}>
                {chatMessages?.map((msg) => <li key={msg.id}>{msg.content}</li>)}
            </ul>
            <input id={"msg" + task.id} placeholder="enter message" />
            <button type="button" onClick={connect}>connect</button>
            <button type="button" onClick={send}>send</button>
            {isAdmin ?
                <div>
                    <button type="button" onClick={setInProgressStatus}>in progress</button>
                    <button type="button" onClick={setDoneStatus}>done</button>
                </div>
                : null}
        </TaskContainer>
    );
};

export default Task;
