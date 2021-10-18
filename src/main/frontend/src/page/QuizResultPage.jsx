import React, { useState, useEffect } from 'react';
import MarginContainer from '../component/MarginContainer';
import LearnWrapper from '../component/LearnWrapper';
import AxiosClient from '../config/axios/AxiosClient';
import GlobalSpinner from '../component/GlobalSpinner';

function QuizResultPage({ match }) {
    const [quizResult, setQuizResult] = useState(null)

    useEffect(() => {
        fetchQuizResult();
    }, [])

    const fetchQuizResult = async () => {
        const { data } = await AxiosClient.get(`/quizzes/${match.params.lessonId}/quiz/${match.params.quizId}/result`)
        console.log(data)
        setQuizResult(data)
    }

    return (
        <MarginContainer>
            <LearnWrapper>
                {quizResult == null ?
                    <GlobalSpinner />
                    :
                    <div>
                        <h1>{quizResult.quizName}</h1>
                        <ul>
                            <li>
                                Rozpoczęto: {quizResult.startDate}
                            </li>
                            <li>
                                Zakończono: {quizResult.finishDate}
                            </li>
                            <li>
                                Punktów do uzyskania: {quizResult.pointsToGain}
                            </li>
                            <li>
                                Punktów uzyskano: {quizResult.pointsGained}
                            </li>
                            <li>
                                Wynik: {(quizResult.pointsGained / quizResult.pointsToGain) * 100}%
                            </li>
                        </ul>
                    </div>
                }
            </LearnWrapper>
        </MarginContainer>
    )
}

export default QuizResultPage;