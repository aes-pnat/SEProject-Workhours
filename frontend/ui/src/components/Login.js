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

		const LOGIN_URL = 'http://localhost:8080/login';
        //const LOGIN_URL = 'https://radno-vrijeme-app.herokuapp.com/login'
        
		const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
		myHeaders.append("Authorization", "Basic " + authdata);

        await fetch(LOGIN_URL,{
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
        <div className="container">
            <div className="container login-card">
                <form onSubmit={handleSubmit}>
                    <div className='mb-3'>
                        <label className="form-label">Korisničko ime:</label>
                        <input type="text" className="form-control" name="username" 
                                            value={inputs.username || ""} 
                                            onChange={handleChange} />
                    </div>
                    <div className='mb-3'>
                        <label className="form-label">Zaporka:</label>
                        <input type="password" className="form-control" name="password"
                                            value={inputs.password || ""}
                                            onChange={handleChange} />
                    </div>
                    <button type="submit" className="btn btn-primary">Prijavi se</button>
                </form>
            </div>
        </div>
    )
};
export default Login;