import styled from "styled-components"

const StyledHref = styled.span`
    padding: 0px;
    margin: 0px;
    &:hover{
        cursor: pointer;
    }
`

const HrefWrapper = ({ hrefAction, children }) => {
    return (
        <StyledHref onClick={() => hrefAction()}>
            {children}
        </StyledHref>
    )
}

export default HrefWrapper;