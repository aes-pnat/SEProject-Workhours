import React from 'react';
import {Link} from 'react-router-dom';

class Tasks extends React.Component {
    state = {
        tasks: []
    }

    render () {
        return (
            <div className="container">
                <div className="container mb-3 mt-3">
                    <button className="btn btn-primary"><Link to='/tasks/add'>Dodaj zadatak</Link></button>
                </div>
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