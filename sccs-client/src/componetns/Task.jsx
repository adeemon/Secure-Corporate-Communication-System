import React from 'react';
import styled from 'styled-components';

const TaskContainer = styled.div`
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
`;

const Task = ({ task }) => (
    <TaskContainer>
        <h3>{task.title}</h3>
        <p>{task.description}</p>
        <p>Status: {task.status}</p>
        {task.chatMessages.map((msg) => <p>{msg.content}</p>)}
    </TaskContainer>
);

export default Task;
