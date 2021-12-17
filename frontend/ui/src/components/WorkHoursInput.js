import React from 'react';

class WorkHoursInput extends React.Component {
    state = {
        task: '',
        date: '',
        hoursDone: '',
        message: ''
    }

    handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        this.setState({ [name]: value });
    }

    render () {
        return (
            <div className="container mt-5">
                <div className="container">
                    {this.state.message}
                </div>
                <div className="row">
                    <form onSubmit={this.handleSubmit}>
                        <div className="mb-3">
                            <label className="form-label">Zadatak:</label>
                            <select className="form-select"
                                    name="task"
                                    onChange={this.handleChange}
                            >
                                <option value="1">Zadatak 1</option>
                                <option value="2">Zadatak 2</option>
                            </select>
                        </div>
                        <div className='mb-3'>
                            <label className="form-label">Datum:</label>
                            <input 
                                type="date" 
                                className="form-control"
                                name="date" 
                                onChange={this.handleChange} 
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Broj sati:</label>
                            <input
                                type="number"
                                className="form-control"
                                name="hoursDone"
                                onChange={this.handleChange}
                                min="1"
                                max="24"
                            />
                        </div>
                        <button type="submit" className="btn btn-primary mb-5">Pošalji</button>
                    </form>
                </div>
            </div>
        );
    }
}

export default WorkHoursInput;