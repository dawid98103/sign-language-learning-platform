import React from 'react';
import styled from 'styled-components';

const ElementLeftCounter = styled.div`
margin: 5px 0px 15px 0px;
text-align: center;
font-weight: bold;
font-size: 1.5rem;
padding: 5px;
border-radius: 15px;
`

function ElementCounter({ children }) {

    return (
        <ElementLeftCounter>
            {children}
        </ElementLeftCounter>
    )
}

export default ElementCounter