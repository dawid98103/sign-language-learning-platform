import React from 'react';
import styled from 'styled-components';
import { Container } from 'react-bootstrap';

const CenteredContainer = styled(Container)`
    min-height: 100%;
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    background-image: ${props => props.withBackground ? "url(" + process.env.PUBLIC_URL + "/pictures/background.jpg)" : "white"};
    background-repeat: no-repeat;
    background-size: cover;
`

function CenteredMarginContainer({ children, withBackground }) {
    return (
        <CenteredContainer withBackground={withBackground} fluid>
            {children}
        </CenteredContainer>
    )
}

export default CenteredMarginContainer;

