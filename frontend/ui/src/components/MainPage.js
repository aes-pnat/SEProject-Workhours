import React from 'react'
import * as VFX from 'react-vfx';
import Backvid from './video/background.mp4';
import Logo from './video/mpr-logo.png';

const MainPage = () => {
    return (
        <div style={{
            overflow:"hidden"
        }}>
                <p className="text-center text-white h1"
                style={{
                    position:"absolute",
                    width:"100%",
                    top: "50%",
                    height: "100%",
                    overflow:"hidden"
                }}>Radno vrijeme&copy; by Mi puno radimo
                <br />
                <img src={Logo} alt="Logo" style={{
                    width:"378px",
                    height:"295px",
                }}/></p>
                
        </div>
    )
}

export default MainPage
