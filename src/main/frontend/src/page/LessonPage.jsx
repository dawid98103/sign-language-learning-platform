import React, { useState, useEffect, useContext } from 'react';
import { Col, ListGroup, Row, Card } from 'react-bootstrap';
import LessonListItem from '../component/LessonListItem';
import MarginContainer from '../component/MarginContainer';
import AxiosClient from '../config/axios/AxiosClient';
import { GlobalContext } from '../context/GlobalContext';
import { LESSON_PAGE } from '../constants/Pages';
import GlobalSpinner from '../component/GlobalSpinner';
import { toast } from 'react-toastify';
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
    const [quizzes, setQuizzes] = useState([]);
    const { state, dispatch } = useContext(GlobalContext);

    useEffect(() => {
        dispatch({
            type: "SET_PAGE",
            payload: { page: LESSON_PAGE }
        })

        renderNotificationIfExists()
        fetchLessons();
        fetchQuizzes();
    }, []);

    const fetchLessons = async () => {
        const { data } = await AxiosClient.get(`/lessons`)
        setLessons(data);
    }

    const fetchQuizzes = async () => {
        const { data } = await AxiosClient.get(`/quizzes`)
        setQuizzes(data)
    }

    const getQuizForLesson = (idToFind) => {
        console.log(idToFind);
        console.log(quizzes);
        return quizzes.find(({ lessonId }) => lessonId === idToFind);
    }

    const renderNotificationIfExists = () => {
        if (state.globalNotification !== null) {
            toast.success(`${state.globalNotification}`, {
                position: "bottom-right"
            })
            dispatch({
                type: "CLEAR_NOTIFICATION"
            })
        }
    }

    return (
        <MarginContainer>
            <LessonContentWrapper>
                <Row> {/* //TODO  usunąć styl przy COL */}
                    <Col xs={7}>
                        <ListGroupWithMargin>
                            {
                                lessons.length > 0
                                    ?
                                    lessons.map(lesson => {
                                        return (
                                            <LessonListItem key={lesson.lessonId} lessonId={lesson.lessonId} lessonName={lesson.lessonId + ". " + lesson.name} isCompleted={false} disabled={!state.isAuthenticated && lesson.loginRequired} quiz={getQuizForLesson(lesson.lessonId)} />
                                        )
                                    })
                                    : <GlobalSpinner />
                            }
                        </ListGroupWithMargin>
                    </Col>
                    <Col>
                        <UserCard>
                            <Card.Body>
                                <Card.Img variant="top" src="https://biografia24.pl/wp-content/uploads/2013/11/adam-malysz.png" />
                                <Card.Title>
                                    <Card body>
                                        {state.user}
                                    </Card>
                                </Card.Title>
                                <Card.Text>
                                    <Card body>
                                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
                                    </Card>

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

