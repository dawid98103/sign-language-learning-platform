import React, { useState, useContext } from 'react'
import { Form, Button, Col, Row } from 'react-bootstrap'
import styled from 'styled-components';
import ValidationErrors from '../constants/ValidationErrors';
import { useHistory } from 'react-router-dom';
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

    const deletePasswordValidationError = (val) => {
        const index = passwordValidationErrors.indexOf(val);
        if (index > -1) {
            passwordValidationErrors.splice(index, 1);
        }
    }

    const validateEmail = (email) => {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLowerCase());
    }

    const validationErrorsExists = () => {
        if(usernameValidationErrors.length > 0 || nameValidationErrors.length > 0 || surnameValidationErrors.length > 0 || passwordValidationErrors.length > 0 || emailValidationErrors.length > 0){
            return true;
        }else {
            return false;
        }
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        event.stopPropagation();
        if(validationErrorsExists()){
            toast.warning("Wystąpiły błędy walidacji!. Popraw je i spróbuj ponownie", {
                position: "bottom-right"
            })
        }else {
            registerUser()
        }
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
        <CenteredMarginContainer withbackground={false}>
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
                        <Form onSubmit={handleSubmit}>
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
                                    />
                                </Form.Group>
                            </Row>
                            <Row className="mb-3">
                                <Form.Group as={Col} md="12" controlId="validationCustom02">
                                    <Form.Label>Nazwisko:</Form.Label>
                                    <Form.Control
                                        value={surname}
                                        type="Nazwisko"
                                        placeholder=""
                                        onChange={handleSurnameInput}
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

export default RegisterPage;