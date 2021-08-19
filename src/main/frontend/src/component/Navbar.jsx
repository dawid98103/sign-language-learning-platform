import React from 'react'
import styled from 'styled-components'
import { Navbar, Container, Nav } from 'react-bootstrap'

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
    return (
        <Navbar bg="light" >
            <Container style={{ minWidth: 1550 }}>
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
                <Navbar.Collapse>
                    <Nav className="me-auto">
                        <NavLink>
                            <NavButton href="">
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
                    <NavIcon
                        alt=""
                        src={process.env.PUBLIC_URL + "/icons/user.svg"}
                        className="d-inline-block align-top"
                    >
                    </NavIcon>
                    <Navbar.Text>
                        username
                    </Navbar.Text>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}

export default AppNavbar