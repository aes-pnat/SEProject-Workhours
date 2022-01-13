import React, {useState, useEffect} from 'react';
import {
    BrowserRouter as Router,
    Routes,
    Switch,
    Route,
    Link,
    useRouteMatch
  } from 'react-router-dom';
import authHeader from '../services/auth-header';
import JobsAdd from './JobsAdd';

class Jobs extends React.Component {
    state = {
        data:[],
        success: null,
    }
    

    componentDidMount = async () => {
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);

        await fetch(process.env.REACT_APP_BACKEND_URL + '/jobs', {
            method: 'GET',
            headers: myHeaders
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({ data: jsonResponse });
        })

    }

    handleDelete = async (e) => {
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);
        // console.log("here");
        // console.log(e);
        // console.log("there");

        const body = JSON.stringify({
            groupId: parseInt(e),
        });
        console.log(body);
        await fetch(process.env.REACT_APP_BACKEND_URL + '/jobs/delete?id='+e, {

            method: 'POST',
            headers : myHeaders
        }).then((response) => {
            if(response.ok){
                //this.setState({ success: true });
            }
        }).catch((err) => {
            throw err;
        });
        try{
            getJobs();
        }catch(err){
            console.log("error u jobs");
        }
        
    }
    

    render () {
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
        }

        //console.log(employees)

        return (
            <div className='container'>
            <br />
            {props.role === "[ROLE_OWNER]" &&
                <div>
                 <Link to={`${url}/add`} className="btn btn-primary">Dodaj djelatnost</Link>
                </div>
            }
            <Switch>
                <Route exact path={path}>
                <div>
                    {this.state.data.map((job)=>(
                        <div>
                            <div className="card">
                                <div className="card-body">
                                    <p className="h5">{job.name}</p>
                                    <p className="fst-italic">Cijena radnog sata: {job.hourprice} kn </p>
                                    <p className="fst-italic">Cijena djelatnosti: {job.price} kn </p>
                                    <p>{job.description}</p>
                                    <button
                                        className="btn btn-danger mb-5"
                                        onClick={() => handleDelete(job.id)}
                                    >Obriši
                                    </button>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
                </Route>
                <Route path={`${path}/add`}>
                    <JobsAdd />
                </Route>
            </Switch>

        </div>
        );
    }
}

export default Jobs;
