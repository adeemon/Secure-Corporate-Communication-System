// TaskPage.js
import React, { useEffect, useState } from 'react';
import TaskList from './TaskList';
import CreateTask from './CreateTask';
import styled from 'styled-components';
import { jwtDecode } from 'jwt-decode';
import { Grid, Paper } from '@mui/material';


const TaskPageContainer = styled.div`
  max-width: 900px;
  margin: 0 auto;
  padding: 20px;
`;

const StatusColumn = styled(Grid)`
  margin: 0 10px;
`;

const TaskPage = () => {
    const [tasks, setTasks] = useState([]);

    const [users, setUsers] = useState([]);

    const [isAdmin, setIsAdmin] = useState(jwtDecode(sessionStorage.getItem("access_token")).authorities === "ROLE_ADMIN");

    useEffect(
        () => {
            fetch("http://localhost:8080/tasks", {
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": `Bearer ${sessionStorage.getItem("access_token")}`
                }
            })
                .then(response => response.json())
                .then(data => { console.log(data); setTasks(data) });
            // .catch((error) => { console.log(error) })
            fetch(`http://localhost:8080/user`, {
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": `Bearer ${sessionStorage.getItem("access_token")}`
                },
                method: "GET",
                credentials: "include"
            })
                .then(response => response.json())
                .then(data => setUsers(data));
        },
        []
    );

    const handleCreateTask = (newTask) => {
        fetch("http://localhost:8080/tasks/createTask", {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${sessionStorage.getItem("access_token")}`
            },
            method: "POST",
            body: JSON.stringify({ "title": newTask.title, "description": newTask.description }),
            credentials: "include"
        })
            .then(response => response.json())
            .then(data => { console.log(data); setTasks([...tasks, data]) })
    };

    return (
        <TaskPageContainer>
            <h1>Task Management App</h1>
            <Grid container spacing={3}>
                <StatusColumn item xs={4}>
                    <Paper elevation={3}>
                        <h2>To Do</h2>
                        <TaskList tasks={tasks.filter(task => task.status === 'TODO')} users={users}/>
                    </Paper>
                </StatusColumn>
                <StatusColumn item xs={4}>
                    <Paper elevation={3}>
                        <h2>In Progress</h2>
                        <TaskList tasks={tasks.filter(task => task.status === 'IN_PROGRESS')} users={users}/>
                    </Paper>
                </StatusColumn>
                <StatusColumn item xs={4}>
                    <Paper elevation={3}>
                        <h2>Done</h2>
                        <TaskList tasks={tasks.filter(task => task.status === 'DONE')} users={users}/>
                    </Paper>
                </StatusColumn>
            </Grid>
            {isAdmin ? <CreateTask onCreate={handleCreateTask} /> : null}
        </TaskPageContainer>
    );
};

export default TaskPage;
