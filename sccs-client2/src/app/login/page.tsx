"use client";

import React, { useState, ChangeEvent, FormEvent } from 'react';
import { Button, TextField, Typography, Container, Paper, Avatar } from '@mui/material';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { useRouter } from 'next/navigation';

const Login: React.FC = () => {

    const router = useRouter();

    const [formData, setFormData] = useState({
        username: '',
        password: '',
    });

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const form = e.currentTarget;
        const user = form.username.value;
        const password = form.password.value;
        fetch("http://localhost:8080/auth/login", {
            headers: {
                'Content-Type': 'application/json',
                'Origin': "http://localhost:3000",
            },
            method: 'POST',
            credentials: "include",
            body: JSON.stringify({ 'username': user, 'password': password })
        })
            .then(res => res.json())
            .then(data => sessionStorage.setItem("access_token", data.access_token))
            .then(() => router.push("/"));
    };
    
    return (
        <Container component="main" maxWidth="xs">
            <Paper elevation={3} style={{ marginTop: '8px', display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                <Avatar style={{ margin: '8px', backgroundColor: 'secondary.main' }}>
                    <LockOutlinedIcon />
                </Avatar>
                <Typography component="h1" variant="h5">
                    Вход в систему
                </Typography>
                <form style={{ width: '100%', marginTop: '8px' }} onSubmit={handleSubmit}>
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        id="username"
                        label="Имя пользователя"
                        name="username"
                        // autoComplete="email"
                        autoFocus
                        onChange={handleChange}
                    />
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Пароль"
                        type="password"
                        id="password"
                        autoComplete="current-password"
                        onChange={handleChange}
                    />
                    <Button type="submit" fullWidth variant="contained" color="primary" style={{ margin: '24px 0 16px' }}>
                        Войти
                    </Button>
                </form>
            </Paper>
        </Container>
    );
};

export default Login;
