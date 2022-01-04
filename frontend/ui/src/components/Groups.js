import React from 'react'
import ReactDOM from 'react-dom';
import {useState, useEffect} from 'react';
import {
    BrowserRouter as Router,
    Routes,
    Switch,
    Route,
    Link,
    useRouteMatch
  } from 'react-router-dom';
import '../Jobs.css'
import GroupsAdd from './GroupsAdd';

const Groups = () => {
    const [groups, setGroup] = useState([]);
    let { path, url } = useRouteMatch();

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
            <br></br>
            <Link to={`${url}/add`} className="btn btn-primary">Dodaj grupu</Link>

            <Switch>
                <Route exact path={path}>
                <div>
                    {groups.map((gr)=>(
                        <div className="container">
                            <div className="card">
                                <div className="card-body">
                                    <p className="h5">{gr.name}</p>
                                    <p className="fst-italic">Voditelj: {gr.idleader.name} {gr.idleader.surname} </p>
                                    <p className="fst-italic">E-mail: {gr.idleader.email}</p>
                                    <p className="fst-italic">Posao: {gr.idjob.name}</p>
                                    <p className="fst-italic">Opis: {gr.idjob.description}</p>
                                    {/* <p>{job.description}</p> */}
                                    <button>Obriši</button>
                                </div>
                            </div>
                        </div>

                    ))}
                </div>
                </Route>
                <Route path={`${path}/add`}>
                    <GroupsAdd />
                </Route>
            </Switch>

            {/* <li>Pozdrav iz groups stranice</li>
            {group && (group.length > 1) ?
                group.map((item) => <li>{item.pid}</li>) : <li>Ime grupe: {group.name}</li>
            } */}
        </div>
        
    ); 
    
};
export default Groups;
