import React, { useState, useEffect, useContext } from 'react';
import { toast } from 'react-toastify';
import styled from 'styled-components';
import { Col, ListGroup, Row, Card, Image } from 'react-bootstrap';
import LessonListItem from '../component/LessonListItem';
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

const LessonContentWrapper = styled.div`
    padding: 20px;
    border: 0.5px solid #e5e5e5;
    border-radius: 25px;
    width: 100%;
`

function LessonPage() {
    const [lessons, setLessons] = useState([]);
    const [quizzes, setQuizzes] = useState([]);
    const [userInfo, setUserInfo] = useState({ lastActivityDateTime: new Date().toLocaleTimeString(), consecutiveDays: 0, gainedPoints: 0 });
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

    const terminateActiveQuizzes = async () => {
        await AxiosClient.post(`/quizzes/process/quiz/terminate`)
    }

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
            <LessonContentWrapper>
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
                                                isCompleted={false}
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
                                    <Image src={state.avatarUrl} />
                                </ImageContainer>
                                <Card.Title>
                                    <Card body>
                                        {state.user}
                                    </Card>
                                </Card.Title>
                                <Card.Text>
                                    <Card body>
                                        <ListGroup variant="flush">
                                            <ListGroup.Item>Ostatnia aktywność: {userInfo.lastActivityDateTime}</ListGroup.Item>
                                            <ListGroup.Item>Dni nauki z rzędu: {userInfo.consecutiveDays}</ListGroup.Item>
                                            <ListGroup.Item>Zdobytych punktów: {userInfo.gainedPoints}</ListGroup.Item>
                                        </ListGroup>
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

