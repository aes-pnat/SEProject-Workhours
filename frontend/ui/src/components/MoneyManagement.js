import React from 'react'
import Select from 'react-select';
import '../index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import authHeader from '../services/auth-header';
class MoneyManagement extends React.Component {
    state = {
        profits: '',
        expenses: '',
    }

    handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        this.setState({ [name]: value });
        console.log(name , value);
        
    }
    handleProfits = async (e) => {
        e.preventDefault();
        
        const body = JSON.stringify({
            profit: parseInt(e),
        });
        console.log("almost");
        console.log(body);
        console.log("did it");

        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);
        
        await fetch(process.env.REACT_APP_BACKEND_URL + '/profit', {
            method: 'GET',
            headers: myHeaders,
            body: body
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({ profits: jsonResponse });
            console.log(this.state.profits);
        })
    }

    handleExpenses = async (e) => {
        e.preventDefault();
        
        const body = JSON.stringify({
            expense: parseInt(e),
        });
        console.log("almost");
        console.log(body);
        console.log("did it");

        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);
        
        await fetch(process.env.REACT_APP_BACKEND_URL + '/profit', {
            method: 'GET',
            headers: myHeaders,
            body: body
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({ expenses: jsonResponse });
            console.log(this.state.expenses);
        })
    }

    render () {
        return (
            
            
            <div className="container mt-5">
                <div className="h3 mb-3">Well Hello there</div>
                <div className="h3 mb-3">General Kenobi</div>
            </div>
        );
    }
}
export default MoneyManagement;
