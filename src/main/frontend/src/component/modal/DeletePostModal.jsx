import React from 'react'
import { Modal, Button } from 'react-bootstrap'

function DeletePostModal({ modalShow, closeModal, deletePost }) {

    return (
        <Modal centered size="lg" aria-labelledby="contained-modal-title-vcenter" show={modalShow} onHide={closeModal}>
            <Modal.Header closeButton>
                <Modal.Title>Usuń post</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <p>Czy na pewno chcesz usunąć ten post?</p>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="primary" onClick={closeModal}>
                    Cofnij
                </Button>
                <Button variant="danger" onClick={deletePost}>
                    Usuń
                </Button>
            </Modal.Footer>
        </Modal>
    )
}

export default DeletePostModal;