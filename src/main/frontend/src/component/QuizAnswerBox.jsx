import React, { useState } from 'react';
import styled from 'styled-components';

const AnswerBox = styled.div`
    display: flex;
    cursor: pointer;
    flex-direction: row;
    flex-wrap: nowrap;
    align-items: center;
    padding: 15px 35px 15px 35px;
    border-radius: 10px;
    border: 1px solid #e5e5e5;
    ${props => props.selected && `
        background-color: lightgray;
    `}
    ${props => !props.selected && `
    &: hover{
        background-color: #f8f9fa;
    }
    `}

`

const Separator = styled.div`
    width: 10px;
`

function QuizAnswerBox({ optionId, selectedId, content, onSelect }) {
    const [id, setId] = useState(optionId)

    return (
        <AnswerBox onClick={() => onSelect(id)} selected={id === selectedId}>
            <Separator />
            {content}
        </AnswerBox>
    )
}

export default QuizAnswerBox;