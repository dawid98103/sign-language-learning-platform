import React from 'react';
import { Card } from 'react-bootstrap'

function QuizAnswerCheckbox({ content }) {
    return (
        <Card>
            <Card.Body>
                {content}
            </Card.Body>
        </Card>
    )
}

export default QuizAnswerCheckbox;