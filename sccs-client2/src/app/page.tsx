"use client";
/*@ts-ignore*/;

import { jwtDecode } from "jwt-decode";
import Link from "next/link";
import { useRouter } from "next/navigation";
import React, { useEffect, useState } from "react";


export default function Home() {

    const router = useRouter();

    const [isAdmin, setIsAdmin] = useState();

    useEffect(() => {
        !sessionStorage.getItem("access_token") ? router.push("/login") : console.log("logged in");
        setIsAdmin(jwtDecode(sessionStorage.getItem("access_token")?.toString())?.authorities === "ROLE_ADMIN");
    }, []);


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
                .catch((error) => { console.log(error) })
        }, []
    );

    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');

    const handleCreate = (e) => {
        e.preventDefault();
        // onCreate({ title, description, status: 'Todo' });
        fetch("http://localhost:8080/tasks/createTask", {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${sessionStorage.getItem("access_token")}`
            },
            method: "POST",
            body: JSON.stringify({ "title": title, "description": description }),
            credentials: "include"
        });
        setTitle('');
        setDescription('');
    };

    return (
        <div>
            <header />
            <ul>
                {tasks.map((task) => (
                    <li key={task.id}>
                        <p>{task.title}</p>
                        <p>{task.description}</p>
                        <p>{task.status}</p>
                    </li>
                ))}
            </ul>
            {isAdmin ? <div> <label>Title:</label>
                <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />
                <label>Description:</label>
                <textarea value={description} onChange={(e) => setDescription(e.target.value)} />
                <button onClick={handleCreate}>Create Task</button> </div> : null}
            <footer />
        </div>
    );
}
