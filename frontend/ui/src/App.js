import React, {useState} from 'react';
import Login from "./components/Login";
import Nav from "./components/Nav";
import {BrowserRouter, Route} from "react-router-dom";
import Home from "./components/Home";
import './App.css'

function App() {
    const [username, setUsername] = useState('');

    // useEffect(() => {
    //     (
    //         async () => {
    //             const content = await fetch('http://localhost:8080/login', {
    //                 headers: {'Content-Type': 'application/json'},
    //                 credentials: 'include',
    //             }).then( (response) => {
    //               return response.json();
    //             }).catch((e) =>{
    //               alert("App error u fetchu");
    //             });
    //             if(content){
    //               setUsername(content.name);
    //             }
    //         }
    //     )();
    // });


    return (
        <div className="App">
            <BrowserRouter>
                <Nav username={username} setUsername={setUsername}/>

                <main className="form-signin">
                    <Route path="/" exact component={() => <Home name={username}/>}/>
                    <Route path="/login" component={() => <Login setUsername={setUsername}/>}/>
                </main>
            </BrowserRouter>
        </div>
    );
}

export default App;