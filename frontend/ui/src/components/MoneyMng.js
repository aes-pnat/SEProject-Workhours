import React from 'react'
import { useState, useEffect } from 'react';

const MoneyMng =  ()  => {
    const [profits, setProfits] = useState([]);

    const fetchInfo = async () => {
        console.log("TU SAAAAAM")
        const res = await fetch('http://localhost:8080/moneymanagement') //.then((response) => setProfits(response.json))
        const data = await res.json()
        return data
        // //setProfits(data)
        // console.log(data)
        //return fetch("http://localhost:8080/moneymanagement").then((response) => response.json()).then((data) => console.log(data));
    }

    useEffect(() => {
        const getInfo = async () => {
          const tasksFromServer = await fetchInfo()
          setProfits(tasksFromServer)
        }
    
        getInfo()
    }, [])

    return (
        <div>
            <h1>Money management</h1>
            <p>{fetchInfo}</p> 

        </div>
    );
}

export default MoneyMng;
