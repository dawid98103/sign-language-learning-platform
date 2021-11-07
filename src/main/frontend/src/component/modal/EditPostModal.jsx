import React, { useState, useEffect } from 'react'
import { Modal, Button, Form } from 'react-bootstrap'

function EditPostModal({ modalShow, closeModal, content, editPost }) {

    const [newContent, setNewContent] = useState("")

    useEffect(() => {
        setNewContent(content)
    }, [content])

    const handleContentChange = (event) => {
        setNewContent(event.target.value)
    }

    return (
        <Modal centered size="lg" aria-labelledby="contained-modal-title-vcenter" show={modalShow} onHide={closeModal}>
            <Modal.Header closeButton>
                <Modal.Title>Edycja postu</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group className="mb-3">
                        <Form.Label>Treść pytania:</Form.Label>
                        <Form.Control as="textarea" rows={3} value={newContent} onChange={handleContentChange} />
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="primary" onClick={() => editPost(newContent)}>
                    Edytuj
                </Button>
            </Modal.Footer>
        </Modal>
    )
}

export default EditPostModal;