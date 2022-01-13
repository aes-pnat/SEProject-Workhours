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
            }else{
                alert("Error in fetching groups from server");
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
            throw err;
        });
        try{
            getJobs();
        }catch(err){
            console.log("error u groups");
        }
        
    }

    // let keys = Object.keys(groups)
    // console.log('here')
    // console.log(keys)
    // let keyslist =[]
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
        <div>
        {props.role === "[ROLE_OWNER]" ? 
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
        </div>}
        </div>
    ); 
    
};
export default Groups;
