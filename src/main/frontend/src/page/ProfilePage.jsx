import React, { useState, useEffect, useContext } from 'react'
import AxiosClient from '../config/axios/AxiosClient';
import { Container, Row, Col, Image, ListGroup, Button } from 'react-bootstrap';
import styled from 'styled-components';
import FriendListElement from '../component/FriendListElement';
import MarginContainer from '../component/MarginContainer';
import GlobalContentWrapper from '../component/GlobalContentWrapper';
import GlobalSpinner from '../component/GlobalSpinner';
import { toast } from 'react-toastify';
import { GlobalContext } from '../context/GlobalContext';
import history from '../config/history';

const ProfileUsernameWrapper = styled.div`
    display:flex;
    flex-direction: column;
    color: #3c3c3c;
    margin-bottom: 1.5rem;
`

const IconWithTextWrapper = styled.div`
    display:flex;
    flex-direction: row;
    padding: 5px 0px 5px 0px;
`

const PanelWrapper = styled.div`
    border: 0.5px solid #e5e5e5;
    border-radius: .25rem;
    padding: 0.8rem
`

const ProfileHeaderColumn = styled(Col)`
    display: flex;
    justify-content: space-between;
    align-items: center;
`

const FriendButton = styled(Button)`
    width: 10.5rem;
    height: 4.0rem;
`

const ListItemWithoutPadding = styled(ListGroup.Item)`
    padding: 10px 0px 10px 0px;
`

const ProfilePage = ({ match: { params } }) => {
    const [user, setUser] = useState(null)
    const [friends, setFriends] = useState([])
    const [refresh, setRefresh] = useState(false)
    const [isCurrentLoggedUserAccount, setIsCurrentLoggedUserAccount] = useState(true)
    const [currentlyLoggedUserFriendsList, setCurrentlyLoggedUserFriendsList] = useState([])
    const { state } = useContext(GlobalContext)

    useEffect(() => {
        const fetchUserInfo = async () => {
            const currentLoggedUserUsername = state.user;
            const { data: { userBasicInfoDTO, friends, currentUserAccount } } = await AxiosClient.get(`/users/user/info/${params.username}`)
            const { data: { friends: currentlyLoggedUserFriendsList } } = await AxiosClient.get(`/users/user/info/${currentLoggedUserUsername}`)
            setFriends(friends)
            setUser(userBasicInfoDTO)
            setIsCurrentLoggedUserAccount(currentUserAccount)
            setCurrentlyLoggedUserFriendsList(currentlyLoggedUserFriendsList)
        }
        fetchUserInfo();
    }, [refresh, params.username])

    const changePage = (username) => {
        history.push(`/profile/${username}`)
        setRefresh(!refresh)
    }

    const deleteFriend = async (username) => {
        const { data: { message } } = await AxiosClient.delete(`/users/friend/${username}`)
        toast.success(message, {
            position: "bottom-right"
        })
        setRefresh(!refresh)
    }

    const addFriend = async (username) => {
        const { data: { message } } = await AxiosClient.post(`/users/friend/${username}`)
        toast.success(message, {
            position: "bottom-right"
        })
        setRefresh(!refresh)
    }

    const renderAddFriendButton = () => {
        if (!isCurrentLoggedUserAccount) {
            const userIsAlreadyFriend = currentlyLoggedUserFriendsList.filter(friend => friend.username === user.username).length > 0
            if (userIsAlreadyFriend) {
                return (
                    <FriendButton variant='danger' onClick={() => { deleteFriend(user.username) }}>
                        - Usuń z obserwowanych
                    </FriendButton>
                )
            } else {
                return (
                    <FriendButton variant='primary' onClick={() => { addFriend(user.username) }}>
                        + Obserwuj
                    </FriendButton>
                )
            }
        } else {
            return null
        }
    }

    return (
        <MarginContainer>
            <GlobalContentWrapper>
                {user == null ?
                    <GlobalSpinner />
                    :
                    <Container>
                        <Row>
                            <Col xs={12} lg={3} >
                                <Image src={user.avatarUrl} />
                            </Col>
                            <Col lg={9}>
                                <Row>
                                    <ProfileHeaderColumn>
                                        <ProfileUsernameWrapper>
                                            <h2>{user.firstName} {user.surname}</h2>
                                            {user.username}
                                        </ProfileUsernameWrapper>
                                        {renderAddFriendButton()}
                                    </ProfileHeaderColumn>
                                </Row>
                                <Row>
                                    <Col>
                                        <IconWithTextWrapper>
                                            <Image src={process.env.PUBLIC_URL + "/icons/clock.svg"} width={20} />&nbsp;
                                            Na Migawce od {user.registerDate}
                                        </IconWithTextWrapper>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col>
                                        <IconWithTextWrapper>
                                            <Image src={process.env.PUBLIC_URL + "/icons/history.svg"} width={25} />&nbsp;
                                            Ostatnie logowanie {user.lastActivityDateTime}
                                        </IconWithTextWrapper>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col>
                                        <IconWithTextWrapper>
                                            <Image src={process.env.PUBLIC_URL + "/icons/user1.svg"} width={25} />&nbsp;
                                            Rola {user.roleId === 1 ? "UŻYTKOWNIK" : "ADMINISTRATOR"}
                                        </IconWithTextWrapper>
                                    </Col>
                                </Row>
                            </Col>
                        </Row>
                        <div>
                            <hr />
                        </div>
                        <Row>
                            <Col xs={8}>
                                <PanelWrapper>
                                    <h4>Statystyki</h4>
                                    <div>
                                        <ListGroup variant="flush">
                                            <ListItemWithoutPadding>
                                                Dni nauki z rzędu: {user.consecutiveDays}
                                            </ListItemWithoutPadding>
                                            <ListItemWithoutPadding>
                                                Suma punktów: {user.gainedPoints}
                                            </ListItemWithoutPadding>
                                        </ListGroup>
                                    </div>
                                </PanelWrapper>
                            </Col>
                            <Col xs={4}>
                                <PanelWrapper>
                                    <h4>Obserwowani</h4>
                                    <div>
                                        <ListGroup variant="flush">
                                            {
                                                friends.map(friend => {
                                                    return <FriendListElement key={friend.username} friend={friend} deleteFriend={deleteFriend} changePage={changePage} />
                                                })
                                            }
                                        </ListGroup>
                                    </div>
                                </PanelWrapper>
                            </Col>
                        </Row>
                    </Container>
                }
            </GlobalContentWrapper>
        </MarginContainer>
    )
}

export default ProfilePage;