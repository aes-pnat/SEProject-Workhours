import React from 'react'

const Register = () => {
    return (
        <div>
            <h2>Create a new account:</h2>
            <form>
                <label>Unesite E-mail:</label>
                <input type="email"  name="email" />
                <label>Unesite novo korisničko ime:</label>
                <input type="text"  name="username"/>
                <label>Unesite zaporku:</label>
                <input type="password"  name="password" />
                <label>Ponovite zaporku:</label>
                <input type="password"  name="password" />
                <input type="submit" />
            </form>
            
        </div>
    )
}

export default Register;
