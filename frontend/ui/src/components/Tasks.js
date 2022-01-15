import React from 'react';
import { Switch, Route, Link } from 'react-router-dom';
import AddTask from './AddTask';
import authHeader from '../services/auth-header';
import User from '../services/User';
class Tasks extends React.Component {
    state = {
        tasks: [],
        groups: [],
        selectedGroup: null
    }

    componentDidMount = async () => {
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);

        // await fetch(process.env.REACT_APP_BACKEND_URL + '/tasks', {
        //     method: 'GET',
        //     headers: myHeaders
        // }).then((response) => {
        //     if(response.ok) {
        //         return response.json();
        //     }
        // }).then((jsonResponse) => {
        //     jsonResponse.forEach(task => {
        //         this.setState({
        //             task: this.state.tasks.push(task)
        //         });
        //     });
        // }).catch(error => {
        //     console.log(error);
        // });

        await fetch(process.env.REACT_APP_BACKEND_URL + '/tasks?groupName=-1', {
            method: 'GET',
            headers: myHeaders
        }).then((response) =>{
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({ groups: jsonResponse });
        }).catch((err) => {
            console.log("failed fetch");
        });
    }

    handleGroupChange = (e) => {
        const groupName = e.target.value;
        await fetch(process.env.REACT_APP_BACKEND_URL + '/tasks?groupName=' + groupName, {
            method: 'GET',
            headers: myHeaders
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({ tasks: jsonResponse });
        }).catch(error => {
            console.log(error);
        });
    }

    render () {
        let tasks, groupsOptions = [];
        var role = User.getRole();
        if(role !== ""){
            tasks = this.state.tasks.map(task => {
                return (
                    <div className="card mb-3">
                        <div className="card-body">
                            <p className="h4 fst-italic">{task.taskName}</p>
                            <p className="h6">
                                Djelatnik: {task.employeeName} {task.employeeSurname} <br />
                                Djelatnost: {task.job.name}
                            </p>
                            <p className="h6">
                                Od: {(new Date(task.startDateAndTime)).toLocaleString('en-GB')} do: {(new Date(task.endDateAndTime)).toLocaleString('en-GB')} <br />
                                Procjena broja sati: {task.estimatedDuration}
                            </p>
                            <p>
                                {task.description}
                            </p>
                        </div>
                    </div>
                );
            })
            groupsOptions = this.state.groups.map(groupName => {
                return (
                    <option key={groupName} value={groupName}>{groupName}</option>
                );
            });
        }
        return (
            <div className="container mt-5">
                {role === "[ROLE_LEADER]" ?
                    <div className="h3 mb-3">
                        <h3 className='text-light mb-3'>Zadaci djelatnika iz mojih grupa:</h3>
                    <Link to={`tasks/add`}>
                        <button className="btn btn-light mb-3 ">Novi zadatak</button>
                    </Link>
                    <select name="group" onChange={handleGroupChange}>
                        {groupsOptions}
                    </select>
                    {tasks}
                    <Switch>
                        <Route exact path={`/add`}>
                            <AddTask />
                        </Route>
                    </Switch>
                </div>
                :
                <div className='alert alert-danger d-flex justify-content-center'>
                    <h3>Prijavite se kako bi vidjeli ovlaštene zadatke!</h3>
                </div>
                }  
            </div>
        );
    }
}

export default Tasks;