import HomePage from './page/HomePage';
import RegisterPage from './page/RegisterPage';
import LoginPage from './page/LoginPage';
import ForumPage from './page/ForumPage';
import AppNavbar from './component/AppNavbar';
import LessonPage from './page/LessonPage';
import LearnPage from './page/LearnPage';
import QuizPage from './page/QuizPage';
import PostPage from './page/PostPage';
import QuizResultPage from './page/QuizResultPage';
import ResultPage from './page/ResultPage';
import AchievementPage from './page/AchievementPage';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import history from './config/history';
import { Router, Switch, Route } from 'react-router-dom'
import ProfilePage from './page/ProfilePage';

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
        <Route path="/forum" exact component={ForumPage} />
        <Route path="/forum/:postId" exact component={PostPage} />
        <Route path="/learn/:lessonId/:stageId" exact component={LearnPage} />
        <Route path="/quiz/:lessonId/result/:quizId" exact component={QuizResultPage} />
        <Route path="/quiz/:lessonId/:quizId" exact component={QuizPage} />
        <Route path="/profile/:username" exact component={ProfilePage} />
        <Route path="/result" exact component={ResultPage} />
        <Route path="/achievement" exact component={AchievementPage} />
      </Switch>
    </>
  )
}

export default App;
