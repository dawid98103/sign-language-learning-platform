import React, { useState, useContext } from 'react'
import { Image, Form, Button, Spinner } from 'react-bootstrap'
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import AxiosClient from '../config/axios/AxiosClient'
import { useHistory } from 'react-router-dom';
import CenteredMarginContainer from '../component/CenteredMarginContainer';
import { GlobalContext } from '../context/GlobalContext';

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

function LoginPage() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [processing, isProcessing] = useState(false);
    const { dispatch } = useContext(GlobalContext);
    const history = useHistory();

    function loginUser({ username, password }) {
        isProcessing(true)
        AxiosClient.post("/auth/signin", { username, password }).then(response => {
            alert("Pomyślnie zalogowano użytkownika")
            const { username, token, roles } = response.data;
            console.log(roles)
            dispatch({
                type: "LOGIN",
                payload: { user: username, token: token, roles: roles }
            })
            isProcessing(false);
            history.push("/learn")
        }).catch(error => {
            console.log(error);
            alert(error.response.data.error.message);
            isProcessing(false);
        })
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
                        Zaloguj się
                    </span>
                </HeaderTextBlock>
                <Form>
                    <Form.Group className="mb-3">
                        <Form.Label>Nazwa użytkownika</Form.Label>
                        <Form.Control type="text" size="lg" placeholder="Nazwa użytkownika" onChange={val => setUsername(val.target.value)} />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Hasło</Form.Label>
                        <Form.Control type="password" size="lg" placeholder="Hasło" onChange={val => setPassword(val.target.value)} />
                    </Form.Group>
                    <div style={{ textAlign: "center" }}>
                        <Button variant="primary" className="mt-3" size="lg" disabled={processing} onClick={() => loginUser({ username, password })}>
                            {processing &&
                                <Spinner
                                    as="span"
                                    animation="grow"
                                    size="sm"
                                    role="status"
                                    aria-hidden="true"
                                />}
                            Zaloguj się
                        </Button>
                    </div>
                </Form>
            </FormWrapper>
        </CenteredMarginContainer>
    )
}

export default LoginPage;