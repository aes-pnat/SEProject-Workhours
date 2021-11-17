
import './App.css';
import {BrowserRouter as Router,Routes, Route} from 'react-router-dom';
import JobCont from './components/JobCont';
import GroupCont from './components/GroupCont';
import MoneyMng from './components/MoneyMng';


function App() {
  
  
  return (
    <Router>
          <h1>Main app</h1>

      <Route path="/groupcont">
        <GroupCont/>
      </Route>
      <Route path="/jobcont">
        <JobCont/>
      </Route>
      <Route path="/moneymng">
        <MoneyMng/>
      </Route>
    </Router>
  );
}

export default App;
