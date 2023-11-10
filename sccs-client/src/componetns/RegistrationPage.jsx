import React, { useState } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

const RegistrationPageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-color: #f5f5f5;
`;

const RegistrationForm = styled.form`
  width: 300px;
  padding: 20px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  text-align: center;
`;

const RegistrationInput = styled.input`
  width: 80%;
  display: flex-box;
  padding: 10px;
  margin: 10px 0;
  border: 1px solid #ccc;
  border-radius: 3px;
`;

const RegistrationButton = styled.button`
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  &:hover {
    background-color: #24a4ff;
  }
`;

function RegistrationPage() {
//     const history = useHistory();
//     const [registrationData, setRegistrationData] = useState({
//     // Ваши данные регистрации (имя, email, пароль и т. д.)
//   });
    const navigate = useNavigate();
    const goToLogin = () => navigate('/login', {replace: true});

  return (
    <RegistrationPageContainer>
      <RegistrationForm>
        <h2>Регистрация</h2>
        <RegistrationInput type="text" placeholder="Имя" />
        <RegistrationInput type="email" placeholder="Email" />
        <RegistrationInput type="password" placeholder="Пароль" />
        <RegistrationButton onClick={goToLogin}>Зарегистрироваться</RegistrationButton>
      </RegistrationForm>
    </RegistrationPageContainer>
  );
}

export default RegistrationPage;
