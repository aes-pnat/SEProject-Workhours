import React from 'react'
import ReactDOM from 'react-dom';
import {useState, useEffect} from 'react';
import authHeader from '../services/auth-header';
import {
    BrowserRouter as Router,
    Routes,
    Switch,
    Route,
    Link,
    useRouteMatch
  } from 'react-router-dom';
import GroupsAdd from './GroupsAdd';

const Groups = (props) => {
    const [groups, setGroup] = useState([]);
    let { path, url } = useRouteMatch();
    

    var API_URI = "http://localhost:8080/groups";
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
    let keys = Object.keys(groups)
    // console.log('here')
    // console.log(keys)
    let keyslist =[]
    // keys.forEach( (key) =>(
    //     keyslist.push(
    //         <div className="container">
    //             <div className="card">
    //                 <div className="card-body">
    //                     <p className="h5">{key}</p>
    //                     <p>Voditelj: 
    //                         {groups[key].filter((mem)=> (
    //                             mem.idrole.id===2
    //                         )).map(s => <p className="fst-italic"> {s.name} {s.surname} </p>)}
    //                     </p>
    //                     <p>Članovi: 
    //                         <ul>
    //                             {groups[key].filter((mem)=> (
    //                                 mem.idrole.id===1
    //                             )).map(s => <li className="fst-italic"> {s.name} {s.surname} </li>)}
    //                         </ul>
    //                     </p>
    //                     <button>Obriši</button>
    //                 </div>
    //             </div>
    //         </div>
    //     )
    // ))
    
    return (
        props.role === "[ROLE_OWNER]" ? 
            <div className='container'>
                <br></br>
                <Link to={`${url}/add`} className="btn btn-primary">Dodaj grupu</Link>

                <Switch>
                    <Route exact path={path}>
                    <div>
                        {/* {keyslist.map((k)=>(k))} */}
                        {groups.map((gr)=>
                            <div>
                            <div className="card">
                                <div className="card-body">
                                    <p className="h5">{gr.name}</p>
                                    <span>Voditelj: {gr.leader.name} {gr.leader.surname}</span>
                                    <br />
                                    <span>Članovi: </span>
                                    <ul>
                                        {gr.members.map( (mem)=>
                                            <li>{mem.name} {mem.surname}</li>                    
                                        )}
                                    </ul>
                                    
                                    <button>Obriši</button>
                                </div>
                            </div>
                        </div>
                        )}
                    </div>
                    </Route>
                    <Route path={`${path}/add`}>
                        <GroupsAdd />
                    </Route>
                </Switch>
            </div>
        :
        <div className='container d-flex justify-content-center'>
            <h1 className='text-danger'>Nedovoljne permisije za prikaz grupa!</h1>
        </div>
    ); 
    
};
export default Groups;
