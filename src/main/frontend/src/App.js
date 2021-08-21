import LearnPage from './page/LearnPage';
import HomePage from './page/HomePage';
import RegisterPage from './page/RegisterPage';
import AppNavbar from './component/Navbar';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'

function App() {
  return (
    <>
      <AppNavbar />
      <Router>
        <Route path="/" exact component={HomePage}></Route>
        <Route path="/learn" component={LearnPage}></Route>
        <Route path="/register" component={RegisterPage}></Route>
      </Router>
    </>
  );
}

export default App;
