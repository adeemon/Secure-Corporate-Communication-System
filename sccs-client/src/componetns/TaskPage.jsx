// TaskPage.js
import React, { useEffect, useState } from 'react';
import TaskList from './TaskList';
import CreateTask from './CreateTask';
import styled from 'styled-components';


const TaskPageContainer = styled.div`
  max-width: 600px;
  margin: 0 auto;
`;

const TaskPage = () => {
    const [tasks, setTasks] = useState([]);

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
            <CreateTask onCreate={handleCreateTask} />
        </TaskPageContainer>
    );
};

export default TaskPage;
