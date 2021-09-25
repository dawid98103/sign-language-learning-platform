import React from 'react'
import styled from 'styled-components'
import { Container } from 'react-bootstrap'

const ContainerWithMargin = styled(Container)`
    display: flex;
    justify-items: center;
    margin-top: 20px;
    margin-bottom: 20px;
`

function MarginContainer({ children }) {
    return (
        <ContainerWithMargin>
            {children}
        </ContainerWithMargin>
    )
}

export default MarginContainer