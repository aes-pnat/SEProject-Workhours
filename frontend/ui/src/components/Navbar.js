import '../Navbar.css';
import {
  BrowserRouter as Router,
  Routes,
  Switch,
  Route,
  Link
} from 'react-router-dom';
import Groups from './Groups';
import Jobs from './Jobs';
import Workhours from './Workhours';
import Tasks from './Tasks';
import Login from './Login';
import Moneymanagement from './Moneymanagement';
import Register from './Register';

function Navbar() {
  return (
    <Router>
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <div className="container-fluid justify-content-around">
        <a className="navbar-brand" href="#">Radno vrijeme</a>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse justify-content-around" id="navbarNavAltMarkup">
            <div className="navbar-nav justify-content-around">
              <div className="nav-link">
                <Link to='/groups'>Groups</Link>
              </div>
              <div className="nav-link">
                <Link to='/'>Jobs</Link>
              </div>
              <div className="nav-link">
                <Link to='/moneymanagement'>Moneymanagement</Link>
              </div>
              <div className="nav-link">
                <Link to='/tasks'>Tasks</Link>
              </div>
              <div className="nav-link">
                <Link to='/workhours'>Workhours</Link>
              </div>
              <div className="nav-link">
                <Link to='/login'>Log In</Link>
              </div>
              <div className="nav-link">
                <Link to='/register'>Register</Link>
              </div>
            </div>
          </div>
        </div>
      </nav>
      <Switch>
            <Route path="/login">
              <Login />
            </Route>
            <Route path="/groups">
              <Groups />
            </Route>
            <Route exact path="/">
              <Jobs />
            </Route>
            <Route path="/moneymanagement">
              <Moneymanagement />
            </Route>
            <Route path="/tasks">
              <Tasks />
            </Route>
            <Route path="/workhours">
              <Workhours />
            </Route>
            <Route path="/register">
              <Register />
            </Route>
          </Switch>
    </Router>
    
  );
}

export default Navbar;