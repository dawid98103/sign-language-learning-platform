import React from 'react'
import { Modal, Button } from 'react-bootstrap'

const NotificationModal = ({ headerText, contentText, modalShow, closeModal }) => {
    return (
        <Modal centered size="lg" aria-labelledby="contained-modal-title-vcenter" show={modalShow} onHide={closeModal}>
            <Modal.Header closeButton>
                <Modal.Title>{headerText}</Modal.Title>
            </Modal.Header>
            <Modal.Body><p>{contentText}</p></Modal.Body>
            <Modal.Footer>
                <Button variant="primary" onClick={closeModal}>
                    Rozumiem
                </Button>
            </Modal.Footer>
        </Modal>
    )
}

export default NotificationModal;