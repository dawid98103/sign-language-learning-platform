import React, { useState, useContext } from 'react'
import { Image, Form, Button, Col, Row } from 'react-bootstrap'
import styled from 'styled-components';
import history from '../config/history';
import ValidationErrors from '../constants/ValidationErrors';
import { Link, useHistory } from 'react-router-dom';
import AxiosClient from '../config/axios/AxiosClient'
import { GlobalContext } from '../context/GlobalContext';
import { toast } from 'react-toastify';
import CenteredMarginContainer from '../component/CenteredMarginContainer';
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

function RegisterPage() {
    const [username, setUsername] = useState("")
    const [name, setName] = useState("")
    const [surname, setSurname] = useState("")
    const [password, setPassword] = useState("")
    const [passwordRepeat, setPasswordRepeat] = useState("")
    const [email, setEmail] = useState("")
    const [avatar, setAvatar] = useState(null);
    const { dispatch } = useContext(GlobalContext);

    const [usernameValidationErrors, setUsernameValidationErrors] = useState([])
    const [nameValidationErrors, setNameValidationErrors] = useState([])
    const [surnameValidationErrors, setSurnameValidationErrors] = useState([])
    const [passwordValidationErrors, setPasswordValidationErrors] = useState([])
    const [emailValidationErrors, setEmailValidationErrors] = useState([])

    const [processing, isProcessing] = useState(false);
    const history = useHistory();

    var specialCharacters = /^[!@#\$%\^\&*\)\(+=._-]+$/g

    async function registerUser() {
        isProcessing(true);
        AxiosClient.post("/auth/signup", { username, name, surname, password, passwordRepeat, email, avatarUrl: "" }).then(response => {
            isProcessing(false);
            dispatch({
                type: "REGISTER",
                payload: {}
            })
            history.push("/")
        }).catch(error => {
            console.log(error);
            isProcessing(false);
        })
    }

    const handleUsernameInput = (event) => {
        const usernameValue = event.target.value;
        setUsername(usernameValue);

        if (usernameValue.length < 6) {
            if (usernameValidationErrors.length == 0) {
                setUsernameValidationErrors([...usernameValidationErrors, ValidationErrors.SHORT_USERNAME])
            }
        } else {
            setUsernameValidationErrors([])
        }
    }

    const handleNameInput = (event) => {
        const nameValue = event.target.value;
        setName(nameValue);
    }

    const handleSurnameInput = (event) => {
        const surnameValue = event.target.value;
        setSurname(surnameValue);;
    }

    const deletePasswordValidationError = (val) => {
        const index = passwordValidationErrors.indexOf(val);
        if (index > -1) {
            passwordValidationErrors.splice(index, 1);
        }
    }

    const handlePasswordInput = (event) => {
        const passwordValue = event.target.value;
        setPassword(passwordValue);

        if (passwordValue.length < 6) {
            if (!passwordValidationErrors.includes(ValidationErrors.PASSWORD_TOO_SHORT)) {
                setPasswordValidationErrors([...passwordValidationErrors, ValidationErrors.PASSWORD_TOO_SHORT])
            }
        } else {
            deletePasswordValidationError(ValidationErrors.PASSWORD_TOO_SHORT)
        }

        if (!containsSpecialCharacter(passwordValue)) {
            if (!passwordValidationErrors.includes(ValidationErrors.PASSWORD_NOT_SAFE)) {
                setPasswordValidationErrors([...passwordValidationErrors, ValidationErrors.PASSWORD_NOT_SAFE])
            }
        } else {
            deletePasswordValidationError(ValidationErrors.PASSWORD_NOT_SAFE)
        }
    }

    const handleRepeatPasswordInput = (event) => {
        const repeatPasswordValue = event.target.value;
        setPasswordRepeat(repeatPasswordValue);
    }

    const handleEmailInput = (event) => {
        const emailValue = event.target.value;
        setEmail(emailValue);

        if (!validateEmail(emailValue)) {
            if (!emailValidationErrors.includes(ValidationErrors.BAD_EMAIL)) {
                setEmailValidationErrors([...emailValidationErrors, ValidationErrors.BAD_EMAIL])
            }
        } else {
            setEmailValidationErrors([])
        }
    }

    const handleFileInput = (event) => {
        const fileValue = event.target.value;
        console.log(fileValue);
    }

    const validateEmail = (email) => {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLowerCase());
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        event.stopPropagation();
        registerUser()
    }

    const containsSpecialCharacter = (value) => {
        for (var i = 0; i < value.length; i++) {
            if (new RegExp(specialCharacters).test(value.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    return (
        <CenteredMarginContainer withBackground={false}>
            <FormWrapper>
                {processing ?
                    <GlobalSpinner />
                    :
                    <>
                        <HeaderTextBlock>
                            <CloseButtonWrapper>
                                <Button onClick={() => history.push("/")}>X</Button>
                            </CloseButtonWrapper>
                            <span style={{ margin: "auto" }}>
                                Zarejestruj się
                            </span>
                        </HeaderTextBlock>
                        <Form noValidate onSubmit={handleSubmit}>
                            <Row className="mb-3">
                                <Form.Group as={Col} md="12" controlId="validationCustom01">
                                    <Form.Label>Nazwa użytkownika:</Form.Label>
                                    <Form.Control
                                        required
                                        value={username}
                                        name="username"
                                        type="text"
                                        placeholder=""
                                        onChange={handleUsernameInput}
                                        defaultValue=""
                                        isInvalid={usernameValidationErrors.length > 0}
                                    />
                                    {usernameValidationErrors.length > 0 &&
                                        <Form.Control.Feedback type="invalid">
                                            * Nazwa użytkownika powinna zawierać min. 6 znaków
                                        </Form.Control.Feedback>
                                    }
                                </Form.Group>
                            </Row>
                            <Row className="mb-3">
                                <Form.Group as={Col} md="12" controlId="validationCustom02">
                                    <Form.Label>Imie:</Form.Label>
                                    <Form.Control
                                        required
                                        value={name}
                                        type="text"
                                        placeholder=""
                                        onChange={handleNameInput}
                                        defaultValue=""
                                    />
                                </Form.Group>
                            </Row>
                            <Row className="mb-3">
                                <Form.Group as={Col} md="12" controlId="validationCustom02">
                                    <Form.Label>Nazwisko:</Form.Label>
                                    <Form.Control
                                        required
                                        value={surname}
                                        type="Nazwisko"
                                        placeholder=""
                                        onChange={handleSurnameInput}
                                        defaultValue=""
                                    />
                                </Form.Group>
                            </Row>
                            <Row className="mb-3">
                                <Form.Group as={Col} md="12" controlId="validationCustom02">
                                    <Form.Label>Hasło:</Form.Label>
                                    <Form.Control
                                        required
                                        value={password}
                                        type="password"
                                        placeholder=""
                                        onChange={handlePasswordInput}
                                        defaultValue=""
                                        isInvalid={passwordValidationErrors.length > 0}
                                    />
                                    {passwordValidationErrors.includes(ValidationErrors.PASSWORD_TOO_SHORT) &&
                                        <Form.Control.Feedback type="invalid">
                                            * Hasło powinno zawierać min. 6 znaków
                                        </Form.Control.Feedback>
                                    }
                                    {passwordValidationErrors.includes(ValidationErrors.PASSWORD_NOT_SAFE) &&
                                        <Form.Control.Feedback type="invalid">
                                            * Hasło powinno zawierać conajmniej jeden znak specjalny
                                        </Form.Control.Feedback>
                                    }
                                </Form.Group>
                            </Row>
                            <Row className="mb-3">
                                <Form.Group as={Col} md="12" controlId="validationCustom02">
                                    <Form.Label>Powtórz hasło:</Form.Label>
                                    <Form.Control
                                        required
                                        value={passwordRepeat}
                                        type="password"
                                        placeholder=""
                                        onChange={handleRepeatPasswordInput}
                                        defaultValue=""
                                    />
                                </Form.Group>
                            </Row>
                            <Row className="mb-3">
                                <Form.Group as={Col} md="12" controlId="inputGroupPrepend">
                                    <Form.Label>Email:</Form.Label>
                                    <Form.Control
                                        required
                                        value={email}
                                        type="email"
                                        placeholder=""
                                        onChange={handleEmailInput}
                                        defaultValue=""
                                        isInvalid={emailValidationErrors.includes(ValidationErrors.BAD_EMAIL)}
                                    />
                                    {emailValidationErrors.includes(ValidationErrors.BAD_EMAIL) &&
                                        <Form.Control.Feedback type="invalid">
                                            * Nieprawidłowy format adresu email
                                        </Form.Control.Feedback>
                                    }
                                </Form.Group>
                            </Row>
                            <Row className="mb-3">
                                <Form.Group as={Col} md="12" controlId="inputGroupPrepend">
                                    <Form.Label>Avatar:</Form.Label>
                                    <Form.Control
                                        required
                                        type="file"
                                        placeholder=""
                                        onChange={handleFileInput}
                                        defaultValue=""
                                    />
                                    <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                                </Form.Group>
                            </Row>
                            <Button type="submit">Zarejestruj się</Button>
                        </Form>
                    </>
                }
            </FormWrapper>
        </CenteredMarginContainer>
    )
}

{/* <HeaderTextBlock>
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
                </Form> */}

export default RegisterPage;