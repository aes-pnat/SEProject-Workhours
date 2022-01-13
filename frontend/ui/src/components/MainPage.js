import React from 'react'
import * as VFX from 'react-vfx';
import Backvid from './video/background.mp4';

const MainPage = () => {
    return (
        <div>
            <div className="align-top"></div>
            <div className="align-middle">
                <p className="text-center h1">Radno vrijeme&copy; by Mi puno radimo </p>
            </div>
            <video autoPlay loop muted>
                <source src={Backvid} type="video/mp4"/>
            </video>
        </div>
    )
}

export default MainPage
