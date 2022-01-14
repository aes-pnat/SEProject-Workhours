import React from 'react'
import Select from 'react-select';
import '../index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import authHeader from '../services/auth-header';
class MoneyManagement extends React.Component {
    state = {
        profits: '',
        expenses: '',
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
        console.log(name , value);
        
    }
    handleProfits = async (e) => {
        e.preventDefault();
        
        const body = JSON.stringify({
            profit: parseInt(this.state.inpProf),
        });
        console.log("almost");
        console.log(body);
        console.log('/profit?price='+this.state.inpProf);

        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);
        
        await fetch(process.env.REACT_APP_BACKEND_URL + '/profit?price='+this.state.inpProf, {
            method: 'GET',
            headers: myHeaders,
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({ profits: jsonResponse });
            console.log(this.state.profits);
        })
    }

    handleExpenses = async (e) => {
        e.preventDefault();
        
        const body = JSON.stringify({
            expense: parseInt(this.state.inpExp),
        });
        console.log("almost");
        console.log(body);
        console.log("did it");

        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);
        
        await fetch(process.env.REACT_APP_BACKEND_URL + '/expense?difference='+this.state.inpExp, {
            method: 'GET',
            headers: myHeaders,
        }).then((response) => {
            if(response.ok) {
                return response.json();
            }
        }).then((jsonResponse) => {
            this.setState({ expenses: jsonResponse });
            console.log(this.state.expenses);
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
        }
        return (
            <div className="container mt-5 text-light">
                {this.props.role === "[ROLE_OWNER]" ?
                    <div>
                        <div className="h3 mb-3">Računanje resursa</div>
                        <div className="container">
                            {messageBox}
                        </div>
                        <div className="row">
                            <form onSubmit={this.handleProfits}>
                                <div className="mb-3">
                                    <label className="form-label">Unesite predviđeni prihod (HRK):</label>
                                    <input
                                        type="number"
                                        className="form-control"
                                        name="inpProf"
                                        onChange={this.handleChange}
                                    />
                                </div>
                                <button 
                                    type="submit"
                                    className="btn btn-light mb-5"
                                    onClick={this.state.inpExp != null ? this.handleProfits : this.failedInp}
                                >
                                    Izračunaj planiranu zaradu
                                </button>
                            </form>
                        </div>
                        <div className="row">
                            <form onSubmit={this.handleExpenses}>
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
                                    onClick={this.state.inpExp != null ? this.handleExpenses : this.failedInp}
                                >
                                    Izračunaj ukupni trošak
                                </button>
                            </form>
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
