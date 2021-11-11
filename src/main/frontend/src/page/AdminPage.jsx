import React, { useState, useEffect, useContext } from 'react'
import MarginContainer from '../component/MarginContainer';
import GlobalContentWrapper from '../component/GlobalContentWrapper';
import styled from 'styled-components';
import AdminListElement from '../component/AdminListElement';
import AxiosClient from '../config/axios/AxiosClient';
import GlobalSpinner from '../component/GlobalSpinner';
import Table from 'react-bootstrap/Table'
import DeleteUserModal from '../component/modal/DeleteUserModal';
import history from '../config/history';

const AdminPageHeader = styled.div`
    display: flex;
    width: 100%;
    padding: 15px;
    justify-content: center;
`

const UserTableWrapper = styled.div`
    display: flex;
    justify-content: space-between;
    width: 100%;
    border: 0.5px solid #e5e5e5;
`

const AdminPage = () => {
    const [users, setUsers] = useState([])
    const [selectedUser, setSelectedUser] = useState({ username: "", userId: 0 })
    const [showDeleteUserModal, setShowDeleteUserModal] = useState(false)
    const [refresh, setRefresh] = useState(false)

    useEffect(() => {
        const fetchUsers = async () => {
            const { data } = await AxiosClient.get(`/users`)
            setUsers(data)
        }
        fetchUsers()
    }, [refresh])

    const deleteUser = async (userId) => {
        await AxiosClient.delete(`/users/user/${userId}`)
        setShowDeleteUserModal(false)
        setRefresh(!refresh)
    }

    const openProfilePage = async (username) => {
        history.push(`/profile/${username}`)
    }

    const handleCloseDeleteUserModal = () => {
        setShowDeleteUserModal(false)
    }

    const handleOpenDeleteUserModal = (user) => {
        setSelectedUser(user)
        setShowDeleteUserModal(true)
    }

    return (
        <MarginContainer>
            <GlobalContentWrapper>
                {users.length === 0 ?
                    <GlobalSpinner />
                    :
                    <>
                        <AdminPageHeader>
                            <h1>Lista użytkowników</h1>
                        </AdminPageHeader>
                        <UserTableWrapper>
                            <Table striped bordered hover>
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Nazwa użytkownika</th>
                                        <th>Rola</th>
                                        <th>Założenie konta</th>
                                        <th>Akcja</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {users.map(user => {
                                        return <AdminListElement {...user} openDeleteUserModal={handleOpenDeleteUserModal} openProfilePage={openProfilePage} />
                                    })}
                                </tbody>
                            </Table>
                        </UserTableWrapper>
                    </>
                }
            </GlobalContentWrapper>
            <DeleteUserModal selectedUser={selectedUser} modalShow={showDeleteUserModal} closeModal={handleCloseDeleteUserModal} deleteUser={deleteUser} />
        </MarginContainer >
    )
}

export default AdminPage;