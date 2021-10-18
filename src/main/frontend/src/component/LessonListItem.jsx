import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import AxiosClient from '../config/axios/AxiosClient';
import { ListGroup, Collapse } from 'react-bootstrap';
import history from '../config/history';

const ListGroupItemWithCursour = styled(ListGroup.Item)`
    cursor: pointer;
    display: flex;
    justify-content: space-between;
    padding: .9rem 1rem;
`

function LessonListItem({ lessonName, disabled, lessonId, quiz }) {
    const [open, setOpen] = useState(false);
    const [lessonStages, setLessonStages] = useState([]);

    useEffect(() => {
        AxiosClient.get(`/lessons/${lessonId}/stage`).then((response) => {
            console.log("ref");
            setLessonStages(response.data);
        }).catch((error) => {
            console.log(error);
        })
    }, [])

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

    const handleQuizStart = async () => {
        await AxiosClient.post(`/quizzes/process/quiz/${quiz.quizId}/start`);
        history.push(`/quiz/${lessonId}/${quiz.quizId}`)
    }

    return (
        <>
            <ListGroupItemWithCursour action onClick={() => setOpen(!open)} aria-controls="example-collapse" disabled={disabled} aria-expanded={open}>{lessonName}</ListGroupItemWithCursour>
            <Collapse in={open}>
                <div id="example-collapse">
                    {renderLessonStages(lessonStages)}
                    {quiz &&
                        <ListGroupItemWithCursour action
                            key={quiz.quizId}
                            variant="flush"
                            disabled={quiz.quizResult != null}
                            onClick={() => handleQuizStart()}>
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
            </Collapse>
        </>
    )
}

export default LessonListItem;