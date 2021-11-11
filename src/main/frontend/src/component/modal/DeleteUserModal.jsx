import React from 'react'
import { Modal, Button } from 'react-bootstrap'

function DeleteUserModal({ selectedUser: { username, userId }, modalShow, closeModal, deleteUser }) {

    return (
        <Modal centered size="lg" aria-labelledby="contained-modal-title-vcenter" show={modalShow} onHide={closeModal}>
            <Modal.Header closeButton>
                <Modal.Title>Usuń użytkownika</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <p>Czy na pewno chcesz usunąć użytkownika {username}?</p>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="primary" onClick={closeModal}>
                    Cofnij
                </Button>
                <Button variant="danger" onClick={() => deleteUser(userId)}>
                    Usuń
                </Button>
            </Modal.Footer>
        </Modal>
    )
}

export default DeleteUserModal;