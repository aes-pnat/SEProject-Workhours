import React from 'react';

class Occupancy extends React.Component {
    state = {
        employee: '',
        startDate: '',
        endDate: '',
        message: ''
    }

    handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        this.setState({ [name]: value });
    }

    render() {
        return (
            <div className="container mt-5">
                <div className="container">
                    {this.state.message}
                </div>
                <div className="row">
                    <form onSubmit={this.handleSubmit}>
                        <div className="mb-3">
                            <label className="form-label">Djelatnik:</label>
                            <select className="form-select"
                                    name="employee"
                                    onChange={this.handleChange}
                            >
                                <option value="1">Pero Perić</option>
                                <option value="2">Đuro Đurić</option>
                            </select>
                        </div>
                        <div className='mb-3'>
                            <label className="form-label">Početak perioda provjere:</label>
                            <input 
                                type="date" 
                                className="form-control"
                                name="startDate" 
                                onChange={this.handleChange} 
                            />
                        </div>
                        <div className='mb-3'>
                            <label className="form-label">Kraj perioda provjere:</label>
                            <input 
                                type="date" 
                                className="form-control"
                                name="endDate" 
                                onChange={this.handleChange} 
                            />
                        </div>
                        <button type="submit" className="btn btn-primary mb-5">Provjeri</button>
                    </form>
                </div>
            </div>
        );
    }
}

export default Occupancy;