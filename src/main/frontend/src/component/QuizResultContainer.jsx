import React from 'react';
import styled from 'styled-components';
import { Dropdown, ListGroup, Button } from 'react-bootstrap';
import history from '../config/history';


const ResultHeader = styled.div`
    display: flex;
    justify-content: center;
    padding: 15px 0px 15px 0px;
`

const ResultContainer = styled.div`
    font-size: 1.3rem;
`

const ResultFooter = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 15px 0px 15px 0px;
`

const handleReturnToHomePage = () => {
    history.push(`/lesson`)
}

function QuizResultContainer({ quizResult }) {
    return (
        <>
            <ResultHeader>
                <h1>{quizResult.quizName}</h1>
            </ResultHeader>
            <Dropdown.Divider />
            <ResultContainer>
                <ListGroup variant="flush">
                    <ListGroup.Item>Rozpoczęto: {quizResult.startDate}</ListGroup.Item>
                    <ListGroup.Item>Zakończono: {quizResult.finishDate}</ListGroup.Item>
                    <ListGroup.Item>Punktów do uzyskania: {quizResult.pointsToGain}</ListGroup.Item>
                    <ListGroup.Item>Punktów uzyskano: {quizResult.pointsGained}</ListGroup.Item>
                    <ListGroup.Item>Wynik: {(quizResult.pointsGained / quizResult.pointsToGain) * 100}%</ListGroup.Item>
                </ListGroup>
            </ResultContainer>
            <Dropdown.Divider />
            <ResultFooter>
                <Button primary onClick={handleReturnToHomePage}>Wróć na stronę główną</Button>
            </ResultFooter>
        </>
    )
}

export default QuizResultContainer;