import React from 'react';
import styled from 'styled-components';

const DescriptionWrapper = styled.div`
    margin-top: 2em;
    background-color: #e5e5e5;
    text-align: center;
    padding: 20px;
    border-radius: 15px;
`

function LearnDescription({ text }) {

    return (
        <DescriptionWrapper>
            <h2>
                {text}
            </h2>
        </DescriptionWrapper>
    )
}

export default LearnDescription