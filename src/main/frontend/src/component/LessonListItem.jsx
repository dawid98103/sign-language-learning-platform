import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import NotificationModal from './modal/NotificationModal';
import AxiosClient from '../config/axios/AxiosClient';
import { ListGroup, Collapse, Placeholder } from 'react-bootstrap';
import history from '../config/history';

const ListGroupItemWithCursour = styled(ListGroup.Item)`
    cursor: pointer;
    display: flex;
    justify-content: space-between;
    padding: .9rem 1rem;
`

function LessonListItem({ lessonName, disabled, lessonId, quiz }) {
    const [open, setOpen] = useState(false);
    const [showModal, setShowModal] = useState(false);
    const [lessonStages, setLessonStages] = useState(null);

    useEffect(() => {
        fetchLessonStages();
    }, [])

    const checkIsCompleted = () => {
        let containsFalse = false;
        lessonStages.forEach(lessonStage => {
            if (lessonStage.completed === false) {
                containsFalse = true
            }
        })

        if (containsFalse) {
            return false
        } else {
            return true
        }
    }

    const fetchLessonStages = async () => {
        const { data } = await AxiosClient.get(`/lessons/${lessonId}/stage`);
        setLessonStages(data)
    }

    const handleCloseModal = async () => {
        setShowModal(false)
        await AxiosClient.post(`/quizzes/process/quiz/${quiz.quizId}/start`);
        history.push(`/quiz/${lessonId}/${quiz.quizId}`)
    }

    const handleQuizStart = () => {
        setShowModal(true)
    }

    function renderLessonStages() {
        return lessonStages.map(lessonStage => {
            return (
                <ListGroupItemWithCursour action
                    key={lessonStage.lessonStageId}
                    variant={lessonStage.completed ? "success" : ""}
                    onClick={() => history.push(`/learn/${lessonId}/${lessonStage.lessonStageId}`)}>{lessonStage.index}. {lessonStage.name}</ListGroupItemWithCursour>
            )
        })
    }

    return (
        <>
            {lessonStages == null ?
                <>
                    <Placeholder as={ListGroup.Item} animation="glow">
                        <Placeholder xs={12} />
                    </Placeholder>
                </>
                :
                <ListGroupItemWithCursour action onClick={() => setOpen(!open)} aria-controls="example-collapse" disabled={disabled} aria-expanded={open}>{lessonName} {checkIsCompleted() ? <img
                    alt=""
                    src={process.env.PUBLIC_URL + "/icons/valid.svg"}
                    width="30"
                    height="30"
                    style={{ marginRight: 10 }}
                    className="d-inline-block align-center"
                /> : ""}</ListGroupItemWithCursour>
            }
            <Collapse in={open}>
                {lessonStages == null ?
                    <>
                        <Placeholder as={ListGroup.Item} animation="glow">
                            <Placeholder xs={12} />
                        </Placeholder>
                    </>
                    :
                    <div id="example-collapse">
                        {renderLessonStages(lessonStages)}
                        {quiz &&
                            <ListGroupItemWithCursour action
                                key={quiz.quizId}
                                variant="flush"
                                disabled={quiz.quizResult != null}
                                onClick={handleQuizStart}>
                                <div>
                                    <img
                                        alt=""
                                        src={process.env.PUBLIC_URL + "/icons/quiz.svg"}
                                        width="30"
                                        height="30"
                                        style={{ marginRight: 10 }}
                                        className="d-inline-block align-center"
                                    />
                                    {quiz.title}
                                </div>
                                <div>
                                    {quiz.quizResult != null
                                        ? `Wynik: ${(quiz.quizResult.pointsGained / quiz.quizResult.pointsToGain) * 100}%` : ""}
                                </div>
                            </ListGroupItemWithCursour>}

                    </div>
                }
            </Collapse>
            <NotificationModal
                headerText={"Ważna informacja"}
                contentText={"Kiedy przejdziesz do rozwiązywania quizu nie będzie już możliwości powrotu, każde wyjście w połowie rozwiązywania wiąże się z zapisaniem uzyskanych do tej pory punktów!!"}
                modalShow={showModal}
                closeModal={handleCloseModal}
            />
        </>
    )
}

export default LessonListItem;