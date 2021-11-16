import React, {useState, useEffect} from 'react';
import ReactDOM from 'react-dom';
import '../Jobs.css';
const Jobs = () => {
    const [data, setData] = useState([]);
    var API_URI = "http://localhost:8080/jobs";
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
        .then(myData => {
            console.log(myData);
            setData(myData);
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
            <li>Pozdrav iz job stranice</li>
            {data && (data.length > 1) ?
                data.map((item) => <li>{item.name}</li>) : <li>Podatak: {data.name}</li>
            }
        </div>
        
    ); 
};
export default Jobs;
