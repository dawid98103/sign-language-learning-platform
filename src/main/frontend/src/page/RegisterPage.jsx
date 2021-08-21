import React from 'react'
import { Image, Form, Button } from 'react-bootstrap'
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import CenteredMarginContainer from '../component/CenteredMarginContainer';

const FormWrapper = styled.div`
    border-radius: 25px;
    border: 2px solid lightgray;
    width: 550px;
    padding: 50px;
`

const HeaderTextBlock = styled.div`
    display: flex;
    color: #3c3c3c;
    margin: 20px 0 50px 0;
    font-size: 25px;
    font-weight: 700;
`

const CloseButtonWrapper = styled.div`
    position: fixed;
    cursor: pointer;
`

function RegisterPage() {
    return (
        <CenteredMarginContainer withBackground={false}>
            <FormWrapper>
                <HeaderTextBlock>
                    <CloseButtonWrapper>
                        <Link to="/">
                            <Image src={process.env.PUBLIC_URL + "/icons/close.svg"} width={40} hieght={40} />
                        </Link>
                    </CloseButtonWrapper>
                    <span style={{ margin: "auto" }}>
                        Załóż konto
                    </span>
                </HeaderTextBlock>
                <Form>
                    <Form.Group className="mb-3">
                        <Form.Label>Nazwa użytkownika</Form.Label>
                        <Form.Control type="text" size="lg" placeholder="Nazwa użytkownika" />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Imię</Form.Label>
                        <Form.Control type="text" size="lg" placeholder="Imie" />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Nazwisko</Form.Label>
                        <Form.Control type="text" size="lg" placeholder="Nazwisko" />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Hasło</Form.Label>
                        <Form.Control type="password" size="lg" placeholder="Hasło" />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" size="lg" placeholder="Email" />
                    </Form.Group>
                    <div style={{ textAlign: "center" }}>
                        <Button variant="primary" className="mt-3" size="lg">Zarejestruj się</Button>
                    </div>
                </Form>
            </FormWrapper>
        </CenteredMarginContainer>
    )
}

export default RegisterPage;