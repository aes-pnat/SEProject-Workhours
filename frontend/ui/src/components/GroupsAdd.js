import React from 'react'
import Select from 'react-select';
import '../index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
class GroupsAdd extends React.Component {
    state = {
        name: '',
        leaderid: '',
        employeesids: [],
        //success: null,
    }

    componentDidMount = async () => {
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");

        await fetch(process.env.REACT_APP_BACKEND_URL + '/occupancy?idEmployee='
            + this.state.HARDKODIRANI_ID_PROMIJENITI_OVO, {
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
            name: this.state.name,
            leaderid: this.state.leaderid,
            employeesids: this.state.employeesids,
        });

        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");

        await fetch(process.env.REACT_APP_BACKEND_URL + '/groups/add', {
            method: 'POST',
            headers: myHeaders,
            body: body
        }).then((response) => {
            if(response.ok){
                //this.setState({ success: true });
            }
        }).catch((err) => {
            throw err;
        });
    }

    render () {
        let employees;
        if (this.state.employeesList.length === 0) {
            employees = (
                <option disabled>Nema zaposlenika</option>
            );
        } else {
            employees = this.state.employeesList.map(employee => {
                return (
                    <option key={employee.name} value={employee.name}>{employee.name}</option>
                );
            })
        }

        let emplist=[];
        if (this.state.employeesList.length === 0) {
            emplist = (
                <li>Nema zaposlenika</li>
            );
        } else {
            emplist = this.state.employeesList.map(employee => {
                return (
                    <div>
                    
                    <li key={employee.name} value={employee.name}>   <input value={employee.name} type="checkbox" />   {employee.name}    </li>
                    </div>
                );
            })
        }

        let messageBox = '';
        if (this.state.success) {
            messageBox = (
                <div className="alert alert-success">
                    Grupa je stvorena
                </div>
            );
        }

        console.log(employees)

        return (
            
            
            <div className="container mt-5">
                <div className="h3 mb-3">Stvaranje grupe</div>
                <div className="container">
                    {messageBox}
                </div>
                <div className="row">
                    <form onSubmit={this.handleSubmit}>
                        <div className="mb-3">
                            <label className="form-label">Ime grupe:</label>
                            <input
                                className="form-control"
                                name="groupName"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Voditelj:</label>
                            <select className="form-select"
                                    name="employee"
                                    onChange={this.handleChange}
                            >
                                <option selected disabled>Odaberite voditelja</option>
                                {employees}
                            </select>
                        </div>
                        <div className="scroll">
                            <ul>
                                {emplist}
                            </ul>
                        </div>
                        <button 
                            type="submit"
                            className="btn btn-primary mb-5"
                            onClick={this.handleSubmit}
                        >
                            Stvori grupu
                        </button>
                    </form>
                </div>
            </div>
        );
    }
}
export default GroupsAdd
