import React from 'react';
import ReactPlayer from 'react-player';
import MarginContainer from './MarginContainer';
import styled from 'styled-components';
import LearnDescription from '../component/LearnDescription'
import QuizAnswerCheckbox from './QuizAnswerCheckbox';
import { Container, Col, Row } from 'react-bootstrap';

const LearnWrapper = styled.div`
    margin: auto;
    padding: 30px;
    border: 2px solid #e5e5e5;
    border-radius: 25px;
`
const ControlButtonContainer = styled.div`
    display: flex;
    justify-content: space-between;
    padding: 15px 0px 15px 0px;
`

function QuizContainer({ videoUrl, questionName, answers }) {
    return (
        <MarginContainer>
            <LearnWrapper>
                <ReactPlayer width={800} height={600} url={videoUrl} />
                <LearnDescription text={questionName} />
                <ControlButtonContainer>
                    <Container fluid>
                        {answers.map(answer => {
                            return (
                                <Row>
                                    <Col>
                                        <QuizAnswerCheckbox content={answer.description} />
                                    </Col>
                                </Row>
                            )
                        })}
                    </Container>
                </ControlButtonContainer>
            </LearnWrapper>
        </MarginContainer>
    )

}

export default QuizContainer