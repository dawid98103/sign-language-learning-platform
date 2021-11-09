import styled from 'styled-components';

const LessonContentWrapper = styled.div`
    padding: 20px;
    border: 0.5px solid #e5e5e5;
    border-radius: 25px;
    width: 100%;
`

const GlobalContentWrapper = ({ children }) => {
    return (
        <LessonContentWrapper>
            {children}
        </LessonContentWrapper>
    )
}

export default GlobalContentWrapper