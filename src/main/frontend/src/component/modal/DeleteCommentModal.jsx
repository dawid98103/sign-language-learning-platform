import React from 'react'
import { Modal, Button } from 'react-bootstrap'

function DeleteCommentModal({ modalShow, closeModal, deleteComment }) {

    return (
        <Modal centered size="lg" aria-labelledby="contained-modal-title-vcenter" show={modalShow} onHide={closeModal}>
            <Modal.Header closeButton>
                <Modal.Title>Usuń komentarz</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <p>Czy na pewno chcesz usunąć ten komentarz?</p>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="primary" onClick={closeModal}>
                    Cofnij
                </Button>
                <Button variant="danger" onClick={() => deleteComment()}>
                    Usuń
                </Button>
            </Modal.Footer>
        </Modal>
    )
}

export default DeleteCommentModal;