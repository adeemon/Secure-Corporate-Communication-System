import logo from './logo.svg';
import './App.css';
import RegistrationForm from './componetns/RegistrationForm';
import LoginForm from './componetns/LoginForm';
import AppRouter from './componetns/AppRouter';
import { BrowserRouter } from 'react-router-dom';
import Tasks from './componetns/Tasks';

function App() {
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
      <RegistrationForm />
      <LoginForm />
      <Tasks />
    </div>
  );
}

export default App;
