import ReactDOM from 'react-dom';
import {useState} from 'react';
import '../Login.css';
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
        </form>
    )
};
export default Login;