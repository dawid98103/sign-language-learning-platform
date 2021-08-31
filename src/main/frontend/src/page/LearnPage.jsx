import React, { useState, useEffect, useContext } from 'react';
import { Col, ListGroup, Row, Card } from 'react-bootstrap';
import LessonListItem from '../component/LessonListItem';
import MarginContainer from '../component/MarginContainer';
import AxiosClient from '../config/axios/AxiosClient';
import { GlobalContext } from '../context/GlobalContext';
import styled from 'styled-components';

const lessonStages = [
    {
        id: 1,
        name: "1.1. Witanie i pożegnanie",
        isCompleted: false
    },
    {
        id: 2,
        name: "1.2. Podstawowe zwroty o sobie",
        isCompleted: false
    },
    {
        id: 3,
        name: "1.3. Przestawienie się",
        isCompleted: false
    },
    {
        id: 4,
        name: "1.4. Quiz",
        isCompleted: false
    }
]

const ListGroupWithMargin = styled(ListGroup)`
    margin: 1rem 10px 1rem 10px;
`

const UserCard = styled(Card)`
    margin: 1rem 10px 1rem 10px;
`

function LearnPage() {

    const [lessons, setLessons] = useState([]);
    const [completedLessons, setCompletedLessons] = useState([]);
    const { state, dispatch } = useContext(GlobalContext);

    const userCompletedLessonsList = [];

    useEffect(() => {
        if (state.isAuthenticated) {
            AxiosClient.get(`/lessons/user/completed`).then((response) => {
                console.log(response.data);
                setCompletedLessons(response.data);
            }).catch(error => {
                console.log(error);
            }, []);
        }

        AxiosClient.get(`/lessons`).then((response) => {
            console.log(response.data);
            setLessons(response.data);
        }).catch(error => {
            console.log(error);
        });
    }, []);

    useEffect(() => {
        completedLessons.forEach(completedLesson => {
            if (userCompletedLessonsList.length > 0) {
                if (!userCompletedLessonsList.find(completedLesson.lessonStageId))
                    userCompletedLessonsList.push(completedLesson.lessonStageId)
            } else {
                userCompletedLessonsList.push(completedLesson.lessonStageId)
            }
        });
    }, [completedLessons, lessons])

    return (
        <MarginContainer>
            <Row> {/* //TODO  usunąć styl przy COL */}
                <Col xs={7} style={{ backgroundColor: "black" }}>
                    <ListGroupWithMargin>
                        {
                            lessons.map(lesson => {
                                return (
                                    <LessonListItem key={lesson.lessonId} lessonName={lesson.lessonId + ". " + lesson.name} lessonStages={lessonStages} isCompleted={false} disabled={!state.isAuthenticated && lesson.loginRequired} />
                                )
                            })
                        }
                    </ListGroupWithMargin>
                </Col>
                <Col style={{ backgroundColor: "red" }}>
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
        </MarginContainer>
    )
}

export default LearnPage

