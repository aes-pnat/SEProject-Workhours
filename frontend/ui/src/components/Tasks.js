import React from 'react';
import { BrowserRouter, Switch, Route, Link, Router } from 'react-router-dom';
import AddTask from './AddTask';
import authHeader from '../services/auth-header';
import User from '../services/User';
class Tasks extends React.Component {
    state = {
        HARDKODIRANI_ID_PROMIJENITI_OVO: '00000000001',
        tasks: []
    }

    componentDidMount = async () => {
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);

        await fetch(process.env.REACT_APP_BACKEND_URL + '/tasks?idLeader='
             + this.state.HARDKODIRANI_ID_PROMIJENITI_OVO, {
            method: 'GET',
            headers: myHeaders
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            jsonResponse.forEach(task => {
                this.setState({
                    task: this.state.tasks.push(task)
                });
            });
        }).catch(error => {
            console.log(error);
        });
    }

    render () {
<<<<<<< HEAD
        let tasks;
        var role = User.getRole();
        if(role !== ""){
            tasks = this.state.tasks.map(task => {
                return (
                    <div className="card mb-3">
                        <div className="card-body">
                            <p className="h5 text-light">{task.taskName}</p>
                            <p className="fst-italic text-light">
                                Djelatnik: {task.employeeName} {task.employeeSurname} <br />
                                Djelatnost: {task.job.name}
                            </p>
                            <p className="fst-italic text-light">
                                Od: {(new Date(task.startDateAndTime)).toLocaleString('en-GB')} do: {(new Date(task.endDateAndTime)).toLocaleString('en-GB')} <br />
                                Procjena broja sati: {task.estimatedDuration}
                            </p>
                            <p>
                                {task.description}
                            </p>
                        </div>
=======
        
        let tasks = this.state.tasks.map(task => {
            return (
                <div className="card mb-3">
                    <div className="card-body">
                        <p className="h5">{task.taskName}</p>
                        <p className="fst-italic">
                            Djelatnik: {task.employeeName} {task.employeeSurname} <br />
                            Djelatnost: {task.job.name}
                        </p>
                        <p className="fst-italic">
                            Od: {(new Date(task.startDateAndTime)).toLocaleString('en-GB')} do: {(new Date(task.endDateAndTime)).toLocaleString('en-GB')} <br />
                            Procjena broja sati: {task.estimatedDuration}
                        </p>
                        <p>
                            {task.description}
                        </p>
>>>>>>> 36185bc6725f420a6c17d9552e1befb9f71f6490
                    </div>
                );
            })
        }
        return (
<<<<<<< HEAD
            <div className="container mt-5">
                {role !== "" ?
                    <div>
                    {role === "[ROLE_EMPLOYEE]" ?
                        <div className="h3 mb-3 text-light">Moji zadaci:</div>
                    :
                        <div className="h3 mb-3 text-light">Zadaci djelatnika iz mojih grupa:</div>
                    }
                        
=======
            <div>
                {this.props.role === "[ROLE_LEADER]" ?
            
                <div className="container mt-5">
                    <div className="h3 mb-3 text-light">Zadaci djelatnika iz mojih grupa</div>
>>>>>>> 36185bc6725f420a6c17d9552e1befb9f71f6490
                    <Link to={`tasks/add`}>
                        <button className="btn btn-light mb-3">Novi zadatak</button>
                    </Link>
                    {tasks}
<<<<<<< HEAD
=======

>>>>>>> 36185bc6725f420a6c17d9552e1befb9f71f6490
                    <Switch>
                        <Route exact path={`/add`}>
                            <AddTask />
                        </Route>
                    </Switch>
                </div>
<<<<<<< HEAD
                :
                <div className='alert alert-danger d-flex justify-content-center'>
                    <h3>Prijavite se kako bi vidjeli ovlaštene zadatke!</h3>
                </div>
                }  
=======

                :

                <div className='container d-flex justify-content-center'>
                    <h1 className='alert-danger'>Nedovoljne permisije za prikaz zadataka!</h1>
                </div>}
>>>>>>> 36185bc6725f420a6c17d9552e1befb9f71f6490
            </div>
        );
    }
}

export default Tasks;