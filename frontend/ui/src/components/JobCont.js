import React from 'react'

const JobCont = () => {
    const fetchTasks = async () => {
        const res = await fetch('http://localhost:8080/jobs')
        const data = await res.json()
    
        return data
    }

    return (
        <div>
            <h2>Job controller</h2>

        </div>
    )
}

export default JobCont
