import React from 'react'
import styled from 'styled-components'
import { Container } from 'react-bootstrap'

const ContainerWithMargin = styled(Container)`
    margin-top: 60px;
`

function MarginContainer(props) {
    return (
        <ContainerWithMargin>
            {props.children}
        </ContainerWithMargin>
    )
}

export default MarginContainer