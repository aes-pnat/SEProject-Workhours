import React from 'react'

const JobDisplay = (jobname, jobhp, jobtp, jobdesc) => {
    return (
        // <div>
        //     <h2>{jobname}</h2>
        //     <h3>Cijena radnog sata: {jobhp} kn</h3>
        //     <h3>Cijena djelatnosti: {jobtp} kn</h3>
        //     <hr></hr>
        //     <h3>{jobdesc}</h3>
        // </div>
        <div className="container">
            <div className="card">
                <div className="card-body">
                    <p className="h5">{jobname}</p>
                    <p className="fst-italic">Cijena radnog sata: {jobhp} kn </p>
                    <p className="fst-italic">Cijena djelatnosti: {jobtp} kn </p>
                    <p>{jobdesc}</p>
                </div>
            </div>
        </div>
    )
}

export default JobDisplay
