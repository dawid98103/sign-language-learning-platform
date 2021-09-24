import React, { useState, useEffect } from 'react';
import MarginContainer from '../component/MarginContainer';
import AxiosClient from '../config/axios/AxiosClient';
import GlobalSpinner from '../component/GlobalSpinner';
import QuizContainer from '../component/QuizContainer';

function QuizPage({ match }) {
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

    const checkAnswer = async (questionAnswerId) => {
        const { data } = await AxiosClient.get(`/quizzes/${match.params.lessonId}/quiz/${match.params.quizId}/question/${questions[currentQuestion].quizQuestionId}/answers/${questionAnswerId}`)
        setUserAnswers(userAnswers.push({ quizAnswerId: questions[currentQuestion].quizQuestionId, answer: data }))
        console.log(userAnswers);
    }

    const nextQuestion = (questionAnswerId) => {
        console.log(questionAnswerId);
        checkAnswer(questionAnswerId);


        setCurrentQuestion(currentQuestion + 1)
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