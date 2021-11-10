import React, { useState, useEffect, useContext } from 'react';
import MarginContainer from '../component/MarginContainer';
import AxiosClient from '../config/axios/AxiosClient';
import { Container, ListGroup } from 'react-bootstrap';
import GlobalContentWrapper from '../component/GlobalContentWrapper';
import GlobalSpinner from '../component/GlobalSpinner';
import ResultListElement from '../component/ResultListElement';
import history from '../config/history';
import { GlobalContext } from '../context/GlobalContext';

const ResultPage = () => {
    const [quizzesWithCompletion, setQuizzesWithCompletion] = useState([])
    const { state } = useContext(GlobalContext);

    useEffect(() => {
        const fetchQuizzesWithCompletionStatus = async () => {
            const { data } = await AxiosClient.get(`/quizzes/finished`)
            setQuizzesWithCompletion(data);
        }
        fetchQuizzesWithCompletionStatus()
    }, [])

    const openQuizResultPage = (lessonId, quizId) => {
        history.push(`/quiz/${lessonId}/result/${quizId}`)
    }

    return (
        <MarginContainer>
            <GlobalContentWrapper>
                {quizzesWithCompletion.length === 0 ?
                    <GlobalSpinner />
                    :
                    <Container>
                        <ListGroup variant="flush">
                            {
                                quizzesWithCompletion.map(quizCompletion => {
                                    return (
                                        <ResultListElement quizCompletion={quizCompletion} openQuizResultPage={openQuizResultPage} />
                                    )
                                })
                            }
                        </ListGroup>
                    </Container>
                }
            </GlobalContentWrapper>
        </MarginContainer >
    )
}

export default ResultPage;