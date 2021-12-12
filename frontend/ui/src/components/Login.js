import ReactDOM from 'react-dom';
import {useState} from 'react';
import '../Login.css';
import '../Navbar.css';
import {
  BrowserRouter as Router,
  Routes,
  Switch,
  Route,
  Link
} from 'react-router-dom';
import Register from './Register';

function Login(){
    const [inputs, setInputs] = useState({});


    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputs(values => ({...values, [name]:value}))
    }
    const handleSubmit = async (event) => {
        event.preventDefault();
        const username = inputs.username;
        const password = inputs.password;
        const credentials = {username, password};	
		const authdata = window.btoa(username + ':' + password);
		
		const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
		myHeaders.append("Authorization", "Basic " + authdata);

        await fetch('http://localhost:8080/login',{
            method: 'POST',
            headers: myHeaders,
            body: JSON.stringify(credentials)
        }).then((response) => {
            if(response.ok){
                alert("successful login");
                console.log("success");
            }else{
                alert("failed login");
                console.log(response);
            }
        }).catch(() =>{
            console.log("error fetching");
        });
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
            <label/>

            <h3>Not a member yet?</h3>
            <label>
                <Link to='/register'>Register</Link>
            </label>


        </form>

        
    )
};
export default Login;