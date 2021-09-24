import React, { useState } from 'react';
import ReactPlayer from 'react-player';
import MarginContainer from './MarginContainer';
import styled from 'styled-components';
import LearnDescription from '../component/LearnDescription'
import QuizAnswerBox from './QuizAnswerBox';
import ControlButton from './ControlButton';
import ElementCounter from './ElementCounter';
import { Col } from 'react-bootstrap';

const LearnWrapper = styled.div`
    width: 65%;
    margin: auto;
    padding: 30px;
    border: 2px solid #e5e5e5;
    border-radius: 25px;
`

const PlayerContainer = styled.div`
    display: flex;
    justify-content: center;
`

const ControlButtonContainer = styled.div`
    display: flex;
    justify-content: flex-end;
    padding: 15px 0px 15px 0px;
`

const AnswerBoxWrapper = styled.div`
    display: flex;
    padding: 5px 0px 5px 0px;
`

const ColWithoutPadding = styled(Col)`
    padding: 0px;
`

function QuizContainer({ currentQuestion, questionsCount, videoUrl, questionName, answers, handleNextQuestion }) {
    const [selectedOptionId, setselectedOptionId] = useState(0)

    const selectOption = (optionId) => {
        optionId === selectedOptionId ? setselectedOptionId(0) : setselectedOptionId(optionId)
    }

    return (
        <MarginContainer>
            <LearnWrapper>
                <ElementCounter>
                    {`${currentQuestion + 1} / ${questionsCount}`}
                </ElementCounter>
                <PlayerContainer>
                    <ReactPlayer width={900} height={500} url={videoUrl} />
                </PlayerContainer>
                <LearnDescription text={questionName} />
                {answers.map(answer => {
                    return (
                        <AnswerBoxWrapper>
                            <ColWithoutPadding>
                                <QuizAnswerBox
                                    key={answer.quizAnswerId}
                                    optionId={answer.quizAnswerId}
                                    selectedId={selectedOptionId}
                                    content={answer.description}
                                    onSelect={selectOption} />
                            </ColWithoutPadding>
                        </AnswerBoxWrapper>
                    )
                })}
                <ControlButtonContainer>
                    {(currentQuestion + 1) === questionsCount
                        ? <ControlButton text={"Zakończ"} disabled={false} clickEvent={() => handleNextQuestion(selectedOptionId)} />
                        : <ControlButton text={"Dalej"} disabled={false} clickEvent={() => handleNextQuestion(selectedOptionId)} />}
                </ControlButtonContainer>
            </LearnWrapper>
        </MarginContainer>
    )

}

export default QuizContainer