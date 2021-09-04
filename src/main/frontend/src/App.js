import HomePage from './page/HomePage';
import RegisterPage from './page/RegisterPage';
import LoginPage from './page/LoginPage';
import AppNavbar from './component/AppNavbar';
import LessonPage from './page/LessonPage';
import LearnPage from './page/LearnPage';
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
      <Route path="/" exact component={HomePage} />
      <Route path="/register" exact component={RegisterPage} />
      <Route path="/login" exact component={LoginPage} />
    </>
  )
}

const RoutesWithNav = () => {
  return (
    <>
      <AppNavbar />
      <Route path="/lesson" component={LessonPage} />
      <Route path="/learn/:lessonId/:stageId" exact component={LearnPage} />
    </>
  )
}

export default App;
