import React from 'react'

const Register = () => {
    return (
        <div>
            <h2>Create a new account:</h2>
            <form>
                <label>
                    Username:
                    <input type="text" name="name" />
                </label>
                <input type="submit" value="Submit" />
                <label>
                    E-mail:
                    <input type="text" name="name" />
                </label>
                <input type="submit" value="Submit" />
                <label>
                    Password:
                    <input type="text" name="name" />
                </label>
                <input type="submit" value="Submit" />
            </form>
            
        </div>
    )
}

export default Register;
