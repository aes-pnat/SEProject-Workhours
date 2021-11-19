import React from 'react'
import ReactDOM from 'react-dom';
import {useState, useEffect} from 'react';

const Groups = () => {
    const [group, setGroup] = useState([]);
    var API_URI = "http://localhost:8080/groups";
    const myHeaders = new Headers();
	myHeaders.append("Content-Type","application/json");
    myHeaders.append("Accept","application/json");
    const getJobs = () => {
        fetch(API_URI,
        {
            headers : myHeaders
        })
        .then(response => {
            if(response.ok){
                return response.json();
            }else{
                alert("Error in fetching tasks from server");
            }
        })
        .then(myGroup => {
            console.log(myGroup);
            setGroup(myGroup);
        }).catch(() => {
            console.log("error u dohvacanju api u tasks")
        });
    }
    try{
        useEffect( () => {
            getJobs();
        },[]);
    }catch(err){
        console.log("error u tasks");
    }
    
    return (
        <div>
            <h1>Pozdrav iz groups stranice</h1>
            {group && (group.length > 1) ?
                group.map((item) => <li>{item.pid}</li>) : <li>Grupa: {group.pid}</li>
            }
        </div>
        
    ); 
    
};
export default Groups;
