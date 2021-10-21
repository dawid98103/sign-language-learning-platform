import React, { useState, useEffect } from 'react';
import MarginContainer from '../component/MarginContainer';
import LearnWrapper from '../component/LearnWrapper';
import AxiosClient from '../config/axios/AxiosClient';
import QuizResultContainer from '../component/QuizResultContainer';
import GlobalSpinner from '../component/GlobalSpinner';

function QuizResultPage({ match }) {
    const [quizResult, setQuizResult] = useState(null)

    useEffect(() => {
        fetchQuizResult();
    }, [])

    const fetchQuizResult = async () => {
        const { data } = await AxiosClient.get(`/quizzes/${match.params.lessonId}/quiz/${match.params.quizId}/result`)
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