import React, {useState, useEffect} from 'react';
import {
    BrowserRouter as Router,
    Routes,
    Switch,
    Route,
    Link,
    useRouteMatch
  } from 'react-router-dom';
import authHeader from '../services/auth-header';
//import '../Jobs.css';
import JobsAdd from './JobsAdd';
import Backvid from './video/background.mp4';
const Jobs = (props) => {
    const [data, setData] = useState([]);
    let { path, url } = useRouteMatch();

    //const navigator = useNavigation();


    //var API_URI = "https://radno-vrijeme-app.herokuapp.com/jobs";
    var API_URI = process.env.REACT_APP_BACKEND_URL + '/jobs';
    
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
            id: parseInt(e),
        });
        console.log(body);
        //await fetch(process.env.REACT_APP_BACKEND_URL + '/jobs/delete?id='+e, {
        await fetch(process.env.REACT_APP_BACKEND_URL + '/jobs/delete', {

            method: 'POST',
            headers : myHeaders,
            body: body
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
            console.log("error u jobs");
        }
        
    }
    useEffect(()=>{
        getJobs()
    },[])
    
    return (
        <div className='container'>
            <br />
            {props.role === "[ROLE_OWNER]" &&
                <div>
                 <Link to={`${url}/add`} className="btn btn-primary">Dodaj djelatnost</Link>
                </div>
            }
            <Switch>
                <Route exact path={path}>
                <div>
                    {data.map((job)=>(
                        <div className="container">
                            <div className="card">
                                <div className="card-body">
                                    <p className="h5">{job.name}</p>
                                    <p className="fst-italic">Cijena radnog sata: {job.hourprice} kn </p>
                                    <p className="fst-italic">Cijena djelatnosti: {job.price} kn </p>
                                    <p>{job.description}</p>
                                    
                                    {props.role === "[ROLE_OWNER]" &&
                                        <button className="btn btn-danger mb-5"
                                                onClick={() => handleDelete(job.id)}>
                                            Obriši
                                    </button>
                                    }
                                    
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
                </Route>
                {props.role === "[ROLE_OWNER]" &&
                    <Route path={`${path}/add`}>
                        <JobsAdd />
                    </Route>
                }
                
            </Switch>

        </div>
    ); 
};

export default Jobs;
