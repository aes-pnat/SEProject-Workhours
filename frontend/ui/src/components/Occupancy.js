import React from 'react';
import authHeader from '../services/auth-header';
class Occupancy extends React.Component {
    state = {
        id: '',
        dateStart: '',
        dateEnd: '',
        message: '',
        employeesList: []
    }

    componentDidMount = async () => {
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);

        await fetch(process.env.REACT_APP_BACKEND_URL + '/occupancy', {
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

    handleSubmit = async (e) => {
        e.preventDefault();
        const body = JSON.stringify({
            id: this.state.id,
            dateStart: this.state.dateStart,
            dateEnd: this.state.dateEnd
        });

        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);
        
        this.setState({ message: '' });

        await fetch(process.env.REACT_APP_BACKEND_URL + '/occupancy', {
            method: 'POST',
            headers: myHeaders,
            body: body
        }).then((response) => {
            if(response.ok){
                return response.text();
            }
        }).then((responseText) => {
            this.setState({ message: responseText });
        }).catch( (err) => {
            console.log(err);
        });

    }

    render() {
        var employeesOptions;
        if(this.state.employeesList && 'length' in this.state.employeesList){
            employeesOptions = this.state.employeesList.map((employee) => {
                return (
                    <option key={employee.id} value={employee.id}>{employee.name}</option>
                );
            });
        }
        

        let messageBox = '';
        if (this.state.message === "Djelatnik je slobodan u odabranom periodu.") {
            messageBox = (
                <div className="alert alert-success">
                    {this.state.message}
                </div>
            );
        } else if (this.state.message !== '') {
            messageBox = (
                <div className="alert alert-danger">
                    {this.state.message}
                </div>
            );
        }

        return (
            <div>
                {this.props.role === "[ROLE_OWNER]" ?
            
                    <div className="container mt-5 text-light">
                        <div className="h3 mb-3">Provjera zauzetosti djelatnika</div>
                        <div className="container">
                            {messageBox}
                        </div>
                        <div className="row">
                            <form onSubmit={this.handleSubmit}>
                                <div className="mb-3">
                                    <label className="form-label">Djelatnik:</label>
                                    <select className="form-select"
                                            name="id"
                                            onChange={this.handleChange}
                                    >
                                        <option selected disabled>Odaberite djelatnika</option>
                                        {employeesOptions}
                                    </select>
                                </div>
                                <div className='mb-3'>
                                    <label className="form-label">Po??etak perioda provjere:</label>
                                    <input 
                                        type="date" 
                                        className="form-control"
                                        name="dateStart" 
                                        onChange={this.handleChange} 
                                    />
                                </div>
                                <div className='mb-3'>
                                    <label className="form-label">Kraj perioda provjere:</label>
                                    <input 
                                        type="date" 
                                        className="form-control"
                                        name="dateEnd" 
                                        onChange={this.handleChange} 
                                    />
                                </div>
                                <button
                                    type="submit" 
                                    className="btn btn-light mb-5"
                                    onClick={this.handleSubmit}
                                >
                                    Provjeri
                                </button>
                            </form>
                        </div>
                    </div>

                :

                <div className='container d-flex justify-content-center'>
                    <h1 className='alert-danger'>Nedovoljne permisije za prikaz zadataka!</h1>
                </div>}
            </div>

            
        );
    }
}

export default Occupancy;