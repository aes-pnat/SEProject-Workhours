import React from 'react'
import Select from 'react-select';
import '../index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import authHeader from '../services/auth-header';
class GroupsAdd extends React.Component {

    state = {
        name: '',
        leaderid: '',
        employeesids: [],
        employeesList:[],
        employeesWoLeader:[],
        jobid:'',
        jobs:[],
        success: null,
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
            this.setState({ employeesWoLeader: jsonResponse });
        })

        await fetch(process.env.REACT_APP_BACKEND_URL + '/jobs', {
            method: 'GET',
            headers: myHeaders
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({ jobs: jsonResponse });
        })
    }
    

    handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        this.setState({ [name]: value });
        console.log(name , value);
        
    }

    handleChangeLID = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        this.setState({ [name]: value });
        let removeleader = this.state.employeesList.filter ( a => a.id != value);
        this.setState({ employeesWoLeader: removeleader });
    }

    

    handleChangeArray = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        //const newempid = this.state.employeesids;
        this.setState(previousState => ({
            employeesids: [...previousState.employeesids, value]
        }));
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
        
        await fetch(process.env.REACT_APP_BACKEND_URL + '/groups/add', {
            method: 'POST',
            headers: myHeaders,
            body: body
        }).then((response) => {
            if(response.ok){
                this.setState({ success: true });
            } else {
                this.setState({ success: false });
            }
        }).catch((err) => {
            throw err;
        });
    }

    render () {
        //console.log('here');
        //console.log(this.state.jobs);
        //console.log('done');
        let employees;
        if (this.state.employeesList.length === 0) {
            employees = (
                <option disabled>Nema zaposlenika za voditelja</option>
            );
        } else {
            employees = this.state.employeesList.map(employee => {
                return (
                    <option key={employee.name} value={employee.id}>{employee.name}</option>
                );
            })
        }

        let jobslist;
        if (this.state.jobs.length === 0) {
            jobslist = (
                <option disabled>Nema djelatnosti</option>
            );
        } else {
            jobslist = this.state.jobs.map(job => {
                return (
                    <option key={job.id} value={job.id}>{job.name}</option>
                );
            })
        }

        let emplist=[];
        if (this.state.employeesWoLeader.length === 0) {
            emplist = (
                <li>Nema zaposlenika</li>
            );
        } else {
            emplist = this.state.employeesWoLeader.map(employee => {
                return (
                    <div>                    
                    <li key={employee.name} value={employee.id}>   
                        <input
                            name="employeesids"
                            value={employee.id} 
                            type="checkbox" 
                            onChange={this.handleChangeArray}
                        />   {employee.name}    
                    </li>
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
        } else if(this.state.success === false){
            messageBox = (
                <div className="alert alert-danger">
                    Neispravan unos grupe
                </div>
            );
        }

        //console.log(employees)

        return (
            
            
            <div className="container mt-5 text-light">
                {this.props.role === "[ROLE_OWNER]" ?
                    <div>
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
                                name="name"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Djelatnost:</label>
                            <select className="form-select"
                                    name="jobid"
                                    onChange={this.handleChange}
                            >
                                <option selected disabled>Odaberite djelatnost</option>
                                {jobslist}
                            </select>
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Voditelj:</label>
                            <select className="form-select"
                                    name="leaderid"
                                    onChange={this.handleChangeLID}
                            >
                                <option selected disabled>Odaberite voditelja</option>
                                {employees}
                            </select>
                        </div>
                        <div className="scroll" >
                            <label className="form-label">Odaberite članove grupe:</label>
                            <ul>
                                {emplist}
                            </ul>
                        </div>
                        <button 
                            type="submit"
                            className="btn btn-light mb-5"
                            onClick={this.handleSubmit}
                        >
                            Stvori grupu
                        </button>
                    </form>
                </div>
                    </div>
                :
                <div className='d-flex justify-content-center alert alert-danger'>
                    <h2>Nedovoljne ovlasti za dodavanje grupa!</h2>
                </div>
            }
                
            </div>
        );
    }
}
export default GroupsAdd
