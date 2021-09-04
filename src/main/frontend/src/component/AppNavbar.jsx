import React, { useContext } from 'react';
import styled from 'styled-components';
import { GlobalContext } from '../context/GlobalContext';
import { Link, useHistory } from 'react-router-dom';
import { Navbar, Container, Nav, NavDropdown } from 'react-bootstrap';

const NavButton = styled.div`
    display:flex;
    align-items: center;
    flex-direction:row;
    cursor: pointer;
    filter: invert(50%);
    transition-duration: 0.6s;
    &:hover {
        filter: invert(0%); 
    }
`

const NavDropdownBlack = styled(NavDropdown)`
    color: black;
    &:focus {
        color: black;
    };
    &:hover {
        color: black;
    };
`

const MenuText = styled.span`
    font-size: 1.2rem;
    font-weight: 600;
`

const NavLink = styled(Nav.Link)`
    padding-left: 1.5rem !important; 
    padding-right: 1.5rem !important; 
`

const NavIcon = styled.img`
    width: 2.5rem;
    height: 2.5rem;
`

const NavLogo = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 0 1.5rem 0 1.5rem;
`

function AppNavbar() {
    const { state, dispatch } = useContext(GlobalContext);
    const history = useHistory();


    function logout() {
        dispatch({
            type: "LOGOUT"
        });
        alert("Pomyślnie wylogowano")
        history.push("/");
    }

    function pushToLogin() {
        history.push("/login");
    }

    return (
        <Navbar bg="light" expand="lg">
            <Container>
                <NavLogo>
                    <img
                        alt=""
                        src={process.env.PUBLIC_URL + "/logo/logo.svg"}
                        width="50"
                        height="50"
                        className="d-inline-block align-top"
                    />{' '}
                    <div>
                        <h4>Migawka</h4>
                    </div>
                </NavLogo>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <NavLink>
                            <NavButton href="" onClick={() => history.push("/lesson")}>
                                <div style={{ paddingRight: 10 }}>
                                    <NavIcon
                                        alt=""
                                        src={process.env.PUBLIC_URL + "/icons/learning.svg"}
                                        className="d-inline-block align-top"
                                    />
                                </div>
                                <MenuText>
                                    Ucz się
                                </MenuText>
                            </NavButton>
                        </NavLink>
                        <NavLink>
                            <NavButton href="">
                                <div style={{ paddingRight: 10 }}>
                                    <NavIcon
                                        alt=""
                                        src={process.env.PUBLIC_URL + "/icons/forum.svg"}
                                        className="d-inline-block align-top"
                                    />
                                </div>
                                <MenuText>
                                    Forum
                                </MenuText>
                            </NavButton>
                        </NavLink>
                        <NavLink>
                            <NavButton href="">
                                <div style={{ paddingRight: 10 }}>
                                    <NavIcon
                                        alt=""
                                        src={process.env.PUBLIC_URL + "/icons/shop.svg"}
                                        className="d-inline-block align-top"
                                    />
                                </div>
                                <MenuText>
                                    Sklep
                                </MenuText>
                            </NavButton>
                        </NavLink>
                        <NavLink>
                            <NavButton href="">
                                <div style={{ paddingRight: 10 }}>
                                    <NavIcon
                                        alt=""
                                        src={process.env.PUBLIC_URL + "/icons/more.svg"}
                                        className="d-inline-block align-top"
                                    />
                                </div>
                                <MenuText>
                                    Więcej
                                </MenuText>
                            </NavButton>
                        </NavLink>
                    </Nav>
                </Navbar.Collapse>
                <Navbar.Collapse className="justify-content-end">
                    <Link to="/login">
                        <NavDropdownBlack title="" id="navbarScrollingDropdown">
                            <NavDropdown.Item onClick={() => state.isAuthenticated ? logout() : pushToLogin()}>
                                {state.isAuthenticated ? "Wyloguj" : "Zaloguj"}
                            </NavDropdown.Item>
                        </NavDropdownBlack>
                    </Link>
                    <NavButton>
                        {state.user}
                        <NavIcon
                            alt=""
                            src={process.env.PUBLIC_URL + "/icons/user.svg"}
                            className="d-inline-block align-top"
                        >
                        </NavIcon>
                    </NavButton>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}

export default AppNavbar