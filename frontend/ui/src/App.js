import React, { useState} from 'react';
import Login from "./components/Login";
import Nav from "./components/Nav";
import {BrowserRouter, Route, Switch} from "react-router-dom";
import Home from "./components/Home";
import './App.css';
import User from './services/User';
import Navbar from './components/Navbar';
function App() {
    const [token, setToken] = useState(User.getToken());
    const [role, setRole] = useState(User.getRole());
    const [user, setUser] = useState('');
    return (
        <Navbar token={token} 
                setToken={setToken} 
                role={role} setRole={setRole} 
                setUser={setUser}
                username={user}/>
    );
}

export default App;