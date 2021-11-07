import React, { useState, useEffect, useRef } from 'react';
import MarginContainer from '../component/MarginContainer';
import AddPostModal from '../component/modal/AddPostModal';
import DeletePostModal from '../component/modal/DeletePostModal';
import EditPostModal from '../component/modal/EditPostModal';
import AxiosClient from '../config/axios/AxiosClient';
import { Container, Row, Col, ListGroup, Form, Button } from 'react-bootstrap';
import GlobalContentWrapper from '../component/GlobalContentWrapper';
import PostListElement from '../component/PostListElement';
import GlobalSpinner from '../component/GlobalSpinner';
import styled from 'styled-components';
import history from '../config/history';
import { toast } from 'react-toastify';

const FullWitdhButton = styled(Button)`
    width: 100%;
    margin: 10px 0px 10px 0px;
`

const ColWithoutPadding = styled(Col)`
    padding: 0px;
`

function ForumPage() {
    const [showModal, setShowModal] = useState(false);
    const [showDeleteModal, setShowDeleteModal] = useState(false)
    const [showEditModal, setShowEditModal] = useState(false)
    const [selectedPostId, setSelectedPostId] = useState(null)
    const [selectedPost, setSelectedPost] = useState({})
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [refresh, setRefresh] = useState(false);
    const oldValues = useRef([])

    useEffect(() => {
        fetchPosts()
    }, [refresh])

    const fetchPosts = async () => {
        setLoading(true)
        const { data } = await AxiosClient.get(`/forum/simple/post`)
        oldValues.current = data;
        setLoading(false)
        setPosts(data)
    }

    const deletePost = async () => {
        const responseMessage = await AxiosClient.delete(`/forum/post/${selectedPostId}`)
        setShowDeleteModal(false);
        setRefresh(!refresh)

        toast.success(responseMessage.data.message, {
            position: "bottom-right"
        })
    }

    const editPost = async (newContent) => {
        const responseMessage = await AxiosClient.put(`/forum/post`, { ...selectedPost, content: newContent })
        setShowEditModal(false);
        setRefresh(!refresh)

        toast.success(responseMessage.data.message, {
            position: "bottom-right"
        })
    }

    const refreshPosts = () => {
        setRefresh(!refresh);
    }

    const handleCloseModal = () => {
        setShowModal(false);
    }

    const handleCloseDeleteModal = () => {
        setShowDeleteModal(false);
    }

    const handleCloseEditModal = () => {
        setShowEditModal(false);
    }

    const handleOpenModal = () => {
        setShowModal(true);
    }

    const handleOpenDeleteModal = (postId) => {
        setSelectedPostId(postId);
        setShowDeleteModal(true);
    }

    const handleOpenEditModal = (postBody) => {
        setSelectedPost(postBody)
        setShowEditModal(true);
    }

    const prepareContent = (content) => {
        return content.substring(0, 128).concat("...")
    }

    const handleSearchPost = (event) => {
        const searchValue = event.target.value.toLowerCase();
        if (searchValue.length > 0) {
            let filteredValues = oldValues.current.filter(post => {
                return post.topic.toLowerCase().includes(searchValue) || post.content.toLowerCase().includes(searchValue)
            })
            setPosts(filteredValues)
        } else {
            setPosts(oldValues.current)
        }
    }

    return (
        <MarginContainer>
            <GlobalContentWrapper>
                <Container>
                    <Row>
                        <Col xs={12} md={9}>
                            {loading ?
                                <GlobalSpinner />
                                :
                                <ListGroup variant="flush">
                                    {posts.map(post => {
                                        return (
                                            <ListGroup.Item key={post.postId}>
                                                <PostListElement
                                                    postId={post.postId}
                                                    topic={post.topic}
                                                    content={prepareContent(post.content)}
                                                    author={post.author}
                                                    creationDate={post.creationDate}
                                                    avatarUrl={post.avatarUrl}
                                                    editable={post.editable}
                                                    openDeleteModal={handleOpenDeleteModal}
                                                    openEditModal={handleOpenEditModal}
                                                    openPost={() => history.push(`/forum/${post.postId}`)}
                                                />
                                            </ListGroup.Item>
                                        )
                                    })}
                                </ListGroup>
                            }
                        </Col>
                        <Col md={3}>
                            <Container style={{ paddingTop: 10 }}>
                                <Row>
                                    <Form.Control onChange={handleSearchPost} type="text" placeholder="Wyszukaj post..." />
                                </Row>
                                <Row>
                                    <ColWithoutPadding md={12}>
                                        <FullWitdhButton onClick={handleOpenModal}>Dodaj post +</FullWitdhButton>
                                    </ColWithoutPadding>
                                </Row>
                            </Container>
                        </Col>
                    </Row>
                </Container>
            </GlobalContentWrapper>
            <AddPostModal modalShow={showModal} closeModal={handleCloseModal} refreshPosts={refreshPosts} />
            <DeletePostModal modalShow={showDeleteModal} closeModal={handleCloseDeleteModal} deletePost={deletePost} />
            <EditPostModal modalShow={showEditModal} closeModal={handleCloseEditModal} content={selectedPost?.content} editPost={editPost} />
        </MarginContainer >
    )
}

export default ForumPage;