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
import '../Jobs.css';
import JobsAdd from './JobsAdd';
import JobDisplay from './compartments/JobDisplay';
const Jobs = ({jobslist}) => {
    const [data, setData] = useState([]);
    let { path, url } = useRouteMatch();

    //const navigator = useNavigation();


    //var API_URI = "https://radno-vrijeme-app.herokuapp.com/jobs";
    var API_URI = 'https://radno-vrijeme-app.herokuapp.com/jobs'
    
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
            <Link to={`${url}/add`} className="btn btn-primary">Dodaj djelatnost</Link>

            <Switch>
                <Route exact path={path}>
                <div>
                    <li>Pozdrav iz job stranice</li>
                    {data && (data.length > 1) ?
                        data.map((item) => <li>{item.name}</li>) : <li>Podatak: {data.name}</li>
                    }

                    {jobslist.map((data)=>(
                        <JobDisplay 
                            key={data.index}
                            job={data}
                        ></JobDisplay>
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
