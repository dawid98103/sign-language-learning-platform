import React, { useState } from 'react'
import styled from 'styled-components';
import { ListGroup, Collapse } from 'react-bootstrap'

const ListGroupItemWithCursour = styled(ListGroup.Item)`
    cursor: pointer;
`

function renderLessonStages(lessonStages) {
    return lessonStages.map(lessonStage => {
        return (
            <ListGroupItemWithCursour action key={lessonStage.id} variant={lessonStage.isCompleted ? "success" : ""}>{lessonStage.name}</ListGroupItemWithCursour>
        )
    })
}

function LessonListItem({ lessonName, lessonStages, isCompleted, disabled }) {
    const [open, setOpen] = useState(false);

    return (
        <>
            <ListGroupItemWithCursour action onClick={() => setOpen(!open)} aria-controls="example-collapse" disabled={disabled} aria-expanded={open}>{lessonName} / {isCompleted ? "Ukończony" : "Nieukończony"}</ListGroupItemWithCursour>
            <Collapse in={open}>
                <div id="example-collapse">
                    {renderLessonStages(lessonStages)}
                </div>
            </Collapse>
        </>
    )
}

export default LessonListItem;