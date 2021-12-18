import React from 'react';

class Tasks extends React.Component {
    state = {
        HARDKODIRANI_ID_PROMIJENITI_OVO: '00000000001',
        tasks: []
    }

    componentDidMount = async () => {
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");

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
        });
    }

    render () {
        let tasks = this.state.tasks.map(task => {
            return (
                <div className="card">
                    <div className="card-body">
                        <p className="h5">{task.taskName}</p>
                        <p className="fst-italic">
                            Djelatnik: {task.employeeName} {task.employeeSurname}
                        </p>
                        <p className="fst-italic">
                            Od: {task.startDateAndTime} 
                            do: {task.endDateAndTime}
                        </p>
                        <p>
                            Opis zadatka opis zadatka opis zadatka opis zadatka opis zadatka
                        </p>
                    </div>
                </div>
            );
        })
        return (
            <div className="container mt-5">
                <div className="h3 mb-5">Zadaci djelatnika iz mojih grupa</div>
                {tasks}
            </div>
        );
    }
}

export default Tasks;