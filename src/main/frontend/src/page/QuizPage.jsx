import React, { useState, useEffect } from 'react';
import MarginContainer from '../component/MarginContainer';
import AxiosClient from '../config/axios/AxiosClient';
import GlobalSpinner from '../component/GlobalSpinner';
import QuizContainer from '../component/QuizContainer';

function QuizPage({ match }) {

    const [questions, setQuestions] = useState([])
    const [currentQuestion, setCurrentQuestion] = useState(0)

    useEffect(() => {
        fetchQuestions();
    }, [])

    const fetchQuestions = async () => {
        const { data } = await AxiosClient.get(`/quizzes/${match.params.lessonId}/quiz/${match.params.quizId}/questions`)
        console.log(data);
        setQuestions(data)
    }

    return (
        <MarginContainer>
            {questions.length > 0
                ? <QuizContainer videoUrl={questions[currentQuestion].videoUrl} questionName={questions[currentQuestion].questionName} answers={questions[currentQuestion].answers} />
                : <GlobalSpinner />}
        </MarginContainer>
    )
}

export default QuizPage;