import React, {useState, useEffect} from 'react';
import ReactDOM from 'react-dom';
import Navbar from './Navbar';
function Tasks(){
    const [data, setData] = useState([]);
    var API_URI = "http://localhost:8080/moneymanagement";
    const getTasks = () => {
        fetch(API_URI,
        {
            headers : { 
            'Content-Type': 'application/json',
            'Accept': 'application/json'
            }
        })
        .then(response => {
            return response.json();
        })
        .then(myData => {
            console.log(myData);
            setData(myData);
        }).catch(() => {
            console.log("error u dohvacanju api u tasks")
        })
    }
    try{
        useEffect(()=>{
            getTasks();
        },[]);
    }catch(err){
        console.log("error u tasks");
    }
    
    return (
        <label>{data.plannedDifference}</label>
    ); 
}

export default Tasks;
