import React from 'react';
import '../Register.css';


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
        error: null
    }

    handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        this.setState({ [name]: value });
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

        this.setState({ responseMsg: '', error: null });

        await fetch('https://radno-vrijeme-app.herokuapp.com/register', {
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
                    Djelatnik je uspješno registriran.
                </div>
            );
        }
        return (
            <div className="container register-form-container">
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
            </div>
        );
    }
}

export default Register;