import React from 'react'
import { useState } from "react"
import authHeader from '../services/auth-header';

class JobsAdd extends React.Component {

    
    state = {
        
        // id: '',
        name: '',
        price: '',
        hourprice: '',
        description: '',

    }

    // componentDidMount = async () => {
    //     const myHeaders = new Headers();
	// 	myHeaders.append("Content-Type","application/json");
    //     myHeaders.append("Accept","application/json");

    //     await fetch(process.env.REACT_APP_BACKEND_URL + '/jobs', {
    //         method: 'GET',
    //         headers: myHeaders
    //     }).then((response) => {
    //         if(response.ok) {
    //             return response.json();
    //         }
    //     }).then((jsonResponse) => {
    //         this.setState({ 
    //             jobs: jsonResponse
    //         });
    //     })
    // }

    handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        this.setState({ [name]: value });
        // const rand =  Math.random();
        // this.setState({ id: rand});
    }

    handleSubmit = async (e) => {
        e.preventDefault();
        const body = JSON.stringify({
            // id: this.state.id,
            name: this.state.name,            
            price: Number(this.state.price),
            hourprice: Number(this.state.hourprice),
            description: this.state.description,
        });

        console.log(body);

        const myHeaders = new Headers();
		myHeaders.append("Content-Type","application/json");
        myHeaders.append("Accept","application/json");
        const token = authHeader();
        myHeaders.append("Authorization", token);
        
        console.log(body);

        await fetch(process.env.REACT_APP_BACKEND_URL + '/jobs/add', {
            method: 'POST',
            headers: myHeaders,
            body: body
        }).then((response) => {
            if(response.ok){
                // this.setState({ success: true });
            }
        }).catch((err) => {
            console.log("Error prilikom POST-a serveru:")
            console.log(err);
        });
    }

    render () {
        

        return (
            <div className="container mt-5">
                <div className="h3 mb-3">Dodavanje nove djelatnosti</div>
                <div className="row">
                    <form onSubmit={this.handleSubmit}>
                        <div className="mb-3">
                            <label className="form-label">Naziv djelatnosti:</label>
                            <input
                                className="form-control"
                                name="name"
                                onChange={this.handleChange}
                            />
                        </div>
                        
                        <div className="mb-3">
                            <label className="form-label">Cijena djelatnosti:</label>
                            <input
                                type="number"
                                className="form-control"
                                name="price"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Cijena radnog sata:</label>
                            <input
                                type="number"
                                className="form-control"
                                name="hourprice"
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Opis djelatnosti:</label>
                            <input
                                className="form-control"
                                name="description"
                                onChange={this.handleChange}
                            />
                        </div>
                        <button 
                            type="submit"
                            className="btn btn-primary mb-5"
                            onClick={this.handleSubmit}>
                            Dodaj
                        </button>
                    </form>
                </div>
            </div>
        );
    }
}





// const JobsAdd = () => {
//     return (
//         <div>
//             <h4>Dodavanje nove djelatnosti</h4>
//             <form 
//             // onSubmit={onSubmit}
//             >
//                 <div className='form-control'>
//                     <label>
//                         Naziv djelatnosti
//                     </label>
//                     <input type="text" 
//                     placeholder='Naziv' 
//                     //value={text} 
//                     // onChange={(e) => setText(e.target.value)}
//                     />
//                 </div>
//                 <div className='form-control'>
//                     <label>
//                         Cijena djelatnosti
//                     </label>
//                     <input type="text" 
//                     placeholder='Cijena' 
//                     // value={text} 
//                     // onChange={(e) => setText(e.target.value)}
//                     />
//                 </div>
//                 <div className='form-control form-control-check'>
//                     <label>
//                         Cijena radnog sata
//                     </label>
//                     <input type="text" 
//                     placeholder='Cijena' 
//                     // value={text} 
//                     // onChange={(e) => setText(e.target.value)}
//                     />
//                 </div>
//                 <div className='form-control form-control-check'>
//                     <label>
//                         Opis djelatnosti
//                     </label>
//                     <input type="text" 
//                     placeholder='Opis' 
//                     // value={text} 
//                     // onChange={(e) => setText(e.target.value)}
//                     />
//                 </div>
//                 <input type="submit" value='Dodaj' className='btn btn-block'/>

//             </form>
//         </div>
//     )
// }

export default JobsAdd
