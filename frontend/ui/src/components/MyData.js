import React from 'react';

class MyData extends React.Component {
    state = {
        firstName: '',
        lastName: '',
        username: '',
        pid: '',
        role: '',
        groups: [],
        tasks: []
    }

    render() {
        return (
            <div className="container">
                <div className="card mt-5">
                    <div className="card-body">
                        <p className="h3 mb-3">Moji podaci</p>
                        <p>
                            <span className="fw-bold">Ime i prezime: </span>
                            Pero Perić
                        </p>
                        <p>
                            <span className="fw-bold">E-mail: </span>
                            pero.peric@firma.hr
                        </p>
                        <p>
                            <span className="fw-bold">Korisničko ime: </span>
                            peroperic
                        </p>
                        <p>
                            <span className="fw-bold">OIB: </span>
                            12345678901
                        </p>
                        <p>
                            <span className="fw-bold">Uloga: </span>
                            Djelatnik
                        </p>
                    </div>
                </div>
                <div className="card mt-5">
                    <div className="card-body">
                        <p className="h3 mb-3">Moje grupe</p>
                        <ul>
                            <li>Grupa 1</li>
                            <li>Grupa 2</li>
                        </ul>
                    </div>
                </div>
                <div className="card mt-5 mb-3">
                    <div className="card-body">
                        <p className="h3 mb-3">Moji zadaci</p>
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
                </div>
            </div>
        );
    }
}

export default MyData;