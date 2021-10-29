import React, { useState } from 'react'
import { Modal, Button, Form } from 'react-bootstrap'
import AxiosClient from '../../config/axios/AxiosClient';

function AddPostModal({ modalShow, closeModal, refreshPosts }) {
    const [topic, setTopic] = useState("")
    const [content, setContent] = useState("")

    const handleAddNewPost = async () => {
        await AxiosClient.post(`/forum/post`, { topic: topic, content: content })
        refreshPosts()
        closeModal()
    }

    const handleTopicChange = (event) => {
        setTopic(event.target.value)
    }

    const handleContentChange = (event) => {
        setContent(event.target.value)
    }

    return (
        <Modal centered size="lg" aria-labelledby="contained-modal-title-vcenter" show={modalShow} onHide={closeModal}>
            <Modal.Header closeButton>
                <Modal.Title>Dodaj nowy post</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group className="mb-3">
                        <Form.Label>Temat</Form.Label>
                        <Form.Control type="email" placeholder="Temat" value={topic} onChange={handleTopicChange} />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Treść pytania</Form.Label>
                        <Form.Control as="textarea" rows={3} value={content} onChange={handleContentChange} />
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="primary" onClick={handleAddNewPost}>
                    Dodaj
                </Button>
            </Modal.Footer>
        </Modal>
    )
}

export default AddPostModal;