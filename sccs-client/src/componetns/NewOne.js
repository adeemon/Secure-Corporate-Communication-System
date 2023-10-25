import { useEffect } from "react";
import { useState } from "react"

const NewOne = () => {
    let [tasks, setTasks] = useState();
    useEffect(() => {
        fetch("http://localhost:8080/api/v1/tasks")
            .then(data => data.json())
            .then(commits => {console.log(commits); setTasks(JSON.stringify(commits))}).catch((error) => { console.log(error) })
    }, [])
    return (<div>
        {tasks}
    </div>)
}

export default NewOne
