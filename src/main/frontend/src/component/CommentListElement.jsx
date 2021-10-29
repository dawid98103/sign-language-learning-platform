import React from 'react';
import { Container, Row, Col, Image } from 'react-bootstrap';
import styled from 'styled-components';

const CommentWrapper = styled.div`
    display: flex,
    padding:25px;
    border: 2px solid #e5e5e5;
    border-radius: 15px;
    &:hover {
        background-color: #f8f9fa;
        cursor: pointer;
    }
`

const ContentWrapper = styled.div`
    display: flex;
    padding: 15px;
`

const ImageWrapper = styled.div`
    display: flex;
    height: 100%;
    width: 100%;
    font-size: 1.2em;
    font-weight: bold;
    align-items: center;
    justify-content: center;
`

const FittedImage = styled(Image)`
    width: 100%;
    height: auto;
`

function CommentListElement({ content, author, creationDate, avatar }) {
    return (
        <CommentWrapper>
            <Container>
                <Row>
                    <Col xs={2}>
                        <ImageWrapper>
                            {/* <FittedImage src={avatar} rounded /> */}
                        </ImageWrapper>
                    </Col>
                    <Col xs={10}>
                        <Row>
                            <ContentWrapper>
                                {content}
                            </ContentWrapper>
                        </Row>
                        <hr />
                        <Row>
                            <Col lg={5}>
                                <p>Autor: {author}</p>
                            </Col>
                            <Col lg={7}>
                                <p>Data utworzenia: {creationDate}</p>
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </Container>
        </CommentWrapper>
    )
}

export default CommentListElement;