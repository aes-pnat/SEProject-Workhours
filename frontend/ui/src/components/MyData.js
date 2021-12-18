import React from 'react';

class MyData extends React.Component {
    state = {
        HARDKODIRANI_USERNAME_PROMIJENITI_OVO: 'hWang',
        name: '',
        surname: '',
        username: '',
        pid: '',
        roleName: '',
        groupNames: [],
        taskNames: []
    }

    componentDidMount = async () => {
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");

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
                taskNames: jsonResponse.taskNames
            });
        });
    }

    render() {
        let groupNames = this.state.groupNames.map((group) => {
            return (
                <li key={group}>{group}</li>
            );
        });

        let taskNames = this.state.taskNames.map((task) => {
            return (
                <li key={task}>{task}</li>
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
                <div className="card mt-5 mb-3">
                    <div className="card-body">
                        <p className="h3 mb-3">Moji zadaci</p>
                        <ul>
                            {taskNames}
                        </ul>
                    </div>
                </div>
            </div>
        );
    }
}

export default MyData;