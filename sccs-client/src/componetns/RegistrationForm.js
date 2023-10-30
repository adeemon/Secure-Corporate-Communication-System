import React, { useState } from 'react';

const RegistrationForm = () => {
  const [formData, setFormData] = useState({ username: '', password: '' });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    const { username, password } = formData;
    console.log(formData);
    let response = await fetch("http://localhost:8080/api/v1/auth/register", {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: 'POST',
      body: JSON.stringify(formData),
    }).then(data => data.json())
      .then(data => console.log(JSON.stringify(data)))
    // console.log(response.json())
  }

  return (
    <form onSubmit={handleSubmit}>
      <h2>Регистрация</h2>
      <div>
        <label>Логин:</label>
        <input
          type="text"
          name="username"
          value={formData.username}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label>Пароль:</label>
        <input
          type="password"
          name="password"
          value={formData.password}
          onChange={handleInputChange}
        />
      </div>
      <button type="submit">Зарегистрироваться</button>
    </form>
  );
}

export default RegistrationForm;
