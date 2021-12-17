import React from 'react';

class Tasks extends React.Component {
    state = {
        tasks: []
    }

    render () {
        return (
            <div className="container">
                <div className="card">
                    <div className="card-body">
                        <p className="h5">Zadatak 1</p>
                        <p className="fst-italic">
                            Od: 1.1.2021. 
                            do: 2.1.2021.
                        </p>
                        <p>
                            Opis zadatka opis zadatka opis zadatka opis zadatka opis zadatka
                        </p>
                    </div>
                </div>
            </div>
        );
    }
}

export default Tasks;