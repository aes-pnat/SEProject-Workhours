import React from 'react'

const GroupCont = () => {
    const fetchTasks = async () => {
        const res = await fetch('http://localhost:8080/groups')
        const data = await res.json()
    
        return data
    }

    return (
        <div>
            <h1>Group controller</h1>

            
        </div>
    )
}

export default GroupCont
