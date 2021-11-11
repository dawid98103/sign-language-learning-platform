import { Button, Image } from 'react-bootstrap';
import styled from 'styled-components';

const ImageWithFilteredContent = styled(Image)`
    filter: invert(98%) sepia(44%) saturate(8%) hue-rotate(209deg) brightness(100%) contrast(103%);
`

const AdminTableRow = ({ userId, username, roleId, creationDate, openDeleteUserModal, openProfilePage }) => {
    return (
        <tr>
            <td>{userId}</td>
            <td><span style={{ cursor: 'pointer' }} onClick={() => openProfilePage(username)}>{username}</span></td>
            <td>{roleId === 1 ? "UŻYTKOWNIK" : "ADMINISTRATOR"}</td>
            <td>{creationDate}</td>
            <td align='center'>
                <Button variant='danger' onClick={() => openDeleteUserModal({ userId, username })}><ImageWithFilteredContent src={process.env.PUBLIC_URL + "/icons/trash.svg"} width={20} /></Button>
            </td>
        </tr>
    )
}

export default AdminTableRow;