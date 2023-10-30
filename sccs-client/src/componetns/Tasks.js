import { useEffect } from "react";
import { useState } from "react"

const Tasks = () => {
    // let [tasks, setTasks] = useState();
    // useEffect(() => {
    //     fetch("http://localhost:8080/api/v1/tasks")
    //         .then(data => data.json())
    //         .then(data => {console.log(data); setTasks(JSON.stringify(data))}).catch((error) => { console.log(error) })
    // }, [])

    let [tasks, setTasks] = useState();

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!e.isDefaultPrevented()) {
            e.returnValue = false;
        }
        let response = await fetch("http://localhost:8080/api/v1/tasks", {
            headers: {
                'Content-Type': 'application/json',
                // 'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            method: 'GET',
        }).then(data => data.json())
            .then(data => { console.log(JSON.stringify(data)); setTasks(JSON.stringify(data)) })
        
        return false;
    }

    return (<div>
        <form>
            <button type="submit" onClick={handleSubmit} onSubmit={handleSubmit}>Задачи</button>
        </form>
        {tasks}
    </div>);
}

export default Tasks;
