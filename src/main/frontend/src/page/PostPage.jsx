import React, { useState, useEffect } from 'react';
import MarginContainer from '../component/MarginContainer';
import GlobalContentWrapper from '../component/GlobalContentWrapper';
import { Container, Row, Col, ListGroup, Form, Button, Image } from 'react-bootstrap';
import AxiosClient from '../config/axios/AxiosClient';
import GlobalSpinner from '../component/GlobalSpinner';
import CommentListElement from '../component/CommentListElement';
import history from '../config/history';
import styled from 'styled-components';

const PostHeader = styled.div`
    display: flex;
    padding: 20px 0px 20px 0px;
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

function PostPage({ match }) {
    const [post, setPost] = useState(null)

    useEffect(() => {
        fetchPost()
    }, [])

    const fetchPost = async () => {
        console.log(match)
        const { data } = await AxiosClient.get(`/forum/post/${match.params.postId}`)
        setPost(data)
    }

    return (
        <MarginContainer>
            <GlobalContentWrapper>
                {
                    post == null ?
                        <GlobalSpinner />
                        :
                        <>
                            <Container>
                                <PostHeader>
                                    <Row>
                                        <Col xs={3} md={5}>
                                            <ImageWrapper>
                                                <FittedImage src={post.simplePostDTO.avatarUrl} rounded />
                                            </ImageWrapper>
                                        </Col>

                                        <Col xs={9} md={7}>
                                            <h4>{post.simplePostDTO.topic}</h4>
                                            {post.simplePostDTO.content}
                                        </Col>
                                    </Row>
                                </PostHeader>
                                <hr />
                                <Row>
                                    <Form>
                                        <Form.Group className="mb-3">
                                            <Form.Control as="textarea" rows={3} placeholder="Dodaj komentarz..." />
                                        </Form.Group>
                                        <Button>Dodaj komentarz +</Button>
                                    </Form>
                                </Row>
                            </Container>
                            <hr />
                            {
                                post.comments.map(comment => {
                                    return (
                                        <CommentListElement
                                            content={comment.content}
                                            author={comment.author}
                                            creationDate={comment.creationDate}
                                            avatar={comment.avatarUrl}
                                        />
                                    )
                                })
                            }
                        </>
                }

            </GlobalContentWrapper>
        </MarginContainer>
    )
}

export default PostPage;