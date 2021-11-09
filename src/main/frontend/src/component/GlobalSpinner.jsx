import React from 'react'
import styled from 'styled-components';
import { Spinner } from 'react-bootstrap'

const SpinnerContainer = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
`

const PageSpinner = styled(Spinner)`
    color: #e5e5e5;
    margin: auto;
    width: 4rem;
    height: 4rem;
`

function GlobalSpinner() {
    return (
        <SpinnerContainer>
            <PageSpinner animation="border" role="status" />
        </SpinnerContainer>
    )
}

export default GlobalSpinner;