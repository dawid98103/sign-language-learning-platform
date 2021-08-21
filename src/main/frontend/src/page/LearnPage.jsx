import React from 'react';
import { Col, ListGroup, Row } from 'react-bootstrap';
import LessonListItem from '../component/LessonListItem';
import MarginContainer from '../component/MarginContainer';

const lessonStages = [
    {
        id: 1,
        name: "1.1. Witanie i pożegnanie",
        isCompleted: true
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

function LearnPage() {
    return (
        <MarginContainer>
            <Row> {/* //TODO  usunąć styl przy COL */}
                <Col xs={7} style={{ backgroundColor: "black" }}>
                    <ListGroup>
                        <LessonListItem lessonName="1. Podstawy" lessonStages={lessonStages} isCompleted={false} />

                        {/*                       <ListGroup.Item action onClick={() => setOpen(!open)} aria-controls="example-collapse" aria-expanded={open}>1. Podstawy</ListGroup.Item>
                        <Collapse in={open}>
                            <div id="example-collapse">
                                <ListGroup.Item action>1.1. Witanie i pożegnanie</ListGroup.Item>
                                <ListGroup.Item action>1.2. Podstawowe zwroty o sobie</ListGroup.Item>
                                <ListGroup.Item action>1.3. Przestawienie się</ListGroup.Item>
                                <ListGroup.Item disabled>1.4. Quiz</ListGroup.Item>
                            </div>
                        </Collapse> */}
                        <ListGroup.Item action>Cras justo odio</ListGroup.Item>
                        <ListGroup.Item action>Cras justo odio</ListGroup.Item>
                        <ListGroup.Item action>Cras justo odio</ListGroup.Item>
                        <ListGroup.Item action>Cras justo odio</ListGroup.Item>
                    </ListGroup>
                </Col>
                <Col style={{ backgroundColor: "red" }}>


                </Col>
            </Row>
        </MarginContainer>
    )
}

export default LearnPage

