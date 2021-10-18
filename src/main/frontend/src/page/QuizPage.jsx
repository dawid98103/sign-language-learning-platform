import React, { useState, useEffect } from 'react';
import MarginContainer from '../component/MarginContainer';
import AxiosClient from '../config/axios/AxiosClient';
import GlobalSpinner from '../component/GlobalSpinner';
import QuizContainer from '../component/QuizContainer';
import history from '../config/history';

function QuizPage({ match }) {
    const [quizResult, setQuizResult] = useState(null)
    const [questions, setQuestions] = useState([])
    const [userAnswers, setUserAnswers] = useState([])
    const [currentQuestion, setCurrentQuestion] = useState(0)

    useEffect(() => {
        fetchQuestions();
    }, [])

    const fetchQuestions = async () => {
        const { data } = await AxiosClient.get(`/quizzes/${match.params.lessonId}/quiz/${match.params.quizId}/questions`)
        setQuestions(data)
    }

    const nextQuestion = async (questionAnswerId) => {
        await checkAnswer(questionAnswerId);
        if (questions.length === currentQuestion + 1) {
            await finishQuiz();
            history.push(`/quiz/${match.params.lessonId}/result/${match.params.quizId}`)
        } else {
            setCurrentQuestion(currentQuestion + 1);
        }
    }

    const checkAnswer = async (questionAnswerId) => {
        const { data } = await AxiosClient.get(`/quizzes/process/quiz/${match.params.quizId}/question/${questions[currentQuestion].quizQuestionId}/answers/${questionAnswerId}`)
        setUserAnswers([...userAnswers, { quizAnswerId: questions[currentQuestion].quizQuestionId, isGoodAnswer: data }])
    }

    const finishQuiz = async () => {
        const { data } = await AxiosClient.post(`/quizzes/process/quiz/${match.params.quizId}/finish`)
        setQuizResult(data)
    }

    return (
        <MarginContainer>
            {questions.length > 0
                ? <QuizContainer
                    currentQuestion={currentQuestion}
                    questionsCount={questions.length}
                    videoUrl={questions[currentQuestion].videoUrl}
                    questionName={questions[currentQuestion].questionName}
                    answers={questions[currentQuestion].answers}
                    handleNextQuestion={nextQuestion} />
                : <GlobalSpinner />}
        </MarginContainer>
    )
}

export default QuizPage;