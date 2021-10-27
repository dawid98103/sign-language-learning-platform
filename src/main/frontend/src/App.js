import HomePage from './page/HomePage';
import RegisterPage from './page/RegisterPage';
import LoginPage from './page/LoginPage';
import ForumPage from './page/ForumPage';
import AppNavbar from './component/AppNavbar';
import LessonPage from './page/LessonPage';
import LearnPage from './page/LearnPage';
import QuizPage from './page/QuizPage';
import QuizResultPage from './page/QuizResultPage';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import history from './config/history';
import { Router, Switch, Route } from 'react-router-dom'

function App() {
  return (
    <>
      <Router history={history}>
        <ToastContainer icon={false} />
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
      <Switch>
        <Route path="/lesson" component={LessonPage} />
        <Route path="/forum" component={ForumPage} />
        <Route path="/learn/:lessonId/:stageId" exact component={LearnPage} />
        <Route path="/quiz/:lessonId/result/:quizId" exact component={QuizResultPage} />
        <Route path="/quiz/:lessonId/:quizId" exact component={QuizPage} />
      </Switch>
    </>
  )
}

export default App;
