import React, { useState} from 'react';
import './App.css';
import User from './services/User';
import Navbar from './components/Navbar';
import Backvid from './components/video/background.mp4';
function App() {
    const [token, setToken] = useState(User.getToken());
    const [role, setRole] = useState(User.getRole());
    const [user, setUser] = useState('');
    return (
        <div>
            
            <video autoPlay loop muted
            style={{
                position:"fixed",
                width:"100%",
                left: "50%",
                top: "50%",
                height: "100%",
                objectFit:"cover",
                transform:"translate(-50%, -50%)",
                zIndex:"-1",
            }}
            >
                 <source src={Backvid} type="video/mp4"/>
            </video>
            
            <Navbar token={token} 
                setToken={setToken} 
                role={role} setRole={setRole} 
                setUser={setUser}
                username={user}/>
        </div>
        
    );
}

export default App;