import React, { useState, useEffect, useContext } from 'react';
import { Col, ListGroup, Row, Card } from 'react-bootstrap';
import LessonListItem from '../component/LessonListItem';
import MarginContainer from '../component/MarginContainer';
import AxiosClient from '../config/axios/AxiosClient';
import { GlobalContext } from '../context/GlobalContext';
import styled from 'styled-components';

const ListGroupWithMargin = styled(ListGroup)`
    margin: 1rem 10px 1rem 10px;
`

const UserCard = styled(Card)`
    margin: 1rem 10px 1rem 10px;
`

const LessonContentWrapper = styled.div`
padding: 30px;
border: 2px solid #e5e5e5;
border-radius: 25px;
`

function LessonPage() {

    const [lessons, setLessons] = useState([]);
    const { state, dispatch } = useContext(GlobalContext);

    useEffect(() => {
        AxiosClient.get(`/lessons`).then((response) => {
            setLessons(response.data);
        }).catch(error => {
            console.log(error);
        });
    }, []);

    return (
        <MarginContainer>
            <LessonContentWrapper>
                <Row> {/* //TODO  usunąć styl przy COL */}
                    <Col xs={7}>
                        <ListGroupWithMargin>
                            {
                                lessons.map(lesson => {
                                    return (
                                        <LessonListItem key={lesson.lessonId} lessonId={lesson.lessonId} lessonName={lesson.lessonId + ". " + lesson.name} isCompleted={false} disabled={!state.isAuthenticated && lesson.loginRequired} />
                                    )
                                })
                            }
                        </ListGroupWithMargin>
                    </Col>
                    <Col>
                        <UserCard>
                            <Card.Body>
                                <Card.Img variant="top" src="https://biografia24.pl/wp-content/uploads/2013/11/adam-malysz.png" />
                                <Card.Title>{state.user}</Card.Title>
                                <Card.Text>
                                    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                </Card.Text>
                            </Card.Body>
                        </UserCard>
                    </Col>
                </Row>
            </LessonContentWrapper>
        </MarginContainer>
    )
}

export default LessonPage

