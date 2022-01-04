import React from 'react';

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
        jobs: []
    }

    componentDidMount = async () => {
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");

        await fetch(process.env.REACT_APP_BACKEND_URL + '/tasks/add', {
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
        })
    }

    handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.type == 'checkbox' ? e.target.checked : e.target.value;
        this.setState({ [name]: value });
    }


    render () {
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

        return (
            <div className="container mt-5">
                <div className="h3 mb-3">Novi zadatak</div>
                <div className="row">
                    <form onSubmit={this.handleSubmit}>
                        <div className="mb-3">
                            <label className="form-label">Naziv zadatka:</label>
                            <input
                                className="form-control"
                                name="taskName"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <div className="h6 mb-3">Djelatnici</div>
                            {employeeChecks}
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Opis:</label>
                            <textarea
                                className="form-control"
                                name="taskDescription"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Procjena broja sati:</label>
                            <input
                                type="number"
                                className="form-control"
                                name="hoursEstimate"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className='mb-3'>
                            <label className="form-label">Datum početka:</label>
                            <input 
                                type="date" 
                                className="form-control"
                                name="dateStart" 
                                onChange={this.handleChange} 
                            />
                        </div>
                        <div className='mb-3'>
                            <label className="form-label">Datum završetka:</label>
                            <input 
                                type="date" 
                                className="form-control"
                                name="dateEnd" 
                                onChange={this.handleChange} 
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Djelatnost:</label>
                            <select className="form-select"
                                    name="jobID"
                                    onChange={this.handleChange}
                            >
                                <option selected disabled>Odaberite djelatnost</option>
                                <option>Nesto</option>
                            </select>
                        </div>
                        <div className="h6 mb-3 mt-3">Lokacija - odaberite postojeću ili dodajte novu</div>
                        <div className="mb-3">
                            <label className="form-label">Postojeća lokacija:</label>
                            <select className="form-select"
                                    name="locationID"
                                    onChange={this.handleChange}
                            >
                                <option selected disabled>Odaberite lokaciju</option>
                                <option>Nesto</option>
                            </select>
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Adresa nove lokacije:</label>
                            <input
                                className="form-control"
                                name="newLocationAddress"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Mjesto nove lokacije:</label>
                            <input
                                className="form-control"
                                name="newLocationPlaceName"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Geografska širina nove lokacije:</label>
                            <input
                                type="number"
                                className="form-control"
                                name="newLocationLatitude"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="mb-10">
                            <label className="form-label">Geografska duljina nove lokacije:</label>
                            <input
                                type="number"
                                className="form-control"
                                name="newLocationLongitude"
                                onChange={this.handleChange}
                            />
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}

export default AddTask;