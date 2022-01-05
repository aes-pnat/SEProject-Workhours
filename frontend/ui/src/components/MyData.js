import React from 'react';
import authHeader from '../services/auth-header';
class MyData extends React.Component {
    state = {
        HARDKODIRANI_USERNAME_PROMIJENITI_OVO: 'hWang',
        name: '',
        surname: '',
        username: '',
        pid: '',
        roleName: '',
        groupNames: [],
        tasks: []
    }

    componentDidMount = async () => {
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);

        await fetch(process.env.REACT_APP_BACKEND_URL + '/mydata?username='
             + this.state.HARDKODIRANI_USERNAME_PROMIJENITI_OVO, {
            method: 'GET',
            headers: myHeaders
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({
                name: jsonResponse.name,
                surname: jsonResponse.surname,
                email: jsonResponse.email,
                username: jsonResponse.username,
                pid: jsonResponse.pid,
                roleName: jsonResponse.roleName,
                groupNames: jsonResponse.groupNames,
                tasks: jsonResponse.tasks
            });
        });
    }

    render() {
        let groupNames = this.state.groupNames.map((group) => {
            return (
                <li key={group}>{group}</li>
            );
        });

        let tasks = this.state.tasks.map((task) => {
            return (
                <div className="card mb-3">
                    <div className="card-body">
                        <p className="h5">{task.name}</p>
                        <p className="fst-italic">
                            Djelatnost: {task.idjob.name} <br />
                            Lokacija: {task.idlocation === null ? "Nema lokacije" : task.idlocation.placename + ', ' + task.idlocation.address}
                        </p>
                        <p className="fst-italic">
                            Od: {(new Date(task.datetimestart)).toLocaleString('en-GB')} do: {(new Date(task.datetimeend)).toLocaleString('en-GB')} <br />
                            Procjena broja sati: {task.hoursneededestimate}
                        </p>
                        <p>
                            {task.description}
                        </p>
                    </div>
                </div>
            );
        });

        return (
            <div className="container">
                <div className="card mt-5">
                    <div className="card-body">
                        <p className="h3 mb-3">Moji podaci</p>
                        <p>
                            <span className="fw-bold">Ime i prezime: </span>
                            {this.state.name} {this.state.surname}
                        </p>
                        <p>
                            <span className="fw-bold">E-mail: </span>
                            {this.state.email}
                        </p>
                        <p>
                            <span className="fw-bold">Korisničko ime: </span>
                            {this.state.username}
                        </p>
                        <p>
                            <span className="fw-bold">OIB: </span>
                            {this.state.pid}
                        </p>
                        <p>
                            <span className="fw-bold">Uloga: </span>
                            {this.state.roleName}
                        </p>
                    </div>
                </div>
                <div className="card mt-5">
                    <div className="card-body">
                        <p className="h3 mb-3">Moje grupe</p>
                        <ul>
                            {groupNames}
                        </ul>
                    </div>
                </div>
                <div className="card mt-5">
                    <div className="card-body">
                        <p className="h3 mb-3">Moji zadaci</p>
                        {tasks}
                    </div>
                </div>
            </div>
        );
    }
}

export default MyData;