import { ListGroupItem } from 'react-bootstrap';
import styled from 'styled-components';

const ResultListElementWrapper = styled.div`
    display: flex;
    justify-content: center;
    width: 100%;
    height: 100%;
    padding: 15px 0px 15px 0px;
    opacity: ${props => props.completed ? "" : " .65"};
    &:hover{
        cursor: ${props => props.completed ? "pointer" : "default"};
        background-color: ${props => props.completed ? "#f8f9fa" : ""} ;
    }

`

const ResultListElement = ({ quizCompletion: { lessonId, quizId, quizName, completed }, openQuizResultPage }) => {
    return (
        <ListGroupItem>
            <ResultListElementWrapper completed={completed} onClick={completed ? () => openQuizResultPage(lessonId, quizId) : null}>
                {quizName}
            </ResultListElementWrapper>
        </ListGroupItem>
    )
}

export default ResultListElement;