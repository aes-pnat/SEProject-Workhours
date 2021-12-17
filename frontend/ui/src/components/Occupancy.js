import React from 'react';

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

        await fetch(process.env.REACT_APP_BACKEND_URL + '/occupancy', {
            method: 'GET',
            headers: myHeaders
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({ employeesList: jsonResponse });
        })
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

        this.setState({ message: '' });
        console.log(body);

        await fetch(process.env.REACT_APP_BACKEND_URL + '/occupancy', {
            method: 'POST',
            headers: myHeaders,
            body: body
        }).then((response) => {
            if(response.ok){
                return response.json();
            }
        }).then((jsonResponse) => {
            console.log(jsonResponse);
        });

    }

    render() {
        let employeesOptions = this.state.employeesList.map((employee) => {
            return (
                <option key={employee.id} value={employee.id}>{employee.name}</option>
            );
        });

        return (
            <div className="container mt-5">
                <div className="container">
                    {this.state.message}
                </div>
                <div className="row">
                    <form onSubmit={this.handleSubmit}>
                        <div className="mb-3">
                            <label className="form-label">Djelatnik:</label>
                            <select className="form-select"
                                    name="id"
                                    onChange={this.handleChange}
                            >
                                {employeesOptions}
                            </select>
                        </div>
                        <div className='mb-3'>
                            <label className="form-label">Početak perioda provjere:</label>
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
                            className="btn btn-primary mb-5"
                            onClick={this.handleSubmit}
                        >
                            Provjeri
                        </button>
                    </form>
                </div>
            </div>
        );
    }
}

export default Occupancy;