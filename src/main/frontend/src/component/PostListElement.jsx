import React from 'react';
import { Container, Row, Col, Image } from 'react-bootstrap';
import styled from 'styled-components';

const PostWrapper = styled.div`
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
    display: flex,
    padding: 10px;
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

const TitleWrapper = styled.div`
    display: flex;
    padding: 16px 12px 16px 12px;
    height: 100%;
    width: 100%;
    font-size: 1.2em;
    font-weight: bold;
`

const FittedImage = styled(Image)`
    width: 100%;
    height: auto;
`

function PostListElement({ title, content, author, creationDate, avatar, openPost }) {
    return (
        <PostWrapper onClick={openPost}>
            <Container>
                <Row>
                    <Col xs={2}>
                        <ImageWrapper>
                            <FittedImage src={avatar} rounded />
                        </ImageWrapper>
                    </Col>
                    <Col xs={10}>
                        <Row>
                            <TitleWrapper>
                                {title}
                            </TitleWrapper>
                        </Row>
                        <Row>
                            <ContentWrapper>
                                {content}
                            </ContentWrapper>
                        </Row>
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
        </PostWrapper>
    )
}

export default PostListElement;