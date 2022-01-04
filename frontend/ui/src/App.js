import React, { useState} from 'react';
import Login from "./components/Login";
import Nav from "./components/Nav";
import {BrowserRouter, Route, Switch} from "react-router-dom";
import Home from "./components/Home";
import './App.css';
import User from './services/User';
function App() {
    const [username, setUsername] = useState(User.getToken());

    return (
        <BrowserRouter>
            <div className="App">
                <Nav username={username} setUsername={setUsername}/>

                <main>
                    <Switch>
                        <Route path="/" exact>
                            <Home username={username} />
                        </Route>
                        <Route path="/login">
                            <Login username={username} setUsername={setUsername} />                        </Route>
                    </Switch>
                </main>
            </div>
        </BrowserRouter>
    );
}

export default App;

/*              <main>
                    <Route path="/" component={() => <Home username={username}/>}/>
                    {!username &&
                        <Route path="/login" exact component={() => <Login setUsername={setUsername} />} />
                    }
                </main> */