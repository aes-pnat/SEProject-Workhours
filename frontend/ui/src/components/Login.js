import React, {useState} from 'react';
import {Redirect} from "react-router-dom";
import User from "../services/User"
const Login = (props) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [redirect, setRedirect] = useState(false);

    const submit = async (e) => {
        e.preventDefault();

        await fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            //credentials: 'include',
            body: JSON.stringify({
                username,
                password
            })
        }).then((data) => {
            console.log(data.headers);
            if(data.headers.get("accessToken")){
                
                props.setUsername(data.headers.accessToken);
                User.saveToken(data.headers.accessToken);
                setRedirect(true);
            }
        }).catch( (e) => {
            console.log("Error u login fetchu");
        });
    }

    if (redirect) {
        return <Redirect to="/"/>;
    }

    return (
        <form onSubmit={submit}>
            <h1 className="h3 mb-3 fw-normal">Please sign in</h1>
            <input type="text" className="form-control" placeholder="Username" required
                   onChange={e => setUsername(e.target.value)}
            />

            <input type="password" className="form-control" placeholder="Password" required
                   onChange={e => setPassword(e.target.value)}
            />

            <button className="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
        </form>
    );
};

export default Login;