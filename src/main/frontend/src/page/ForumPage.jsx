import React, { useState, useEffect, useContext } from 'react';
import MarginContainer from '../component/MarginContainer';
import { Container, Row, Col, ListGroup, Form } from 'react-bootstrap';
import GlobalContentWrapper from '../component/GlobalContentWrapper';
import PostListElement from '../component/PostListElement';

function ForumPage() {

    return(
        <MarginContainer>
            <GlobalContentWrapper>
                <Container>
                    <Row>
                        <h3>FORUM JĘZYKOWE</h3>
                        <Col xs={12} md={7}>
                        <ListGroup variant="flush">
                        <ListGroup.Item><PostListElement/></ListGroup.Item>
                        <ListGroup.Item><PostListElement/></ListGroup.Item>
                        <ListGroup.Item><PostListElement/></ListGroup.Item>
                        </ListGroup>   
                        </Col>
                        <Col md={5}>
                            <div>
                                <Form.Control type="text" placeholder="Wyszukaj post..."/>
                            </div>
                        </Col>
                    </Row>
                </Container>
            </GlobalContentWrapper>
        </MarginContainer>
    )
}

export default ForumPage;