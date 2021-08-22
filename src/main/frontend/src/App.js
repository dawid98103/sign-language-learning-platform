import LearnPage from './page/LearnPage';
import HomePage from './page/HomePage';
import RegisterPage from './page/RegisterPage';
import LoginPage from './page/LoginPage';
import AppNavbar from './component/AppNavbar';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'

function App() {
  return (
    <>
      <Router basename="/signlearning">
        <Switch>
          <Route path="/" exact component={RoutesWithoutNav} />
          <Route path="/register" exact component={RoutesWithoutNav} />
          <Route path="/login" exact component={RoutesWithoutNav} />
          <Route component={RoutesWithNav} />
        </Switch>
      </Router>
    </>
  );
}

const RoutesWithoutNav = () => {
  return (
    <>
      <Route path="/" exact component={HomePage}></Route>
      <Route path="/register" exact component={RegisterPage}></Route>
      <Route path="/login" exact component={LoginPage}></Route>
    </>
  )
}

const RoutesWithNav = () => {
  return (
    <>
      <AppNavbar />
      <Route path="/learn" component={LearnPage}></Route>
    </>
  )
}

export default App;
