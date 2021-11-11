import React, { useContext } from 'react';
import styled from 'styled-components';
import { GlobalContext } from '../context/GlobalContext';
import { Link } from 'react-router-dom';
import { LESSON_PAGE, FORUM_PAGE, RESULT_PAGE, ACHIEVEMENT_PAGE, ADMIN_PAGE } from '../constants/Pages';
import history from '../config/history'
import { Navbar, Container, Nav, NavDropdown } from 'react-bootstrap';

const NavButton = styled.div`
    display:flex;
    align-items: center;
    flex-direction:row;
    cursor: ${props => props.disabled ? "default" : "pointer"};
    filter: ${props => props.active ? "invert(0%)" : "invert(50%)"};
    transition-duration: 0.6s;
    &:hover {
        ${props => props.disabled ? "" : "invert(0%)"};
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

    function logout() {
        dispatch({
            type: "LOGOUT"
        });
        history.push("/");
    }

    const handleChangeToLessonPage = () => {
        dispatch({
            type: "SET_PAGE",
            payload: { page: LESSON_PAGE }
        })
        history.push("/lesson")
    }

    const handleChangeToForumPage = () => {
        dispatch({
            type: "SET_PAGE",
            payload: { page: FORUM_PAGE }
        })
        history.push("/forum")
    }

    const handleChangeToResultPage = () => {
        dispatch({
            type: "SET_PAGE",
            payload: { page: RESULT_PAGE }
        })
        history.push("/result")
    }

    const handleChangeToAchievementPage = () => {
        dispatch({
            type: "SET_PAGE",
            payload: { page: ACHIEVEMENT_PAGE }
        })
        history.push("/achievement")
    }

    const handleChangeToAdminPage = () => {
        dispatch({
            type: "SET_PAGE",
            payload: { page: ADMIN_PAGE }
        })
        history.push("/admin/management")
    }

    const handleChangeToUserProfile = () => {
        history.push(`/profile/${state.user}`)
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
                            <NavButton href="" onClick={handleChangeToLessonPage} active={state.currentPage === LESSON_PAGE}>
                                <div style={{ paddingRight: 10 }}>
                                    <NavIcon
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
                            <NavButton href="" onClick={!state.isAuthenticated ? null : handleChangeToForumPage} disabled={!state.isAuthenticated} active={state.currentPage === FORUM_PAGE}>
                                <div style={{ paddingRight: 10 }}>
                                    <NavIcon
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
                            <NavButton href="" onClick={!state.isAuthenticated ? null : handleChangeToAchievementPage} disabled={!state.isAuthenticated} active={state.currentPage === ACHIEVEMENT_PAGE}>
                                <div style={{ paddingRight: 10 }}>
                                    <NavIcon
                                        alt=""
                                        src={process.env.PUBLIC_URL + "/icons/goal.svg"}
                                        className="d-inline-block align-top"
                                    />
                                </div>
                                <MenuText>
                                    Osiągnięcia
                                </MenuText>
                            </NavButton>
                        </NavLink>
                        <NavLink>
                            <NavButton href="" onClick={!state.isAuthenticated ? null : handleChangeToResultPage} disabled={!state.isAuthenticated} active={state.currentPage === RESULT_PAGE}>
                                <div style={{ paddingRight: 10 }}>
                                    <NavIcon
                                        src={process.env.PUBLIC_URL + "/icons/results.svg"}
                                        className="d-inline-block align-top"
                                    />
                                </div>
                                <MenuText>
                                    Wyniki
                                </MenuText>
                            </NavButton>
                        </NavLink>
                        {state.isAdmin &&
                            <NavLink>
                                <NavButton href="" onClick={handleChangeToAdminPage} active={state.currentPage === ADMIN_PAGE}>
                                    <div style={{ paddingRight: 10 }}>
                                        <NavIcon
                                            src={process.env.PUBLIC_URL + "/icons/admin.svg"}
                                            className="d-inline-block align-top"
                                        />
                                    </div>
                                    <MenuText>
                                        Zarządzanie
                                    </MenuText>
                                </NavButton>
                            </NavLink>}
                    </Nav>
                </Navbar.Collapse>
                <Navbar.Collapse className="justify-content-end">
                    <Link to="/login">
                        <NavDropdownBlack title="" id="navbarScrollingDropdown">
                            {state.isAuthenticated ?
                                <>
                                    <NavDropdown.Item onClick={() => handleChangeToUserProfile()}>
                                        Konto
                                    </NavDropdown.Item>
                                    <NavDropdown.Item onClick={() => logout()}>
                                        Wyloguj
                                    </NavDropdown.Item>
                                </>
                                :
                                <NavDropdown.Item onClick={() => pushToLogin()}>
                                    Zaloguj
                                </NavDropdown.Item>
                            }
                        </NavDropdownBlack>
                    </Link>
                    <NavButton active>
                        {state.user}
                        <NavIcon
                            src={state.avatarUrl}
                            width={20}
                            height={20}
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