import React from 'react'
import styled from 'styled-components';

const StyledControlButton = styled.div`
    font-size: 1.25rem;
    line-height: 1.5;
    background-color: transparent;
    text-align: center;
    width: 10em;
    padding: 15px 10px 15px 10px;
    border: 1px solid #e5e5e5;
    border-radius: 10px;
    display: block;
    font-weight: 400;
    opacity: ${props => props.disabled ? ".65" : "1"};
    &:hover {
        background-color: #f8f9fa;
        cursor: ${props => props.disabled ? "default" : "pointer"};
    }
`

function ControlButton({ text, disabled, clickEvent }) {

    return (
        <StyledControlButton disabled={disabled} onClick={() => clickEvent()}>
            {text}
        </StyledControlButton>
    )
}

export default ControlButton