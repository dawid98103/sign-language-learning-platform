import React, { useState } from 'react'
import { Image, Form, Button, Spinner } from 'react-bootstrap'
import styled from 'styled-components';
import { Link, useHistory } from 'react-router-dom';
import AxiosClient from '../config/axios/AxiosClient'
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
    const [username, setUsername] = useState("")
    const [name, setName] = useState("")
    const [surname, setSurname] = useState("")
    const [password, setPassword] = useState("")
    const [email, setEmail] = useState("")
    const [processing, isProcessing] = useState(false);
    const history = useHistory();

    async function registerUser({ username, name, surname, password, email }) {
        isProcessing(true);
        await AxiosClient.post("/auth/signup", { username, name, surname, password, email, avatarUrl: "" })
        isProcessing(false);
        alert("Pomyślnie zarejestrowano użytkownika")
        history.push("/lesson")
    }

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
                        <Form.Control type="text" size="lg" placeholder="Nazwa użytkownika" onChange={val => setUsername(val.target.value)} />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Imię</Form.Label>
                        <Form.Control type="text" size="lg" placeholder="Imie" onChange={val => setName(val.target.value)} />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Nazwisko</Form.Label>
                        <Form.Control type="text" size="lg" placeholder="Nazwisko" onChange={val => setSurname(val.target.value)} />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Hasło</Form.Label>
                        <Form.Control type="password" size="lg" placeholder="Hasło" onChange={val => setPassword(val.target.value)} />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" size="lg" placeholder="Email" onChange={val => setEmail(val.target.value)} />
                    </Form.Group>
                    <div style={{ textAlign: "center" }}>
                        <Button variant="primary" className="mt-3" size="lg" disabled={processing} onClick={() => registerUser({ username, name, surname, password, email })}>
                            {processing &&
                                <Spinner
                                    as="span"
                                    animation="grow"
                                    size="sm"
                                    role="status"
                                    aria-hidden="true"
                                />}
                            Zarejestruj się
                        </Button>
                    </div>
                </Form>
            </FormWrapper>
        </CenteredMarginContainer>
    )
}

export default RegisterPage;