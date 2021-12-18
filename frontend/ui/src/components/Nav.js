import React from 'react';
import {Link} from "react-router-dom";

const Nav = (props) => {
        const logout = async () => {
    //     await fetch('http://localhost:8080/logout', {
    //         method: 'POST',
    //         headers: {'Content-Type': 'application/json'},
    //         credentials: 'include'
    //     });

        props.setUsername('');
    }

    let menu;

    if (props.username === '') {
        menu = (
            <ul className="navbar-nav me-auto mb-2 mb-md-0">
                <li className="nav-item active">
                    <Link to="/login" className="nav-link">Login</Link>
                </li>
                <li className="nav-item active">
                    <Link to="/login" className="nav-link">Also Login</Link>
                </li>
            </ul>
        )
    } else {
        console.log("Username je: "+props.username);
        menu = (
            <ul className="navbar-nav me-auto mb-2 mb-md-0">
                <li className="nav-item active">
                    <Link to="/login" className="nav-link" onClick={logout}>Logout</Link>
                </li>
            </ul>
        )
    }

    return (
        <nav className="navbar navbar-expand-md navbar-dark bg-dark mb-4">
            <div className="container-fluid">
                <Link to="/" className="navbar-brand">Home</Link>

                <div>
                    {menu}
                </div>
            </div>
        </nav>
    );
};

export default Nav;