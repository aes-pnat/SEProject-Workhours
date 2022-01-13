import React from 'react'
import Select from 'react-select';
import '../index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import authHeader from '../services/auth-header';
class GroupsAdd extends React.Component {
    state = {
        features: '',
    }

    componentDidMount = async () => {
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);

        await fetch(process.env.REACT_APP_BACKEND_URL + '/moneymanagement', {
            method: 'GET',
            headers: myHeaders
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({ features: jsonResponse });
        })

    }
    

    handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        this.setState({ [name]: value });
        console.log(name , value);
        
    }
    handleSubmit = async (e) => {
        e.preventDefault();
        
        const body = JSON.stringify({
            groupName: this.state.name,
            idLeader: this.state.leaderid,
            idMembers: this.state.employeesids,
            idJob: this.state.jobid,
        });
        console.log("almost");
        console.log(body);
        console.log("did it");

        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);
        
        await fetch(process.env.REACT_APP_BACKEND_URL + '/moneymanagement', {
            method: 'POST',
            headers: myHeaders,
            body: body
        }).then((response) => {
            if(response.ok){
                this.setState({ success: true });
            }
        }).catch((err) => {
            throw err;
        });
    }

    render () {
        console.log(this.state.features);
        return (
            
            
            <div className="container mt-5">
                <div className="h3 mb-3">Well Hello there</div>
                <div className="h3 mb-3">General Kenobi</div>
            </div>
        );
    }
}
export default GroupsAdd
