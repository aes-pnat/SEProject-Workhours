import React, {useState, useEffect} from 'react';
import ReactDOM from 'react-dom';
import {
    BrowserRouter as Router,
    Routes,
    Switch,
    Route,
    Link,
    useRouteMatch
  } from 'react-router-dom';
import JobsAdd from './JobsAdd';
//import jobs from './json_ph/jobs.json'
const Jobs = () => {
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
    // const getData=()=>{
    //     fetch('data.json'
    //     ,{
    //       headers : { 
    //         'Content-Type': 'application/json',
    //         'Accept': 'application/json'
    //        }
    //     }
    //     )
    //       .then(function(response){
    //         console.log(response)
    //         return response.json();
    //       })
    //       .then(function(myJson) {
    //         console.log(myJson);
    //         setData(myJson)
    //       });
    // }
    useEffect(()=>{
        getJobs()
      },[])
    
    return (
        <div>
            <br></br>
            <Link to={`${url}/add`} className="btn btn-primary">Dodaj djelatnost</Link>
            <Switch>
                <Route exact path={path}>
                <div>
                    {/* {data && (data.length > 1) ?
                        data.map((item) => <li>{item.name}</li>) : <li>Podatak: {data.name}</li>
                    } */}
                    {/* <div className="App">
                        {
                        data && data.length>0 && data.map((item)=><p>{item.name}</p>)
                        }
                    </div> */}
                    {data.map((job)=>(
                        <div className="container">
                            <div className="card">
                                <div className="card-body">
                                    <p className="h5">{job.name}</p>
                                    <p className="fst-italic">Cijena radnog sata: {job.hourprice} kn </p>
                                    <p className="fst-italic">Cijena djelatnosti: {job.price} kn </p>
                                    <p>{job.description}</p>
                                </div>
                            </div>
                        </div>

                    ))}
                </div>
                </Route>
                <Route path={`${path}/add`}>
                    <JobsAdd />
                </Route>
            </Switch>

        </div>
    ); 
};

export default Jobs;
