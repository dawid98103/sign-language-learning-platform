import React, { useState, useEffect, useContext } from 'react';
import { toast } from 'react-toastify';
import styled from 'styled-components';
import { Col, ListGroup, Row, Card, Image } from 'react-bootstrap';
import Placeholder from 'react-bootstrap/Placeholder'
import LessonListItem from '../component/LessonListItem';
import GlobalContentWrapper from '../component/GlobalContentWrapper';
import MarginContainer from '../component/MarginContainer';
import AxiosClient from '../config/axios/AxiosClient';
import { GlobalContext } from '../context/GlobalContext';
import { LESSON_PAGE } from '../constants/Pages';
import GlobalSpinner from '../component/GlobalSpinner';

const ImageContainer = styled.div`
    display: flex;
    justify-content: center;
    padding: 10px;
`

const ListGroupWithMargin = styled(ListGroup)`
    margin: 1rem 10px 1rem 10px;
`

const UserCard = styled(Card)`
    margin: 1rem 10px 1rem 10px;
`

const LessonPage = () => {
    const [lessons, setLessons] = useState([]);
    const [quizzes, setQuizzes] = useState([]);
    const [userInfo, setUserInfo] = useState(null);
    const { state, dispatch } = useContext(GlobalContext);

    useEffect(async () => {
        dispatch({
            type: "SET_PAGE",
            payload: { page: LESSON_PAGE }
        })
        renderNotificationIfExists();
        await terminateActiveQuizzes();
        fetchUserBasicInfo();
        fetchLessons();
        fetchQuizzes();
    }, []);

    const fetchUserBasicInfo = async () => {
        const { data } = await AxiosClient.get(`/users/user/info`)
        setUserInfo(data);
    }

    const fetchLessons = async () => {
        const { data } = await AxiosClient.get(`/lessons`)
        setLessons(data);
    }

    const fetchQuizzes = async () => {
        const { data } = await AxiosClient.get(`/quizzes`)
        setQuizzes(data)
    }

    const terminateActiveQuizzes = async () => {
        if (state.isAuthenticated) {
            await AxiosClient.post(`/quizzes/process/quiz/terminate`)
        }
    }

    const getQuizForLesson = (idToFind) => {
        if (state.isAuthenticated) {
            return quizzes.find(({ lessonId }) => lessonId === idToFind);
        }
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
            <GlobalContentWrapper>
                <Row>
                    <Col sm={7}>
                        <ListGroupWithMargin>
                            {
                                lessons.length > 0
                                    ?
                                    lessons.map(lesson => {
                                        return (
                                            <LessonListItem
                                                key={lesson.lessonId}
                                                lessonId={lesson.lessonId}
                                                lessonName={lesson.lessonId + ". " + lesson.name}
                                                disabled={!state.isAuthenticated && lesson.loginRequired}
                                                quiz={getQuizForLesson(lesson.lessonId)}
                                            />
                                        )
                                    })
                                    : <GlobalSpinner />
                            }
                        </ListGroupWithMargin>
                    </Col>
                    <Col sm={5}>
                        <UserCard>
                            <Card.Body>
                                <ImageContainer>
                                    {state.isAuthenticated ?
                                        <Image src={state.avatarUrl} />
                                        :
                                        <Image src={process.env.PUBLIC_URL + "/pictures/user_mockup.png"} />
                                    }
                                </ImageContainer>
                                <Card.Title>
                                    <Card body>
                                        {state.isAuthenticated ? state.user : "Gość"}
                                    </Card>
                                </Card.Title>
                                <Card.Text>
                                    <Card body>
                                        <ListGroup variant="flush">
                                            {userInfo == null && state.isAuthenticated ?
                                                <>
                                                    <Placeholder as={ListGroup.Item} animation="glow">
                                                        <Placeholder xs={12} />
                                                    </Placeholder>
                                                    <Placeholder as={ListGroup.Item} animation="glow">
                                                        <Placeholder xs={12} />
                                                    </Placeholder>
                                                    <Placeholder as={ListGroup.Item} animation="glow">
                                                        <Placeholder xs={12} />
                                                    </Placeholder>

                                                </>
                                                :
                                                <>
                                                    {state.isAuthenticated ?
                                                        <>
                                                            <ListGroup.Item>Ostatnia aktywność: {userInfo.lastActivityDateTime}</ListGroup.Item>
                                                            <ListGroup.Item>Dni nauki z rzędu: {userInfo.consecutiveDays}</ListGroup.Item>
                                                            <ListGroup.Item>Zdobytych punktów: {userInfo.gainedPoints}</ListGroup.Item>
                                                        </>
                                                        :
                                                        <ListGroup.Item>Zaloguj się</ListGroup.Item>
                                                    }
                                                </>
                                            }
                                        </ListGroup>
                                    </Card>
                                </Card.Text>
                            </Card.Body>
                        </UserCard>
                    </Col>
                </Row>
            </GlobalContentWrapper>
        </MarginContainer>
    )
}

export default LessonPage

