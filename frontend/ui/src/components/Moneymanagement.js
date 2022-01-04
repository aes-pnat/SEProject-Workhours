import ReactDOM from 'react-dom';
import {useState, useEffect} from 'react';

function Moneymanagement(){
    const [profits, setProfits] = useState([]);
    var API_URI = "http://localhost:8080/moneymanagement";
    const myHeaders = new Headers();
	myHeaders.append("Content-Type","application/json");
    myHeaders.append("Accept","application/json");
    const getProfits = () => {
        fetch(API_URI,
        {
            headers : myHeaders
        })
        .then(response => {
            if(response.ok){
                //console.log(response.json())
                return response.json();
            }else{
                alert("Error in fetching tasks from server");
            }
        })
        .then(myProfits => {
            console.log(myProfits);
            setProfits(myProfits);
        }).catch(() => {
            console.log("error u dohvacanju api u tasks")
        });
    }
    try{
        useEffect( () => {
            getProfits();
        },[]);
    }catch(err){
        console.log("error u tasks");
    }
    
    return (
        <div>
            <h2>Money management</h2>
            {profits ? 
                <div>
                    <p>plannedProfitSum: {profits.plannedProfitSum}</p>
                    <p>realizedProfitSum: {profits.realizedProfitSum}</p>
                    <p>plannedCostSum: {profits.plannedCostSum}</p>
                    <p>realizedCostSum: {profits.realizedCostSum}</p>
                </div>
                :
                <p>Nothing to show...</p>
            }
            

            {/* {profits && (profits.length > 1) ?
                profits.map((item) => <li>{item.name}</li>) : <li>Poprofitsk: {profits.name}</li>
            } */}
        </div>
        
    ); 
}
export default Moneymanagement;