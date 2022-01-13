import React from 'react'
import * as VFX from 'react-vfx';

const MainPage = () => {
    return (
        <div>
            <div class="align-top"></div>
            <div class="align-middle">
                <p class="text-center h1">Radno vrijeme&copy; by Mi puno radimo </p>
            </div>
            <VFX.VFXProvider>
                {/* Render text as image, then apply the shader effect! */}
                <VFX.VFXSpan shader="rainbow">Hi there!</VFX.VFXSpan>

                {/* Render image with shader */}
                <VFX.VFXImg src="cat.png" alt="image" shader="rgbShift"/>

                {/* It also supports animated GIFs! */}
                <VFX.VFXImg src="doge.gif" shader="pixelate"/>

                {/* and videos! */}
                <VFX.VFXVideo src="mind_blown.mp4"
                    autoplay playsinline loop muted
                    shader="halftone"/>
            </VFX.VFXProvider>
        </div>
    )
}

export default MainPage
