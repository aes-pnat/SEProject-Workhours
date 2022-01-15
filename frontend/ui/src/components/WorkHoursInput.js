import React from 'react';
import authHeader from '../services/auth-header';
import User from '../services/User';
class WorkHoursInput extends React.Component {
    state = {
        task: '',
        date: '',
        hoursDone: '',
        success: null,
        tasksList: []
    }

    componentDidMount = async () => {
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);

        await fetch(process.env.REACT_APP_BACKEND_URL + '/workhoursinput', {
            method: 'GET',
            headers: myHeaders
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({ tasksList: jsonResponse });
        }).catch( (error) => {
            console.log(error);
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
            task: this.state.task,
            date: this.state.date,
            hoursDone: this.state.hoursDone,
            idEmployee: this.state.HARDKODIRANI_ID_PROMIJENITI_OVO
        });

        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);
        
        await fetch(process.env.REACT_APP_BACKEND_URL + '/workhoursinput', {
            method: 'POST',
            headers: myHeaders,
            body: body
        }).then((response) => {
            if(response.ok){
                this.setState({ success: true });
            }
        }).catch((err) => {
            console.log(err);
        });
    }

    render () {
        var role = User.getRole();
        let tasks;
        if (this.state.tasksList && 'length' in this.state.tasksList && this.state.tasksList.length > 0) {
            tasks = this.state.tasksList.map(task => {
                return (
                    <option value={task}>{task}</option>
                );
            })
        } else {
            tasks = (
                <option disabled>Nema aktivnih zadataka za djelatnika</option>
            );
        }

        let messageBox = '';
        if (this.state.success) {
            messageBox = (
                <div className="alert alert-success">
                    Radni sati uspješno upisani.
                </div>
            );
        }

        return (
            <div className="container mt-5 text-light">
                {role === "[ROLE_LEADER]" || role === "[ROLE_EMPLOYEE]" ?
                    <div>
                    <div className="h3 mb-3">Unos radnih sati</div>
                    <div className="container">
                        {messageBox}
                    </div>
                    <div className="row">
                    <form onSubmit={this.handleSubmit}>
                        <div className="mb-3">
                            <label className="form-label">Zadatak:</label>
                            <select className="form-select"
                                    name="task"
                                    onChange={this.handleChange}
                            >
                                <option selected disabled>Odaberite zadatak</option>
                                {tasks}
                            </select>
                        </div>
                        <div className='mb-3'>
                            <label className="form-label">Datum:</label>
                            <input 
                                type="date" 
                                className="form-control"
                                name="date" 
                                onChange={this.handleChange} 
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Broj sati:</label>
                            <input
                                type="number"
                                className="form-control"
                                name="hoursDone"
                                onChange={this.handleChange}
                                min="1"
                                max="24"
                            />
                        </div>
                        <button 
                            type="submit"
                            className="btn btn-primary mb-5"
                            onClick={this.handleSubmit}
                        >
                            Pošalji
                        </button>
                    </form>
                    </div>
                </div>
                :
                <div className='d-flex justify-content-center alert alert-danger'>
                        <h3 className='danger alert-danger'>Prijavite se kako biste mogli upisati odrađene sate!</h3>
                    </div>
                }
                
            </div>
        );
    }
}

export default WorkHoursInput;