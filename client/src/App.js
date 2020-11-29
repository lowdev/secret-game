import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Button, Form } from 'react-bootstrap';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

import CreateRoom from './page/CreateRoom';
import JoinRoom from './page/JoinRoom';
import Room from './page/Room';

function App() {
  return (
    <div className="App">
      <Router>
        <header className="App-header">
          <Switch>
            <Route exact path="/">
              <Home />
            </Route>
            <Route path="/create">
              <CreateRoom />
            </Route>
            <Route path="/join">
              <JoinRoom />
            </Route>
            <Route path="/room/:id" component={Room} />
          </Switch>
        </header>
          
      </Router>
    </div>
  );
}

function Home() {
  return (
    <div>
      <img src={logo} className="App-logo" alt="logo" />

      <div>
        <Link to="/create">
          <Button variant="secondary" size="lg">
            Create a Room
          </Button>{' '}
        </Link>
        <Link to="/join" >
          <Button variant="primary" size="lg">
            Join a Room
          </Button>
        </Link>
      </div>
    </div>
  );
}

export default App;
