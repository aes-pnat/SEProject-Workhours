import React from 'react'
import { useState } from "react"

const JobsAdd = () => {
    return (
        <div>
            <h4>Dodavanje nove djelatnosti</h4>
            <form 
            // onSubmit={onSubmit}
            >
                <div className='form-control'>
                    <label>
                        Naziv djelatnosti
                    </label>
                    <input type="text" 
                    placeholder='Naziv' 
                    //value={text} 
                    // onChange={(e) => setText(e.target.value)}
                    />
                </div>
                <div className='form-control'>
                    <label>
                        Cijena djelatnosti
                    </label>
                    <input type="text" 
                    placeholder='Cijena' 
                    // value={text} 
                    // onChange={(e) => setText(e.target.value)}
                    />
                </div>
                <div className='form-control form-control-check'>
                    <label>
                        Cijena radnog sata
                    </label>
                    <input type="text" 
                    placeholder='Cijena' 
                    // value={text} 
                    // onChange={(e) => setText(e.target.value)}
                    />
                </div>
                <div className='form-control form-control-check'>
                    <label>
                        Opis djelatnosti
                    </label>
                    <input type="text" 
                    placeholder='Opis' 
                    // value={text} 
                    // onChange={(e) => setText(e.target.value)}
                    />
                </div>
                <input type="submit" value='Dodaj' className='btn btn-block'/>

            </form>
        </div>
    )
}

export default JobsAdd
