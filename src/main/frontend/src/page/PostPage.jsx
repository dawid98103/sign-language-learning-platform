import React, { useState, useEffect } from 'react';
import MarginContainer from '../component/MarginContainer';
import GlobalContentWrapper from '../component/GlobalContentWrapper';
import { Container, Row, Col, ListGroup, Form, Button, Image } from 'react-bootstrap';
import AxiosClient from '../config/axios/AxiosClient';
import GlobalSpinner from '../component/GlobalSpinner';
import CommentListElement from '../component/CommentListElement';
import history from '../config/history';
import styled from 'styled-components';
import { set } from 'react-hook-form';

const PostHeader = styled.div`
    display: flex;
    padding: 20px 0px 20px 0px;
`

const ContentWrapper = styled.div`
    padding: 5px 0px 5px 0px;
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
    const [refresh, setRefresh] = useState(false);
    const [comment, setComment] = useState("")

    useEffect(() => {
        fetchPost()
    }, [refresh])

    const fetchPost = async () => {
        const { data } = await AxiosClient.get(`/forum/post/${match.params.postId}`)
        setPost(data)
    }

    const addComment = async () => { 
        console.log(comment)   
        if(comment.length > 0){
            await AxiosClient.patch(`/forum/post/comment`, {content: comment, postId: match.params.postId})
            setRefresh(!refresh)
        }
    }

    const handleChangeComment = (event) => {
        const commentContent = event.target.value;
        setComment(commentContent)
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
                                        <Col xs={2}>
                                            <ImageWrapper>
                                                <FittedImage src={post.simplePostDTO.avatarUrl} rounded />
                                            </ImageWrapper>
                                        </Col>
                                        <Col xs={10}>
                                            <h4>{post.simplePostDTO.topic}</h4>
                                            <ContentWrapper>
                                                {post.simplePostDTO.content}
                                            </ContentWrapper>
                                            <hr />
                                            <Row>
                                                <Col>Autor: {post.simplePostDTO.author}</Col>
                                                <Col>Data utworzenia: {post.simplePostDTO.creationDate}</Col>
                                            </Row>
                                        </Col>
                                    </Row>
                                </PostHeader>
                                <hr />
                                <Row>
                                    <Form>
                                        <Form.Group className="mb-3">
                                            <Form.Control as="textarea" rows={3} value={comment} onChange={handleChangeComment} placeholder="Dodaj komentarz..." />
                                        </Form.Group>
                                        <Button onClick={addComment}>Dodaj komentarz +</Button>
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