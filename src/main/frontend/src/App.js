import LearnPage from './page/LearnPage';
import HomePage from './page/HomePage';
import RegisterPage from './page/RegisterPage';
import AppNavbar from './component/Navbar';
import { BrowserRouter as Router, Route } from 'react-router-dom'

function App() {
  return (
    <>
      <Router>
        <Switch>
          <Route path="/" exact component={NoNavRoutes} />
          <Route path="/register" component={NoNavRoutes} />
          <Route component={NavRoutes} />
        </Switch>
      </Router>
    </>
  );
}

const NoNavRoutes = () => {
  return (
    <>
      <Route path="/" exact component={HomePage}></Route>
      <Route path="/register" component={RegisterPage}></Route>
    </>
  )
}

const NavRoutes = () => {
  return (
    <>
      <AppNavbar />
      <Route path="/learn" component={LearnPage}></Route>
    </>
  )
}

export default App;
