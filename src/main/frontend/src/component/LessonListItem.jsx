import React, { useState, useEffect } from 'react'
import styled from 'styled-components';
import AxiosClient from '../config/axios/AxiosClient';
import { ListGroup, Collapse } from 'react-bootstrap'
import { useHistory } from 'react-router-dom';

const ListGroupItemWithCursour = styled(ListGroup.Item)`
    cursor: pointer;
`

function LessonListItem({ lessonName, disabled, lessonId }) {

    const [open, setOpen] = useState(false);
    const [lessonStages, setLessonStages] = useState([]);
    const history = useHistory();

    useEffect(() => {
        console.log(lessonId);
        AxiosClient.get(`/lessons/${lessonId}/stage`).then((response) => {
            console.log("ref");
            setLessonStages(response.data);
        }).catch((error) => {
            console.log(error);
        })
    }, [])

    function renderLessonStages() {
        return lessonStages.map(lessonStage => {
            console.log(lessonStage);
            return (
                <ListGroupItemWithCursour action key={lessonStage.lessonStageId} variant={lessonStage.completed ? "success" : ""} onClick={() => history.push(`/learn/${lessonId}/${lessonStage.lessonStageId}`)}>{lessonStage.index}. {lessonStage.name}</ListGroupItemWithCursour>
            )
        })
    }

    return (
        <>
            <ListGroupItemWithCursour action onClick={() => setOpen(!open)} aria-controls="example-collapse" disabled={disabled} aria-expanded={open}>{lessonName}</ListGroupItemWithCursour>
            <Collapse in={open}>
                <div id="example-collapse">
                    {renderLessonStages(lessonStages)}
                </div>
            </Collapse>
        </>
    )
}

export default LessonListItem;