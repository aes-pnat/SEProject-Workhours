import React from 'react';
//import '../Register.css';
import authHeader from '../services/auth-header';
import User from '../services/User';

class Register extends React.Component {
    
    state = {
        pid: '',
        name: '',
        surname: '',
        email: '',
        username: '',
        password: '',
        passwordCheck: '',
        responseMsg: '',
        deleteId: '',
        employeesList: [],
        error: null
    }

    componentDidMount = async () => {
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);

        await fetch(process.env.REACT_APP_BACKEND_URL + '/register', {
            method: 'GET',
            headers: myHeaders
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({ employeesList: jsonResponse });
        }).catch( (err) => {
            console.log(err);
        });
    }

    handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        this.setState({ [name]: value });
    }

    handleDeleteSubmit = async (e) => {
        e.preventDefault();
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);
        this.setState({ responseMsg: '', error: null });


        await fetch(process.env.REACT_APP_BACKEND_URL + '/register/delete?pid=' + this.state.deleteId, {
            method: 'GET',
            headers: myHeaders,
        }).then((response) => {
            if(response.ok){
                console.log("success");
                this.setState({ error: false });
            }else{
                console.log("fail");
                this.setState({ error: true });
            }
            return response;
        }).catch(() => {
            console.log("error fetching");
        });
    }

    handleSubmit = async (e) => {
        e.preventDefault();
        const body = JSON.stringify({
            pid: this.state.pid,
            name: this.state.name,
            surname: this.state.surname,
            email: this.state.email,
            username: this.state.username,
            password: this.state.password,
            passwordCheck: this.state.passwordCheck
        });

        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);
        
        this.setState({ responseMsg: '', error: null });

        await fetch(process.env.REACT_APP_BACKEND_URL + '/register', {
            method: 'POST',
            headers: myHeaders,
            body: body
        }).then((response) => {
            if(response.ok){
                console.log("success");
                this.setState({ error: false });
            }else{
                console.log("fail");
                this.setState({ error: true });
            }
            return response.json();
        }).then((jsonResponse) => {
            console.log(jsonResponse);
            this.setState({ responseMsg: jsonResponse.message });
        }).catch(() => {
            console.log("error fetching");
        });
    }

    render() {
        var employeesOptions = [];
        if(this.state.employeesList && 'length' in this.state.employeesList){
            employeesOptions = this.state.employeesList.map((employee) => {
                return (
                    <option key={employee.id} value={employee.id}>{employee.name + ' ' + employee.surname}</option>
                );
            });
        }

        var messageBox = "";
        if (this.state.error) {
            messageBox = (
                <div className="alert alert-danger">
                    {this.state.responseMsg}
                </div>
            );
        } else if (this.state.error !== null) {
            messageBox = (
                <div className="alert alert-success">
                    Djelatnik je uspješno registriran/obrisan.
                </div>
            );
        }
        let role = User.getRole();
        return (
            <div className="container text-light">
                <br/>
                <br/>
                
                {role === "[ROLE_OWNER]" ?
                    <div className='register-form-container'>
                        <div className="h3 mb-3">Registracija novog djelatnika</div>
                        <div className="container">
                            {messageBox}
                        </div>
                        <div className="row">
                            <form onSubmit={this.handleSubmit}>
                                <div className='mb-3'>
                                    <label className="form-label">Ime djelatnika:</label>
                                    <input 
                                        type="text" 
                                        className="form-control"
                                        name="name"
                                        onChange={this.handleChange} 
                                    />
                                </div>
                                <div className='mb-3'>
                                    <label className="form-label">Prezime djelatnika:</label>
                                    <input 
                                        type="text" 
                                        className="form-control"
                                        name="surname" 
                                        onChange={this.handleChange} 
                                    />
                                </div>
                                <div className='mb-3'>
                                    <label className="form-label">E-mail djelatnika:</label>
                                    <input 
                                        type="text" 
                                        className="form-control"
                                        name="email" 
                                        onChange={this.handleChange} 
                                    />
                                </div>
                                <div className='mb-3'>
                                    <label className="form-label">OIB djelatnika:</label>
                                    <input 
                                        type="text" 
                                        className="form-control"
                                        name="pid" 
                                        onChange={this.handleChange} 
                                    />
                                </div>
                                <div className='mb-3'>
                                    <label className="form-label">Korisničko ime:</label>
                                    <input 
                                        type="text" 
                                        className="form-control"
                                        name="username" 
                                        onChange={this.handleChange} 
                                    />
                                </div>
                                <div className='mb-3'>
                                    <label className="form-label">Lozinka:</label>
                                    <input 
                                        type="password" 
                                        className="form-control"
                                        name="password" 
                                        onChange={this.handleChange} 
                                    />
                                </div>
                                <div className='mb-3'>
                                    <label className="form-label">Ponovljena lozinka:</label>
                                    <input 
                                        type="password" 
                                        className="form-control"
                                        name="passwordCheck" 
                                        onChange={this.handleChange} 
                                    />
                                </div>
                                <button type="submit" className="btn btn-primary mb-5">Registriraj djelatnika</button>
                            </form>
                        </div>
                        <div className="h3 mb-3">Brisanje djelatnika</div>
                        <div className="row">
                            <form onSubmit={this.handleDeleteSubmit}>
                                <div className="mb-3">
                                    <label className="form-label">Djelatnik:</label>
                                    <select className="form-select"
                                            name="deleteId"
                                            onChange={this.handleChange}
                                    >
                                        <option selected disabled>Odaberite djelatnika</option>
                                        {employeesOptions}
                                    </select>
                                </div>
                                <button
                                    type="submit" 
                                    className="btn btn-light mb-5"
                                    onClick={this.handleDeleteSubmit}
                                >
                                    Obriši
                                </button>
                            </form>
                        </div>
                </div>
                :
                <div className='d-flex flex-row justify-content-center'>
                    <br />
                    <h1 className='alert alert-danger'>Nedovoljne permisije za registraciju korisnika!</h1>    
                </div>
                }
                

            </div>
        );
    }
}

export default Register;