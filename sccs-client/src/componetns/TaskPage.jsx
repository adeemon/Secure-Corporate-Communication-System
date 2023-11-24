// TaskPage.js
import React, { useEffect, useState } from 'react';
import TaskList from './TaskList';
import CreateTask from './CreateTask';
import styled from 'styled-components';
import { jwtDecode } from 'jwt-decode';


const TaskPageContainer = styled.div`
  max-width: 600px;
  margin: 0 auto;
`;

const TaskPage = () => {
    const [tasks, setTasks] = useState([]);
    
    const [isAdmin, setIsAdmin] = useState(jwtDecode(sessionStorage.getItem("access_token")).authorities === "ROLE_ADMIN");

    // console.log(jwtDecode(sessionStorage.getItem("access_token")).authorities === "ROLE_ADMIN")

    useEffect(
        () => {
            fetch("http://localhost:8080/tasks", {
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": `Bearer ${sessionStorage.getItem("access_token")}`
                }
            })
                .then(response => response.json())
                .then(data => { console.log(data); setTasks(data) })
            // .catch((error) => { console.log(error) })
        }, []
    );

    const handleCreateTask = (newTask) => {
        setTasks([...tasks, { id: tasks.length + 1, ...newTask }]);
    };

    // console.log(tasks);

    return (
        <TaskPageContainer>
            <h1>Task Management App</h1>
            <TaskList tasks={tasks} />
            {isAdmin ? <CreateTask onCreate={handleCreateTask} /> : null}
        </TaskPageContainer>
    );
};

export default TaskPage;
