import './Navbar.css';
import {
  BrowserRouter as Router,
  Routes,
  Switch,
  Route,
  Link
} from 'react-router-dom';
import Login from './components/Login';
import Home from './components/Home';
import { Component } from 'react';
import AuthService from './services/auth.service'

class App extends Component {
  constructor(props){
    super(props);
    
    this.state = {
      currentUser: undefined
    };
  }

  componentDidMount(){
    const user = AuthService.getCurrentUser();
  }


  render (){
    return (
      <Router>
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <div className="container-fluid justify-content-around">
          <a class="navbar-brand" href="#">Radno vrijeme</a>
            <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse justify-content-around" id="navbarNavAltMarkup">
              <div className="navbar-nav justify-content-around">
                <div className="nav-link">
                  <Link to='/login'>Log In</Link>
                </div>
                <div className="nav-link">
                  <Link to='/'>Home</Link>
                </div>
                {/* <Link to='/register'>Register</Link> */}
              </div>
            </div>
          </div>
        </nav>
        <Switch>
              <Route path="/login">
                <Login />
              </Route>
              <Route exact path="/">
                <Home />
              </Route>
            </Switch>
      </Router>
    );
  };
}

export default App;