import ReactDOM from 'react-dom';
import Navbar from './Navbar';
import {useState} from 'react';
import '../Login.css';
function Login(){
    const [inputs, setInputs] = useState({});


    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputs(values => ({...values, [name]:value}))
    }
    const handleSubmit = (event) => {
        event.preventDefault();
        const username = inputs.username;
        const password = inputs.password;
        const credentials = {username, password};
        alert(credentials.username + " " + credentials.password);
		const body = 'username=${username}&password=${password}';

        fetch('http://localhost:8080/login',{
            method: 'POST',
            headers: {'Content-Type':'application/x-www-form-urlencoded'},
            body: body
        }).then((response) => {
            let res = response.json();
            console.log(res);
        })
      }
    return (
        <form onSubmit={handleSubmit}>
            <label>Unesite korisničko ime:</label>
            <input type="text"  name="username" 
                                value={inputs.username || ""} 
                                onChange={handleChange} />
            <label>Unesite zaporku:</label>
            <input type="password"  name="password"
                                value={inputs.password || ""}
                                onChange={handleChange} />
            <input type="submit" />
        </form>
    )
};
export default Login;