import React from 'react';
import { Container, Row, Col, Image, Button } from 'react-bootstrap';
import styled from 'styled-components';
import HrefWrapper from './HrefWrapper';
import history from '../config/history';

const CommentWrapper = styled.div`
    display: flex,
    padding:25px;
    margin-top: 10px;
    border: 2px solid #e5e5e5;
    border-radius: 15px;
`

const ContentWrapper = styled.div`
    display: flex;
    padding: 15px;
`

const ImageWrapper = styled.div`
    display: flex;
    height: 100%;
    width: 100%;
    padding: 10px;
    font-size: 1.2em;
    font-weight: bold;
    align-items: center;
    justify-content: center;
    img{
        width: 70%;
        height: auto;
        @media(max-width: 768px) {
            display: none;
        }
    }
`

const ActionWrapper = styled.div`
    display: flex;
    padding: 15px;
    border-left: 2px solid #e5e5e5;
    height: 100%;
    width: 100%;
    flex-direction: row;
    justify-content: space-evenly;
    align-items: center;
    button > img {
        filter: invert(98%) sepia(44%) saturate(8%) hue-rotate(209deg) brightness(100%) contrast(103%);
        @media(max-width: 768px){
            width: 20px;
        }
    }
    @media(max-width: 992px) {
        flex-direction: column;
    }
`

const CommentFooterWrapper = styled.div`
    display: flex;
    width: 100%;
    height: 100%;
    flex-direction: row;
    align-items: flex-end;
    justify-content: space-between;
`

function CommentListElement({ commentId, content, author, creationDate, avatarUrl, editable, openDeleteModal, openEditModal }) {
    return (
        <CommentWrapper>
            <Container>
                <Row>
                    <Col sm={2}>
                        <ImageWrapper>
                            <Image src={avatarUrl} rounded />
                        </ImageWrapper>
                    </Col>
                    <Col xs={12} sm={10}>
                        <Row>
                            {editable ?
                                <Container>
                                    <Row>
                                        <Col xs={9} md={10}>
                                            <ContentWrapper>
                                                {content}
                                            </ContentWrapper>
                                        </Col>
                                        <Col xs={3} md={2}>
                                            <ActionWrapper>
                                                <Button variant={'danger'} onClick={() => openDeleteModal(commentId)}>
                                                    <Image src={process.env.PUBLIC_URL + "/icons/trash.svg"} width={'20px;'} />
                                                </Button>
                                                <Button variant={'primary'} onClick={() => openEditModal({ commentId, content, author, creationDate, avatarUrl, editable })}>
                                                    <Image src={process.env.PUBLIC_URL + "/icons/edit.svg"} width={'20px;'} />
                                                </Button>
                                            </ActionWrapper>
                                        </Col>
                                    </Row>
                                </Container>
                                :
                                <ContentWrapper>
                                    {content}
                                </ContentWrapper>
                            }
                        </Row>
                        <hr />
                        <Row>
                            <CommentFooterWrapper>
                                <HrefWrapper hrefAction={() => history.push(`/profile/${author}`)}>
                                    <p>Autor: {author}</p>
                                </HrefWrapper>
                                <p>Data utworzenia: {creationDate}</p>
                            </CommentFooterWrapper>
                        </Row>
                    </Col>
                </Row>
            </Container>
        </CommentWrapper>
    )
}

export default CommentListElement;