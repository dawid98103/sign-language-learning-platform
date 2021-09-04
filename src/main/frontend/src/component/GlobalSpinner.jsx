import React from 'react'
import styled from 'styled-components';
import { Spinner } from 'react-bootstrap'

const PageSpinner = styled(Spinner)`
    color: #e5e5e5;
    margin: auto;
    width: 4rem;
    height: 4rem;
`

function GlobalSpinner() {
    return (
        <PageSpinner animation="border" role="status" />
    )
}

export default GlobalSpinner;