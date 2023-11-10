import logo from './logo.svg';
import './App.css';
import AppRouter from './componetns/AppRouter';
import Tasks from './componetns/Tasks';
import RegistrationPage from './componetns/RegistrationPage';
import { Route, Routes, Link } from 'react-router-dom';
import LoginPage from './componetns/LoginPage';
import RequireAuth from './hoc/RequireAuth';
import { AuthProvider } from './hoc/AuthProvider';
// import SockJS from 'sockjs-client';
// import { over } from 'stompjs';

function App() {
  // let sokcet = new SockJS("/ws");
  // let stompClient = over(sokcet);
  // stompClient.connect({}, frame => {
  //   console.log("connected")
  //   stompClient.subscribe("/topic/greet", greet => {
  //     console.log("subscribe")
  //   })
  // })
  return (
    <div className="App">
      {/* <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header> */}
      {/* <BrowserRouter> */}
      {/* <AppRouter/> */}
      {/* </BrowserRouter> */}
      {/* <RegistrationForm /> */}
      {/* <LoginForm /> */}
      {/* <Tasks /> */}
      <AuthProvider>
      <Routes>
        <Route path='/' element={
        <RequireAuth>
          <Tasks/>
        </RequireAuth>
      }/>
        <Route path='/register' element={<RegistrationPage/>}/>
        <Route path='/login' element={<LoginPage/>}/>
      </Routes>
      </AuthProvider>
    </div>
  );
}

export default App;
