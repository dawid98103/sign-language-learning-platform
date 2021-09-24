import React from 'react';
import styled from 'styled-components';

const DescriptionWrapper = styled.div`
    margin: 1em 0 1em 0;
    text-align: center;
    border: 2px solid #e5e5e5;
    border-radius: 25px;
    padding: 10px;
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