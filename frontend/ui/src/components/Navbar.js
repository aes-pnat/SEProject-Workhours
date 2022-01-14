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
import Tasks from './Tasks';
import Login from './Login';
//import Moneymanagement from './Moneymanagement';
import Register from './Register';
import Occupancy from './Occupancy';
import WorkHoursInput from './WorkHoursInput';
import MyData from './MyData';
import MainPage from './MainPage';
import JobsAdd from './JobsAdd';
import Map from './Map';
import AddTask from './AddTask';
import User from '../services/User';
import MoneyManagement from './MoneyManagement';

function Navbar(props) {
  const logout = () => {
    User.removeToken();
    User.removeRole();
    props.setToken('');
    props.setRole('');
  }
  return (
    <Router>
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <div className="container-fluid justify-content-around">
        <a className="navbar-brand" href="/">Radno vrijeme</a>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse justify-content-around" id="navbarNavAltMarkup">
            <div className="navbar-nav justify-content-around">
              
              {/* <div className="nav-link">
                <Link to='/'>Main page</Link>
              </div> */}
            {props.role === "[ROLE_LEADER]" &&
              <div className="nav-link">
                <Link to='/tasks'>Zadaci</Link>
              </div>
            }
            {props.role === "[ROLE_OWNER]" &&
              <div className="nav-link">
                <Link to='/occupancy'>Zauzetost</Link>
              </div>
            }
            {props.role === "[ROLE_OWNER]" &&
              <div className="nav-link">
                <Link to='/moneymanagement'>Upravljanje resursima</Link>
              </div>
            }
              <div className="nav-link">
                <Link to='/jobs'>Djelatnosti</Link>
              </div>
            {props.token !== "" &&
              <div className="nav-link">
                <Link to='/mydata'>Moji podaci</Link>
              </div>
            }
            {props.role === "[ROLE_OWNER]" &&
              <div className="nav-link">
                <Link to='/map'>Karta</Link>
              </div>
            }
              {/* <div className="nav-link">
                <Link to='/moneymanagement'>Moneymanagement</Link>
              </div> */}
              {props.role !== "[ROLE_OWNER]" && props.token !== "" &&
              <div className="nav-link">
                <Link to='/workhoursinput'>Unos radnih sati</Link>
              </div>
            }
            {props.role === "[ROLE_OWNER]" &&
              <div className="nav-link">
                <Link to='/groups'>Grupe</Link>
              </div>
            }
            {props.role === "[ROLE_OWNER]" &&
              <div className="nav-link">
                <Link to='/register'>Registracija</Link>
              </div>
            }
            {props.token !== "" ?
              <div className="nav-link">
                <Link to='/login' onClick={logout}>Odjava</Link>
              </div>
              :
              <div className="nav-link">
                <Link to='/login'>Prijava</Link>
              </div>
            }
            </div>
          </div>
        </div>
      </nav>
      <Switch>
            <Route path="/login">
              <Login setToken={props.setToken} setRole={props.setRole}/>
            </Route>
            <Route path="/groups">
              <Groups role={props.role}/>
            </Route>
            <Route path="/" exact>
              <MainPage />
            </Route>
            <Route path="/moneymanagement" exact>
              <MoneyManagement role={props.role}/>
            </Route>
            <Route path="/jobs">
              <Jobs role={props.role}/>
            </Route>
            <Route path="/jobs/add">
              <JobsAdd role={props.role} />
            </Route>
            {/* <Route path="/moneymanagement">
              <Moneymanagement />
            </Route> */}
            <Route path="/tasks" exact component={Tasks}>
              <Tasks role={props.role}/>
            </Route>
            <Route path="/tasks/add" exact component={AddTask}>
              <AddTask role={props.role}/>
            </Route>
            <Route path="/register">
              <Register role={props.role}/>
            </Route>
            <Route path="/occupancy" >
              <Occupancy role={props.role}/>
            </Route>
            <Route path="/workhoursinput">
              <WorkHoursInput role={props.role}/>
            </Route>
            <Route path="/mydata">
              <MyData role={props.role}/>
            </Route>
            <Route path="/map">
              <Map role={props.role}/>
            </Route>
          </Switch>
    </Router>
    
  );
}

export default Navbar;