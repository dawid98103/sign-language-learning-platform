import React, { useState, useEffect, useRef } from 'react';
import MarginContainer from '../component/MarginContainer';
import AddPostModal from '../component/modal/AddPostModal';
import AxiosClient from '../config/axios/AxiosClient';
import { Container, Row, Col, ListGroup, Form, Button } from 'react-bootstrap';
import GlobalContentWrapper from '../component/GlobalContentWrapper';
import PostListElement from '../component/PostListElement';
import GlobalSpinner from '../component/GlobalSpinner';
import styled from 'styled-components';
import history from '../config/history';

const FullWitdhButton = styled(Button)`
    width: 100%;
    margin: 10px 0px 10px 0px;
`

function ForumPage() {
    const [showModal, setShowModal] = useState(false);
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

    const refreshPosts = () => {
        setRefresh(!refresh)
    }

    const handleCloseModal = () => {
        setShowModal(false)
    }

    const handleOpenModal = () => {
        setShowModal(true);
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
                        <Col xs={12} md={7}>
                            {loading ?
                                <GlobalSpinner />
                                :
                                <ListGroup variant="flush">
                                    {posts.map(post => {
                                        return (
                                            <ListGroup.Item>
                                                <PostListElement
                                                    title={post.topic}
                                                    content={prepareContent(post.content)}
                                                    author={post.author}
                                                    creationDate={post.creationDate}
                                                    avatar={post.avatarUrl}
                                                    openPost={() => history.push(`/forum/${post.postId}`)}
                                                />
                                            </ListGroup.Item>
                                        )
                                    })}
                                </ListGroup>
                            }
                        </Col>
                        <Col md={5}>
                            <Container>
                                <Row>
                                    <Form.Control onChange={handleSearchPost} type="text" placeholder="Wyszukaj post..." />
                                </Row>
                                <Row>
                                    <Col>
                                        <FullWitdhButton onClick={handleOpenModal}>Dodaj post +</FullWitdhButton>
                                    </Col>
                                    <Col></Col>
                                </Row>
                            </Container>
                        </Col>
                    </Row>
                </Container>
            </GlobalContentWrapper>
            <AddPostModal modalShow={showModal} closeModal={handleCloseModal} refreshPosts={refreshPosts} />
        </MarginContainer>
    )
}

export default ForumPage;