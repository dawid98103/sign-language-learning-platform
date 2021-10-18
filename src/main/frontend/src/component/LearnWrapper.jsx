
import React from 'react'
import styled from 'styled-components'

const LearnWrapperComponent = styled.div`
margin: auto;
min-width: 70%;
padding: 30px;
border: 2px solid #e5e5e5;
border-radius: 25px;
`

function LearnWrapper({ children }) {
    return (
        <LearnWrapperComponent>
            {children}
        </LearnWrapperComponent>
    )
}

export default LearnWrapper