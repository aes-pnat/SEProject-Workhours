import React from 'react'
import * as VFX from 'react-vfx';
import Backvid from './video/background.mp4';

const MainPage = () => {
    return (
        <div>
            <div className="align-top"></div>
            <div className="align-middle">
            <video autoPlay loop muted
            style={{
                position:"absolute",
                width:"100%",
                left: "50%",
                top: "50%",
                height: "100%",
                objectFit:"cover",
                transform:"translate(-50%, -50%)",
                zIndex:"-1"
            }}
            >
                <source src={Backvid} type="video/mp4"/>
            </video>
                <p className="text-center text-white h1">Radno vrijeme&copy; by Mi puno radimo </p>
            </div>
            
        </div>
    )
}

export default MainPage
