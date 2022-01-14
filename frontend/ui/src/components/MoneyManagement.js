import React from 'react'
import Select from 'react-select';
import '../index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import authHeader from '../services/auth-header';
class MoneyManagement extends React.Component {
    state = {
        profits: '',
        mms: '',
        inpProf:null,
        inpExp:null,
        success:null
    }

    failedInp = (e) => {
        this.setState({success:false});
    }
    validInp = (e) => {
        this.setState({success:true});
    }

    handleChange = (e) => {
        this.validInp();
        const name = e.target.name;
        const value = e.target.value;
        this.setState({ [name]: value });
        this.setState({success:null});
        
    }
    componentDidMount = async (e) => {

        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);
        
        await fetch(process.env.REACT_APP_BACKEND_URL + '/moneymanagement', {
            method: 'GET',
            headers: myHeaders,
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({ mms: jsonResponse });
            console.log(this.state.mms);
        })
    }

    
    handleSubmit = async (e) => {
        e.preventDefault();
        
        const body = JSON.stringify({
            profit: parseInt(this.state.inpProf),
        });
        //console.log("almost");
        //console.log(body);
        console.log('/profit?price='+this.state.inpProf);

        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);
        
        await fetch(process.env.REACT_APP_BACKEND_URL + '/moneymanagement/calculate?revenue='+this.state.inpProf+'&cost='+this.state.inpExp, {
            method: 'GET',
            headers: myHeaders,
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({ profits: jsonResponse });
            this.setState({ success: true });
            console.log(this.state.profits);
        })
    }


    render () {
        let messageBox = '';
        if(this.state.success==false){
            messageBox = (
                <div className="alert alert-danger">
                    Neispravan unos podataka
                </div>
            );
        } else if (this.state.success===true){
            //console.log(this.state.expenses,'1',this.state.profits.price,'2',this.state.inpProf, 'ayoooooo');
            messageBox = (
                <div>
                
                    <div className="alert alert-info">
                        {this.state.profits.revenueDifference>0 ?
                        <p>Ostvareni prihod je veći od predviđenog za {this.state.profits.revenueDifference} HRK</p>
                        :
                        <p>Ostvareni prihod je manji od predviđenog za {this.state.profits.revenueDifference} HRK</p>
                        }
                        {this.state.profits.costDifference>0 ?
                        <p>Ostvareni trošak je veći od predviđenog za {Math.abs(this.state.profits.costDifference)} HRK</p>
                        :
                        <p>Ostvareni trošak je manji od predviđenog za {Math.abs(this.state.profits.costDifference)} HRK</p>
                        }
                        {this.state.profits.profitDifference ?
                        <p>Ostvareni profit je veći od predviđenog za {this.state.profits.profitDifference} HRK</p>
                        :
                        <p>Ostvareni profit je manji od predviđenog za  {this.state.profits.profitDifference} HRK</p>
                        }
                         
                    </div>
                    
                </div>

            );    
        }
        return (
            <div className="container mt-5 text-light">
                <div>
                    <div className="card text-dark">
                        <div className="card-body">
                            <p>Ostvarena dobit: {this.state.mms.realizedProfit}</p>
                            <p>Ostvaren trošak: {this.state.mms.realizedCost}</p>
                            <p>Ostvareni prihod: {this.state.mms.realizedRevenue}</p>
                        </div>
                    </div>
                    <br/>
                </div>
                {this.props.role === "[ROLE_OWNER]" ?
                    <div>
                        <div className="h3 mb-3">Računanje resursa</div>
                        
                        <div className="row">
                            <form onSubmit={this.handleSubmit}>
                                <div className="mb-3">
                                    <label className="form-label">Unesite predviđeni prihod (HRK):</label>
                                    <input
                                        type="number"
                                        className="form-control"
                                        name="inpProf"
                                        onChange={this.handleChange}
                                    />
                                </div>
                                <div className="mb-3">
                                    <label className="form-label">Unesite predviđeni trošak (HRK):</label>
                                    <input
                                        type="number"
                                        className="form-control"
                                        name="inpExp"
                                        onChange={this.handleChange}
                                    />
                                </div>
                                <button 
                                    type="submit"
                                    className="btn btn-light mb-5"
                                    //onClick={this.state.inpExp != null ? this.handleProfits : this.failedInp}
                                    onClick={this.handleSubmit}
                                >
                                    Izračunaj planiranu zaradu
                                </button>
                            </form>
                        </div>
                        <div className="container">
                            {messageBox}
                        </div>
                    </div>
                :
                <div className='d-flex justify-content-center alert alert-danger'>
                    <h2>Nedovoljne ovlasti za računanje resursa!</h2>
                </div>
            }
                
            </div>
        );
    }
}
export default MoneyManagement;
