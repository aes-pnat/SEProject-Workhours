import React from 'react'
import {useState, useEffect} from 'react';
import authHeader from '../services/auth-header';
import {
    Switch,
    Route,
    Link,
    useRouteMatch
  } from 'react-router-dom';
//import '../Jobs.css'
import GroupsAdd from './GroupsAdd';

const Groups = (props) => {
    const [groups, setGroup] = useState([]);
    let { path, url } = useRouteMatch();
    

    var API_URI = process.env.REACT_APP_BACKEND_URL + '/groups';
    const myHeaders = new Headers();
	myHeaders.append("Content-Type","application/json");
    myHeaders.append("Accept","application/json");
    const token = authHeader();
    myHeaders.append("Authorization", token);


    const getJobs = () => {
        fetch(API_URI,
        {
            headers : myHeaders
        })
        .then(response => {
            if(response.ok){
                return response.json();
            }
        })
        .then(myGroup => {
            console.log(myGroup);
            setGroup(myGroup);
        }).catch(() => {
            console.log("error u dohvacanju api u groups")
        });
    }
    try{
        useEffect( () => {
            getJobs();
        },[]);
    }catch(err){
        console.log("error u groups");
    }

    const handleDelete = async (e) => {
        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);
        // console.log("here");
        // console.log(e);
        // console.log("there");

        const body = JSON.stringify({
            groupId: parseInt(e),
        });
        console.log(body);
        await fetch(process.env.REACT_APP_BACKEND_URL + '/groups/delete?groupId='+e, {

            method: 'POST',
            headers : myHeaders
        }).then((response) => {
            if(response.ok){
                //this.setState({ success: true });
            }
        }).catch((err) => {
            console.log(err);
        });
        try{
            getJobs();
        }catch(err){
            console.log("error u groups");
        }
        
    }
    
    try{
        getJobs();
    }catch(err){
        console.log("error u groups");
    }
    return (
        <div>
        {props.role === "[ROLE_OWNER]" ? 
            <div className='container'>
                <br></br>
                <div>
                    <Link to={`${url}/add`} className="btn btn-light">Dodaj grupu</Link>
                    <br/>
                    <br/>
                </div>


            <Switch>
                <Route exact path={path}>
                <div>
                    {/* {keyslist.map((k)=>(k))} */}
                    {groups.map((gr)=>
                        <div>
                            <div className="card">
                                <div className="card-body">
                                    <p className="h5">{gr.name}</p>
                                    <p>Djelatnost: {gr.job.name}</p>
                                    <p>Voditelj: {gr.leader.name} {gr.leader.surname}</p>
                                    <span>Članovi: 
                                        <ul>
                                            {gr.members.map( (mem)=>
                                                <li>{mem.name} {mem.surname}</li>                    
                                            )}
                                        </ul>
                                    </span>
                                    <button
                                        className="btn btn-danger mb-5"
                                        onClick={() => handleDelete(gr.id)}
                                    >Obriši</button>
                                </div>
                            </div>
                            <br/>
                        </div>
                    
                    )}
                </div>
                </Route>
                <Route path={`${path}/add`}>
                    <GroupsAdd role={props.role}/>
                </Route>
            </Switch>
            </div>
        :
        <div className='container d-flex justify-content-center'>
            <h1 className='alert alert-danger mt-3'>Nedovoljne permisije za prikaz grupa!</h1>
        </div>}
        </div>
    ); 
    
};
export default Groups;
