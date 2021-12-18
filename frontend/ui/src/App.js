import React, {useEffect, useState} from 'react';
import Login from "./components/Login";
import Nav from "./components/Nav";
import {BrowserRouter, Route} from "react-router-dom";
import Home from "./components/Home";
import './App.css';
import User from './services/User';
function App() {
    const [username, setUsername] = useState(User.getToken());
    useEffect(() => {
        console.log(username);
    },[username]);


    return (
        <div className="App">
            <BrowserRouter>
                <Nav username={username} setUsername={setUsername}/>

                <main className="form-signin">
                    <Route path="/" exact component={() => <Home username={username}/>}/>
                    <Route path="/login" component={() => <Login setUsername={setUsername}/>}/>
                </main>
            </BrowserRouter>
        </div>
    );
}

export default App;