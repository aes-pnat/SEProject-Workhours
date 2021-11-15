import React from 'react'

const MoneyMng = async () => {
    const fetchInfo = async () => {
        const res = await fetch('http://localhost:8080/moneymanagement')
        const data = await res.json()
    
        return data
    }

    return (
        <div>
            <h1>Money management</h1>
        </div>
    )
}

export default MoneyMng
