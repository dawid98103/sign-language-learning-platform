import React, { useState, useRef ,useContext } from 'react'
import { Image, Form, Button, Row, Col } from 'react-bootstrap'
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import history from '../config/history';
import AxiosClient from '../config/axios/AxiosClient'
import CenteredMarginContainer from '../component/CenteredMarginContainer';
import { GlobalContext } from '../context/GlobalContext';
import { withRouter } from "react-router";
import { toast } from 'react-toastify';
import GlobalSpinner from '../component/GlobalSpinner';

const FormWrapper = styled.div`
    display: flex;
    justify-content: center;
    flex-direction: column;
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

function LoginPage() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [processing, setIsProcessing] = useState(false);
    const { dispatch } = useContext(GlobalContext);

    function loginUser() {
        AxiosClient.post("/auth/signin", { username, password }).then(response => {
            const { username, avatarUrl, token, roles } = response.data;
            dispatch({
                type: "LOGIN",
                payload: { user: username, userAvatar: avatarUrl, token: token, roles: roles }
            })
            setIsProcessing(false);
            history.push("/lesson")
        }).catch(error => {
            toast.warn("Wprowadzono złe dane logowania", {
                position: "bottom-right"
            })
            setIsProcessing(false);
        })
    }

    const handleUsernameInput = (event) => {
        const usernameValue = event.target.value;
        setUsername(usernameValue);
    }

    const handlePasswordInput = (event) => {
        const passwordValue = event.target.value;
        setPassword(passwordValue);
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        event.stopPropagation();
        setIsProcessing(true)
        loginUser();
    }

    return (
        <CenteredMarginContainer withbackground={false}>
            <FormWrapper>
                {processing ?
                    <GlobalSpinner />
                    :
                    <>
                        <HeaderTextBlock>
                            <CloseButtonWrapper>
                            <CloseButtonWrapper>
                                <Button onClick={() => history.push("/")}>X</Button>
                            </CloseButtonWrapper>
                            </CloseButtonWrapper>
                            <span style={{ margin: "auto" }}>
                                Zaloguj się
                            </span>
                        </HeaderTextBlock>
                        <Form onSubmit={handleSubmit}>
                            <Row className="mb-3">
                                <Form.Group as={Col} md="12">
                                    <Form.Label>Nazwa użytkownika</Form.Label>
                                    <Form.Control
                                        required
                                        type="text"
                                        placeholder="Nazwa użytkownika"
                                        onChange={handleUsernameInput}
                                        value={username}
                                    />
                                </Form.Group>
                            </Row>
                            <Row className="mb-3">
                                <Form.Group as={Col} md="12">
                                    <Form.Label>Hasło</Form.Label>
                                    <Form.Control
                                        required
                                        type="password"
                                        placeholder="Hasło"
                                        onChange={handlePasswordInput}
                                        value={password}
                                    />
                                </Form.Group>
                            </Row>
                            <Button type="submit">Zaloguj się</Button>
                        </Form>
                    </>
                }
            </FormWrapper>
        </CenteredMarginContainer>
    )
}

export default withRouter(LoginPage);