import { ListGroupItem } from 'react-bootstrap';
import styled from 'styled-components';

const AchievementListElementWrapper = styled.div`
    display: flex;
    justify-content: center;
    width: 100%;
    height: 100%;
    padding: 15px 0px 15px 0px;
    opacity: ${props => props.assigned ? "" : " .65"};
    &:hover{
        background-color: ${props => props.assigned ? "#f8f9fa" : ""} ;
    }

`

const AchievementListElement = ({ achievement: { achievementLocalName, creationDate } }) => {
    return (
        <ListGroupItem>
            <AchievementListElementWrapper assigned={creationDate != null}>
                {achievementLocalName}
            </AchievementListElementWrapper>
        </ListGroupItem>
    )
}

export default AchievementListElement;