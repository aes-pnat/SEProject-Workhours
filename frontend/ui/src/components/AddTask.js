import React from 'react';
import authHeader from '../services/auth-header';
import User from '../services/User';
class AddTask extends React.Component {
    state = {
        taskName: '',
        taskDescription: '',
        hoursEstimate: '',
        dateStart: '',
        dateEnd: '',
        jobID: '',
        locationID: '',
        newLocationAddress: '',
        newLocationPlaceName: '',
        newLocationLatitude: '',
        newLocationLongitude: '',
        employees: [],
        existingLocations: [],
        jobs: [],
        groups: [],
        selectedGroup: null
    }

    componentDidMount = async () => {
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);

        await fetch(process.env.REACT_APP_BACKEND_URL + '/tasks?groupName=-1', {
            method: 'GET',
            headers: myHeaders
        }).then((response) =>{
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            if (jsonResponse !== undefined) {
                this.setState({ groups: jsonResponse.groups });
            }
        }).catch((err) => {
            console.log("failed fetch");
        });
    }

    handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.type === 'checkbox' ? e.target.checked : e.target.value;
        this.setState({ [name]: value });
    }

    handleGroupChange = async (e) => {
        const groupName = e.target.value;
        this.setState({ selectedGroup: groupName });

        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);

        await fetch(process.env.REACT_APP_BACKEND_URL + '/tasks/add?groupName=' + groupName, {
            method: 'GET',
            headers: myHeaders
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({ 
                employees: jsonResponse.employees,
                existingLocations: jsonResponse.existingLocations,
                jobs: jsonResponse.jobs
            });
            this.state.employees.forEach(employee => {
                this.setState({ [employee.id]: false });
            });
        }).catch( (error) => {
            console.log(error);
        })
    }

    handleSubmit = async (e) => {
        e.preventDefault();

        let employeeIDs = [];
        this.state.employees.forEach(employee => {
            if (this.state[employee.id]) {
                employeeIDs.push(employee.id);
            }
        });

        const body = JSON.stringify({
            taskName: this.state.taskName,
            employeeIDs,
            taskDescription: this.state.taskDescription,
            hoursEstimate: this.state.hoursEstimate,
            dateStart: this.state.dateStart,
            dateEnd: this.state.dateEnd,
            jobID: this.state.jobID,
            locationID: this.state.locationID,
            newLocationAddress: this.state.newLocationAddress,
            newLocationPlaceName: this.state.newLocationPlaceName,
            newLocationLatitude: this.state.newLocationLatitude,
            newLocationLongitude: this.state.newLocationLongitude
        });

        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);

        console.log(body);

        await fetch(process.env.REACT_APP_BACKEND_URL + '/tasks/add', {
            method: 'POST',
            headers: myHeaders,
            body: body
        }).then((response) => {
            if(response.ok){
                alert("Uspjesno dodano");
            }
        }).catch((err) => {
            alert("ERROR - NIJE USPJESNO IZVEDENO!");
        });
    }

    render () {
        var role = User.getRole();
        let employeeChecks = this.state.employees.map(employee => {
            return (
                <div key={employee.id} className="form-check">
                    <input 
                        className="form-check-input" 
                        type="checkbox" 
                        name={employee.id}
                        value=""
                        id={employee.id}
                        onChange={this.handleChange}
                    />
                    <label className="form-check-label" htmlFor={employee.id}>
                        {employee.name}
                    </label>
                </div>
            );
        });

        let groupsOptions;
        groupsOptions = this.state.groups.map(groupName => {
            return (
                <option key={groupName} value={groupName}>{groupName}</option>
            );
        });

        let locationOptions = this.state.existingLocations.map(location => {
            return (
                <option
                    key={location.id}
                    value={location.id}
                >
                    {location.address}
                </option>
            );
        });

        let jobOptions = this.state.jobs.map(job => {
            return (
                <option
                    key={job.id}
                    value={job.id}
                >
                    {job.name}
                </option>
            )
        })
        return (
            <div className="container mt-5 text-light">
                {role === "[ROLE_LEADER]" ?
                    <div>
                        <div className="h3 mb-3 text-light">Novi zadatak</div>
                     <div className="row">
                    <form onSubmit={this.handleSubmit}>
                        <div className="mb-3">
                            <label className="form-label text-light">Naziv zadatka:</label>
                            <input
                                className="form-control"
                                name="taskName"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className='mb-3'>
                            <select className="form-select mb-3" name="group" onChange={this.handleGroupChange}>
                                <option>Odaberite grupu</option>
                                {groupsOptions}
                            </select>
                        </div>
                        <div className="mb-3 scroll">
                            <div className="h6 mb-3 text-light">Djelatnici</div>
                            {employeeChecks}
                        </div>
                        <div className="mb-3">
                            <label className="form-label text-light">Opis:</label>
                            <textarea
                                className="form-control"
                                name="taskDescription"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label text-light">Procjena broja sati:</label>
                            <input
                                type="number"
                                className="form-control"
                                name="hoursEstimate"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className='mb-3'>
                            <label className="form-label text-light">Datum početka:</label>
                            <input 
                                type="date" 
                                className="form-control"
                                name="dateStart" 
                                onChange={this.handleChange} 
                            />
                        </div>
                        <div className='mb-3'>
                            <label className="form-label text-light">Datum završetka:</label>
                            <input 
                                type="date" 
                                className="form-control"
                                name="dateEnd" 
                                onChange={this.handleChange} 
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label text-light">Djelatnost:</label>
                            <select className="form-select"
                                    name="jobID"
                                    onChange={this.handleChange}
                            >
                                <option selected disabled>Odaberite djelatnost</option>
                                {jobOptions}
                            </select>
                        </div>
                        <div className="h6 mb-3 mt-3 text-light">Lokacija - odaberite postojeću ili dodajte novu</div>
                        <div className="mb-3">
                            <label className="form-label text-light">Postojeća lokacija:</label>
                            <select className="form-select"
                                    name="locationID"
                                    onChange={this.handleChange}
                            >
                                <option selected disabled>Odaberite lokaciju</option>
                                {locationOptions}
                            </select>
                        </div>
                        <div className="mb-3">
                            <label className="form-label text-light">Adresa nove lokacije:</label>
                            <input
                                className="form-control"
                                name="newLocationAddress"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label text-light">Mjesto nove lokacije:</label>
                            <input
                                className="form-control"
                                name="newLocationPlaceName"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label text-light">Geografska širina nove lokacije:</label>
                            <input
                                type="number"
                                className="form-control"
                                name="newLocationLatitude"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label text-light">Geografska duljina nove lokacije:</label>
                            <input
                                type="number"
                                className="form-control"
                                name="newLocationLongitude"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div>
                            <button 
                                type="submit"
                                className="btn btn-primary mb-5 btn-light"
                                onClick={this.handleSubmit}
                            >
                                Stvori zadatak
                            </button>
                        </div>
                    </form>
                </div>
                    </div>
                    :
                    <div className='d-flex justify-content-center alert alert-danger'>
                        <h3 className='danger alert-danger'>Prijavite se kako biste mogli dodavat zadatke!</h3>
                    </div>
                }

                
            </div>
        );
    }
}

export default AddTask;