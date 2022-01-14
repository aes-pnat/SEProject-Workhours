import React from 'react';
import authHeader from '../services/auth-header';
import User from '../services/User';
class MyData extends React.Component {
    state = {
        user: this.props.username,
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
             + this.state.user, {
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
        }).catch( (error) => {
            console.log(error);
        });
    }

    render() {
        let groupNames;
        let tasks;
        if(this.state.user !== ''){
            if(User.getRole() === "[ROLE_LEADER]"){
                groupNames = this.state.groupNames.map((group) => {
                    return (
                        <li key={group}>{group}</li>
                    );
                });
            }
            if(this.state.tasks){
                tasks = this.state.tasks.map((obj) => {
                    return (
                        <div className="card mb-3">
                            <div className="card-body">
                                <p className="h5">{obj.task.name}</p>
                                <p className="fst-italic">
                                    Djelatnost: {obj.task.idjob.name} <br />
                                    Lokacija: {obj.task.idlocation === null ? "Nema lokacije" : obj.task.idlocation.placename + ', ' + obj.task.idlocation.address}
                                </p>
                                <p className="fst-italic">
                                    Od: {(new Date(obj.task.datetimestart)).toLocaleString('en-GB')} do: {(new Date(obj.task.datetimeend)).toLocaleString('en-GB')} <br />
                                    Procjena broja sati: {obj.task.hoursneededestimate}
                                </p>
                                <p>
                                    {obj.task.description}
                                </p>
                            </div>
                        </div>
                    );
                });
            }else{
                tasks = <p className='fst-italic'>Nema dodijeljenih zadataka</p>
            }
            
        }

        return (
            <div className="container">
                { this.state.user !== '' ?
                    <div>
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
                        {this.props.role === "[ROLE_LEADER]" &&
                            <div className="card mt-5">
                                <div className="card-body">
                                    <p className="h3 mb-3">Moje grupe</p>
                                    <ul>
                                        {groupNames}
                                    </ul>
                                </div>
                            </div>
                        }
                        {this.props.role !== "[ROLE_OWNER]" &&
                            <div className="card mt-5">
                                <div className="card-body">
                                    <p className="h3 mb-3">Moji zadaci</p>
                                    {tasks}
                                </div>
                        </  div>
                        }
                </div>
            :
            <div className='d-flex justify-content-center alert alert-danger mt-3'>
                <h3>Trebate se prijavit kako biste vidjeli svoje podatke!</h3>
            </div>    
            }
                
            </div>
        );
    }
}

export default MyData;