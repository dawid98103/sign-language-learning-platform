import React, { useState, useEffect } from 'react';
import MarginContainer from '../component/MarginContainer';
import LearnWrapper from '../component/LearnWrapper';
import AxiosClient from '../config/axios/AxiosClient';
import QuizResultContainer from '../component/QuizResultContainer';
import GlobalSpinner from '../component/GlobalSpinner';

function QuizResultPage({ match: { params: { quizId, lessonId } } }) {
    const [quizResult, setQuizResult] = useState(null)

    useEffect(() => {
        fetchQuizResult();
    }, [])

    const fetchQuizResult = async () => {
        const { data } = await AxiosClient.get(`/quizzes/${lessonId}/quiz/${quizId}/result`)
        setQuizResult(data)
    }

    return (
        <MarginContainer>
            <LearnWrapper>
                {quizResult == null ?
                    <GlobalSpinner />
                    :
                    <QuizResultContainer quizResult={quizResult} />
                }
            </LearnWrapper>
        </MarginContainer>
    )
}

export default QuizResultPage;