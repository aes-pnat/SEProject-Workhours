import React from 'react';
import { BrowserRouter, Switch, Route, Link, Router } from 'react-router-dom';
import AddTask from './AddTask';
import authHeader from '../services/auth-header';
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
                    </div>
                </div>
            );
        })
        return (
            <div className="container mt-5">
                <div className="h3 mb-3">Zadaci djelatnika iz mojih grupa</div>
                <Link to={`tasks/add`}>
                    <button className="btn btn-primary mb-3">Novi zadatak</button>
                </Link>
                {tasks}

                <Switch>
                    <Route exact path={`/add`}>
                        <AddTask />
                    </Route>
                </Switch>
            </div>
        );
    }
}

export default Tasks;