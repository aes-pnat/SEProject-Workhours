import React, {useState, useEffect} from 'react';
import ReactDOM from 'react-dom';
function Tasks(){
    const [data, setData] = useState([]);
    var API_URI = "http://localhost:8080/tasks";
    const myHeaders = new Headers();
	myHeaders.append("Content-Type","application/json");
    myHeaders.append("Accept","application/json");
    const getTasks = () => {
        fetch(API_URI,
        {
            headers : myHeaders
        })
        .then(response => {
            if(response.ok)
                return response.json();
            else
                alert("Error in fetching tasks from server");
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
        <div>
            <h1> Tasks</h1>
            {data && data.map((item) => <li>item</li>) }
        </div>
        
    ); 
}

export default Tasks;
