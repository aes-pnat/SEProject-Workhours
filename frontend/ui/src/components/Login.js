import React, {useState} from 'react';
//import {Redirect} from "react-router-dom";
import User from "../services/User"
import { useHistory } from 'react-router-dom';
import { useEffect } from 'react';
const Login = (props) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [redirect, setRedirect] = useState(false);
    const [msg, setMsg] = useState('');
    //const [err, setErr] = useState('');
    let history = useHistory();
    
    useEffect( () => {
        if(redirect) history.push("/");
    }, [redirect]);

    const submit = async (e) => {
        e.preventDefault();

        await fetch(process.env.REACT_APP_BACKEND_URL + '/login', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            //credentials: 'include',
            body: JSON.stringify({
                username,
                password
            })
        }).then( (response) => {
            if(response.status === 200){
                return response;
            }else if(response.status === 401){
                setMsg("Neuspjeh! Provjerite korisnicko ime i lozinku.");
            }else{
                setMsg("Neuspjesno prijavljivanje.");
            }
        }).then((data) => {
            if(data && data.headers && data.headers.get("accessToken")){
                User.saveToken(data.headers.get("accessToken"));
                props.setToken(data.headers.get("accessToken"));
                User.saveRole(data.headers.get("roles"));
                props.setRole(data.headers.get("roles"));
                setRedirect(true);
            }
        }).catch( (error) => {
            setMsg("Doslo je do greske prilikom dohvata podataka sa servera...");
            console.log(error);
        });
    }

    

    return (
        <form onSubmit={submit} className="centered">
            {msg !== '' && 
                <div className="d-flex alert alert-danger justify-content-center">
                    <h5 className='justify-content-center'>{msg}</h5>
                </div>
            }
            <h1 className="h3 mb-3 fw-normal text-white d-flex nowrap justify-content-center">
                Please sign in
            </h1>
            <br />
            <input type="text" className="form-control" placeholder="Username" required
                   onChange={e => setUsername(e.target.value)}
            />
            <br />
            <input type="password" className="form-control" placeholder="Password" required
                   onChange={e => setPassword(e.target.value)}
            />
            <br />
            <button className="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
        </form>
    );
};

export default Login;