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
      <div className="navbar navbar-horizontal center bg">
        <nav>
          <Link to='/groups'>Groups</Link>
          <Link to='/'>Jobs</Link>
          <Link to='/moneymanagement'>Moneymanagement</Link>
          <Link to='/tasks'>Tasks</Link>
          <Link to='/workhours'>Workhours</Link>
          <Link to='/register'>Register</Link>
        </nav>
      </div>
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