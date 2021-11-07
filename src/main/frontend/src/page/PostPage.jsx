import React, { useState, useEffect } from 'react';
import MarginContainer from '../component/MarginContainer';
import GlobalContentWrapper from '../component/GlobalContentWrapper';
import { Container, Row, Col, Form, Button, Image } from 'react-bootstrap';
import AxiosClient from '../config/axios/AxiosClient';
import GlobalSpinner from '../component/GlobalSpinner';
import CommentListElement from '../component/CommentListElement';
import DeleteCommentModal from '../component/modal/DeleteCommentModal';
import EditCommentModal from '../component/modal/EditCommentModal';
import { toast } from 'react-toastify';
import styled from 'styled-components';

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

const PostFooterWrapper = styled.div`
display: flex;
width: 100%;
flex-direction: row;
justify-content: space-between;
`

function PostPage({ match }) {
    const [post, setPost] = useState(null);
    const [refresh, setRefresh] = useState(false);
    const [comment, setComment] = useState("");
    const [selectedCommentId, setSelectedCommentId] = useState(null);
    const [selectedComment, setSelectedComment] = useState(null)
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [showEditModal, setShowEditModal] = useState(false);

    useEffect(() => {
        fetchPost()
    }, [refresh])

    const fetchPost = async () => {
        const { data } = await AxiosClient.get(`/forum/post/${match.params.postId}`)
        setPost(data)
    }

    const addComment = async () => {
        if (comment.length > 0) {
            const responseMessage = await AxiosClient.patch(`/forum/post/${match.params.postId}/comment`, { content: comment, postId: match.params.postId })
            setRefresh(!refresh)

            toast.success(responseMessage.data.message, {
                position: "bottom-right"
            })
        }
    }

    const editComment = async (newContent) => {
        const responseMessage = await AxiosClient.put(`/forum/post/${match.params.postId}/comment`, {
            commentId: selectedComment.commentId,
            content: newContent,
            creationDate: selectedComment.creationDate,
            avatarUrl: selectedComment.avatarUrl,
            author: selectedComment.author,
            editable: selectedComment.editable
        })
        setShowEditModal(false)
        setRefresh(!refresh)

        toast.success(responseMessage.data.message, {
            position: "bottom-right"
        })
    }

    const deleteComment = async () => {
        const responseMessage = await AxiosClient.delete(`/forum/post/${match.params.postId}/comment/${selectedCommentId}`)
        setShowDeleteModal(false)
        setRefresh(!refresh)

        toast.success(responseMessage.data.message, {
            position: "bottom-right"
        })
    }

    const handleOpenDeleteModal = (commentId) => {
        setSelectedCommentId(commentId);
        setShowDeleteModal(true);
    }

    const handleOpenEditModal = (commentBody) => {
        setSelectedComment(commentBody);
        setShowEditModal(true)
    }

    const handleChangeComment = (event) => {
        const commentContent = event.target.value;
        setComment(commentContent)
    }

    const handleCloseDeleteModal = () => {
        setShowDeleteModal(false);
    }

    const handleCloseEditModal = () => {
        setShowEditModal(false);
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
                                    <Row style={{ 'width': '100%' }}>
                                        <Col xs={2}>
                                            <ImageWrapper>
                                                <FittedImage src={post.simplePostDTO.avatarUrl} rounded />
                                            </ImageWrapper>
                                        </Col>
                                        <Col xs={10}>
                                            <h3>{post.simplePostDTO.topic}</h3>
                                            <ContentWrapper>
                                                {post.simplePostDTO.content}
                                            </ContentWrapper>
                                            <hr />
                                            <Row>
                                                <PostFooterWrapper>
                                                    <p>Autor: {post.simplePostDTO.author}</p>
                                                    <p>Data utworzenia: {post.simplePostDTO.creationDate}</p>
                                                </PostFooterWrapper>
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
                                        <Button type="submit" onClick={addComment}>Dodaj komentarz +</Button>
                                    </Form>
                                </Row>
                            </Container>
                            <hr />
                            {
                                post.comments.map(comment => {
                                    return (
                                        <CommentListElement
                                            key={comment.commentId}
                                            {...comment}
                                            openDeleteModal={handleOpenDeleteModal}
                                            openEditModal={handleOpenEditModal}
                                        />
                                    )
                                })
                            }
                        </>
                }
                <DeleteCommentModal modalShow={showDeleteModal} closeModal={handleCloseDeleteModal} deleteComment={deleteComment} />
                <EditCommentModal modalShow={showEditModal} closeModal={handleCloseEditModal} content={selectedComment?.content} editComment={editComment} />
            </GlobalContentWrapper>
        </MarginContainer>
    )
}

export default PostPage;